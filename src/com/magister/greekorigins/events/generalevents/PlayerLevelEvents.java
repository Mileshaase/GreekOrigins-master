package com.magister.greekorigins.events.generalevents;

import com.magister.greekorigins.files.CustomConfig;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerLevelEvents implements Listener {
    public static HashMap<UUID, Double> PlayerLevel = new HashMap<UUID, Double>();

    @EventHandler
    public static void playerKill(PlayerDeathEvent event){
        Player player = event.getEntity().getKiller();
        assert player != null;
        if (PlayerLevel.get(player.getUniqueId()) >= 50) {
            PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.5);
        } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
            PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.7);
        } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
            PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.7);
        } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
            PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.8);
        } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
            PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.9);
        } else if (PlayerLevel.get(player.getUniqueId()) < 10) {
            PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 1);
        }
        CustomConfig.get().addDefault(String.valueOf(player.getUniqueId() + " level:"), PlayerLevel.get(player.getUniqueId()));
        if(PlayerLevel.get(player.getUniqueId()) == 10){
            player.sendTitle("You're Level", "10", 50, 50, 50);
        }
        else if(PlayerLevel.get(player.getUniqueId()) == 20){
            player.sendTitle("You're Level", "20", 50, 50, 50);
        }
        else if(PlayerLevel.get(player.getUniqueId()) == 30){
            player.sendTitle("You're Level", "30", 50, 50, 50);
        }
        else if(PlayerLevel.get(player.getUniqueId()) == 40){
            player.sendTitle("You're Level", "40", 50, 50, 50);
        }
        else if(PlayerLevel.get(player.getUniqueId()) == 50){
            player.sendTitle("You're Level", "50", 50, 50, 50);
        }
    }

    @EventHandler
    public static void mobKill(EntityDeathEvent event) {
        Entity mob = event.getEntity();
        Player player = event.getEntity().getKiller();
        assert player != null;
        if (mob.getType().equals(EntityType.COW) || mob.getType().equals(EntityType.PIG) || mob.getType().equals(EntityType.SHEEP) || mob.getType().equals(EntityType.CHICKEN) || mob.getType().equals(EntityType.DONKEY) || mob.getType().equals(EntityType.HORSE)) {
            if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.1);
            } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.2);
            } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.3);
            } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.4);
            } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
                PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.5);
            } else if (PlayerLevel.get(player.getUniqueId()) < 10) {
                PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.6);
            }
        }
        if (mob.getType().equals(EntityType.SKELETON) || mob.getType().equals(EntityType.ZOMBIE) || mob.getType().equals(EntityType.HUSK) || mob.getType().equals(EntityType.STRAY) || mob.getType().equals(EntityType.CREEPER) || mob.getType().equals(EntityType.SPIDER) || mob.getType().equals(EntityType.CAVE_SPIDER) || mob.getType().equals(EntityType.DROWNED)) {
            if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.2);
            } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.3);
            } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.4);
            } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.5);
            } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
                PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.6);
            } else if (PlayerLevel.get(player.getUniqueId()) < 10) {
                PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.7);
            }
        }
        CustomConfig.get().addDefault(String.valueOf(player.getUniqueId() + " level:"), PlayerLevel.get(player.getUniqueId()));
        if(PlayerLevel.get(player.getUniqueId()) == 10){
            player.sendTitle("You're Level", "10", 50, 50, 50);
        }
        else if(PlayerLevel.get(player.getUniqueId()) == 20){
            player.sendTitle("You're Level", "20", 50, 50, 50);
        }
        else if(PlayerLevel.get(player.getUniqueId()) == 30){
            player.sendTitle("You're Level", "30", 50, 50, 50);
        }
        else if(PlayerLevel.get(player.getUniqueId()) == 40){
            player.sendTitle("You're Level", "40", 50, 50, 50);
        }
        else if(PlayerLevel.get(player.getUniqueId()) == 50){
            player.sendTitle("You're Level", "50", 50, 50, 50);
        }
    }
}