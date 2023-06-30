package me.broseidon.proximitychat.commands.completer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class GiveMegaphoneCommandCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> onlinePlayers = new ArrayList<>();
        for (Player player: Bukkit.getOnlinePlayers()) {
            onlinePlayers.add(player.getName());
        }
        if (strings.length == 1) {
            return StringUtil.copyPartialMatches(strings[0], onlinePlayers, new ArrayList<>());
        }

        return new ArrayList<>();
    }
}
