# P001 — Architecture

## Topologie cible

```text
Internet
  |
Velocity
  |
+-------------------------------+
| Hub | PvP-01 | PvP-02 | Event |
+-------------------------------+
              |
        Nexus Core API
              |
   +----------+----------+
   |                     |
PostgreSQL          Telemetry
   |                     |
   +----------+----------+
              |
       AI Recommendation
```

## Modules
- `nexus-core`: lifecycle, configuration, shared APIs.
- `nexus-player`: identity, profile and progression.
- `nexus-combat`: combat abstractions and match lifecycle.
- `nexus-duels`: 1v1 queues and arenas.
- `nexus-ffa`: free-for-all sessions.
- `nexus-ranked`: rating and seasons.
- `nexus-training`: controlled skill exercises.
- `nexus-world`: hub, zones and teleport routing.
- `nexus-events`: scheduled and dynamic events.
- `nexus-telemetry`: append-only gameplay events.
- `nexus-ai`: deterministic recommendations and operator tooling.
- `nexus-admin`: moderation and operational controls.

## Architecture constraints
1. Gameplay modules must not own player identity independently.
2. Telemetry is event-oriented and append-only.
3. AI recommendations cannot directly execute privileged server actions without an explicit control layer.
4. Competitive results must be reproducible from authoritative match events.
5. Configuration must be externalized where practical.
6. No production payment integration in the foundation phase.

## First implementation target
Build a compilable `nexus-core` foundation before implementing the full network. The first code milestone must prove plugin loading, configuration loading, player lifecycle events and a health command.
