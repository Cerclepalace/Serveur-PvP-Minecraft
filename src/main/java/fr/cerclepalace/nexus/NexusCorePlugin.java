package fr.cerclepalace.nexus;

import fr.cerclepalace.nexus.combat.CombatListener;
import fr.cerclepalace.nexus.combat.CombatService;
import fr.cerclepalace.nexus.duels.DuelService;
import fr.cerclepalace.nexus.ffa.FfaService;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class NexusCorePlugin extends JavaPlugin {
    private CombatService combatService;
    private DuelService duelService;
    private FfaService ffaService;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        combatService = new CombatService();
        ffaService = new FfaService();
        duelService = new DuelService(combatService);
        Bukkit.getPluginManager().registerEvents(new CombatListener(combatService, ffaService), this);
        getLogger().info("NexusCore enabled — P005/P006/P007 combat foundation online.");
    }

    @Override
    public void onDisable() {
        getLogger().info("NexusCore disabled — shutdown complete.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("nexus")) return false;

        if (args.length == 0) {
            sender.sendMessage(Component.text("NEXUS CORE: ONLINE"));
            sender.sendMessage(Component.text("Combat: " + combatServiceStatus()));
            sender.sendMessage(Component.text("FFA players: " + ffaService.onlineCount()));
            return true;
        }

        if (args[0].equalsIgnoreCase("duel")) {
            if (!(sender instanceof Player first)) {
                sender.sendMessage("Players only.");
                return true;
            }
            if (args.length < 2) {
                sender.sendMessage("Usage: /nexus duel <player>");
                return true;
            }
            Player second = Bukkit.getPlayerExact(args[1]);
            if (second == null) {
                sender.sendMessage("Player not found.");
                return true;
            }
            try {
                duelService.start(first, second);
            } catch (IllegalArgumentException | IllegalStateException ex) {
                sender.sendMessage("§cDuel rejected: " + ex.getMessage());
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("ffa")) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage("Players only.");
                return true;
            }
            if (args.length < 2) {
                sender.sendMessage("Usage: /nexus ffa <join|leave|status>");
                return true;
            }
            switch (args[1].toLowerCase()) {
                case "join" -> sender.sendMessage(ffaService.join(player.getUniqueId())
                        ? "§aJoined FFA."
                        : "§eAlready in FFA.");
                case "leave" -> sender.sendMessage(ffaService.leave(player.getUniqueId())
                        ? "§aLeft FFA."
                        : "§eNot in FFA.");
                case "status" -> sender.sendMessage("§bFFA participants: §f" + ffaService.onlineCount());
                default -> sender.sendMessage("Usage: /nexus ffa <join|leave|status>");
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("end")) {
            if (!(sender instanceof Player player)) return true;
            duelService.end(player.getUniqueId());
            return true;
        }

        sender.sendMessage("Usage: /nexus [duel|ffa|end]");
        return true;
    }

    private String combatServiceStatus() {
        return "READY";
    }
}
