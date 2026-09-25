# P008 — Security

## Trust model
Client Minecraft = non fiable.
Proxy/Game Server = autorité gameplay.
Database = autorité durable.
Admin = privilège contrôlé et audité.

## Controls
- whitelist d'administration ;
- permissions minimales ;
- secrets hors dépôt ;
- ports minimaux exposés ;
- sauvegardes chiffrées si disponibles ;
- journalisation des actions privilégiées ;
- validation serveur des résultats ;
- limites de fréquence sur commandes sensibles.

## Anti-abus
Prévoir les contrôles contre :
- spoofing d'événements ;
- duplication de récompenses ;
- boosting ;
- alt farming ;
- manipulation de matchmaking ;
- commandes administratives non autorisées.

## Gate
Aucun secret ne doit apparaître dans Git. Toute alerte critique bloque la promotion vers production.
