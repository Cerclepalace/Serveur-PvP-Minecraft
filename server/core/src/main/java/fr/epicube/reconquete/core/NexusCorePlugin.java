package fr.epicube.reconquete.core;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class NexusCorePlugin extends JavaPlugin {
    private static final String PROTOCOL_VERSION = "1";

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("NexusCore enabled; protocol=" + PROTOCOL_VERSION);
    }

    @Override
    public void onDisable() {
        getLogger().info("NexusCore disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("nexus")) {
            return false;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("health")) {
            sender.sendMessage("NexusCore HEALTH=OK PROTOCOL=" + PROTOCOL_VERSION);
            return true;
        }

        sender.sendMessage("Usage: /nexus health");
        return true;
    }
}
