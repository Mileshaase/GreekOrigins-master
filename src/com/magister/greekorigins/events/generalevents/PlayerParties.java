package com.magister.greekorigins.events.generalevents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.Objects;

public class PlayerParties implements Listener {
    public static HashMap<String, String> Party = new HashMap<>();
    public static HashMap<String, String> PartyLeaders = new HashMap<>();

    @EventHandler
    public static void friendlyFire (EntityDamageByEntityEvent event){
        Player player = (Player) event.getDamager();
        Player attacked = (Player) event.getEntity();
        if(Objects.equals(Party.get(player.getName()), Party.get(attacked.getName()))) {
            event.setCancelled(true);
        }
    }
}
