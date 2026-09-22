package fr.cerclepalace.nexus.matchmaking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * In-memory FIFO queue for one exact QueueKey partition.
 * This step intentionally contains no persistence, networking, or match creation.
 */
public final class MatchmakingQueue {
    private final QueueKey key;
    private final ArrayDeque<QueueTicket> tickets = new ArrayDeque<>();

    public MatchmakingQueue(QueueKey key) {
        this.key = key;
    }

    public QueueKey key() {
        return key;
    }

    public boolean enqueue(QueueTicket ticket) {
        if (!key.equals(ticket.key()) || contains(ticket.playerId())) {
            return false;
        }
        tickets.addLast(ticket);
        return true;
    }

    public QueueTicket peek() {
        return tickets.peekFirst();
    }

    public QueueTicket poll() {
        return tickets.pollFirst();
    }

    public boolean remove(UUID playerId) {
        return tickets.removeIf(ticket -> ticket.playerId().equals(playerId));
    }

    public boolean contains(UUID playerId) {
        return tickets.stream().anyMatch(ticket -> ticket.playerId().equals(playerId));
    }

    public int size() {
        return tickets.size();
    }

    public List<QueueTicket> snapshot() {
        return List.copyOf(new ArrayList<>(tickets));
    }
}
