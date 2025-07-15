package org.brokeski.economyBank.listeners;

import org.brokeski.economyBank.EconomyBank;
import org.brokeski.economyBank.model.BankAccount;
import org.brokeski.economyBank.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BankGUIClickListener implements Listener {

    @EventHandler
    public void onBankClick(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player player)) return;
        Inventory inv = event.getView().getTopInventory();
        String title = ChatColor.stripColor(event.getView().getTitle());

        if(!title.equalsIgnoreCase("Your Bank")) return;

        //cancel gui clicks
        event.setCancelled(true);
        int slot = event.getRawSlot();

        //balance display in slot 0 (top row), disallow interaction
        if(slot == 0) return;

        //handle storage slots: allow dragging items in/out
        if(slot >= 1 && slot < inv.getSize()){
            ItemStack cursor = event.getCursor();
            ItemStack current = event.getCurrentItem();

            //for now simple item swap
            event.setCancelled(false);
        }

        // Withdraw button
        if (slot == 33 && event.getClick().isLeftClick()) {
            BankAccount account = EconomyBank.getInstance().getBankManager().getAccount(player.getUniqueId());
            if (account.getBalance() >= 100) {
                account.setBalance(account.getBalance() - 100);
                player.getInventory().addItem(new ItemStack(Material.GOLD_NUGGET, 1));
                player.sendMessage(ChatUtil.color("&aYou withdrew $100."));
            } else {
                player.sendMessage(ChatUtil.color("&cInsufficient funds to withdraw."));
            }
            player.closeInventory();
            return;
        }

// Deposit button
        if (slot == 29 && event.getClick().isLeftClick()) {
            if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_NUGGET), 1)) {
                player.getInventory().removeItem(new ItemStack(Material.GOLD_NUGGET, 1));
                BankAccount account = EconomyBank.getInstance().getBankManager().getAccount(player.getUniqueId());
                account.setBalance(account.getBalance() + 100);
                player.sendMessage(ChatUtil.color("&aYou deposited $100."));
            } else {
                player.sendMessage(ChatUtil.color("&cYou need at least 1 gold nugget to deposit."));
            }
            player.closeInventory();
            return;
        }

        //sync stored inv on close (can also sync live)
        Bukkit.getScheduler().runTaskLater(EconomyBank.getInstance(), () -> {
            BankAccount account = EconomyBank.getInstance().getBankManager().getAccount(player.getUniqueId());
            Inventory top = player.getOpenInventory().getTopInventory();

            for(int i = 1; i < top.getSize(); i++){
                account.getInventory().setItem(i -1, top.getItem(i));
            }
        }, 1L);
    }
}
