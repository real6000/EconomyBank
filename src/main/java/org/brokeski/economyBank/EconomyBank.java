package org.brokeski.economyBank;

import org.brokeski.economyBank.commands.BankCommand;
import org.brokeski.economyBank.listeners.BankGUIClickListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class EconomyBank extends JavaPlugin {

    private static EconomyBank instance;
    private BankManager bankManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        this.bankManager = new BankManager();
        getCommand("bank").setExecutor(new BankCommand());
        getServer().getPluginManager().registerEvents(new BankGUIClickListener(), this);
        getLogger().info("EconomyBank has been enabled");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if(bankManager != null) bankManager.saveAll();
        getLogger().info("EconomyBank has been disabled");
    }

    public static EconomyBank getInstance(){
        return instance;
    }

    public static void setInstance(EconomyBank instance) {
        EconomyBank.instance = instance;
    }

    public BankManager getBankManager() {
        return bankManager;
    }

    public void setBankManager(BankManager bankManager) {
        this.bankManager = bankManager;
    }
}
