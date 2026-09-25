# P006 — Data Layer

## Source de vérité
PostgreSQL est la source de vérité durable. Redis est réservé au cache, aux files et à l'état éphémère.

## Tables initiales
- players
- player_profiles
- matches
- match_players
- game_events
- ratings
- parties
- achievements
- cosmetics
- audit_logs

## Règles
- UUID Minecraft comme identifiant joueur.
- Les événements de match sont append-only.
- Les statistiques dérivées sont recalculables.
- Les écritures critiques sont idempotentes.
- Aucun paiement réel dans cette phase.

## Retention
La télémétrie haute fréquence doit être agrégée avant persistance longue durée lorsque les événements bruts ne sont pas nécessaires à l'audit.
