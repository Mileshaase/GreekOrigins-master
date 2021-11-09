package com.magister.greekorigins.events.greekdemigodevents;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;

public class ChildOfAres implements Listener {

    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 2400, 0, true, false, true);

    private static Boolean hasShield = false;
    private static int shield = 0;

    @EventHandler
    public static void onPlayerDealDamage(EntityDamageByEntityEvent event){
        Player player = (Player) event.getDamager();
        Random rand = new Random();
        if(GodlyParent.get(player.getName()).equals("Ares")){
            int  n = rand.nextInt(100) + 1;
                if (n <= 25) {
                    double damageBoost = event.getDamage() / 5;
                    if (damageBoost >= 5) {
                        damageBoost = 5;
                    }
                    PotionEffect strength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, (int) damageBoost, true, false, true);
                    player.addPotionEffect(strength);
                }
        }
    }

    @EventHandler
    public static void onPlayerKill(PlayerDeathEvent event){
        Player player = event.getEntity().getKiller();
        assert player != null;
        if(GodlyParent.get(player.getName()).equals("Ares")){
            PotionEffect absorption = new PotionEffect(PotionEffectType.ABSORPTION, 200, 2, true, false, true);
            PotionEffect regen = new PotionEffect(PotionEffectType.REGENERATION, 200, 1, true, false, true);
            player.addPotionEffect(absorption);
            player.addPotionEffect(regen);
        }
    }

    @EventHandler
    public static void giftOfStrength(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.isSneaking()) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                if (GodlyParent.get(player.getName()).equals("Ares")) {
                    if (event.getItem() == null) {
                        if (!(player.hasPotionEffect(cooldown.getType()))) {
                            int duration = 0;
                            int strength = 0;
                            if (PlayerLevel.get(player.getName()) >= 50) {
                                duration = 350;
                                strength = 5;
                            } else if (PlayerLevel.get(player.getName()) >= 40) {
                                duration = 250;
                                strength = 4;
                            } else if (PlayerLevel.get(player.getName()) >= 30) {
                                duration = 150;
                                strength = 3;
                            } else if (PlayerLevel.get(player.getName()) >= 20) {
                                duration = 100;
                                strength = 2;
                            } else if (PlayerLevel.get(player.getName()) >= 10) {
                                duration = 50;
                                strength = 1;
                            }
                            PotionEffect resistance = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, duration, strength, true, false, true);
                            player.addPotionEffect(resistance);
                            player.addPotionEffect(cooldown);
                        }
                    } else if(event.getItem().getType().equals(Material.STONE_SWORD) || event.getItem().getType().equals(Material.IRON_SWORD) || event.getItem().getType().equals(Material.GOLDEN_SWORD) || event.getItem().getType().equals(Material.DIAMOND_SWORD) || event.getItem().getType().equals(Material.WOODEN_SWORD) || event.getItem().getType().equals(Material.NETHERITE_SWORD)){
                        hasShield = !hasShield;
                        giftShield(player);
                    }
                }
            }
        }
    }

    public static void giftShield(Player player){
        ItemStack shield = new ItemStack(Material.SHIELD);
        if(hasShield) {
            player.getInventory().setItemInOffHand(shield);
        } else {
            player.getInventory().setItemInOffHand(null);
        }
    }
}