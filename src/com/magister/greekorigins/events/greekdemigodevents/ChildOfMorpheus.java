package com.magister.greekorigins.events.greekdemigodevents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.PlayerParties.Party;
import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;


public class ChildOfMorpheus implements Listener {

    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 1200, 0, true, false, true);


    @EventHandler
    public static void timeSetNight(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.isSneaking()) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                if (GodlyParent.get(player.getUniqueId()).equals("Morpheus")) {
                    if (event.getItem() == null) {
                        if (!(player.hasPotionEffect(cooldown.getType()))) {

                        }
                    }
                }
            }
        }
    }
}