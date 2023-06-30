package me.broseidon.proximitychat;

import me.broseidon.proximitychat.commands.GiveMegaphoneCommand;
import me.broseidon.proximitychat.commands.GlobalChatCommand;
import me.broseidon.proximitychat.commands.ReloadCommand;
import me.broseidon.proximitychat.commands.completer.GiveMegaphoneCommandCompleter;
import me.broseidon.proximitychat.commands.completer.ReloadCommandCompleter;
import me.broseidon.proximitychat.events.AsyncPlayerChat;
import me.broseidon.proximitychat.manager.ConfigManager;
import me.broseidon.proximitychat.utils.CustomColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.Objects;

public final class ProximityChat extends JavaPlugin {

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChat(this), this);
        Objects.requireNonNull(getCommand("gmp")).setExecutor(new GiveMegaphoneCommand(getMegaphone(), this));
        Objects.requireNonNull(getCommand("gc")).setExecutor(new GlobalChatCommand(getConfigManager()));
        Objects.requireNonNull(getCommand("pc")).setExecutor(new ReloadCommand(this));

        Objects.requireNonNull(getCommand("gmp")).setTabCompleter(new GiveMegaphoneCommandCompleter());
        Objects.requireNonNull(getCommand("pc")).setTabCompleter(new ReloadCommandCompleter());

        getLogger().info("================================");
        getLogger().info("ProximityChat has been enabled!");
        getLogger().info("Made by Broseidon.Java");
        getLogger().info("================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("================================");
        getLogger().info("ProximityChat has been disabled!");
        getLogger().info("================================");
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public ItemStack getMegaphone() {
        ItemStack megaphone = new ItemStack(Material.STICK);
        ItemMeta megaphoneMeta = megaphone.getItemMeta();

        if (megaphoneMeta != null) {
            megaphoneMeta.setDisplayName(CustomColor.translate(configManager.getItemName()));
            megaphoneMeta.setLore(Collections.singletonList(
                    CustomColor.translate(
                            configManager.getItemLore().replace("%range%",
                                    String.valueOf(getConfigManager().getMegaphoneRange())))));
            megaphone.setItemMeta(megaphoneMeta);
        }

        return megaphone;
    }
}
