package com.magister.greekorigins.events.generalevents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerParties implements Listener {
    public static HashMap<UUID, String> Party = new HashMap<>();
    public static HashMap<UUID, String> PartyLeaders = new HashMap<>();

    @EventHandler
    public static void nullParty (PlayerMoveEvent event){
        Player player = event.getPlayer();
        Party.putIfAbsent(player.getUniqueId(), "1");
    }
}
