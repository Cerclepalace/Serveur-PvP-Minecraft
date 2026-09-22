# P005–P007 — Metrics Benchmark

## Purpose

Define the measurable game-performance and server-operation metrics to support Combat Core, 1v1 and FFA before introducing telemetry and AI.

This is a design benchmark, not a copy of another server's implementation.

## Reference surfaces studied

### Minemen Club

Public player profiles expose practice-oriented competitive metrics such as:
- Global ELO
- ladder-specific ELO
- ranked/casual records
- wins/losses
- recent matches
- opponent and ELO delta per recent match
- world rank / leaderboard position

These are useful as the competitive-performance layer.

### Hypixel

The official public API exposes network/player-oriented surfaces including:
- network player counts
- per-game counts
- player data and game statistics
- recent games
- player online/status information
- guild information
- leaderboards

These are useful as the network-observability and player-history layer.

## Our metric model

We deliberately combine the two concepts while keeping the server authoritative.

### 1. Match outcome metrics

Per match:
- matchId
- mode
- durationMs
- participantCount
- winnerId
- loserIds
- resultReason
- score
- ratingDelta
- rematchEligible

### 2. Combat performance metrics

Per player and match:
- kills
- deaths
- assists
- damageDealt
- damageTaken
- hits
- misses
- hitRate
- criticalHits
- comboPeak
- longestCombo
- distanceTravelled
- timeInCombatMs
- finalHealth
- inventoryLosses

These are observable gameplay signals. They are not psychological or cognitive diagnoses.

### 3. Competitive ladder metrics

Per player and ladder:
- rating
- ratingDelta
- wins
- losses
- winRate
- matchesPlayed
- currentStreak
- bestStreak
- rankPosition
- ratingUpdatedAt

Rating is a deterministic competitive value. It must never be treated as a substitute for raw evidence.

### 4. Session metrics

Per player session:
- sessionId
- joinedAt
- leftAt
- durationMs
- matchesPlayed
- wins
- losses
- preferredModes
- activeMode
- disconnects

### 5. Server metrics

Per server instance:
- onlinePlayers
- activeMatches
- queuedPlayers
- matchesStarted
- matchesCompleted
- averageMatchDurationMs
- tickHealth
- memoryUsage
- cpuLoad
- arenaOccupancy

### 6. FFA metrics

Per player and FFA session:
- kills
- deaths
- killStreak
- bestKillStreak
- damageDealt
- damageTaken
- survivalTimeMs
- placement
- participationTimeMs

## Metric hierarchy

```text
RAW AUTHORITATIVE EVENTS
        |
        v
MATCH / SESSION STATE
        |
        +--> COMBAT METRICS
        |
        +--> COMPETITIVE METRICS
        |
        +--> FFA METRICS
        |
        +--> SERVER METRICS
        |
        v
TELEMETRY CONTRACT (future)
        |
        v
SKILL ENGINE (future)
        |
        v
AI DIRECTOR (future)
```

## Integrity rules

1. Match state is authoritative on the server.
2. Derived metrics must be reproducible from authoritative state/events.
3. A missing metric is `UNKNOWN`, not zero.
4. Metrics cannot alter a match result after the fact.
5. Rating changes occur only after a valid match conclusion.
6. Player-facing statistics must be explainable.
7. Anti-cheat signals remain separate from performance scoring.
8. High-frequency telemetry is introduced only after the contract is stable.
9. No metric is interpreted as evidence of intelligence, age, personality or psychological state.

## Product objective

The target is not to reproduce Minemen or Hypixel. The target is to create a unified PvP measurement layer:

**competitive identity + combat evidence + session context + server operations**.

That layer becomes the factual substrate for the later telemetry, skill and AI systems.
