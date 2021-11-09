package com.magister.greekorigins.events.inventoryevents;

import com.magister.greekorigins.inventories.RollForGod;
import com.magister.greekorigins.items.InventoryItemsManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

import static com.magister.greekorigins.events.generalevents.RollEvents.*;

public class RollForGodInventoryEvents implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            if (event.getClickedInventory().getHolder() instanceof RollForGod) {
                event.setCancelled(true);
                Player player = (Player) event.getWhoClicked();
                if (Objects.equals(event.getCurrentItem(), InventoryItemsManager.RollForGod)) {
                    if (NumberOfRolls.get(player.getName()) > 0) {
                        RandomGreekRoll(player);
                    } else if (NumberOfRolls.get(player.getName()) <= 0) {
                        player.sendTitle(ChatColor.RED + "You have no rolls left!", ChatColor.GOLD + "sorry kiddo, your stuck with the parent you have", 1, 20, 1);
                        player.closeInventory();
                    }
                }
            }
        }
    }
}