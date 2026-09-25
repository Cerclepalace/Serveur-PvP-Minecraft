# P004 — Core Foundation

## Objective
Établir le noyau autoritaire du serveur : lifecycle, configuration, commandes de santé et contrats partagés.

## Components
- NexusCorePlugin
- ServerLifecycle
- ConfigService
- HealthService
- GameRegistry
- PlayerSessionRegistry
- ProtocolVersion

## Invariants
1. Une identité joueur est unique par UUID.
2. Aucun résultat de match n'est calculé côté client.
3. Les modules de jeu dépendent du Core, jamais l'inverse.
4. Toute configuration critique possède une valeur par défaut sûre.
5. Le mode maintenance peut empêcher l'entrée en match.

## Gate
GO lorsque le plugin se charge, répond à `/nexus health`, charge sa configuration et expose sa version de protocole.
