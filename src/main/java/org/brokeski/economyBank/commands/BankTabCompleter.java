package org.brokeski.economyBank.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class BankTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender,
                                                @NotNull Command command,
                                                @NotNull String alias,
                                                @NotNull String[] args) {
        if(!(sender instanceof Player)) return Collections.emptyList();

        if(command.getName().equalsIgnoreCase("bank")){
            //no subcommands but can be extended
            return Collections.emptyList();
        }

        if (command.getName().equalsIgnoreCase("bankstats")) {
            // Also no subcommands yet
            return Collections.emptyList();
        }

        return List.of();
    }
}
