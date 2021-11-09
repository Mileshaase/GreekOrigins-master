package com.magister.greekorigins.inventories;

import com.magister.greekorigins.items.InventoryItemsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class RollForGod implements InventoryHolder {

    private Inventory inv;

    public RollForGod(){
        inv = Bukkit.createInventory(this, 9, ChatColor.DARK_PURPLE + "Roll for a New Godly Parent?");
        init();
    }

    private void init(){
        //Center
        inv.setItem(4, InventoryItemsManager.RollForGod);
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}