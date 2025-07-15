package org.brokeski.economyBank.model;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class BankAccount {

    private final UUID owner;
    private double balance;
    private final Inventory inventory;
    private long lastUpdated;

    public BankAccount(UUID owner, double balance) {
        this.owner = owner;
        this.balance = balance;
        this.inventory = Bukkit.createInventory(null, 27, "Your Bank Storage");
        this.lastUpdated = System.currentTimeMillis();
    }

    public UUID getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = System.currentTimeMillis();
    }
}
