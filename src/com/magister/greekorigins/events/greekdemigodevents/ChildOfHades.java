package com.magister.greekorigins.events.greekdemigodevents;

import com.magister.greekorigins.GreekOrigins;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.PlayerParties.Party;
import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;

public class ChildOfHades implements Listener {

    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 1200, 0, true, false, true);
    private static final PotionEffect wither = new PotionEffect(PotionEffectType.WITHER, 100, 1, true, false, true);

    @EventHandler
    public void onEntityTargetLivingEntity(EntityTargetLivingEntityEvent event) {
        Player player  = (Player) event.getTarget();
        Entity entity = event.getEntity();
        assert player != null;
        if (GodlyParent.get(player.getUniqueId()).equals("Hades")) {
            event.setCancelled(true);
        }
        if(Objects.equals(entity.getCustomName(), "Zambie")){
            if(Objects.equals(Objects.requireNonNull(event.getTarget()).getCustomName(), "Zambie")){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public static void playerHitEntity(EntityDamageByEntityEvent event){
        Player player = (Player) event.getDamager();
        Entity attacked = event.getEntity();
        Random rand = new Random();
        if(GodlyParent.get(player.getUniqueId()).equals("Hades")){
            int  n = rand.nextInt(100) + 1;
            if(PlayerLevel.get(player.getUniqueId()) > 0) {
                if (n <= 10){
                    if (Party.get(player.getUniqueId()) != (Party.get(attacked.getUniqueId())) || (Party.get(attacked.getUniqueId()) == null) || (Party.get(attacked.getUniqueId()) == "1")) {
                        ((LivingEntity) attacked).addPotionEffect(wither);
                    }
                }
            }
        }
    }

    @EventHandler
    public static void playerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(player.isSneaking()){
            if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (GodlyParent.get(player.getUniqueId()).equals("Hades")) {
                    if (event.getItem() == null) {
                        if (!(player.hasPotionEffect(cooldown.getType()))) {
                            ArrayList<Entity> zombies = new ArrayList<>();
                            int troops = 0;
                            Location loc1 = player.getLocation();
                            Location loc2 = player.getLocation();
                            Location loc3 = player.getLocation();
                            Location loc4 = player.getLocation();
                            if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                                troops = 4;
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                                troops = 3;
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                                troops = 2;
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                                troops = 1;
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 10){
                                loc1.setX(player.getLocation().getX() - 2);
                                loc1.setZ(player.getLocation().getZ() - 2);
                                Entity zombie1 = player.getWorld().spawnEntity(loc1, EntityType.HUSK);
                                ((Husk) zombie1).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                zombies.add(zombie1);

                                loc2.setX(player.getLocation().getX() + 2);
                                loc2.setZ(player.getLocation().getZ() - 2);
                                Entity zombie2 = player.getWorld().spawnEntity(loc2, EntityType.HUSK);
                                ((Husk) zombie2).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                zombies.add(zombie2);

                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        // What you want to schedule goes here
                                        for(Entity z : zombies){
                                            ((Husk) z).setHealth(0.0);
                                        }
                                    }
                                }.runTaskLater(GreekOrigins.getPlugin(GreekOrigins.class), 500);
                            }
                            for (int i = 0; i < troops; i++) {
                                loc1.setX(player.getLocation().getX() - 2);
                                loc1.setZ(player.getLocation().getZ() - 2);
                                Entity zombie1 = player.getWorld().spawnEntity(loc1, EntityType.HUSK);
                                ((Husk) zombie1).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                zombies.add(zombie1);

                                loc2.setX(player.getLocation().getX() + 2);
                                loc2.setZ(player.getLocation().getZ() - 2);
                                Entity zombie2 = player.getWorld().spawnEntity(loc2, EntityType.HUSK);
                                ((Husk) zombie2).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                zombies.add(zombie2);

                                loc3.setX(player.getLocation().getX() - 2);
                                loc3.setZ(player.getLocation().getZ() + 2);
                                Entity zombie3 = player.getWorld().spawnEntity(loc3, EntityType.HUSK);
                                ((Husk) zombie3).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                zombies.add(zombie3);

                                loc4.setX(player.getLocation().getX() + 2);
                                loc4.setZ(player.getLocation().getZ() + 2);
                                Entity zombie4 = player.getWorld().spawnEntity(loc4, EntityType.HUSK);
                                ((Husk) zombie4).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                zombies.add(zombie4);
                            }
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    // What you want to schedule goes here
                                    for(Entity z : zombies){
                                        ((Husk) z).setHealth(0.0);
                                    }
                                }
                            }.runTaskLater(GreekOrigins.getPlugin(GreekOrigins.class), 500);
                            player.addPotionEffect(cooldown);
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
                            if(entity.getType() != EntityType.SHULKER_BULLET && entity.getType() != EntityType.DROPPED_ITEM && entity.getType() != EntityType.ITEM_FRAME && entity.getType() != EntityType.ARROW && entity.getType() != EntityType.WITHER_SKULL && entity.getType() != EntityType.SNOWBALL && entity.getType() != EntityType.EGG && entity.getType() != EntityType.BOAT && !Objects.equals(entity.getCustomName(), "Zambie")) {
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