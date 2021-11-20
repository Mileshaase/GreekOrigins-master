package com.magister.greekorigins.events.greekdemigodevents;

import org.bukkit.Color;
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
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.PlayerParties.Party;
import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;

public class ChildOfApollo implements Listener {

    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 1200, 0, true, false, true);
    private static final PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, 400, 255, true, true, true);
    private static final PotionEffect glowing = new PotionEffect(PotionEffectType.GLOWING, 400, 255, true, false, true);

    @EventHandler
    public static void onBowFire(EntityShootBowEvent event){
        Player player = (Player) event.getEntity();
        if(GodlyParent.get(player.getUniqueId()).equals("Apollo")){
            event.setConsumeItem(false);
            if(player.isSneaking()) {
                if (!(player.hasPotionEffect(cooldown.getType()))) {
                    float CONE_DEGREES = 0;
                    int amount = 0;
                    if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                        amount = 15;
                        CONE_DEGREES = 32;
                    } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                        amount = 10;
                        CONE_DEGREES = 16;
                    } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                        amount = 5;
                        CONE_DEGREES = 8;
                    } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                        amount = 4;
                        CONE_DEGREES = 4;
                    } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
                        amount = 2;
                        CONE_DEGREES = 2;
                    }

                    Arrow oldArrow = (Arrow) event.getProjectile();
                    oldArrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                    int knockbackStrength = oldArrow.getKnockbackStrength();
                    boolean critical = oldArrow.isCritical();
                    double angleBetweenArrows = (CONE_DEGREES / (amount - 1)) * Math.PI / 180;
                    double pitch = (player.getLocation().getPitch() + 90) * Math.PI / 180;
                    double yaw = (player.getLocation().getYaw() + 90 - CONE_DEGREES / 2) * Math.PI / 180;

                    // Starting direction values for the cone, each arrow increments it's direction on these values.
                    double sZ = Math.cos(pitch);

                    for (int i = 0; i < amount; i++) { // spawn all arrows in a cone of 90 degrees (equally distributed).;
                        double nX = Math.sin(pitch) * Math.cos(yaw + angleBetweenArrows * i);
                        double nY = Math.sin(pitch) * Math.sin(yaw + angleBetweenArrows * i);
                        Vector newDir = new Vector(nX, sZ, nY);

                        Arrow arrow = player.launchProjectile(Arrow.class);
                        arrow.setShooter(player);
                        arrow.setVelocity(newDir.normalize().multiply(oldArrow.getVelocity().length()));
                        arrow.setFireTicks(20);
                        arrow.setKnockbackStrength(knockbackStrength * 2);
                        arrow.setCritical(critical);
                        arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                        arrow.setColor(Color.YELLOW);
                    }
                    oldArrow.remove(); // Remove original arrow.
                    player.addPotionEffect(cooldown);
                }
            }
        }
    }

    @EventHandler
    public static void solarFlare(PlayerInteractEvent event){
        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            Player player = event.getPlayer();
            if(player.isSneaking()){
                if(GodlyParent.get(player.getUniqueId()).equals("Apollo")){
                    if(event.getItem() == null) {
                        if (!(player.hasPotionEffect(cooldown.getType()))) {
                            player.addPotionEffect(glowing);
                            if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                                List<Entity> players = player.getNearbyEntities(30, 5, 30);
                                for (Entity i : players) {
                                    if ((Party.get(player.getUniqueId()) != (Party.get(i.getUniqueId()))) || (Party.get(i.getUniqueId()) == null) || (Party.get(i.getUniqueId()) == "1")) {
                                        ((LivingEntity) i).addPotionEffect(blindness);
                                        ((Player) i).sendTitle("You've been blinded by a solar flare", "", 50, 50, 50);
                                    }
                                }
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                                List<Entity> players = player.getNearbyEntities(20, 5, 20);
                                for (Entity i : players) {
                                    if ((Party.get(player.getUniqueId()) != (Party.get(i.getUniqueId()))) || (Party.get(i.getUniqueId()) == null) || (Party.get(i.getUniqueId()) == "1")) {
                                        ((LivingEntity) i).addPotionEffect(blindness);
                                        ((Player) i).sendTitle("You've been blinded by a solar flare", "", 50, 50, 50);
                                    }
                                }
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                                List<Entity> players = player.getNearbyEntities(15, 5, 15);
                                for (Entity i : players) {
                                    if ((Party.get(player.getUniqueId()) != (Party.get(i.getUniqueId()))) || (Party.get(i.getUniqueId()) == null) || (Party.get(i.getUniqueId()) == "1")) {
                                        ((LivingEntity) i).addPotionEffect(blindness);
                                        ((Player) i).sendTitle("You've been blinded by a solar flare", "", 50, 50, 50);
                                    }
                                }
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 15))).addPotionEffect(blindness);
                                ((Player) Objects.requireNonNull(getNearestEntityInSight(player, 15))).sendTitle("You've been blinded by a solar flare", "", 50, 50, 50);
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 10))).addPotionEffect(blindness);
                                ((Player) Objects.requireNonNull(getNearestEntityInSight(player, 10))).sendTitle("You've been blinded by a solar flare", "", 50, 50, 50);
                            }
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