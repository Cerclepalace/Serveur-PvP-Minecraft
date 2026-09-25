import { existsSync, readFileSync } from "node:fs";

const required = [
  "README.md",
  "docs/00-VISION.md",
  "docs/01-ARCHITECTURE.md",
  "docs/02-GAME-DESIGN.md",
  "docs/03-PLAYER-PROFILE.md",
  "docs/04-CORE.md",
  "docs/05-GAME-MODES.md",
  "docs/06-DATA.md",
  "docs/07-INFRASTRUCTURE.md",
  "docs/08-SECURITY.md",
  "docs/09-OBSERVABILITY.md",
  "docs/10-QA-BULLRUN.md",
  "server/core/build.gradle.kts",
  "server/core/settings.gradle.kts",
  "server/core/src/main/java/fr/epicube/reconquete/core/NexusCorePlugin.java",
  "server/core/src/main/resources/plugin.yml",
  "server/core/src/main/resources/config.yml"
];

const failures = [];
for (const file of required) {
  if (!existsSync(file)) failures.push(`MISSING ${file}`);
}

const plugin = readFileSync("server/core/src/main/resources/plugin.yml", "utf8");
const java = readFileSync("server/core/src/main/java/fr/epicube/reconquete/core/NexusCorePlugin.java", "utf8");
const forbidden = [
  /ghp_[A-Za-z0-9_]{20,}/,
  /github_pat_[A-Za-z0-9_]{20,}/,
  /sk-[A-Za-z0-9]{20,}/,
  /-----BEGIN (RSA|OPENSSH|EC|PRIVATE) KEY-----/
];

for (const [name, text] of [["plugin.yml", plugin], ["NexusCorePlugin.java", java]]) {
  for (const rx of forbidden) if (rx.test(text)) failures.push(`SECRET_PATTERN ${name} ${rx}`);
}

for (const marker of ["name: NexusCore", "main: fr.epicube.reconquete.core.NexusCorePlugin", "commands:", "nexus:"]) {
  if (!plugin.includes(marker)) failures.push(`PLUGIN_CONTRACT ${marker}`);
}

if (!java.includes("NexusCore HEALTH=OK")) failures.push("HEALTH_COMMAND_MISSING");
if (!java.includes("saveDefaultConfig()")) failures.push("CONFIG_LOAD_MISSING");

console.log("BULLRUN-B0");
console.log(`required=${required.length}`);
console.log(`failures=${failures.length}`);
for (const f of failures) console.log(f);
if (failures.length) process.exit(1);
console.log("RESULT=PASS");
