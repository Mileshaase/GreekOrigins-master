package com.magister.greekorigins.events.greekdemigodevents;

import com.magister.greekorigins.events.generalevents.RollEvents;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.PlayerParties.Party;
import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;

public class ChildOfDionysus implements Listener {
    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 1200, 0, true, false, true);

    private static final PotionEffect nausea = new PotionEffect(PotionEffectType.CONFUSION, 300, 255, true, true, true);
    private static final PotionEffect strength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 500, 2, true, true, true);
    private static final PotionEffect absorption = new PotionEffect(PotionEffectType.ABSORPTION, 500, 2, true, true, true);

    @EventHandler
    public static void playerHitEntity(EntityDamageByEntityEvent event){
        assert GodlyParent != null;
        assert PlayerLevel != null;
        Player player = (Player) event.getDamager();
        Entity attacked = event.getEntity();
        Random rand = new Random();
        if(GodlyParent.get(player.getUniqueId()).equals("Dionysus")){
            int  n = rand.nextInt(100) + 1;
            if(PlayerLevel.get(player.getUniqueId()) > 0) {
                if (n <= 10){
                    if (Party.get(player.getUniqueId()) != (Party.get(attacked.getUniqueId())) || (Party.get(attacked.getUniqueId()) == null) || (Party.get(attacked.getUniqueId()) == "1")) {
                        ((LivingEntity) attacked).addPotionEffect(nausea);
                    }
                }
            }
        } else {
            RollEvents.hasNotTheGodYoureLookingFor = true;
        }
    }

    @EventHandler
    public static void drunkenStrength(PlayerMoveEvent event){
        assert GodlyParent != null;
        assert PlayerLevel != null;
        Player player = event.getPlayer();
        if(GodlyParent.get(player.getUniqueId()).equals("Dionysus")) {
            if (player.hasPotionEffect(nausea.getType())) {
                player.addPotionEffect(strength);
                player.addPotionEffect(absorption);
            }
        } else {
            RollEvents.hasNotTheGodYoureLookingFor = true;
        }
    }

    @EventHandler
    public static void playerLeftClick(PlayerInteractEvent event) {
        assert GodlyParent != null;
        assert PlayerLevel != null;
        Player player = event.getPlayer();
        if (player.isSneaking()) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                if (GodlyParent.get(player.getUniqueId()).equals("Dionysus")) {
                    if (event.getItem() == null) {
                        if (!(player.hasPotionEffect(cooldown.getType()))) {
                            if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                                List<Entity> players = player.getNearbyEntities(30, 5, 30);
                                for (Entity i : players) {
                                    if ((Party.get(player.getUniqueId()) != (Party.get(i.getUniqueId()))) || (Party.get(i.getUniqueId()) == null) || (Party.get(i.getUniqueId()) == "1")) {
                                        ((LivingEntity) i).addPotionEffect(nausea);
                                    }
                                }
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                                List<Entity> players = player.getNearbyEntities(20, 5, 20);
                                for (Entity i : players) {
                                    if ((Party.get(player.getUniqueId()) != (Party.get(i.getUniqueId()))) || (Party.get(i.getUniqueId()) == null) || (Party.get(i.getUniqueId()) == "1")) {
                                        ((LivingEntity) i).addPotionEffect(nausea);
                                    }
                                }
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                                List<Entity> players = player.getNearbyEntities(15, 5, 15);
                                for (Entity i : players) {
                                    if ((Party.get(player.getUniqueId()) != (Party.get(i.getUniqueId()))) || (Party.get(i.getUniqueId()) == null) || (Party.get(i.getUniqueId()) == "1")) {
                                        ((LivingEntity) i).addPotionEffect(nausea);
                                    }
                                }
                                player.addPotionEffect(cooldown);
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 15))).addPotionEffect(nausea);
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
                                ((LivingEntity) Objects.requireNonNull(getNearestEntityInSight(player, 10))).addPotionEffect(nausea);
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