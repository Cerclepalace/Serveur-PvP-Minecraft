package fr.cerclepalace.nexus.combat;

import fr.cerclepalace.nexus.ffa.FfaService;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public final class CombatListener implements Listener {
    private final CombatService combatService;
    private final FfaService ffaService;

    public CombatListener(CombatService combatService, FfaService ffaService) {
        this.combatService = combatService;
        this.ffaService = ffaService;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player victim)) return;
        Player attacker = directPlayerAttacker(event.getDamager());
        if (attacker == null) return;

        UUID a = attacker.getUniqueId();
        UUID v = victim.getUniqueId();
        CombatMatch attackerMatch = combatService.getMatch(a);

        if (attackerMatch != null) {
            if (!attackerMatch.contains(v) || !attackerMatch.opponentOf(a).equals(v)) {
                event.setCancelled(true);
            }
            return;
        }

        if (!(ffaService.isInFfa(a) && ffaService.isInFfa(v))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();
        combatService.finish(playerId);
        ffaService.leave(playerId);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();
        combatService.finish(playerId);
        ffaService.leave(playerId);
    }

    private Player directPlayerAttacker(Entity entity) {
        return entity instanceof Player player ? player : null;
    }
}
