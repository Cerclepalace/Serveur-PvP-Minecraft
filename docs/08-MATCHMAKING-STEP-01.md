# P008 — Étape 1 : contrat de queue

Objectif : définir le contrat minimal et déterministe avant d'implémenter le moteur de recherche de match.

## Artefacts

- `QueueKey` : partition exacte `(mode, ladder, region)`.
- `QueueTicket` : intention de jeu immuable `(playerId, key, rating, queuedAt)`.
- `MatchmakingQueue` : file FIFO en mémoire pour une partition donnée.

## Invariants

1. Un ticket ne peut entrer que dans sa `QueueKey` exacte.
2. Un joueur ne peut apparaître qu'une fois dans une queue.
3. Le rating est entier et non négatif.
4. Le timestamp d'entrée est explicite et immuable.
5. Cette étape ne crée aucun match et ne téléporte aucun joueur.
6. Aucune persistance, économie ou télémétrie n'est activée par ce contrat.

## Suite logique

Étape 2 : `MatchmakingService` — enregistrement des queues, annulation, sélection déterministe de deux tickets compatibles et réservation atomique.
