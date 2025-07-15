package org.brokeski.economyBank.gui;

import org.brokeski.economyBank.model.BankAccount;
import org.brokeski.economyBank.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BankGUI {

    public static Inventory createBankGUI(BankAccount account){
        Inventory gui = Bukkit.createInventory(null, 36, "Your Bank");

        //slot 0: balance info
        ItemStack balanceItem = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = balanceItem.getItemMeta();
        meta.setDisplayName(ChatUtil.color("&6Balance: &e$" + String.format("%.2f", account.getBalance())));
        balanceItem.setItemMeta(meta);
        gui.setItem(0, balanceItem);

        //fill the rest with stored items
        for(int i = 1; i < gui.getSize(); i++){
            gui.setItem(i, account.getInventory().getItem(i - 1));
        }

        return gui;
    }
}
