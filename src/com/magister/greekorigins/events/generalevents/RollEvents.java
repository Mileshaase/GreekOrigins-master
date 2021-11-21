package com.magister.greekorigins.events.generalevents;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class RollEvents implements Listener {
    public static HashMap<UUID, String> GodlyParent = new HashMap<>();
    public static HashMap<UUID, Integer> NumberOfRolls = new HashMap<>();

    public static void RandomGreekRoll(Player player){
        Random rand = new Random();
        int chosenGod = rand.nextInt(100);

        if(chosenGod == 1){
            GodlyParent.put(player.getUniqueId(), "Chronos");
        }
        if(chosenGod >= 1 && chosenGod <= 2){
            GodlyParent.put(player.getUniqueId(), "Zeus");
        }
        if(chosenGod >= 2 && chosenGod <= 3){
            GodlyParent.put(player.getUniqueId(), "Poseidon");
        }
        if(chosenGod >= 3 && chosenGod <= 4){
            GodlyParent.put(player.getUniqueId(), "Hades");
        }
        if(chosenGod >= 10 && chosenGod <= 20){
            GodlyParent.put(player.getUniqueId(), "Aphrodite");
        }
        if(chosenGod >= 20  && chosenGod <= 30){
            GodlyParent.put(player.getUniqueId(), "Apollo");
        }
        if(chosenGod >= 30 && chosenGod <= 40){
            GodlyParent.put(player.getUniqueId(), "Ares");
        }
        if(chosenGod >= 40 && chosenGod <= 50){
            GodlyParent.put(player.getUniqueId(), "Artemis");
        }
        if(chosenGod >= 50 && chosenGod <= 60){
            GodlyParent.put(player.getUniqueId(), "Athena");
        }
        if(chosenGod >= 60 && chosenGod <= 70){
            GodlyParent.put(player.getUniqueId(), "Demeter");
        }
        if(chosenGod >= 70 && chosenGod <= 80){
            GodlyParent.put(player.getUniqueId(), "Dionysus");
        }
        if(chosenGod >= 80 && chosenGod <= 90){
            GodlyParent.put(player.getUniqueId(), "Hephaestus");
        }
        if(chosenGod >= 90 && chosenGod <= 98){
            GodlyParent.put(player.getUniqueId(), "Hermes");
        }
        player.sendTitle(ChatColor.DARK_PURPLE + "You're Godly Parent is ", ChatColor.GOLD + GodlyParent.get(player.getUniqueId()), 1, 50, 1);
        NumberOfRolls.put(player.getUniqueId(), NumberOfRolls.get(player.getUniqueId()) - 1);
    }
}
