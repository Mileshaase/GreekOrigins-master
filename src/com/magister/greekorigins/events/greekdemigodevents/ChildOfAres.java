package com.magister.greekorigins.events.greekdemigodevents;

import com.magister.greekorigins.events.generalevents.RollEvents;
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
import static com.magister.greekorigins.events.generalevents.PlayerParties.Party;
import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;

public class ChildOfAres implements Listener {

    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 1200, 0, true, false, true);

    private static Boolean hasShield = false;
    private static int shield = 0;

    @EventHandler
    public static void onPlayerDealDamage(EntityDamageByEntityEvent event){
        Player player = (Player) event.getDamager();
        Random rand = new Random();
        assert GodlyParent != null;
        assert PlayerLevel != null;
        if(GodlyParent.get(player.getUniqueId()).equals("Ares")){
            int  n = rand.nextInt(100) + 1;
            if (n <= 10) {
                if ((Party.get(player.getUniqueId()) != (Party.get(event.getEntity().getUniqueId()))) || (Party.get(event.getEntity().getUniqueId()) == null) || (Party.get(event.getEntity().getUniqueId()) == "1")) {
                    double damageBoost = event.getDamage() / 5;
                    if (damageBoost >= 5) {
                        damageBoost = 5;
                    }
                    PotionEffect strength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, (int) damageBoost, true, true, true);
                    player.addPotionEffect(strength);
                }
            }
        } else {
            RollEvents.hasNotTheGodYoureLookingFor = true;
        }
    }

    @EventHandler
    public static void onPlayerKill(PlayerDeathEvent event){
        assert GodlyParent != null;
        assert PlayerLevel != null;
        Player player = event.getEntity().getKiller();
        assert player != null;
        if(GodlyParent.get(player.getUniqueId()).equals("Ares")){
            PotionEffect absorption = new PotionEffect(PotionEffectType.ABSORPTION, 200, 2, true, true, true);
            PotionEffect regen = new PotionEffect(PotionEffectType.REGENERATION, 200, 1, true, true, true);
            player.addPotionEffect(absorption);
            player.addPotionEffect(regen);
        } else {
            RollEvents.hasNotTheGodYoureLookingFor = true;
        }
    }

    @EventHandler
    public static void giftOfStrength(PlayerInteractEvent event){
        assert GodlyParent != null;
        assert PlayerLevel != null;
        Player player = event.getPlayer();
        if(player.isSneaking()) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                if (GodlyParent.get(player.getUniqueId()).equals("Ares")) {
                    if (event.getItem() == null) {
                        if (!(player.hasPotionEffect(cooldown.getType()))) {
                            int duration = 0;
                            int amplifier = 0;
                            if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                                duration = 350;
                                amplifier = 5;
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                                duration = 250;
                                amplifier = 4;
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                                duration = 150;
                                amplifier = 3;
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                                duration = 100;
                                amplifier = 2;
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
                                duration = 50;
                                amplifier = 1;
                            }
                            PotionEffect strength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, duration, amplifier, true, false, true);
                            player.addPotionEffect(strength);
                            player.addPotionEffect(cooldown);
                        }
                    } else if(event.getItem().getType().equals(Material.STONE_SWORD) || event.getItem().getType().equals(Material.IRON_SWORD) || event.getItem().getType().equals(Material.GOLDEN_SWORD) || event.getItem().getType().equals(Material.DIAMOND_SWORD) || event.getItem().getType().equals(Material.WOODEN_SWORD) || event.getItem().getType().equals(Material.NETHERITE_SWORD)){
                        hasShield = !hasShield;
                        giftShield(player);
                    }
                } else {
                    RollEvents.hasNotTheGodYoureLookingFor = true;
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