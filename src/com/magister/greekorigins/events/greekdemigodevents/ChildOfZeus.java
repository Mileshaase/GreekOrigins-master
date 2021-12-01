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
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.PlayerParties.Party;
import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;

public class ChildOfZeus implements Listener {

    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 1200, 0, true, false, true);

    @EventHandler
    public static void noLightningDamage(EntityDamageEvent event){
        assert GodlyParent != null;
        assert PlayerLevel != null;
        assert event.getEntity().getType() == EntityType.PLAYER;
        Player player = (Player) event.getEntity();
        if(event.getCause() == EntityDamageEvent.DamageCause.LIGHTNING){
            event.setDamage(4);
            if(GodlyParent.get(player.getUniqueId()).equals("Zeus")){
                event.setCancelled(true);
            } else {
                RollEvents.hasNotTheGodYoureLookingFor = true;
            }
        }
    }

    @EventHandler
    public static void playerHitEntity(EntityDamageByEntityEvent event){
        assert GodlyParent != null;
        assert PlayerLevel != null;
        Player player = (Player) event.getDamager();
        Entity attacked = event.getEntity();
        Random rand = new Random();
        if(GodlyParent.get(player.getUniqueId()).equals("Zeus")){
            int  n = rand.nextInt(100) + 1;
            if(PlayerLevel.get(player.getUniqueId()) > 10) {
                if (n <= 10){
                    if (Party.get(player.getUniqueId()) != (Party.get(attacked.getUniqueId())) || (Party.get(attacked.getUniqueId()) == null) || (Party.get(attacked.getUniqueId()) == "1")) {
                        attacked.getWorld().strikeLightning(attacked.getLocation());
                        PlayerLevel.put(player.getUniqueId(), PlayerLevel.get(player.getUniqueId()) + 0.2);
                    }
                }
            }
        } else {
            RollEvents.hasNotTheGodYoureLookingFor = true;
        }
    }

    @EventHandler
    public static void playerRightClick(PlayerInteractEvent event){
        assert GodlyParent != null;
        assert PlayerLevel != null;
        Player player = event.getPlayer();
        if(player.isSneaking()) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                if (GodlyParent.get(player.getUniqueId()).equals("Zeus")) {
                    if (event.getItem() == null) {
                        if (!(player.hasPotionEffect(cooldown.getType()))) {
                            if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                                List<Entity> players = player.getNearbyEntities(20, 10, 20);
                                Location loc;
                                for (Entity i : players) {
                                    if ((Party.get(player.getUniqueId()) != (Party.get(i.getUniqueId()))) || (Party.get(i.getUniqueId()) == null) || (Party.get(i.getUniqueId()) == "1")) {
                                        int p = players.indexOf(i);
                                        loc = players.get(p).getLocation();
                                        player.getWorld().strikeLightning(loc);
                                    }
                                }
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                                List<Entity> players = player.getNearbyEntities(15, 10, 15);
                                Location loc;
                                for (Entity i : players) {
                                    if ((Party.get(player.getUniqueId()) != (Party.get(i.getUniqueId()))) || (Party.get(i.getUniqueId()) == null) || (Party.get(i.getUniqueId()) == "1")) {
                                        int p = players.indexOf(i);
                                        loc = players.get(p).getLocation();
                                        player.getWorld().strikeLightning(loc);
                                    }
                                }
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                                List<Entity> players = player.getNearbyEntities(10, 10, 10);
                                Location loc;
                                for (Entity i : players) {
                                    if ((Party.get(player.getUniqueId()) != (Party.get(i.getUniqueId()))) || (Party.get(i.getUniqueId()) == null) || (Party.get(i.getUniqueId()) == "1")) {
                                        int p = players.indexOf(i);
                                        loc = players.get(p).getLocation();
                                        player.getWorld().strikeLightning(loc);
                                    }
                                }
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                                player.getWorld().strikeLightning(Objects.requireNonNull(getNearestEntityInSight(player, 10)).getLocation());
                                player.getWorld().strikeLightning(Objects.requireNonNull(getNearestEntityInSight(player, 10)).getLocation());
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
                                player.getWorld().strikeLightning(Objects.requireNonNull(getNearestEntityInSight(player, 5)).getLocation());
                            }
                            player.addPotionEffect(cooldown);
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