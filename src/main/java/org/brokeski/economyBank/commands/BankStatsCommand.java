package org.brokeski.economyBank.commands;

import org.brokeski.economyBank.EconomyBank;
import org.brokeski.economyBank.model.BankAccount;
import org.brokeski.economyBank.utils.ChatUtil;
import org.brokeski.economyBank.utils.TimeUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BankStatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player)){
            sender.sendMessage(ChatUtil.color("&4Only players can use this command."));
            return true;
        }

        BankAccount account = EconomyBank.getInstance().getBankManager().getAccount(player.getUniqueId());
        double rate = EconomyBank.getInstance().getConfig().getDouble("interest.rate", 0.02);

        int stored = 0;
        for(var item : account.getInventory().getContents()){
            if(item != null) stored++;
        }

        player.sendMessage(ChatUtil.color("&6=== Bank Stats ==="));
        player.sendMessage(ChatUtil.color("&eBalance: &a$" + String.format("%.2f", account.getBalance())));
        player.sendMessage(ChatUtil.color("&eStored Items: &f" + stored));
        player.sendMessage(ChatUtil.color("&eLast Opened: &f" + TimeUtil.formatTime(account.getLastUpdated())));
        player.sendMessage(ChatUtil.color("&eInterest Rate: &f" + (rate * 100) + "%"));

        return true;
    }
}
