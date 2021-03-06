package com.magister.greekorigins.events.greekdemigodevents;

import com.magister.greekorigins.events.generalevents.RollEvents;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.PlayerParties.Party;
import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;

public class ChildOfAthena implements Listener {

    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 1200, 0, true, false, true);

    @EventHandler
    public static void infiniteLevels(EnchantItemEvent event){
        assert GodlyParent != null;
        assert PlayerLevel != null;
        Player player = event.getEnchanter();
        if(GodlyParent.get(player.getUniqueId()).equals("Athena")){
            if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                player.setLevel(30);
            } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                player.setLevel(25);
            } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                player.setLevel(20);
            } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                player.setLevel(15);
            } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
                player.setLevel(10);
            }
        } else {
            RollEvents.hasNotTheGodYoureLookingFor = true;
        }
    }

    @EventHandler
    public static void infiniteLevels(PlayerMoveEvent event){
        Player player = event.getPlayer();
        assert GodlyParent != null;
        assert PlayerLevel != null;
        if(GodlyParent.get(player.getUniqueId()).equals("Athena")){
            if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                player.setLevel(30);
            } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                player.setLevel(25);
            } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                player.setLevel(20);
            } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                player.setLevel(15);
            } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
                player.setLevel(10);
            }
        } else {
            RollEvents.hasNotTheGodYoureLookingFor = true;
        }
    }

    @EventHandler
    public static void playerHitEntity(EntityDamageByEntityEvent event){
        Player player = (Player) event.getDamager();
        Player attacked = (Player) event.getEntity();
        Random rand = new Random();
        assert GodlyParent != null;
        assert PlayerLevel != null;
        if(GodlyParent.get(player.getUniqueId()).equals("Athena")){
            int  n = rand.nextInt(100) + 1;
            if(PlayerLevel.get(player.getUniqueId()) > 0) {
                if (n <= 10){
                    if (Party.get(player.getUniqueId()) != (Party.get(attacked.getUniqueId())) || (Party.get(attacked.getUniqueId()) == null) || (Party.get(attacked.getUniqueId()) == "1")) {
                        ((Player) attacked).dropItem(true);
                    }
                }
            }
        } else {
            RollEvents.hasNotTheGodYoureLookingFor = true;
        }
    }

    @EventHandler
    public static void playerRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        assert GodlyParent != null;
        assert PlayerLevel != null;
        if(player.isSneaking()) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                if (GodlyParent.get(player.getUniqueId()).equals("Athena")) {
                    if (event.getItem() == null) {
                        if (!(player.hasPotionEffect(cooldown.getType()))) {
                            if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                                List<Entity> players = player.getNearbyEntities(30, 10, 30);
                                for (Entity i : players) {
                                    if ((Party.get(player.getUniqueId()) != (Party.get(i.getUniqueId()))) || (Party.get(i.getUniqueId()) == null) || (Party.get(i.getUniqueId()) == "1")) {
                                        ((Player) i).sendTitle("You've been disarmed!", "", 50, 50, 50);
                                        ((Player) i).dropItem(true);
                                    }
                                }
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                                List<Entity> players = player.getNearbyEntities(20, 10, 20);
                                for (Entity i : players) {
                                    if ((Party.get(player.getUniqueId()) != (Party.get(i.getUniqueId()))) || (Party.get(i.getUniqueId()) == null) || (Party.get(i.getUniqueId()) == "1")) {
                                        ((Player) i).dropItem(true);
                                        ((Player) i).sendTitle("You've been disarmed!", "", 50, 50, 50);
                                    }
                                }
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                                List<Entity> players = player.getNearbyEntities(10, 10, 10);
                                for (Entity i : players) {
                                    if ((Party.get(player.getUniqueId()) != (Party.get(i.getUniqueId()))) || (Party.get(i.getUniqueId()) == null) || (Party.get(i.getUniqueId()) == "1")) {
                                        ((Player) i).sendTitle("You've been disarmed!", "", 50, 50, 50);
                                        ((Player) i).dropItem(true);
                                    }
                                }
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                                Player disarmed = (Player) getNearestEntityInSight(player, 10);
                                assert disarmed != null;
                                ((Player) disarmed).sendTitle("You've been disarmed!", "", 50, 50, 50);
                                ((Player) disarmed).dropItem(true);
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
                                Player disarmed = (Player) getNearestEntityInSight(player, 5);
                                assert disarmed != null;
                                ((Player) disarmed).sendTitle("You've been disarmed!", "", 50, 50, 50);
                                ((Player) disarmed).dropItem(true);
                                player.addPotionEffect(cooldown);
                            }
                        }
                    }
                } else {
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