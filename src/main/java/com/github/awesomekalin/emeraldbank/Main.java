package com.github.awesomekalin.emeraldbank;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class Main extends JavaPlugin {

    public static List<String> banks;
    public static List<Integer> emeralds;
    public static List<Integer> iron;
    public static File bankFile;
    public static FileConfiguration bankConfig;

    @Override
    public void onEnable() { System.out.println("[EmeraldBank] Welcome to EmeraldBank! I hope you are ready to start banking with those emeralds!");
        System.out.println("[EmeraldBank] Preparing commands");
        this.getCommand("emeraldbank").setExecutor(new Commands());
        System.out.println("[EmeraldBank] Commands prepared!");
        System.out.println("[EmeraldBank] Preparing banks! PLEASE IGNORE ERROR MESSAGES UNTIL SAID SO! IT IS NOT A BUG!!!");
        // Plugin startup logic
        createBankConfig();
        banks = GetBankNames();
        emeralds = GetBankEmeralds();
        iron = GetBankIron();
        System.out.println("[EmeraldBank] Stop ignoring error messages for here until said so!");
        System.out.println("[EmeraldBank] Banks prepared!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        save();
        System.out.println("[EmeraldBank] Bank again soon!");
    }

    public static List<String> GetBankNames() {
        return getBankConfig().getStringList("banks.names");
    }

    public static List<Integer> GetBankEmeralds() {
        return getBankConfig().getIntegerList("banks.emeralds");
    }

    public static List<Integer> GetBankIron() {
        return getBankConfig().getIntegerList("banks.iron");
    }

    public static FileConfiguration getBankConfig() {
        return bankConfig;
    }

    public void createBankConfig() {
        bankFile = new File(getDataFolder(), "bank.yml");
        if (!bankFile.exists()) {
            bankFile.getParentFile().mkdirs();
            saveResource("bank.yml", false);
        }

        bankConfig = new YamlConfiguration();
        try {
            bankConfig.load(bankFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        getBankConfig().set("banks.names", banks);
        getBankConfig().set("banks.emeralds", emeralds);
        getBankConfig().set("banks.iron", iron);
        try {
            bankConfig.save(bankFile);
            System.out.println("[EmeraldBank] Config Saved!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[EmeraldBank] Config Save Failed!!!");
        }
    }

}
