package com.magister.greekorigins.events.greekdemigodevents;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;

public class ChildOfAphrodite implements Listener {
    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 2400, 0, true, false, true);
    private static final PotionEffect tempCooldown = new PotionEffect(PotionEffectType.LUCK, 500, 0, true, false, true);

    private static final PotionEffect slow = new PotionEffect(PotionEffectType.SLOW, 100, 255, true, false, true);
    private static final PotionEffect stopjump = new PotionEffect(PotionEffectType.JUMP, 100, 200, true, false, true);
    private static final PotionEffect slowDig = new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 255, true, false, true);

    @EventHandler
    public static void reTargetEvent(EntityDamageByEntityEvent event){
        Player player = (Player) event.getEntity();
        if(GodlyParent.get(player.getName()).equals("Aphrodite")){
            List<Entity> players = player.getNearbyEntities(10, 20, 10);
            Location loc;
            for (Entity i : players) {
                int p = players.indexOf(i);
                ((Mob) i).setTarget((LivingEntity) event.getDamager());
            }
        }
    }

    @EventHandler
    public static void playerLeftClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.isSneaking()) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                if (GodlyParent.get(player.getName()).equals("Aphrodite")) {
                    if (event.getItem() == null) {
                        if (!(player.hasPotionEffect(cooldown.getType()))) {
                            if (PlayerLevel.get(player.getName()) >= 50) {
                                List<Entity> players = player.getNearbyEntities(30, 5, 30);
                                for (Entity i : players) {
                                    ((LivingEntity) i).addPotionEffect(slow);
                                    ((LivingEntity) i).addPotionEffect(stopjump);
                                    ((LivingEntity) i).addPotionEffect(slowDig);
                                    ((LivingEntity) i).addPotionEffect(tempCooldown);
                                }
                                PlayerLevel.put(player.getName(), PlayerLevel.get(player.getName()) + 0.5);
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getName()) >= 40) {
                                List<Entity> players = player.getNearbyEntities(20, 5, 20);
                                for (Entity i : players) {
                                    ((LivingEntity) i).addPotionEffect(slow);
                                    ((LivingEntity) i).addPotionEffect(stopjump);
                                    ((LivingEntity) i).addPotionEffect(slowDig);
                                    ((LivingEntity) i).addPotionEffect(tempCooldown);
                                }
                                PlayerLevel.put(player.getName(), PlayerLevel.get(player.getName()) + 0.5);
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getName()) >= 30) {
                                List<Entity> players = player.getNearbyEntities(20, 5, 20);
                                for (Entity i : players) {
                                    ((LivingEntity) i).addPotionEffect(slow);
                                    ((LivingEntity) i).addPotionEffect(stopjump);
                                    ((LivingEntity) i).addPotionEffect(slowDig);
                                    ((LivingEntity) i).addPotionEffect(tempCooldown);
                                }
                                PlayerLevel.put(player.getName(), PlayerLevel.get(player.getName()) + 0.5);
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getName()) >= 20) {
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 10))).addPotionEffect(slow);
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 10))).addPotionEffect(stopjump);
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 10))).addPotionEffect(slowDig);
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 10))).addPotionEffect(tempCooldown);
                                PlayerLevel.put(player.getName(), PlayerLevel.get(player.getName()) + 0.5);
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getName()) >= 10) {
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 10))).addPotionEffect(slow);
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 10))).addPotionEffect(stopjump);
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 10))).addPotionEffect(slowDig);
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 10))).addPotionEffect(tempCooldown);
                                PlayerLevel.put(player.getName(), PlayerLevel.get(player.getName()) + 0.5);
                                player.addPotionEffect(cooldown);
                            }
                         }
                    }
                }
            }
        }
    }

    public static Entity getNearestEntityInSight(Player player, int range) {
        ArrayList<Entity> entities = (ArrayList<Entity>) player.getNearbyEntities(range, range, range);
        ArrayList<Block> sightBlock = (ArrayList<Block>) player.getLineOfSight(null, range);
        ArrayList<Location> sight = new ArrayList<>();
        for (Block block : sightBlock) sight.add(block.getLocation());
        for (Location location : sight) {
            for (Entity entity : entities) {
                if (Math.abs(entity.getLocation().getX() - location.getX()) < 1.3) {
                    if (Math.abs(entity.getLocation().getY() - location.getY()) < 1.5) {
                        if (Math.abs(entity.getLocation().getZ() - location.getZ()) < 1.3) {
                            if(entity.getType() != EntityType.SHULKER_BULLET && entity.getType() != EntityType.DROPPED_ITEM && entity.getType() != EntityType.ITEM_FRAME && entity.getType() != EntityType.ARROW && entity.getType() != EntityType.WITHER_SKULL && entity.getType() != EntityType.SNOWBALL && entity.getType() != EntityType.EGG && entity.getType() != EntityType.BOAT) {
                                return entity;
                            }
                        }
                    }
                }
            }
        }
        return null; //Return null/nothing if no entity was found
    }
}