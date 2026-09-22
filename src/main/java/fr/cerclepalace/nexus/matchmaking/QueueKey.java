package fr.cerclepalace.nexus.matchmaking;

import java.util.Objects;

/** Immutable partition key for a matchmaking queue. */
public record QueueKey(String mode, String ladder, String region) {
    public QueueKey {
        mode = requireNonBlank(mode, "mode");
        ladder = requireNonBlank(ladder, "ladder");
        region = requireNonBlank(region, "region");
    }

    private static String requireNonBlank(String value, String field) {
        Objects.requireNonNull(value, field);
        if (value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }
}
