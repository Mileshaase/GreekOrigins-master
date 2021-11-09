package com.magister.greekorigins.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryItemsManager {
    public static ItemStack RollForGod;

    public static void init(){
        createRollForGod();
    }

    private static void createRollForGod(){
        ItemStack item = new ItemStack(Material.WHITE_BANNER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Roll For A Godly Parent");
        List<String> lore = new ArrayList<>();
        lore.add("Test your fate, and roll for a new parent among the gods.");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        RollForGod = item;
    }
}