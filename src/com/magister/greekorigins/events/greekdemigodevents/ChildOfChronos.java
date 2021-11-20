package com.magister.greekorigins.events.greekdemigodevents;

import com.magister.greekorigins.GreekOrigins;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.PlayerParties.Party;
import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;

public class ChildOfChronos implements Listener {
    private static final PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW, 100, 255, true, false, true);
    private static final PotionEffect stopjump = new PotionEffect(PotionEffectType.JUMP, 100, 200, true, false, true);
    private static final PotionEffect slowdig = new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 255, true, false, true);

    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 1200, 0, true, false, true);

    @EventHandler
    public static void playerHitEntity(EntityDamageByEntityEvent event){
        Player player = (Player) event.getDamager();
        Random rand = new Random();
        if(GodlyParent.get(player.getUniqueId()).equals("Chronos")){
            int  n = rand.nextInt(100) + 1;
            if(PlayerLevel.get(player.getUniqueId()) > 10) {
                if (n <= 10){
                    List<Entity> players = player.getNearbyEntities(10, 10, 10);
                    for(Entity i : players){
                        ((LivingEntity) i).addPotionEffect(slowness);
                        ((LivingEntity) i).addPotionEffect(stopjump);
                        ((LivingEntity) i).addPotionEffect(slowdig);
                    }
                }
            }
        }
    }

    @EventHandler
    public static void teleportAttack(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.isSneaking()) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                if (GodlyParent.get(player.getUniqueId()).equals("Chronos")) {
                    if (event.getItem() == null) {
                        if (!(player.hasPotionEffect(cooldown.getType()))) {
                            Location loc;
                            Location start = player.getLocation();
                            if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                                List<Entity> players = player.getNearbyEntities(30, 30, 30);
                                for (Entity i : players) {
                                    loc = i.getLocation();
                                    player.teleport(loc);
                                    ((LivingEntity) i).damage(4);
                                    ((LivingEntity) i).addPotionEffect(slowness);
                                    ((LivingEntity) i).addPotionEffect(stopjump);
                                    ((LivingEntity) i).addPotionEffect(slowdig);
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            // What you want to schedule goes here

                                        }
                                    }.runTaskLater(GreekOrigins.getPlugin(GreekOrigins.class), 500);
                                }
                                player.teleport(start);
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                                List<Entity> players = player.getNearbyEntities(20, 20, 20);
                                for (Entity i : players) {
                                    loc = i.getLocation();
                                    player.teleport(loc);
                                    ((LivingEntity) i).damage(4);
                                    ((LivingEntity) i).addPotionEffect(slowness);
                                    ((LivingEntity) i).addPotionEffect(stopjump);
                                    ((LivingEntity) i).addPotionEffect(slowdig);
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            // What you want to schedule goes here

                                        }
                                    }.runTaskLater(GreekOrigins.getPlugin(GreekOrigins.class), 500);
                                }
                                player.teleport(start);
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                                List<Entity> players = player.getNearbyEntities(15, 15, 15);
                                for (Entity i : players) {
                                    loc = i.getLocation();
                                    player.teleport(loc);
                                    ((LivingEntity) i).damage(4);
                                    ((LivingEntity) i).addPotionEffect(slowness);
                                    ((LivingEntity) i).addPotionEffect(stopjump);
                                    ((LivingEntity) i).addPotionEffect(slowdig);
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            // What you want to schedule goes here
                                            player.teleport(start);
                                        }
                                    }.runTaskLater(GreekOrigins.getPlugin(GreekOrigins.class), 10);
                                }
                                player.teleport(start);
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                                Entity opponent = getNearestEntityInSight(player, 25);
                                assert opponent != null;
                                player.teleport(opponent.getLocation());
                                ((LivingEntity) opponent).damage(4);
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        // What you want to schedule goes here
                                        player.addPotionEffect(cooldown);
                                        player.teleport(start);
                                    }
                                }.runTaskLater(GreekOrigins.getPlugin(GreekOrigins.class), 10);
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
                                Entity opponent = getNearestEntityInSight(player, 15);
                                assert opponent != null;
                                player.teleport(opponent.getLocation());
                                ((LivingEntity) opponent).damage(4);
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        // What you want to schedule goes here
                                        player.addPotionEffect(cooldown);
                                        player.teleport(start);
                                    }
                                }.runTaskLater(GreekOrigins.getPlugin(GreekOrigins.class), 10);
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
                                if (Party.get(player.getUniqueId()) != (Party.get(entity.getUniqueId())) || (Party.get(entity.getUniqueId()) == null) || (Party.get(entity.getUniqueId()) == "1")) {
                                    return entity;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null; //Return null/nothing if no entity was found
    }
}
