# P007 — Infrastructure

## Topologie cible
Internet → Firewall → Velocity → Lobby / Game Servers → Data Services

## Séparation
- Proxy : routage.
- Lobby : accueil.
- Game servers : exécution des matchs.
- PostgreSQL : données durables.
- Redis : état éphémère.
- Backups : stockage séparé.

## IONOS
Le serveur IONOS réel n'est pas encore déclaré comme environnement de production. Toute caractéristique matérielle ou réseau doit être marquée VERIFIED uniquement après observation dans le Cloud Panel ou sur la machine.

## Déploiement
- environnement local ;
- staging ;
- production.

Promotion uniquement après passage des tests P009/P010.
