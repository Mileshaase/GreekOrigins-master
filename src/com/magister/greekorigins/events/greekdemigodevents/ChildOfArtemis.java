package com.magister.greekorigins.events.greekdemigodevents;

import com.magister.greekorigins.GreekOrigins;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Objects;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.PlayerParties.Party;
import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;

public class ChildOfArtemis implements Listener {
    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 1200, 0, true, false, true);

    @EventHandler
    public static void onBowFire(EntityShootBowEvent event){
        Player player = (Player) event.getEntity();
        if(GodlyParent.get(player.getUniqueId()).equals("Artemis")){
            event.setConsumeItem(false);
            if(player.isSneaking()) {
                if (!(player.hasPotionEffect(cooldown.getType()))) {
                    float CONE_DEGREES = 0;
                    int amount = 0;
                    if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                        amount = 10;
                        CONE_DEGREES = 32;
                    } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                        amount = 8;
                        CONE_DEGREES = 16;
                    } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                        amount = 6;
                        CONE_DEGREES = 8;
                    } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                        amount = 4;
                        CONE_DEGREES = 4;
                    } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
                        amount = 3;
                        CONE_DEGREES = 3;
                    } else if (PlayerLevel.get(player.getUniqueId()) >= 0) {
                        amount = 2;
                        CONE_DEGREES = 2;
                    }

                    Arrow oldArrow = (Arrow) event.getProjectile();
                    oldArrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                    int knockbackStrength = oldArrow.getKnockbackStrength();
                    double angleBetweenArrows = (CONE_DEGREES / (amount - 1)) * Math.PI / 180;
                    double pitch = (player.getLocation().getPitch() + 90) * Math.PI / 180;
                    double yaw = (player.getLocation().getYaw() + 90 - CONE_DEGREES / 2) * Math.PI / 180;

                    // Starting direction values for the cone, each arrow increments it's direction on these values.
                    double sZ = Math.cos(pitch);

                    for (int i = 0; i < amount; i++) { // spawn all arrows in a cone of 90 degrees (equally distributed).;
                        double nX = Math.sin(pitch) * Math.cos(yaw + angleBetweenArrows * i);
                        double nY = Math.sin(pitch) * Math.sin(yaw + angleBetweenArrows * i);
                        Vector newDir = new Vector(nX, sZ, nY);

                        SpectralArrow arrow = player.launchProjectile(SpectralArrow.class);
                        arrow.setShooter(player);
                        arrow.setVelocity((newDir.normalize().multiply(oldArrow.getVelocity().length())).multiply(2));
                        arrow.setFireTicks(0);
                        arrow.setKnockbackStrength(knockbackStrength * 2);
                        arrow.setCritical(false);
                        arrow.setPierceLevel(2);
                        arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                    }
                    oldArrow.remove(); // Remove original arrow.
                    player.addPotionEffect(cooldown);
                }
            }
        }
    }

    @EventHandler
    public static void playerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(player.isSneaking()){
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                if (GodlyParent.get(player.getUniqueId()).equals("Artemis")) {
                    if (event.getItem() == null) {
                        if (!(player.hasPotionEffect(cooldown.getType()))) {
                            ArrayList<Entity> wolves = new ArrayList<>();
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
                                Entity wolf1 = player.getWorld().spawnEntity(loc1, EntityType.WOLF);
                                wolf1.setCustomName("Hunter");
                                wolf1.setCustomNameVisible(false);
                                wolf1.setGlowing(true);
                                wolf1.setTicksLived(100);
                                ((Wolf) wolf1).setTarget((LivingEntity) getNearestEntityInSight(player, 10));
                                ((Wolf) wolf1).setCollarColor(DyeColor.LIGHT_GRAY);
                                ((Wolf) wolf1).setOwner(player);
                                wolves.add(wolf1);

                                loc2.setX(player.getLocation().getX() + 2);
                                loc2.setZ(player.getLocation().getZ() + 2);
                                Entity wolf2 = player.getWorld().spawnEntity(loc2, EntityType.WOLF);
                                wolf2.setCustomName("Hunter");
                                wolf2.setCustomNameVisible(false);
                                wolf2.setTicksLived(100);
                                wolf2.setGlowing(true);
                                ((Wolf) wolf2).setTarget((LivingEntity) getNearestEntityInSight(player, 10));
                                ((Wolf) wolf2).setCollarColor(DyeColor.LIGHT_GRAY);
                                ((Wolf) wolf2).setOwner(player);
                                wolves.add(wolf2);

                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        // What you want to schedule goes here
                                        for(Entity z : wolves){
                                            ((Wolf) z).setHealth(0.0);
                                        }
                                    }
                                }.runTaskLater(GreekOrigins.getPlugin(GreekOrigins.class), 500);
                            }
                            for (int i = 0; i < troops; i++) {
                                loc1.setX(player.getLocation().getX() - 2);
                                loc1.setZ(player.getLocation().getZ() - 2);
                                Entity wolf1 = player.getWorld().spawnEntity(loc1, EntityType.WOLF);
                                wolf1.setCustomName("Hunter");
                                wolf1.setCustomNameVisible(false);
                                wolf1.setGlowing(true);
                                wolf1.setTicksLived(100);
                                ((Wolf) wolf1).setTarget((LivingEntity) getNearestEntityInSight(player, 10));
                                ((Wolf) wolf1).setCollarColor(DyeColor.LIGHT_GRAY);
                                ((Wolf) wolf1).setOwner(player);
                                wolves.add(wolf1);

                                loc2.setX(player.getLocation().getX() + 2);
                                loc2.setZ(player.getLocation().getZ() - 2);
                                Entity wolf2 = player.getWorld().spawnEntity(loc2, EntityType.WOLF);
                                wolf2.setCustomName("Hunter");
                                wolf2.setCustomNameVisible(false);
                                wolf2.setTicksLived(100);
                                wolf2.setGlowing(true);
                                ((Wolf) wolf2).setTarget((LivingEntity) getNearestEntityInSight(player, 10));
                                ((Wolf) wolf2).setCollarColor(DyeColor.LIGHT_GRAY);
                                ((Wolf) wolf2).setOwner(player);
                                wolves.add(wolf2);

                                loc3.setX(player.getLocation().getX() - 2);
                                loc3.setZ(player.getLocation().getZ() + 2);
                                Entity wolf3 = player.getWorld().spawnEntity(loc3, EntityType.WOLF);
                                wolf3.setCustomName("Hunter");
                                wolf3.setCustomNameVisible(false);
                                wolf3.setTicksLived(100);
                                wolf3.setGlowing(true);
                                ((Wolf) wolf2).setTarget((LivingEntity) getNearestEntityInSight(player, 10));
                                ((Wolf) wolf3).setCollarColor(DyeColor.LIGHT_GRAY);
                                ((Wolf) wolf3).setOwner(player);
                                wolves.add(wolf3);

                                loc4.setX(player.getLocation().getX() + 2);
                                loc4.setZ(player.getLocation().getZ() + 2);
                                Entity wolf4 = player.getWorld().spawnEntity(loc4, EntityType.WOLF);
                                wolf4.setCustomName("Hunter");
                                wolf4.setCustomNameVisible(false);
                                wolf4.setTicksLived(100);
                                wolf4.setGlowing(true);
                                ((Wolf) wolf2).setTarget((LivingEntity) getNearestEntityInSight(player, 10));
                                ((Wolf) wolf3).setCollarColor(DyeColor.LIGHT_GRAY);
                                ((Wolf) wolf3).setOwner(player);
                                wolves.add(wolf4);
                            }
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    // What you want to schedule goes here
                                    for(Entity z : wolves){
                                        ((Wolf) z).setHealth(0.0);
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
                            if(entity.getType() != EntityType.SHULKER_BULLET && entity.getType() != EntityType.DROPPED_ITEM && entity.getType() != EntityType.ITEM_FRAME && entity.getType() != EntityType.ARROW && entity.getType() != EntityType.WITHER_SKULL && entity.getType() != EntityType.SNOWBALL && entity.getType() != EntityType.EGG && entity.getType() != EntityType.BOAT && !Objects.equals(entity.getCustomName(), "Hunter")) {
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
