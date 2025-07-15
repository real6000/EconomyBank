package org.brokeski.economyBank.commands;

import org.brokeski.economyBank.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
        return true;
    }
}
