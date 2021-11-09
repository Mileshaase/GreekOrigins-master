package com.magister.greekorigins.commands;

import com.magister.greekorigins.inventories.RollForGod;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.RollEvents.*;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("heal")) {
            double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue();
            player.setHealth(maxHealth);
            player.sendMessage(ChatColor.DARK_GREEN + "Health restored");
        } else if (cmd.getName().equalsIgnoreCase("feed")) {
            player.setFoodLevel(20);
            player.sendMessage(ChatColor.DARK_GREEN + "Hunger restored");
        } else if (cmd.getName().equalsIgnoreCase("lvl10")) {
            PlayerLevel.put(player.getName(), 10d);
            player.sendMessage(ChatColor.WHITE + "Level 10");
        } else if (cmd.getName().equalsIgnoreCase("lvl20")) {
            PlayerLevel.put(player.getName(), 20d);
            player.sendMessage(ChatColor.WHITE + "Level 20");
        } else if (cmd.getName().equalsIgnoreCase("lvl30")) {
            PlayerLevel.put(player.getName(), 30d);
            player.sendMessage(ChatColor.WHITE + "Level 30");
        } else if (cmd.getName().equalsIgnoreCase("lvl40")) {
            PlayerLevel.put(player.getName(), 40d);
            player.sendMessage(ChatColor.WHITE + "Level 40");
        } else if (cmd.getName().equalsIgnoreCase("lvl50")) {
            PlayerLevel.put(player.getName(), 50d);
            player.sendMessage(ChatColor.WHITE + "Level 50");
        } else if (cmd.getName().equalsIgnoreCase("lvl0")) {
            PlayerLevel.put(player.getName(), 0d);
            player.sendMessage(ChatColor.WHITE + "Level 0");
        } else if (cmd.getName().equalsIgnoreCase("getlvl")) {
            double lvl = Math.round(PlayerLevel.get(player.getName()));
            player.sendMessage(ChatColor.WHITE + "You're Level " + lvl);
        }

        else if (cmd.getName().equalsIgnoreCase("homegreece")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    HomeLocation.put(impactedPlayer.getName(), "Greece");
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                HomeLocation.put(player.getName(), "Greece");
            }
        } else if (cmd.getName().equalsIgnoreCase("homenorway")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    HomeLocation.put(impactedPlayer.getName(), "Norway");
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                HomeLocation.put(player.getName(), "Norway");
            }
        }

        else if (cmd.getName().equalsIgnoreCase("roll")) {
            RollForGod gui = new RollForGod();
            player.openInventory(gui.getInventory());
        }
        else if (cmd.getName().equalsIgnoreCase("addroll")) {
            NumberOfRolls.put(player.getName(), NumberOfRolls.get(player.getName()) + 1);
        }

        else if (cmd.getName().equalsIgnoreCase("parent")) {
            String parent = GodlyParent.get(player.getName());
            player.sendMessage(ChatColor.DARK_PURPLE + "Your Parent is " + parent);
        }

        return true;
    }
}
