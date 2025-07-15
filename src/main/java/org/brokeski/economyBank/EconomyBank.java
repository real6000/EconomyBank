package org.brokeski.economyBank;

import org.brokeski.economyBank.commands.BankCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class EconomyBank extends JavaPlugin {

    private static EconomyBank instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        getCommand("bank").setExecutor(new BankCommand());
        getLogger().info("EconomyBank has been enabled");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("EconomyBank has been disabled");
    }

    public static EconomyBank getInstance(){
        return instance;
    }
}
