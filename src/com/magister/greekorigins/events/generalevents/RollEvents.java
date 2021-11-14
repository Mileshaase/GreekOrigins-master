package com.magister.greekorigins.events.generalevents;

import com.magister.greekorigins.files.CustomConfig;
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
        int chosenGod = rand.nextInt(731);

        if(chosenGod == 1){
            GodlyParent.put(player.getUniqueId(), "Chronos");
        }
        if(chosenGod >= 11){
            GodlyParent.put(player.getUniqueId(), "Zeus");
        }
        if(chosenGod >= 21){
            GodlyParent.put(player.getUniqueId(), "Poseidon");
        }
        if(chosenGod >= 31){
            GodlyParent.put(player.getUniqueId(), "Hades");
        }
        if(chosenGod >= 101){
            GodlyParent.put(player.getUniqueId(), "Aphrodite");
        }
        if(chosenGod >= 171){
            GodlyParent.put(player.getUniqueId(), "Apollo");
        }
        if(chosenGod >= 241){
            GodlyParent.put(player.getUniqueId(), "Ares");
        }
        if(chosenGod >= 311){
            GodlyParent.put(player.getUniqueId(), "Artemis");
        }
        if(chosenGod >= 381){
            GodlyParent.put(player.getUniqueId(), "Athena");
        }
        if(chosenGod >= 451){
            GodlyParent.put(player.getUniqueId(), "Demeter");
        }
        if(chosenGod >= 521){
            GodlyParent.put(player.getUniqueId(), "Dionysus");
        }
        if(chosenGod >= 591){
            GodlyParent.put(player.getUniqueId(), "Hephaestus");
        }
        if(chosenGod >= 661){
            GodlyParent.put(player.getUniqueId(), "Hermes");
        }
        player.sendTitle(ChatColor.DARK_PURPLE + "You're Godly Parent is ", ChatColor.GOLD + GodlyParent.get(player.getUniqueId()), 1, 50, 1);
        NumberOfRolls.put(player.getUniqueId(), NumberOfRolls.get(player.getUniqueId()) - 1);
        CustomConfig.get().addDefault(String.valueOf(player.getUniqueId() + " parent:"), GodlyParent.get(player.getUniqueId()));
        CustomConfig.get().addDefault(String.valueOf(player.getUniqueId() + " rolls:"), NumberOfRolls.get(player.getUniqueId()));
    }
}
