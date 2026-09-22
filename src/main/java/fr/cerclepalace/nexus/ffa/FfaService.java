package fr.cerclepalace.nexus.ffa;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class FfaService {
    private final Set<UUID> participants = ConcurrentHashMap.newKeySet();

    public boolean join(UUID playerId) {
        return participants.add(playerId);
    }

    public boolean leave(UUID playerId) {
        return participants.remove(playerId);
    }

    public boolean isInFfa(UUID playerId) {
        return participants.contains(playerId);
    }

    public int onlineCount() {
        return participants.size();
    }

    public Set<UUID> participants() {
        return Collections.unmodifiableSet(participants);
    }
}
