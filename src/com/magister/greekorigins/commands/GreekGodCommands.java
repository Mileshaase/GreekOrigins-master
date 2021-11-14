package com.magister.greekorigins.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;

public class GreekGodCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("zeus")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getUniqueId(), "Zeus");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getUniqueId()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getUniqueId(), "Zeus");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getUniqueId()));
            }
        } else if (cmd.getName().equalsIgnoreCase("poseidon")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getUniqueId(), "Poseidon");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getUniqueId()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getUniqueId(), "Poseidon");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getUniqueId()));
            }
        } else if (cmd.getName().equalsIgnoreCase("hades")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getUniqueId(), "Hades");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getUniqueId()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getUniqueId(), "Hades");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getUniqueId()));
            }
        } else if (cmd.getName().equalsIgnoreCase("aphrodite")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getUniqueId(), "Aphrodite");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getUniqueId()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getUniqueId(), "Aphrodite");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getUniqueId()));
            }
        } else if (cmd.getName().equalsIgnoreCase("apollo")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getUniqueId(), "Apollo");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getUniqueId()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getUniqueId(), "Apollo");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getUniqueId()));
            }
        } else if (cmd.getName().equalsIgnoreCase("ares")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getUniqueId(), "Ares");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getUniqueId()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getUniqueId(), "Ares");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getUniqueId()));
            }
        } else if (cmd.getName().equalsIgnoreCase("artemis")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getUniqueId(), "Artemis");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getUniqueId()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getUniqueId(), "Artemis");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getUniqueId()));
            }
        } else if (cmd.getName().equalsIgnoreCase("athena")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getUniqueId(), "Athena");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getUniqueId()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getUniqueId(), "Athena");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getUniqueId()));
            }
        } else if (cmd.getName().equalsIgnoreCase("demeter")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getUniqueId(), "Demeter");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getUniqueId()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getUniqueId(), "Demeter");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getUniqueId()));
            }
        } else if (cmd.getName().equalsIgnoreCase("dionysus")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getUniqueId(), "Dionysus");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getUniqueId()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getUniqueId(), "Dionysus");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getUniqueId()));
            }
        } else if (cmd.getName().equalsIgnoreCase("hephaestus")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getUniqueId(), "Hephaestus");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getUniqueId()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getUniqueId(), "Hephaestus");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getUniqueId()));
            }
        } else if (cmd.getName().equalsIgnoreCase("hermes")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getUniqueId(), "Hermes");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getUniqueId()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getUniqueId(), "Hermes");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getUniqueId()));
            }
        } else if (cmd.getName().equalsIgnoreCase("chronos")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getUniqueId(), "Chronos");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getUniqueId()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getUniqueId(), "Chronos");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getUniqueId()));
            }
        }

        return true;
    }
}
