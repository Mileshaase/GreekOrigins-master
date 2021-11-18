package com.magister.greekorigins.events.greekdemigodevents;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Random;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.PlayerParties.Party;
import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;

public class ChildOfHephaestus implements Listener {

    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 2400, 0, true, false, true);
    private static final PotionEffect fireResist = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 500, 255, true, false, true);

    @EventHandler
    public static void noFireDamage(EntityDamageEvent event){
        Player player = (Player) event.getEntity();
        if(event.getCause() == EntityDamageEvent.DamageCause.FIRE || event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK){
            if(GodlyParent.get(player.getUniqueId()).equals("Hephaestus")){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public static void playerBreakOre(BlockBreakEvent event){
        Player player = event.getPlayer();
        Random rand = new Random();
        if(GodlyParent.get(player.getUniqueId()).equals("Hephaestus")){
            if(event.getBlock().getType().equals(Material.IRON_ORE) || event.getBlock().getType().equals(Material.COAL_ORE) || event.getBlock().getType().equals(Material.DIAMOND_ORE) || event.getBlock().getType().equals(Material.GOLD_ORE) || event.getBlock().getType().equals(Material.EMERALD_ORE) || event.getBlock().getType().equals(Material.LAPIS_ORE) || event.getBlock().getType().equals(Material.LAPIS_ORE) || event.getBlock().getType().equals(Material.NETHER_GOLD_ORE) || event.getBlock().getType().equals(Material.NETHER_QUARTZ_ORE) || event.getBlock().getType().equals(Material.REDSTONE_ORE)){
                int  n = rand.nextInt(100) + 1;
                ItemStack drops = (ItemStack) event.getBlock().getDrops();
                int amount = 0;
                if(n <= 10) {
                    if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                        amount = 5;
                    } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                        amount = 4;
                    } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                        amount = 3;
                    } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                        amount = 2;
                    } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
                        amount  = 2;
                    } else if (PlayerLevel.get(player.getUniqueId()) < 10){
                        amount = 1;
                    }
                    for(int i = 0; i < amount; i++){
                        player.getWorld().dropItem(event.getBlock().getLocation(), drops);
                    }
                }
            }
        }
    }

    @EventHandler
    public static void playerNoFireDamage(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(GodlyParent.get(player.getUniqueId()).equals("Hephaestus")) {
            player.addPotionEffect(fireResist);
        }
    }

    @EventHandler
    public static void playerHitEntity(EntityDamageByEntityEvent event){
        Player player = (Player) event.getDamager();
        Entity attacked = event.getEntity();
        Random rand = new Random();
        if(GodlyParent.get(player.getUniqueId()).equals("Hephaestus")){
            int  n = rand.nextInt(100) + 1;
            if(PlayerLevel.get(player.getUniqueId()) > 0) {
                if (n <= 10){
                    if (Party.get(player.getUniqueId()) != (Party.get(attacked.getUniqueId())) || (Party.get(attacked.getUniqueId()) == null) || (Party.get(attacked.getUniqueId()) == "1")) {
                        attacked.setFireTicks(100);
                    }
                }
            }
        }
    }

    @EventHandler
    public static void playerRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.isSneaking()) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                if (GodlyParent.get(player.getUniqueId()).equals("Hephaestus")) {
                    if (event.getItem() == null) {
                        if (!(player.hasPotionEffect(cooldown.getType()))) {
                            float CONE_DEGREES = 0;
                            int amount = 0;
                            if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                                amount = 360;
                                CONE_DEGREES = 360;
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                                amount = 180;
                                CONE_DEGREES = 180;
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                                amount = 90;
                                CONE_DEGREES = 90;
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                                amount = 45;
                                CONE_DEGREES = 45;
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
                                amount = 15;
                                CONE_DEGREES = 15;
                            }

                            Arrow oldArrow = player.launchProjectile(Arrow.class);
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
                                arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                                arrow.setCritical(false);
                                arrow.setDamage(2);
                                arrow.setFireTicks(500);
                                arrow.setColor(Color.RED);
                            }
                            oldArrow.remove(); // Remove original arrow.
                            player.addPotionEffect(cooldown);
                        }
                    }
                }
            }
        }
    }
}
