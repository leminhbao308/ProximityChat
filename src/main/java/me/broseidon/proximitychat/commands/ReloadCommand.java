package me.broseidon.proximitychat.commands;

import me.broseidon.proximitychat.ProximityChat;
import me.broseidon.proximitychat.utils.CustomColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
    private ProximityChat main;

    public ReloadCommand(ProximityChat main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("proximitychat.reload")) {
            commandSender.sendMessage(CustomColor.translate(main.getConfigManager().getNoPerm()));
            return false;
        }

        if (strings.length != 1) {
            commandSender.sendMessage(CustomColor.translate(main.getConfigManager().getUsage())
                    .replace("%command%", "/pc reload"));
            return false;
        } else {
            if (strings[0].equalsIgnoreCase("reload")) {
                main.reloadConfig();
                commandSender.sendMessage(CustomColor.translate(main.getConfigManager().getReloaded()));
                commandSender.sendMessage("Plugin made by Broseidon.Java");
                return true;
            } else {
                commandSender.sendMessage(CustomColor.translate(main.getConfigManager().getUsage())
                        .replace("%command%", "/pc reload"));
                return false;
            }
        }
    }
}
