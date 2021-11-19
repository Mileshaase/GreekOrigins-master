package com.magister.greekorigins.events.greekdemigodevents;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;

public class ChildOfHermes implements Listener {
    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 2400, 0, true, false, true);

    private static final PotionEffect levitate = new PotionEffect(PotionEffectType.LEVITATION, 10, 50, true, true, true);
    private static final PotionEffect jump = new PotionEffect(PotionEffectType.JUMP, 1000, 1, true, false, true);
    private static final PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 1000, 5, true, false, true);
    private static final PotionEffect haste = new PotionEffect(PotionEffectType.FAST_DIGGING, 1000, 8, true, false, true);

    @EventHandler
    public static void negateFallDamage(EntityDamageEvent event) {
        Player player = (Player) event.getEntity();
        if (GodlyParent.get(player.getUniqueId()).equals("Hermes")) {
            if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL) || event.getCause().equals(EntityDamageEvent.DamageCause.FLY_INTO_WALL)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public static void sped(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if (GodlyParent.get(player.getUniqueId()).equals("Hermes")) {
            player.addPotionEffect(speed);
            player.addPotionEffect(jump);
            player.addPotionEffect(haste);
        }
    }

    @EventHandler
    public static void playerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (GodlyParent.get(player.getUniqueId()).equals("Hermes")) {
            if (event.getItem() == null) {
                if (!(player.hasPotionEffect(cooldown.getType()))) {
                    if (player.isSneaking()) {
                        if (event.getAction() == Action.LEFT_CLICK_AIR) {
                            player.addPotionEffect(levitate);
                            player.addPotionEffect(cooldown);
                        }
                        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                            player.addPotionEffect(levitate);
                            player.addPotionEffect(cooldown);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public static void glideWhileFalling(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Location loc1 = player.getLocation();
        Location loc2 = player.getLocation();
        Location loc3 = player.getLocation();
        loc3.setY(loc3.getY() - 3);
        loc1.setY(loc1.getY() - 2);
        loc2.setY(loc2.getY() - 1);

        if(GodlyParent.get(player.getUniqueId()).equals("Hermes")) {
            if (player.isSneaking() && loc1.getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR && loc2.getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR && loc3.getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) {
                player.setGliding(true);
            }
        }
    }

    @EventHandler
    public static void EntityToggleGlideEvent(EntityToggleGlideEvent event){
        Player player = (Player) event.getEntity();
        if(GodlyParent.get(player.getUniqueId()).equals("Hermes")) {
            if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) {
                event.setCancelled(true);
            }
        }
    }
}
