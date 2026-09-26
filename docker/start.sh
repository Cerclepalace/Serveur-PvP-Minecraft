#!/bin/sh
set -eu
exec java -Xms${MEMORY_MIN:-2G} -Xmx${MEMORY_MAX:-4G} -jar paper.jar --nogui
