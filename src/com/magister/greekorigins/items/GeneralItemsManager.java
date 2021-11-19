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

public class GeneralItemsManager {

    public static ItemStack CelestialFragment;
    public static ItemStack Ambrosia;
    public static ItemStack Wine;
    public static ItemStack Mead;

    public static void init(){
        createCelestialFragment();
        createAmbrosia();
        createWine();
        createMead();
    }

    private static void createCelestialFragment(){
        ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Celestial Fragment");
        List<String> lore = new ArrayList<>();
        lore.add("Used in godly crafting recipes.");
        lore.add(ChatColor.ITALIC +  "please don't make a beacon with this!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        CelestialFragment = item;
    }

    private static void createAmbrosia(){
        ItemStack item = new ItemStack(Material.GLOW_BERRIES, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Ambrosia");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "The food of the Gods");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        Ambrosia = item;

        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("ambrosia"), Ambrosia);
        sr.shape("GDG", "DCD", "GDG");
        sr.setIngredient('C', Material.ENCHANTED_GOLDEN_APPLE);
        sr.setIngredient('G', Material.GOLD_INGOT);
        sr.setIngredient('D', Material.DIAMOND);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createWine(){
        ItemStack item = new ItemStack(Material.POTION, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Wine");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_PURPLE + "Hmmm, get drunk");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        Wine = item;

        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("wine"), Wine);
        sr.shape(" B ", "B B", " G ");
        sr.setIngredient('B', Material.SWEET_BERRIES);
        sr.setIngredient('G', Material.GLASS_BOTTLE);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createMead(){
        ItemStack item = new ItemStack(Material.HONEY_BOTTLE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Mead");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Hmmm, get drunk");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        Mead = item;

        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("mead"), Mead);
        sr.shape(" H ", "H H", " G ");
        sr.setIngredient('H', Material.HONEYCOMB);
        sr.setIngredient('G', Material.GLASS_BOTTLE);
        Bukkit.getServer().addRecipe(sr);
    }
}