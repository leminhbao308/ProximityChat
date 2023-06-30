package me.broseidon.proximitychat.commands;

import me.broseidon.proximitychat.manager.ConfigManager;
import me.broseidon.proximitychat.utils.CustomColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GlobalChatCommand implements CommandExecutor {
    private final ConfigManager configManager;

    public GlobalChatCommand(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!configManager.allowGlobalChat()) return false;

        if (!(sender instanceof Player)) {
            sender.sendMessage(CustomColor.translate(configManager.getOnlyPlayer()));
            return false;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            sender.sendMessage(CustomColor.translate(configManager.getUsage().replace("%command%","/gc <message>")));
            return false;
        }

        StringBuilder builder = new StringBuilder();
        for (String word : args) {
            builder.append(word).append(" ");
        }

        Bukkit.broadcastMessage(CustomColor.translate("&8[&5GC&8] &a" + player.getName() + " &8→ &7" + builder.toString().trim()));
        return true;
    }
}
