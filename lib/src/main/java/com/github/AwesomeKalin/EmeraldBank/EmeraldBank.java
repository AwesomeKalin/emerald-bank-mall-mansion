package com.github.AwesomeKalin.EmeraldBank;

import org.bukkit.plugin.java.JavaPlugin;

public class EmeraldBank extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("EmeraldBank is running! If you are still using Bukkit or Spigot, I recommend you to move to Paper, papermc.io");
    }
    @Override
    public void onDisable() {
        getLogger().info("Don't forget to use Paper!");
    }
}