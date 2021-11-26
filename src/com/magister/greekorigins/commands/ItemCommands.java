package com.magister.greekorigins.commands;

import com.magister.greekorigins.items.GeneralItemsManager;
import com.magister.greekorigins.items.GreekWeapons;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ItemCommands  implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if(player.isOp()) {
            if (cmd.getName().equalsIgnoreCase("giveAmbrosia")) {
                player.getInventory().addItem(GeneralItemsManager.Ambrosia);
            }
            else if (cmd.getName().equalsIgnoreCase("giveWine")) {
                player.getInventory().addItem(GeneralItemsManager.Wine);
            }
            else if (cmd.getName().equalsIgnoreCase("giveMead")) {
                player.getInventory().addItem(GeneralItemsManager.Mead);
            }

            else if (cmd.getName().equalsIgnoreCase("giveHelmOfDarkness")) {
                player.getInventory().addItem(GreekWeapons.HelmOfDarkness);
            }
            else if (cmd.getName().equalsIgnoreCase("givePoseidonsTrident")) {
                player.getInventory().addItem(GreekWeapons.PoseidonsTrident);
            }
        }

        return true;
    }
}

