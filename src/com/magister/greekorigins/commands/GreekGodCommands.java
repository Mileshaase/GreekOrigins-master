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
                    GodlyParent.put(impactedPlayer.getName(), "Zeus");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getName()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getName(), "Zeus");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getName()));
            }
        } else if (cmd.getName().equalsIgnoreCase("poseidon")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getName(), "Poseidon");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getName()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getName(), "Poseidon");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getName()));
            }
        } else if (cmd.getName().equalsIgnoreCase("hades")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getName(), "Hades");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getName()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getName(), "Hades");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getName()));
            }
        } else if (cmd.getName().equalsIgnoreCase("aphrodite")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getName(), "Aphrodite");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getName()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getName(), "Aphrodite");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getName()));
            }
        } else if (cmd.getName().equalsIgnoreCase("apollo")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getName(), "Apollo");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getName()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getName(), "Apollo");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getName()));
            }
        } else if (cmd.getName().equalsIgnoreCase("ares")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getName(), "Ares");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getName()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getName(), "Ares");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getName()));
            }
        } else if (cmd.getName().equalsIgnoreCase("artemis")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getName(), "Artemis");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getName()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getName(), "Artemis");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getName()));
            }
        } else if (cmd.getName().equalsIgnoreCase("athena")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getName(), "Athena");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getName()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getName(), "Athena");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getName()));
            }
        } else if (cmd.getName().equalsIgnoreCase("demeter")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getName(), "Demeter");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getName()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getName(), "Demeter");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getName()));
            }
        } else if (cmd.getName().equalsIgnoreCase("dionysus")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getName(), "Dionysus");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getName()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getName(), "Dionysus");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getName()));
            }
        } else if (cmd.getName().equalsIgnoreCase("hephaestus")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getName(), "Hephaestus");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getName()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getName(), "Hephaestus");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getName()));
            }
        } else if (cmd.getName().equalsIgnoreCase("hermes")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getName(), "Hermes");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getName()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getName(), "Hermes");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getName()));
            }
        } else if (cmd.getName().equalsIgnoreCase("chronos")) {
            if (args.length >= 1) {
                try {
                    Player impactedPlayer = Bukkit.getPlayerExact(args[0]);
                    assert impactedPlayer != null;
                    GodlyParent.put(impactedPlayer.getName(), "Chronos");
                    impactedPlayer.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(impactedPlayer.getName()));
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That's not a valid player");
                }
            } else {
                GodlyParent.put(player.getName(), "Chronos");
                player.sendMessage(ChatColor.DARK_PURPLE + "Your Godly Parent is Now " + GodlyParent.get(player.getName()));
            }
        }

        return true;
    }
}
