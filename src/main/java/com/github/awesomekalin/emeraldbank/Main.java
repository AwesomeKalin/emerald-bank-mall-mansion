package com.github.awesomekalin.emeraldbank;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    private File bankFile;
    private FileConfiguration bankConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Welcome to EmeraldBank! I hope you are ready to start banking with those emeralds!");
        System.out.println("Preparing commands");
        this.getCommand("emeraldbank").setExecutor(new Commands());
        System.out.println("Commands prepared!");
        System.out.println("Preparing banks");
        createBankConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Bank again soon!");
    }

    public FileConfiguration getBankConfig() {
        return this.bankConfig;
    }

    private void createBankConfig() {
        bankFile = new File(getDataFolder(), "bank.yml");
        if (!bankFile.exists()) {
            bankFile.getParentFile().mkdirs();
            saveResource("banks.yml", false);
        }

        bankConfig = new YamlConfiguration();
        try {
            bankConfig.load(bankFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    Main(JavaPlugin plugin) {

    }
}
