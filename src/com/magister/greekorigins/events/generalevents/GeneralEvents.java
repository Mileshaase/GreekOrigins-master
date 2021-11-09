package com.magister.greekorigins.events.generalevents;

import com.magister.greekorigins.items.GeneralItemsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.RollEvents.NumberOfRolls;

public class GeneralEvents implements Listener {

    public static final PotionEffect drunk = new PotionEffect(PotionEffectType.CONFUSION, 1200, 255, true, false, true);

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
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(!player.hasPlayedBefore()){
            NumberOfRolls.put(player.getName(), 3);
        }
    }

    @EventHandler
    public void onDrink(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        ItemStack consumable = event.getItem();
        if(Objects.equals(consumable.getItemMeta(), GeneralItemsManager.Wine.getItemMeta())){
            player.addPotionEffect(drunk);
        }
    }
    @EventHandler
    public void onEat(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        ItemStack consumable = event.getItem();
        if(Objects.equals(consumable.getItemMeta(), GeneralItemsManager.Ambrosia.getItemMeta())){
            PlayerLevel.put(player.getName(), 0.0);
            NumberOfRolls.put(player.getName(), NumberOfRolls.get(player.getName()) + 3);
        }
    }
}