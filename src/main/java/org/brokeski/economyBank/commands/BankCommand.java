package org.brokeski.economyBank.commands;

import org.brokeski.economyBank.EconomyBank;
import org.brokeski.economyBank.gui.BankGUI;
import org.brokeski.economyBank.model.BankAccount;
import org.brokeski.economyBank.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BankCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if(!(player.hasPermission("economybank.use"))){
            player.sendMessage(ChatUtil.color("&cYou don't have permission to use this command."));
        }

        //gui integration
        player.sendMessage(ChatUtil.color("&a[Bank] Opening your bank..."));
        BankAccount account = EconomyBank.getInstance().getBankManager().getAccount(player.getUniqueId());
        Inventory gui = BankGUI.createBankGUI(account);
        player.openInventory(gui);
        return true;
    }
}
