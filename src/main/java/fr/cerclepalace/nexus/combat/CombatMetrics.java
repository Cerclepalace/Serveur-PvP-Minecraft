package fr.cerclepalace.nexus.combat;

/**
 * Immutable combat-performance snapshot.
 * Values are authoritative aggregates produced by the combat domain.
 */
public record CombatMetrics(
        int kills,
        int deaths,
        int assists,
        long damageDealt,
        long damageTaken,
        int hits,
        int misses,
        int criticalHits,
        int comboPeak,
        int longestCombo,
        long distanceTravelledBlocks,
        long timeInCombatMs,
        double finalHealth
) {
    public CombatMetrics {
        if (kills < 0 || deaths < 0 || assists < 0 || damageDealt < 0 || damageTaken < 0
                || hits < 0 || misses < 0 || criticalHits < 0 || comboPeak < 0 || longestCombo < 0
                || distanceTravelledBlocks < 0 || timeInCombatMs < 0 || finalHealth < 0) {
            throw new IllegalArgumentException("Combat metrics cannot be negative");
        }
    }

    public double hitRate() {
        int attempts = hits + misses;
        return attempts == 0 ? 0.0 : (double) hits / attempts;
    }
}
