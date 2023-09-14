package me.broseidon.proximitychat.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {
    private final FileConfiguration configuration;

    public ConfigManager(JavaPlugin plugin) {
        this.configuration = plugin.getConfig();
        this.configuration.options().copyDefaults(true);
        plugin.saveDefaultConfig();
    }

    public int getTalkRange() {
        return this.configuration.getInt("talk-range");
    }

    public int getMegaphoneRange() {
        return getTalkRange() + this.configuration.getInt("megaphone-range");
    }

    public boolean showPlayerDistance() {
        return this.configuration.getBoolean("show-distance");
    }

    public boolean allowGlobalChat() {
        return this.configuration.getBoolean("allow-global-chat");
    }

    /* ITEM */
    public String getItemName() {
        return this.configuration.getString("item.name");
    }

    public String getItemLore() {
        return this.configuration.getString("item.lore");
    }

    /* LANGUAGE */
    public String getGlobalMessageFormat(String player, String message) {
        return this.configuration.getString("lang.global-message-format").replace("%player%",player).replace("%message%",message);
    }
    public String getProximityMessageFormat(int distance, String player, String message) {
        return this.configuration.getString("lang.proximity-message-format").replace("%distance%",String.valueOf(distance)).replace("%player%",player).replace("%message%",message);
    }
    public String getProximityHideDistanceMessageFormat(String player, String message) {
        return this.configuration.getString("lang.proximity-message-hide-distance-format").replace("%player%",player).replace("%message%",message);
    }

    public String getNoPerm() {
        return this.configuration.getString("lang.no-permission");
    }

    public String getReloaded() {
        return this.configuration.getString("lang.reloaded");
    }

    public String getUsage() {
        return this.configuration.getString("lang.usage");
    }

    public String getOnlyPlayer() {
        return this.configuration.getString("lang.only-players");
    }

    public String getGetMegaphone() {
        return this.configuration.getString("lang.get-megaphone");
    }

    public String getNotOnline() {
        return this.configuration.getString("lang.not-online");
    }

    public String getGiveMegaphone() {
        return this.configuration.getString("lang.give-megaphone");
    }

    public String getReceivedMegaphone() {
        return this.configuration.getString("lang.received-megaphone");
    }
}
