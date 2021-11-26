package com.magister.greekorigins.events.generalevents;

import com.magister.greekorigins.items.GreekWeapons;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class WeaponEvents  implements Listener {

    public static HashMap<UUID, Boolean> invisible = new HashMap<>();

    @EventHandler
    public void HelmOfDarkness(EntityTargetLivingEntityEvent event) {
        Player player  = (Player) event.getTarget();
        Entity entity = event.getEntity();
        assert player != null;
        if (invisible.get(player.getUniqueId())){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public static void HelmOfDarkness(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(Objects.requireNonNull(player.getInventory().getHelmet()).getItemMeta() == GreekWeapons.HelmOfDarkness.getItemMeta()){
            if(player.isSneaking()) {
                if (!invisible.get(player.getUniqueId())) {
                    invisible.put(player.getUniqueId(), true);
                } else {
                    invisible.put(player.getUniqueId(), false);
                }
            }
            if(invisible.get(player.getUniqueId())){
                player.setInvisible(true);
            } else {
                player.setInvisible(false);
            }
        }
    }

    @EventHandler
    public static void PoseidonTrident(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.getInventory().getItemInMainHand() == GreekWeapons.PoseidonsTrident){
            if(player.isSneaking()){
                if (event.getAction() == Action.LEFT_CLICK_AIR) {
                    ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                    assert meta != null;
                    if(meta.hasEnchant(Enchantment.RIPTIDE)){
                        meta.removeEnchant(Enchantment.RIPTIDE);
                        meta.addEnchant(Enchantment.CHANNELING, 10, true);
                    } else{
                        meta.removeEnchant(Enchantment.CHANNELING);
                        meta.addEnchant(Enchantment.RIPTIDE, 10, true);
                    }
                }
            }
        }
    }
}
