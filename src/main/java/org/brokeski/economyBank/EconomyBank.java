package org.brokeski.economyBank;

import org.brokeski.economyBank.commands.BankCommand;
import org.brokeski.economyBank.commands.BankStatsCommand;
import org.brokeski.economyBank.commands.BankTabCompleter;
import org.brokeski.economyBank.listeners.BankGUIClickListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

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
        getCommand("bankstats").setExecutor(new BankStatsCommand());
        getServer().getPluginManager().registerEvents(new BankGUIClickListener(), this);

        BankTabCompleter tabCompleter = new BankTabCompleter();
        getCommand("bank").setTabCompleter(tabCompleter);
        getCommand("bankstats").setTabCompleter(tabCompleter);

        startInterestTask();

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

    private void startInterestTask(){
        double interestRate = getConfig().getDouble("interest.rate", 0.02);
        int interval = getConfig().getInt("interest.interval", 600);

        new BukkitRunnable(){
            @Override
            public void run(){
                bankManager.getAllAccounts().forEach(account ->{
                    // oldbalance is the status quo of
                    // balance and the gain is the product
                    // of the status quo balance and the
                    // interest rate according to the config
                    // and that will be the new balance
                    double oldBalance = account.getBalance();
                    double gain = oldBalance * interestRate;
                    account.setBalance(oldBalance + gain);
                });
                getLogger().info("[EconomyBank] Applied interest to all accounts.");
            }
        }.runTaskTimer(this, interval * 20L, interval * 20L); //delay, repeat (in ticks)
    }
}
