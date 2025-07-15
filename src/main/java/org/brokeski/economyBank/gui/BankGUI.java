package org.brokeski.economyBank.gui;

import org.brokeski.economyBank.model.BankAccount;
import org.brokeski.economyBank.utils.ChatUtil;
import org.brokeski.economyBank.utils.TimeUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BankGUI {

    public static Inventory createBankGUI(BankAccount account){
        Inventory gui = Bukkit.createInventory(null, 36, "Your Bank");

        //decorative border glass
        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = filler.getItemMeta();
        meta.setDisplayName(" ");
        filler.setItemMeta(meta);

        int[] borderSlots = {
                // my bitch ahh counting the glass panes
                0, 1, 2, 3, 4, 5, 6, 7, 8, //top row
                9, 17, // left + right side
                18, 26, // left + right side
                27, 28, 29, 30, 31, 32, 33, 34, 35 //bottom row
        };

        for(int slot : borderSlots){
            gui.setItem(slot, filler);
        }

        //slot 29 - deposit button
        ItemStack depositButton = new ItemStack(Material.EMERALD);
        ItemMeta depositMeta = depositButton.getItemMeta();
        depositMeta.setDisplayName(ChatUtil.color("&aDeposit $100"));
        depositMeta.setLore(java.util.List.of(ChatUtil.color("&7Left click to deposit $100")));
        depositButton.setItemMeta(depositMeta);
        gui.setItem(29, depositButton);

        //slot 33 - withdraw button
        ItemStack withdrawButton = new ItemStack(new ItemStack(Material.REDSTONE));
        ItemMeta withdrawMeta = withdrawButton.getItemMeta();
        withdrawMeta.setDisplayName(ChatUtil.color("&cWithdraw $100"));
        withdrawMeta.setLore(java.util.List.of(ChatUtil.color("&7Left click to withdraw $100")));
        withdrawButton.setItemMeta(withdrawMeta);
        gui.setItem(33, withdrawButton);

        // Slot 4 (center top): Balance display
        ItemStack balanceItem = new ItemStack(Material.GOLD_INGOT);
        ItemMeta balMeta = balanceItem.getItemMeta();
        balMeta.setDisplayName(ChatUtil.color("&6Balance: &e$" + String.format("%.2f", account.getBalance())));
        balMeta.setLore(java.util.Arrays.asList(
                ChatUtil.color("&7Interest rate: &a2.0% / hour"),
                ChatUtil.color("&7Last opened: &f" + TimeUtil.formatTime(account.getLastUpdated()))
        ));
        balanceItem.setItemMeta(balMeta);
        gui.setItem(4, balanceItem);

        // Load stored items starting at slot 10
        for (int i = 10; i < 27; i++) {
            gui.setItem(i, account.getInventory().getItem(i - 10));
        }

        return gui;
    }
}
