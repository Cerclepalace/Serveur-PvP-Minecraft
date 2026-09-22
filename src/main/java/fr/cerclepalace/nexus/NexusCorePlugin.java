package fr.cerclepalace.nexus;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class NexusCorePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("NexusCore enabled — foundation online.");
    }

    @Override
    public void onDisable() {
        getLogger().info("NexusCore disabled — shutdown complete.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("nexus")) {
            return false;
        }

        sender.sendMessage(Component.text("NEXUS CORE: ONLINE"));
        sender.sendMessage(Component.text("Version: " + getPluginMeta().getVersion()));
        sender.sendMessage(Component.text("Next milestone: player identity + telemetry contract"));
        return true;
    }
}
