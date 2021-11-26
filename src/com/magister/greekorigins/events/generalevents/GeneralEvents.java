package com.magister.greekorigins.events.generalevents;

import com.magister.greekorigins.files.CustomConfig;
import com.magister.greekorigins.items.GeneralItemsManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.RollEvents.*;

public class GeneralEvents implements Listener {

    public static final PotionEffect drunk = new PotionEffect(PotionEffectType.CONFUSION, 1200, 255, true, false, true);
    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 1200, 0, true, false, true);

    @EventHandler
    public static void lightningStrikeLightFire(BlockIgniteEvent event){
        if(event.getCause().equals(BlockIgniteEvent.IgniteCause.LIGHTNING)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public static void lightningStrikeEntity(LightningStrikeEvent event){
        event.getLightning().setFireTicks(0);
    }

    @EventHandler
    public void logWhenJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if(!player.hasPlayedBefore()){
            PlayerLevel.put(player.getUniqueId(), 0.0);
            NumberOfRolls.put(player.getUniqueId(), 3);
        }

        PlayerLevel.put(player.getUniqueId(), CustomConfig.get().getDouble(player.getUniqueId() + " level:"));
        GodlyParent.put(player.getUniqueId(), (String) CustomConfig.get().get(player.getUniqueId() + " parent:"));
        if(player.hasPlayedBefore()) {
            NumberOfRolls.put(player.getUniqueId(), (int) CustomConfig.get().getDouble(player.getUniqueId() + " rolls:"));
        }
    }

    @EventHandler
    public void logWhenLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();

        if(!player.hasPlayedBefore()){
            CustomConfig.get().addDefault(player.getUniqueId() + " level:", PlayerLevel.get(player.getUniqueId()));
            CustomConfig.get().addDefault(player.getUniqueId() + " parent:", GodlyParent.get(player.getUniqueId()));
            CustomConfig.get().addDefault(player.getUniqueId() + " rolls:", NumberOfRolls.get(player.getUniqueId()));
        } else{
            CustomConfig.get().set(player.getUniqueId() + " level:", PlayerLevel.get(player.getUniqueId()));
            CustomConfig.get().set(player.getUniqueId() + " parent:", GodlyParent.get(player.getUniqueId()));
            CustomConfig.get().set(player.getUniqueId() + " rolls:", NumberOfRolls.get(player.getUniqueId()));
        }
        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();
    }

    @EventHandler
    public void onDrink(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        ItemStack consumable = event.getItem();
        if(Objects.equals(consumable.getItemMeta(), GeneralItemsManager.Wine.getItemMeta()) || Objects.equals(consumable.getItemMeta(), GeneralItemsManager.Mead.getItemMeta())){
            player.addPotionEffect(drunk);
        }
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        ItemStack consumable = event.getItem();
        if(Objects.equals(consumable.getItemMeta(), GeneralItemsManager.Ambrosia.getItemMeta())){
            player.sendTitle("Your mythology has been reset", ChatColor.GOLD + "Good Luck!", 1, 50, 1);
            PlayerLevel.put(player.getUniqueId(), 0.0);
            NumberOfRolls.put(player.getUniqueId(), NumberOfRolls.get(player.getUniqueId()) + 3);
        }
    }

    @EventHandler
    public void onDrinkMilk(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        if(player.hasPotionEffect(PotionEffectType.LUCK)){
            player.addPotionEffect(cooldown);
        }
    }
}