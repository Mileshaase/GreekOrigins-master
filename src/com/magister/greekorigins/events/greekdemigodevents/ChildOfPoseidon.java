package com.magister.greekorigins.events.greekdemigodevents;

import com.magister.greekorigins.GreekOrigins;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;

public class ChildOfPoseidon implements Listener {
    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 2400, 0, true, false, true);

    private static final PotionEffect waterBreathing = new PotionEffect(PotionEffectType.WATER_BREATHING, 100, 255, true, false, false);
    private static final PotionEffect dolphinsGrace = new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 100, 255, true, false, false);
    private static final PotionEffect conduitPower = new PotionEffect(PotionEffectType.CONDUIT_POWER, 100, 255, true, false, false);
    private static final PotionEffect regeneration = new PotionEffect(PotionEffectType.REGENERATION, 100, 1, true, false, false);
    private static final PotionEffect strength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 2, true, false, false);
    private static final PotionEffect levitate = new PotionEffect(PotionEffectType.LEVITATION, 5, 50, true, false, true);

    @EventHandler
    public void onEntityTargetLivingEntity(EntityTargetLivingEntityEvent event) {
        Player player  = (Player) event.getTarget();
        Entity entity = event.getEntity();
        assert player != null;
        if (GodlyParent.get(player.getName()).equals("Poseidon")) {
            if(entity.getType() == EntityType.DROWNED || entity.getType() == EntityType.GUARDIAN || entity.getType() == EntityType.ELDER_GUARDIAN){
                event.setCancelled(true);
            }
        }
        if(entity.getType() == EntityType.GUARDIAN || entity.getType() == EntityType.ELDER_GUARDIAN){
            if(player.getType() == EntityType.DROWNED){
                event.setCancelled(true);
            }
        }
        if(player.getType() == EntityType.DROWNED){
            if(entity.getType() == EntityType.GUARDIAN || entity.getType() == EntityType.ELDER_GUARDIAN){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public static void playerInWater(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(GodlyParent.get(player.getName()).equals("Poseidon")) {
            if (player.isInWater()) {
                if (PlayerLevel.get(player.getName()) >= 40) {
                    player.addPotionEffect(waterBreathing);
                    player.addPotionEffect(dolphinsGrace);
                    player.addPotionEffect(conduitPower);
                    player.addPotionEffect(strength);
                    player.addPotionEffect(regeneration);
                } else if (PlayerLevel.get(player.getName()) >= 20) {
                    player.addPotionEffect(waterBreathing);
                    player.addPotionEffect(dolphinsGrace);
                    player.addPotionEffect(conduitPower);
                    player.addPotionEffect(regeneration);
                } else if (PlayerLevel.get(player.getName()) >= 0) {
                    player.addPotionEffect(waterBreathing);
                    player.addPotionEffect(dolphinsGrace);
                    player.addPotionEffect(conduitPower);
                }

                List<Entity> players = player.getNearbyEntities(100, 100, 100);
                for (Entity i : players) {
                    if(i.getType().equals(EntityType.DOLPHIN)){
                        ((Dolphin) i).setTarget(player);
                    }
                }
            }

        }
    }

    @EventHandler
    public static void playerHitEntity(EntityDamageByEntityEvent event){
        Player player = (Player) event.getDamager();
        Entity attacked = event.getEntity();
        Random rand = new Random();
        Location loc = attacked.getLocation();
        loc.setY(loc.getY() - 0.5);
        if(GodlyParent.get(player.getName()).equals("Poseidon")){
            int  n = rand.nextInt(100) + 1;
            if(PlayerLevel.get(player.getName()) >= 0) {
                if (n <= 10){
                    attacked.getWorld().createExplosion(loc, 2, false, false);
                    attacked.getWorld().spawnParticle(Particle.FALLING_DUST, attacked.getLocation(), 100);
                    ((LivingEntity) attacked).addPotionEffect(levitate);
                }
            }
        }
    }

    @EventHandler
    public static void playerShiftRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (GodlyParent.get(player.getName()).equals("Poseidon")) {
            if (player.isSneaking()) {
                if (event.getAction() == Action.LEFT_CLICK_AIR) {
                    if (event.getItem() == null) {
                        ArrayList<Entity> drowned = new ArrayList<>();
                        int troops = 0;
                        Location loc = player.getLocation();
                        loc.setY(loc.getY() + 5);
                        Location loc1 = player.getLocation();
                        Location loc2 = player.getLocation();
                        Location loc3 = player.getLocation();
                        Location loc4 = player.getLocation();
                        if (!(player.hasPotionEffect(cooldown.getType()))) {
                            if (PlayerLevel.get(player.getName()) >= 50) {
                                troops = 5;
                                ArrayList<Block> blocks = getNearbyBlocks(player.getLocation(), 25, 25);
                                Entity guardian = player.getWorld().spawnEntity(loc, EntityType.ELDER_GUARDIAN);
                                drowned.add(guardian);
                                for (int i = 0; i < troops; i++) {
                                    loc1.setX(player.getLocation().getX() - 2);
                                    loc1.setZ(player.getLocation().getZ() - 2);
                                    Entity drowned1 = player.getWorld().spawnEntity(loc1, EntityType.DROWNED);
                                    ((Drowned) drowned1).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                    drowned.add(drowned1);

                                    loc2.setX(player.getLocation().getX() + 2);
                                    loc2.setZ(player.getLocation().getZ() - 2);
                                    Entity drowned2 = player.getWorld().spawnEntity(loc2, EntityType.DROWNED);
                                    ((Drowned) drowned2).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                    drowned.add(drowned2);

                                    loc3.setX(player.getLocation().getX() - 2);
                                    loc3.setZ(player.getLocation().getZ() + 2);
                                    Entity drowned3 = player.getWorld().spawnEntity(loc3, EntityType.DROWNED);
                                    ((Drowned) drowned3).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                    drowned.add(drowned3);

                                    loc4.setX(player.getLocation().getX() + 2);
                                    loc4.setZ(player.getLocation().getZ() + 2);
                                    Entity drowned4 = player.getWorld().spawnEntity(loc4, EntityType.DROWNED);
                                    ((Drowned) drowned4).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                    drowned.add(drowned4);
                                }
                                for (Block b : blocks) {
                                    if (b.getType() == Material.AIR || b.getType() == Material.DEAD_BUSH || b.getType() == Material.VINE || b.getType() == Material.FERN || b.getType() == Material.LARGE_FERN || b.getType() == Material.GRASS || b.getType() == Material.TALL_GRASS || b.getType() == Material.CORNFLOWER || b.getType() == Material.ALLIUM || b.getType() == Material.AZURE_BLUET || b.getType() == Material.BLUE_ORCHID || b.getType() == Material.BROWN_MUSHROOM || b.getType() == Material.CACTUS|| b.getType() == Material.DANDELION || b.getType() == Material.POPPY || b.getType() == Material.AZURE_BLUET || b.getType() == Material.ORANGE_TULIP || b.getType() == Material.PINK_TULIP || b.getType() == Material.RED_TULIP || b.getType() == Material.WHITE_TULIP || b.getType() == Material.OXEYE_DAISY|| b.getType() == Material.LILY_OF_THE_VALLEY || b.getType() == Material.LILAC || b.getType() == Material.ROSE_BUSH || b.getType() == Material.PEONY) {
                                        b.setType(Material.WATER);
                                        new BukkitRunnable() {
                                            @Override
                                            public void run() {
                                                // What you want to schedule goes here
                                                b.setType(Material.AIR);
                                                for(Entity z : drowned){
                                                    ((Drowned) z).setHealth(0.0);
                                                    ((ElderGuardian) z).setHealth(0.0);
                                                }
                                            }
                                        }.runTaskLater(GreekOrigins.getPlugin(GreekOrigins.class), 1000);
                                    }
                                }
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getName()) >= 40) {
                                troops = 4;
                                ArrayList<Block> blocks = getNearbyBlocks(player.getLocation(), 20, 20);
                                Entity guardian = player.getWorld().spawnEntity(loc, EntityType.GUARDIAN);
                                for (int i = 0; i < troops; i++) {
                                    loc1.setX(player.getLocation().getX() - 2);
                                    loc1.setZ(player.getLocation().getZ() - 2);
                                    Entity drowned1 = player.getWorld().spawnEntity(loc1, EntityType.DROWNED);
                                    ((Drowned) drowned1).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                    drowned.add(drowned1);

                                    loc2.setX(player.getLocation().getX() + 2);
                                    loc2.setZ(player.getLocation().getZ() - 2);
                                    Entity drowned2 = player.getWorld().spawnEntity(loc2, EntityType.DROWNED);
                                    ((Drowned) drowned2).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                    drowned.add(drowned2);

                                    loc3.setX(player.getLocation().getX() - 2);
                                    loc3.setZ(player.getLocation().getZ() + 2);
                                    Entity drowned3 = player.getWorld().spawnEntity(loc3, EntityType.DROWNED);
                                    ((Drowned) drowned3).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                    drowned.add(drowned3);

                                    loc4.setX(player.getLocation().getX() + 2);
                                    loc4.setZ(player.getLocation().getZ() + 2);
                                    Entity drowned4 = player.getWorld().spawnEntity(loc4, EntityType.DROWNED);
                                    ((Drowned) drowned4).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                    drowned.add(drowned4);
                                }
                                for (Block b : blocks) {
                                    if (b.getType() == Material.AIR || b.getType() == Material.DEAD_BUSH  || b.getType() == Material.VINE  || b.getType() == Material.FERN || b.getType() == Material.LARGE_FERN  || b.getType() == Material.GRASS || b.getType() == Material.TALL_GRASS || b.getType() == Material.CORNFLOWER || b.getType() == Material.ALLIUM || b.getType() == Material.AZURE_BLUET || b.getType() == Material.BLUE_ORCHID || b.getType() == Material.BROWN_MUSHROOM || b.getType() == Material.CACTUS|| b.getType() == Material.DANDELION || b.getType() == Material.POPPY || b.getType() == Material.AZURE_BLUET || b.getType() == Material.ORANGE_TULIP || b.getType() == Material.PINK_TULIP || b.getType() == Material.RED_TULIP || b.getType() == Material.WHITE_TULIP || b.getType() == Material.OXEYE_DAISY|| b.getType() == Material.LILY_OF_THE_VALLEY || b.getType() == Material.LILAC || b.getType() == Material.ROSE_BUSH || b.getType() == Material.PEONY) {
                                        b.setType(Material.WATER);
                                        new BukkitRunnable() {
                                            @Override
                                            public void run() {
                                                // What you want to schedule goes here
                                                b.setType(Material.AIR);
                                                for(Entity z : drowned){
                                                    ((Drowned) z).setHealth(0.0);
                                                    ((ElderGuardian) z).setHealth(0.0);
                                                }
                                            }
                                        }.runTaskLater(GreekOrigins.getPlugin(GreekOrigins.class), 1000);
                                    }
                                }

                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getName()) >= 30) {
                                troops = 3;
                                ArrayList<Block> blocks = getNearbyBlocks(player.getLocation(), 15, 15);
                                Entity guardian = player.getWorld().spawnEntity(loc, EntityType.GUARDIAN);
                                for (int i = 0; i < troops; i++) {
                                    loc1.setX(player.getLocation().getX() - 2);
                                    loc1.setZ(player.getLocation().getZ() - 2);
                                    Entity drowned1 = player.getWorld().spawnEntity(loc1, EntityType.DROWNED);
                                    ((Drowned) drowned1).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                    drowned.add(drowned1);

                                    loc2.setX(player.getLocation().getX() + 2);
                                    loc2.setZ(player.getLocation().getZ() - 2);
                                    Entity drowned2 = player.getWorld().spawnEntity(loc2, EntityType.DROWNED);
                                    ((Drowned) drowned2).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                    drowned.add(drowned2);

                                    loc3.setX(player.getLocation().getX() - 2);
                                    loc3.setZ(player.getLocation().getZ() + 2);
                                    Entity drowned3 = player.getWorld().spawnEntity(loc3, EntityType.DROWNED);
                                    ((Drowned) drowned3).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                    drowned.add(drowned3);

                                    loc4.setX(player.getLocation().getX() + 2);
                                    loc4.setZ(player.getLocation().getZ() + 2);
                                    Entity drowned4 = player.getWorld().spawnEntity(loc4, EntityType.DROWNED);
                                    ((Drowned) drowned4).setTarget((LivingEntity) getNearestEntityInSight(player, 20));
                                    drowned.add(drowned4);
                                }
                                for (Block b : blocks) {
                                    if (b.getType() == Material.AIR || b.getType() == Material.DEAD_BUSH  || b.getType() == Material.VINE  || b.getType() == Material.FERN || b.getType() == Material.LARGE_FERN  || b.getType() == Material.GRASS || b.getType() == Material.TALL_GRASS || b.getType() == Material.CORNFLOWER || b.getType() == Material.ALLIUM || b.getType() == Material.AZURE_BLUET || b.getType() == Material.BLUE_ORCHID || b.getType() == Material.BROWN_MUSHROOM || b.getType() == Material.CACTUS|| b.getType() == Material.DANDELION || b.getType() == Material.POPPY || b.getType() == Material.AZURE_BLUET || b.getType() == Material.ORANGE_TULIP || b.getType() == Material.PINK_TULIP || b.getType() == Material.RED_TULIP || b.getType() == Material.WHITE_TULIP || b.getType() == Material.OXEYE_DAISY|| b.getType() == Material.LILY_OF_THE_VALLEY || b.getType() == Material.LILAC || b.getType() == Material.ROSE_BUSH || b.getType() == Material.PEONY) {
                                        b.setType(Material.WATER);
                                        new BukkitRunnable() {
                                            @Override
                                            public void run() {
                                                // What you want to schedule goes here
                                                b.setType(Material.AIR);
                                                for(Entity z : drowned){
                                                    ((Drowned) z).setHealth(0.0);
                                                    ((ElderGuardian) z).setHealth(0.0);
                                                }
                                            }
                                        }.runTaskLater(GreekOrigins.getPlugin(GreekOrigins.class), 1000);
                                    }
                                }

                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getName()) >= 20) {
                                ArrayList<Block> blocks = new ArrayList<>();
                                for(Entity e : player.getNearbyEntities(10, 10, 10)){
                                    blocks.addAll(getNearbyBlocks(e.getLocation(), 1, 2));
                                }
                                for(Block b : blocks){
                                    if (b.getType() == Material.AIR || b.getType() == Material.DEAD_BUSH  || b.getType() == Material.VINE  || b.getType() == Material.FERN || b.getType() == Material.LARGE_FERN  || b.getType() == Material.GRASS || b.getType() == Material.TALL_GRASS || b.getType() == Material.CORNFLOWER || b.getType() == Material.ALLIUM || b.getType() == Material.AZURE_BLUET || b.getType() == Material.BLUE_ORCHID || b.getType() == Material.BROWN_MUSHROOM || b.getType() == Material.CACTUS|| b.getType() == Material.DANDELION || b.getType() == Material.POPPY || b.getType() == Material.AZURE_BLUET || b.getType() == Material.ORANGE_TULIP || b.getType() == Material.PINK_TULIP || b.getType() == Material.RED_TULIP || b.getType() == Material.WHITE_TULIP || b.getType() == Material.OXEYE_DAISY|| b.getType() == Material.LILY_OF_THE_VALLEY || b.getType() == Material.LILAC || b.getType() == Material.ROSE_BUSH || b.getType() == Material.PEONY) {
                                        b.setType(Material.WATER);
                                        new BukkitRunnable() {
                                            @Override
                                            public void run() {
                                                // What you want to schedule goes here
                                                b.setType(Material.AIR);
                                            }
                                        }.runTaskLater(GreekOrigins.getPlugin(GreekOrigins.class), 40);
                                    }
                                }
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getName()) >= 10) {
                                ArrayList<Block> blocks = new ArrayList<>();
                                for(Entity e : player.getNearbyEntities(10, 10, 10)){
                                    blocks.addAll(getNearbyBlocks(e.getLocation(), 1, 2));
                                }
                                for(Block b : blocks){
                                    if (b.getType() == Material.AIR || b.getType() == Material.DEAD_BUSH  || b.getType() == Material.VINE  || b.getType() == Material.FERN || b.getType() == Material.LARGE_FERN  || b.getType() == Material.GRASS || b.getType() == Material.TALL_GRASS || b.getType() == Material.CORNFLOWER || b.getType() == Material.ALLIUM || b.getType() == Material.AZURE_BLUET || b.getType() == Material.BLUE_ORCHID || b.getType() == Material.BROWN_MUSHROOM || b.getType() == Material.CACTUS|| b.getType() == Material.DANDELION || b.getType() == Material.POPPY || b.getType() == Material.AZURE_BLUET || b.getType() == Material.ORANGE_TULIP || b.getType() == Material.PINK_TULIP || b.getType() == Material.RED_TULIP || b.getType() == Material.WHITE_TULIP || b.getType() == Material.OXEYE_DAISY|| b.getType() == Material.LILY_OF_THE_VALLEY || b.getType() == Material.LILAC || b.getType() == Material.ROSE_BUSH || b.getType() == Material.PEONY) {
                                        b.setType(Material.WATER);
                                        new BukkitRunnable() {
                                            @Override
                                            public void run() {
                                                // What you want to schedule goes here
                                                b.setType(Material.AIR);
                                            }
                                        }.runTaskLater(GreekOrigins.getPlugin(GreekOrigins.class), 40);
                                    }
                                }
                                player.addPotionEffect(cooldown);
                            }
                        }
                    }
                }
            }else if (event.getAction() == Action.LEFT_CLICK_AIR) {
                player.getWorld().setWeatherDuration(1);
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

    public static ArrayList<Block> getNearbyBlocks(Location location, int radius, int height) {
        ArrayList<Block> blocks = new ArrayList<>();
        for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for(int y = location.getBlockY() - height; y <= location.getBlockY() + height; y++) {
                for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }
}