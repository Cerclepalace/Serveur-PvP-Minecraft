# P003 — Player Profile

## Objective
Créer un profil de gameplay dynamique à partir d'événements autoritatifs produits par le serveur.

## Model

```text
PlayerIdentity
  |
  +-- Level
  +-- Rating
  +-- Skills
  |    +-- Aim
  |    +-- Movement
  |    +-- Reaction
  |    +-- Decision
  |    +-- Adaptation
  |    +-- Building
  |    +-- Exploration
  |    +-- ResourceManagement
  +-- MatchHistory
  +-- Achievements
  +-- Preferences
```

## Telemetry contract
Chaque événement important doit contenir au minimum :
- event id
- player UUID
- server instance
- timestamp
- event type
- game mode
- session/match id lorsque disponible
- version du protocole

## Examples
`MATCH_STARTED`, `HIT_REGISTERED`, `MATCH_ENDED`, `PLAYER_MOVED`, `TRAINING_COMPLETED`, `QUEUE_JOINED`.

Les événements sensibles à haute fréquence doivent être agrégés lorsque leur granularité brute n'apporte pas de valeur opérationnelle.

## Skill updates
Les compétences sont mises à jour à partir de signaux mesurables et pondérés. Une seule partie ne doit pas provoquer une variation excessive du profil.

## AI boundary
L'IA peut analyser les données de gameplay et produire une recommandation structurée. Elle ne doit pas modifier directement un résultat compétitif, attribuer arbitrairement une sanction ou exécuter une action privilégiée sans règle et contrôle serveur correspondants.
