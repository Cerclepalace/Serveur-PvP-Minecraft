# P009 — Observability

## Signals
- disponibilité proxy ;
- TPS / MSPT des game servers ;
- mémoire JVM ;
- joueurs connectés ;
- files d'attente ;
- matchs actifs ;
- erreurs par module ;
- latence DB/Redis ;
- taux de reconnexion.

## Audit trail
Chaque action administrative critique doit produire :
`timestamp, actor, action, target, reason, result, correlation_id`.

## Health gates
- Core health = PASS
- Database connectivity = PASS
- Redis connectivity = PASS
- Match lifecycle = PASS
- No unhandled critical exception

Les valeurs réelles seront produites par l'environnement d'exécution et ne doivent jamais être inventées dans la documentation.
