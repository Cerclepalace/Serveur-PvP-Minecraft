package fr.cerclepalace.nexus.matchmaking;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/** Immutable declaration of a player's matchmaking intent. */
public record QueueTicket(
        UUID playerId,
        QueueKey key,
        int rating,
        Instant queuedAt
) {
    public QueueTicket {
        Objects.requireNonNull(playerId, "playerId");
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(queuedAt, "queuedAt");
        if (rating < 0) {
            throw new IllegalArgumentException("rating must be >= 0");
        }
    }
}
