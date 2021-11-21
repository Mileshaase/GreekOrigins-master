package com.magister.greekorigins.events.greekdemigodevents;

import com.magister.greekorigins.events.generalevents.RollEvents;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.PlayerParties.Party;
import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;

public class ChildOfAphrodite implements Listener {
    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 1200, 0, true, false, true);
    private static final PotionEffect tempCooldown = new PotionEffect(PotionEffectType.LUCK, 500, 0, true, false, true);

    private static final PotionEffect slow = new PotionEffect(PotionEffectType.SLOW, 100, 255, true, true, true);
    private static final PotionEffect stopjump = new PotionEffect(PotionEffectType.JUMP, 100, 200, true, true, true);
    private static final PotionEffect slowDig = new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 255, true, true, true);

    @EventHandler
    public static void reTargetEvent(EntityDamageByEntityEvent event){
        Player player = (Player) event.getEntity();
        if(GodlyParent.get(player.getUniqueId()).equals("Aphrodite")){
            List<Entity> players = player.getNearbyEntities(10, 10, 10);
            Location loc;
            for (Entity i : players) {
                if ((Party.get(player.getUniqueId()) != (Party.get(event.getDamager().getUniqueId()))) || (Party.get(event.getDamager().getUniqueId()) == null) || (Party.get(event.getDamager().getUniqueId()) == "1")) {
                    int p = players.indexOf(i);
                    ((Mob) i).setTarget((LivingEntity) event.getDamager());
                }
            }
        } else {
            RollEvents.hasNotTheGodYoureLookingFor = true;
        }
    }

    @EventHandler
    public static void playerLeftClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.isSneaking()) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                if (GodlyParent.get(player.getUniqueId()).equals("Aphrodite")) {
                    if (event.getItem() == null) {
                        if (!(player.hasPotionEffect(cooldown.getType()))) {
                            if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                                List<Entity> players = player.getNearbyEntities(30, 5, 30);
                                for (Entity i : players) {
                                    if ((Party.get(player.getUniqueId()) != (Party.get(i.getUniqueId()))) || (Party.get(i.getUniqueId()) == null) || (Party.get(i.getUniqueId()) == "1")) {
                                        i.getWorld().spawnParticle(Particle.HEART, i.getLocation(), 100);
                                        ((LivingEntity) i).addPotionEffect(slow);
                                        ((LivingEntity) i).addPotionEffect(stopjump);
                                        ((LivingEntity) i).addPotionEffect(slowDig);
                                        ((LivingEntity) i).addPotionEffect(tempCooldown);
                                        ((Player) i).sendTitle("You've been stunned!", "", 50, 50, 50);
                                    }
                                }
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                                List<Entity> players = player.getNearbyEntities(25, 5, 25);
                                for (Entity i : players) {
                                    if ((Party.get(player.getUniqueId()) != (Party.get(i.getUniqueId()))) || (Party.get(i.getUniqueId()) == null) || (Party.get(i.getUniqueId()) == "1")) {
                                        i.getWorld().spawnParticle(Particle.HEART, i.getLocation(), 100);
                                        ((LivingEntity) i).addPotionEffect(slow);
                                        ((LivingEntity) i).addPotionEffect(stopjump);
                                        ((LivingEntity) i).addPotionEffect(slowDig);
                                        ((LivingEntity) i).addPotionEffect(tempCooldown);
                                        ((Player) i).sendTitle("You've been stunned!", "", 50, 50, 50);
                                    }
                                }
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                                List<Entity> players = player.getNearbyEntities(20, 5, 20);
                                for (Entity i : players) {
                                    if ((Party.get(player.getUniqueId()) != (Party.get(i.getUniqueId()))) || (Party.get(i.getUniqueId()) == null) || (Party.get(i.getUniqueId()) == "1")) {
                                        i.getWorld().spawnParticle(Particle.HEART, i.getLocation(), 100);
                                        ((LivingEntity) i).addPotionEffect(slow);
                                        ((LivingEntity) i).addPotionEffect(stopjump);
                                        ((LivingEntity) i).addPotionEffect(slowDig);
                                        ((LivingEntity) i).addPotionEffect(tempCooldown);
                                        ((Player) i).sendTitle("You've been stunned!", "", 50, 50, 50);
                                    }
                                }
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 15))).addPotionEffect(slow);
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 15))).addPotionEffect(stopjump);
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 15))).addPotionEffect(slowDig);
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 15))).addPotionEffect(tempCooldown);
                                Objects.requireNonNull(getNearestEntityInSight(player, 15)).getWorld().spawnParticle(Particle.HEART, Objects.requireNonNull(getNearestEntityInSight(player, 15)).getLocation(), 100);
                                ((Player) Objects.requireNonNull(getNearestEntityInSight(player, 15))).sendTitle("You've been stunned!", "", 50, 50, 50);
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 10))).addPotionEffect(slow);
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 10))).addPotionEffect(stopjump);
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 10))).addPotionEffect(slowDig);
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 10))).addPotionEffect(tempCooldown);
                                Objects.requireNonNull(getNearestEntityInSight(player, 10)).getWorld().spawnParticle(Particle.HEART, Objects.requireNonNull(getNearestEntityInSight(player, 10)).getLocation(), 100);
                                ((Player) Objects.requireNonNull(getNearestEntityInSight(player, 10))).sendTitle("You've been stunned!", "", 50, 50, 50);
                                player.addPotionEffect(cooldown);
                            }
                         }
                    }
                }else {
                    RollEvents.hasNotTheGodYoureLookingFor = true;
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