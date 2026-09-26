FROM gradle:9-jdk25 AS build
WORKDIR /workspace
COPY . .
RUN gradle build --no-daemon

FROM eclipse-temurin:25-jre
WORKDIR /server
RUN apt-get update && apt-get install -y --no-install-recommends curl jq ca-certificates && rm -rf /var/lib/apt/lists/*
ARG PAPER_VERSION=26.2
ENV PAPER_VERSION=${PAPER_VERSION}
ENV MEMORY_MIN=2G
ENV MEMORY_MAX=4G
RUN set -eux; UA="NexusCore/0.1 (https://github.com/Cerclepalace/Serveur-PvP-Minecraft)"; builds="$(curl -fsSL -H "User-Agent: ${UA}" "https://fill.papermc.io/v3/projects/paper/versions/${PAPER_VERSION}/builds")"; url="$(printf '%s' "${builds}" | jq -r 'first(.[] | select(.channel == "STABLE") | .downloads."server:default".url) // empty')"; test -n "${url}"; curl -fsSL -H "User-Agent: ${UA}" -o paper.jar "${url}"
COPY --from=build /workspace/build/libs/nexus-core-*.jar /server/plugins/NexusCore.jar
RUN printf 'eula=true\n' > eula.txt
EXPOSE 25565/tcp
CMD ["sh","-c","exec java -Xms$MEMORY_MIN -Xmx$MEMORY_MAX -jar paper.jar --nogui"]