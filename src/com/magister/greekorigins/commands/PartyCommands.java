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
            if(Party.get(player.getUniqueId()) != player.getName()) {
                Party.put(player.getUniqueId(), player.getName());
                PartyLeaders.put(player.getUniqueId(), player.getName());
                player.sendMessage("You're now the leader of the party: " + Party.get(player.getUniqueId()));
            } else {
                player.sendMessage("You are already the leader of a party!");
            }
        }

        else if (cmd.getName().equalsIgnoreCase("invite")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null && impactedPlayer != player;
                    impactedPlayer.sendMessage("You have been invited to " + ChatColor.BLUE + player.getName() + "'s party!");
                    impactedPlayer.sendMessage("Respond with " + ChatColor.GREEN + " /accept " + ChatColor.RESET + "or" + ChatColor.RED + "/deny " + ChatColor.RESET + "to join or deny the party.");
                    Invited.put(impactedPlayer.getUniqueId(), true);
                    TempInvite.put(impactedPlayer.getUniqueId(), player.getName());
                } catch (IllegalArgumentException e) {
                    player.sendMessage("You must invite a valid player");
                }
            } else {
                player.sendMessage("You must invite a valid player");
            }
        }
        else if (cmd.getName().equalsIgnoreCase("remove")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null && impactedPlayer != player;
                    if(Party.get(player.getUniqueId()) == Party.get(impactedPlayer.getUniqueId())) {
                        impactedPlayer.sendMessage("You have been removed from " + ChatColor.BLUE + player.getName() + "'s party!");
                        Party.put(impactedPlayer.getUniqueId(), "1");
                    } else {
                        player.sendMessage("This player is not in your party!");
                    }
                } catch (IllegalArgumentException e) {
                    player.sendMessage("You must remove a valid player");
                }
            } else {
                player.sendMessage("You must remove a valid player");
            }
        }

        else if (cmd.getName().equalsIgnoreCase("accept")) {
            if(Invited.get(player.getUniqueId()) && !TempInvite.get(player.getUniqueId()).equals("null")) {
                Party.put(player.getUniqueId(), TempInvite.get(player.getUniqueId()));
                Invited.put(player.getUniqueId(), false);
                TempInvite.put(player.getUniqueId(), "null");
                player.sendMessage("You accepted the invite!");
            } else{
                player.sendMessage("You have not been invited to any parties");
            }
        }
        else if (cmd.getName().equalsIgnoreCase("deny")) {
            if (Invited.get(player.getUniqueId()) && !TempInvite.get(player.getUniqueId()).equals("null")) {
                player.sendMessage("Okay, you don't have to join their party.");
                Invited.put(player.getUniqueId(), false);
            } else {
                player.sendMessage("You have not been invited to any parties");
            }
        }
        else if (cmd.getName().equalsIgnoreCase("leaveParty")) {
            if(Party.get(player.getUniqueId()) != null && Party.get(player.getUniqueId()) != "1"){
                Party.put(player.getUniqueId(), "1");
                player.sendMessage("You have left the party!");
            } else {
                player.sendMessage("You're not in a party!");
            }
        }
        else if (cmd.getName().equalsIgnoreCase("party")) {
            if(Party.get(player.getUniqueId()) == player.getName()) {
                player.sendMessage("You are leading your own party!");
            } else if(Party.get(player.getUniqueId()) == null || Party.get(player.getUniqueId()) == "1"){
                player.sendMessage("You are not in a party!");
            } else {
                player.sendMessage("You are in " + Party.get(player.getUniqueId()) + "'s party!");
            }
        }

        return true;
    }
}