package com.magister.greekorigins.events.generalevents;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Random;

public class RollEvents implements Listener {
    public static HashMap<String, String> GodlyParent = new HashMap<String, String>();
    public static HashMap<String, String> HomeLocation = new HashMap<String, String>();
    public static HashMap<String, Integer> NumberOfRolls = new HashMap<>();

    public static void RandomGreekRoll(Player player){
        Random rand = new Random();
        int chosenGod = chosenGod = rand.nextInt(731);

        if(chosenGod == 1){
            GodlyParent.put(player.getName(), "Chronos");
        }
        if(chosenGod >= 11){
            GodlyParent.put(player.getName(), "Zeus");
        }
        if(chosenGod >= 21){
            GodlyParent.put(player.getName(), "Poseidon");
        }
        if(chosenGod >= 31){
            GodlyParent.put(player.getName(), "Hades");
        }
        if(chosenGod >= 101){
            GodlyParent.put(player.getName(), "Aphrodite");
        }
        if(chosenGod >= 171){
            GodlyParent.put(player.getName(), "Apollo");
        }
        if(chosenGod >= 241){
            GodlyParent.put(player.getName(), "Ares");
        }
        if(chosenGod >= 311){
            GodlyParent.put(player.getName(), "Artemis");
        }
        if(chosenGod >= 381){
            GodlyParent.put(player.getName(), "Athena");
        }
        if(chosenGod >= 451){
            GodlyParent.put(player.getName(), "Demeter");
        }
        if(chosenGod >= 521){
            GodlyParent.put(player.getName(), "Dionysus");
        }
        if(chosenGod >= 591){
            GodlyParent.put(player.getName(), "Hephaestus");
        }
        if(chosenGod >= 661){
            GodlyParent.put(player.getName(), "Hermes");
        }
        player.sendTitle(ChatColor.DARK_PURPLE + "You're Godly Parent is ", ChatColor.GOLD + GodlyParent.get(player.getName()), 1, 20, 1);
    }
}
