package fr.cerclepalace.nexus.combat;

import java.time.Instant;
import java.util.UUID;

public final class CombatMatch {
    private final UUID id;
    private final CombatParticipant first;
    private final CombatParticipant second;
    private final Instant startedAt;
    private CombatState state;

    public CombatMatch(UUID id, UUID firstPlayer, UUID secondPlayer) {
        this.id = id;
        this.first = new CombatParticipant(firstPlayer);
        this.second = new CombatParticipant(secondPlayer);
        this.startedAt = Instant.now();
        this.state = CombatState.DUELING;
    }

    public UUID id() { return id; }
    public CombatParticipant first() { return first; }
    public CombatParticipant second() { return second; }
    public Instant startedAt() { return startedAt; }
    public CombatState state() { return state; }
    public void finish() { state = CombatState.IDLE; }

    public boolean contains(UUID playerId) {
        return first.playerId().equals(playerId) || second.playerId().equals(playerId);
    }

    public UUID opponentOf(UUID playerId) {
        if (first.playerId().equals(playerId)) return second.playerId();
        if (second.playerId().equals(playerId)) return first.playerId();
        throw new IllegalArgumentException("Player is not part of this match");
    }
}
