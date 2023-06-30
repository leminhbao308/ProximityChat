package me.broseidon.proximitychat.commands;

import me.broseidon.proximitychat.ProximityChat;
import me.broseidon.proximitychat.utils.CustomColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class GiveMegaphoneCommand implements CommandExecutor {
    private final ItemStack megaphone;
    private final ProximityChat main;

    public GiveMegaphoneCommand(ItemStack megaphone, JavaPlugin main) {
        this.main = (ProximityChat) main;
        this.megaphone = megaphone;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("proximitychat.megaphone")) {
            sender.sendMessage(CustomColor.translate(main.getConfigManager().getNoPerm()));
            return false;
        }

        if (args.length == 0) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(CustomColor.translate(CustomColor.translate(main.getConfigManager().getOnlyPlayer())));
                return false;
            }

            Player player = (Player) sender;

            player.getInventory().addItem(megaphone);
            player.sendMessage(CustomColor.translate(CustomColor.translate(main.getConfigManager().getGetMegaphone())));
            return true;

        } else if (args.length == 1) {

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(CustomColor.translate(CustomColor.translate(main.getConfigManager().getNotOnline()
                        .replace("%player%", args[0]))));
                return false;
            }

            if (((Player) sender).getUniqueId().equals(target.getUniqueId())) {
                ((Player) sender).performCommand("gmp");
                return true;
            }

            target.getInventory().addItem(megaphone);
            target.sendMessage(CustomColor.translate(main.getConfigManager().getReceivedMegaphone()
                    .replace("%player%", sender.getName())));
            sender.sendMessage(CustomColor.translate(main.getConfigManager().getGiveMegaphone()
                    .replace("%player%", target.getName())));
            return true;

        } else {
            sender.sendMessage(CustomColor.translate(main.getConfigManager().getUsage())
                    .replace("%command%", "/gmp (player)"));
        }

        return false;
    }
}
