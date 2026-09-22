package fr.cerclepalace.nexus.combat;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class CombatService {
    private final Map<UUID, CombatMatch> matchesByPlayer = new ConcurrentHashMap<>();

    public boolean isInCombat(UUID playerId) {
        return matchesByPlayer.containsKey(playerId);
    }

    public CombatMatch getMatch(UUID playerId) {
        return matchesByPlayer.get(playerId);
    }

    public CombatMatch startDuel(UUID first, UUID second) {
        if (first.equals(second)) throw new IllegalArgumentException("A player cannot duel themselves");
        if (isInCombat(first) || isInCombat(second)) throw new IllegalStateException("A participant is already in combat");

        CombatMatch match = new CombatMatch(UUID.randomUUID(), first, second);
        matchesByPlayer.put(first, match);
        matchesByPlayer.put(second, match);
        return match;
    }

    public void finish(UUID playerId) {
        CombatMatch match = matchesByPlayer.get(playerId);
        if (match == null) return;
        match.finish();
        matchesByPlayer.remove(match.first().playerId());
        matchesByPlayer.remove(match.second().playerId());
    }

    public void finishMatch(CombatMatch match) {
        finish(match.first().playerId());
    }
}
