package com.magister.greekorigins.events.generalevents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class PlayerParties implements Listener {
    public static HashMap<UUID, String> Party = new HashMap<>();
    public static HashMap<UUID, String> PartyLeaders = new HashMap<>();

    @EventHandler
    public static void friendlyFire (EntityDamageByEntityEvent event){
        Player player = (Player) event.getDamager();
        Player attacked = (Player) event.getEntity();
        if(Objects.equals(Party.get(player.getUniqueId()), Party.get(attacked.getUniqueId()))) {
            event.setCancelled(true);
        }
    }
}
