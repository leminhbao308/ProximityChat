package me.broseidon.proximitychat.events;

import me.broseidon.proximitychat.ProximityChat;
import me.broseidon.proximitychat.manager.ConfigManager;
import me.broseidon.proximitychat.utils.CustomColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

public class AsyncPlayerChat implements Listener {

    private final ConfigManager configManager;
    private final ItemStack megaphone;

    public AsyncPlayerChat(ProximityChat plugin) {
        this.configManager = plugin.getConfigManager();
        this.megaphone = plugin.getMegaphone();
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(true);

        Bukkit.getOnlinePlayers().forEach(target -> {
            if (target.getUniqueId().equals(player.getUniqueId())) return;
            int distance = (int) player.getLocation().distance(target.getLocation());
            if (player.getInventory().getItemInMainHand().equals(megaphone)) {
                if (configManager.getMegaphoneRange() == 0) {
                    target.sendMessage(CustomColor.translate(configManager.getGlobalMessageFormat(player.getName(),event.getMessage())));
                } else if (distance <= configManager.getMegaphoneRange()) {
                    if (configManager.showPlayerDistance()) {
                        target.sendMessage(CustomColor.translate(configManager.getProximityMessageFormat(distance, player.getName(), event.getMessage())));
                        return;
                    }
                    target.sendMessage(CustomColor.translate(configManager.getProximityHideDistanceMessageFormat(player.getName(), event.getMessage())));
                }
            } else {
                if (distance <= configManager.getTalkRange()) {
                    if (configManager.showPlayerDistance()) {
                        target.sendMessage(CustomColor.translate(configManager.getProximityMessageFormat(distance, player.getName(), event.getMessage())));
                        return;
                    }
                    target.sendMessage(CustomColor.translate(configManager.getProximityHideDistanceMessageFormat(player.getName(), event.getMessage())));
                }
            }
        });

        player.sendMessage(CustomColor.translate("&a&lYOU" + " &8→ &7" + event.getMessage()));
    }

}