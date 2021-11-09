package com.magister.greekorigins.events.generalevents;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class PlayerLevelEvents implements Listener {
    public static HashMap<String, Double> PlayerLevel = new HashMap<String, Double>();

    @EventHandler
    public static void onPlayerJoinFirstTime(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(!player.hasPlayedBefore()){
            PlayerLevel.put(player.getName(), 0d);
        }
    }

    @EventHandler
    public static void PlayerLevelNull(PlayerJoinEvent event){
        Player player = event.getPlayer();
        PlayerLevel.putIfAbsent(player.getName(), 0d);
    }

    @EventHandler
    public static void PlayerLevelNull(PlayerMoveEvent event){
        Player player = event.getPlayer();
        PlayerLevel.putIfAbsent(player.getName(), 0d);
    }

    @EventHandler
    public static void playerKill(PlayerDeathEvent event){
        Player player = event.getEntity().getKiller();
        assert player != null;
        if (PlayerLevel.get(player.getName()) >= 50) {
            PlayerLevel.put(player.getName(), PlayerLevel.get(player.getName()) + 0.5);
        } else if (PlayerLevel.get(player.getName()) >= 40) {
            PlayerLevel.put(player.getName(), PlayerLevel.get(player.getName()) + 0.7);
        } else if (PlayerLevel.get(player.getName()) >= 30) {
            PlayerLevel.put(player.getName(), PlayerLevel.get(player.getName()) + 0.7);
        } else if (PlayerLevel.get(player.getName()) >= 20) {
            PlayerLevel.put(player.getName(), PlayerLevel.get(player.getName()) + 0.8);
        } else if (PlayerLevel.get(player.getName()) >= 10) {
            PlayerLevel.put(player.getName(), PlayerLevel.get(player.getName()) + 0.9);
        } else if (PlayerLevel.get(player.getName()) < 10) {
            PlayerLevel.put(player.getName(), PlayerLevel.get(player.getName()) + 1);
        }
    }

    @EventHandler
    public static void mobKill(EntityDeathEvent event){
        Entity mob = event.getEntity();
        Player player = event.getEntity().getKiller();
        if(mob.getType().equals(EntityType.COW) || mob.getType().equals(EntityType.PIG) || mob.getType().equals(EntityType.SHEEP) || mob.getType().equals(EntityType.CHICKEN) || mob.getType().equals(EntityType.DONKEY) || mob.getType().equals(EntityType.HORSE)){
            assert player != null;
            PlayerLevel.put(player.getName(), PlayerLevel.get(player.getName()) + 0.2);
        }
        if(mob.getType().equals(EntityType.SKELETON) || mob.getType().equals(EntityType.ZOMBIE) || mob.getType().equals(EntityType.HUSK) || mob.getType().equals(EntityType.STRAY) || mob.getType().equals(EntityType.CREEPER) || mob.getType().equals(EntityType.SPIDER) || mob.getType().equals(EntityType.CAVE_SPIDER) || mob.getType().equals(EntityType.DROWNED)){
            assert player != null;
            PlayerLevel.put(player.getName(), PlayerLevel.get(player.getName()) + 0.4);
        }
    }
}