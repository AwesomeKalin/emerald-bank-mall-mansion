package com.github.awesomekalin.emeraldbank.configs;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.awesomekalin.emeraldbank.Main;

public class BankConfig {

    BankConfig(Main plugin) {
        plugin.getBankConfig().getString("banks");
    }


}
