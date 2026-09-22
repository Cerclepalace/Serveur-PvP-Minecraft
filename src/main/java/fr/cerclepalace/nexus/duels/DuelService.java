package fr.cerclepalace.nexus.duels;

import fr.cerclepalace.nexus.combat.CombatMatch;
import fr.cerclepalace.nexus.combat.CombatService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public final class DuelService {
    private final CombatService combatService;

    public DuelService(CombatService combatService) {
        this.combatService = combatService;
    }

    public CombatMatch start(Player first, Player second) {
        CombatMatch match = combatService.startDuel(first.getUniqueId(), second.getUniqueId());
        first.sendMessage("§bNEXUS §7| §fDuel started against §b" + second.getName());
        second.sendMessage("§bNEXUS §7| §fDuel started against §b" + first.getName());
        return match;
    }

    public void end(UUID playerId) {
        CombatMatch match = combatService.getMatch(playerId);
        if (match == null) return;
        Player first = Bukkit.getPlayer(match.first().playerId());
        Player second = Bukkit.getPlayer(match.second().playerId());
        combatService.finishMatch(match);
        if (first != null) first.sendMessage("§bNEXUS §7| §fDuel ended.");
        if (second != null) second.sendMessage("§bNEXUS §7| §fDuel ended.");
    }
}
