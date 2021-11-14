package com.magister.greekorigins.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

import static com.magister.greekorigins.events.generalevents.PlayerParties.*;

public class PartyCommands implements CommandExecutor {

    public static HashMap<UUID,Boolean> Invited = new HashMap<>();
    public static HashMap<UUID, String> TempInvite = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("createParty")) {
            Party.put(player.getUniqueId(), player.getName());
            PartyLeaders.put(player.getUniqueId(), player.getName());
        }

        else if (cmd.getName().equalsIgnoreCase("invite")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    impactedPlayer.sendMessage("You have been invited to " + ChatColor.BLUE + player.getName() + "'s party!");
                    impactedPlayer.sendMessage("Respond with " + ChatColor.GREEN + " /yes" + ChatColor.RESET + "or" + ChatColor.RED + "/no" + ChatColor.RESET + "to join or deny the party.");
                    Invited.put(impactedPlayer.getUniqueId(), true);
                    TempInvite.put(impactedPlayer.getUniqueId(), player.getName());
                } catch (IllegalArgumentException e) {
                    player.sendMessage("You must invite a valid player");
                }
            } else {
                player.sendMessage("You must invite a valid player");
            }
        }

        else if (cmd.getName().equalsIgnoreCase("yes")) {
            if(Invited.get(player.getUniqueId()) && !TempInvite.get(player.getUniqueId()).equals("null")) {
                Party.put(player.getUniqueId(), TempInvite.get(player.getUniqueId()));
                Invited.put(player.getUniqueId(), false);
                TempInvite.put(player.getUniqueId(), "null");
            } else{
                player.sendMessage("You have not been invited to any parties");
            }
        }
        else if (cmd.getName().equalsIgnoreCase("no")) {

        }

        return true;
    }
}