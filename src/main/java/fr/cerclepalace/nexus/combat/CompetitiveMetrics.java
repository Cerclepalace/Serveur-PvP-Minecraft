package fr.cerclepalace.nexus.combat;

/**
 * Competitive ladder snapshot inspired by common practice-server statistics.
 */
public record CompetitiveMetrics(
        String ladder,
        int rating,
        int ratingDelta,
        int wins,
        int losses,
        int matchesPlayed,
        int currentStreak,
        int bestStreak
) {
    public CompetitiveMetrics {
        if (ladder == null || ladder.isBlank()) {
            throw new IllegalArgumentException("ladder is required");
        }
        if (rating < 0 || wins < 0 || losses < 0 || matchesPlayed < 0
                || currentStreak < 0 || bestStreak < 0) {
            throw new IllegalArgumentException("Competitive metrics cannot be negative");
        }
        if (wins + losses > matchesPlayed) {
            throw new IllegalArgumentException("wins + losses cannot exceed matchesPlayed");
        }
    }

    public double winRate() {
        return matchesPlayed == 0 ? 0.0 : (double) wins / matchesPlayed;
    }
}
