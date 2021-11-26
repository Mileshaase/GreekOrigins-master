package com.magister.greekorigins.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GreekWeapons {

    public static ItemStack HelmOfDarkness;
    public static ItemStack PoseidonsTrident;

    public static void init(){
        createHelmOfDarkness();
        createPoseidonsTrident();
    }

    private static void createHelmOfDarkness(){
        ItemStack item = new ItemStack(Material.NETHERITE_HELMET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLACK + "Hades Helm Of Darkness");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Allows the user to become invisible");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        HelmOfDarkness = item;

        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("helmofdarkness"), HelmOfDarkness);
        sr.shape("NNN", "NSN", "N N");
        sr.setIngredient('N', Material.NETHERITE_INGOT);
        sr.setIngredient('S', Material.NETHER_STAR);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createPoseidonsTrident(){
        ItemStack item = new ItemStack(Material.TRIDENT, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Poseidons Trident");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "Trident of the Seas");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addEnchant(Enchantment.LOYALTY, 10, true);
        meta.addEnchant(Enchantment.CHANNELING, 10, true);
        meta.addEnchant(Enchantment.IMPALING, 10, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        PoseidonsTrident = item;

        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("poseidonstrident"), PoseidonsTrident);
        sr.shape("SHS", "CNC", " C ");
        sr.setIngredient('S', Material.PRISMARINE_SHARD);
        sr.setIngredient('N', Material.NETHER_STAR);
        sr.setIngredient('C', Material.PRISMARINE_CRYSTALS);
        sr.setIngredient('H', Material.HEART_OF_THE_SEA);
        Bukkit.getServer().addRecipe(sr);
    }
}
