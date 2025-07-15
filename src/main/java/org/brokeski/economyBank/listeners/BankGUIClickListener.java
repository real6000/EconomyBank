package org.brokeski.economyBank.listeners;

import org.brokeski.economyBank.EconomyBank;
import org.brokeski.economyBank.model.BankAccount;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
