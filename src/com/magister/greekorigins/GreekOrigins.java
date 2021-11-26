package com.magister.greekorigins;

import com.magister.greekorigins.commands.*;
import com.magister.greekorigins.events.generalevents.*;
import com.magister.greekorigins.events.greekdemigodevents.*;
import com.magister.greekorigins.events.inventoryevents.RollForGodInventoryEvents;
import com.magister.greekorigins.files.CustomConfig;
import com.magister.greekorigins.items.GeneralItemsManager;
import com.magister.greekorigins.items.GreekWeapons;
import com.magister.greekorigins.items.InventoryItemsManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class GreekOrigins extends JavaPlugin {

    @Override
    public void onEnable(){
        InventoryItemsManager.init();
        GeneralItemsManager.init();
        GreekWeapons.init();

        getServer().getPluginManager().registerEvents(new ChildOfAphrodite(), this);
        getServer().getPluginManager().registerEvents(new ChildOfApollo(), this);
        getServer().getPluginManager().registerEvents(new ChildOfAres(), this);
        getServer().getPluginManager().registerEvents(new ChildOfArtemis(), this);
        getServer().getPluginManager().registerEvents(new ChildOfAthena(), this);
        getServer().getPluginManager().registerEvents(new ChildOfChronos(), this);
        getServer().getPluginManager().registerEvents(new ChildOfDemeter(), this);
        getServer().getPluginManager().registerEvents(new ChildOfDionysus(), this);
        getServer().getPluginManager().registerEvents(new ChildOfHades(), this);
        getServer().getPluginManager().registerEvents(new ChildOfHephaestus(), this);
        getServer().getPluginManager().registerEvents(new ChildOfHermes(), this);
        getServer().getPluginManager().registerEvents(new ChildOfPoseidon(), this);
        getServer().getPluginManager().registerEvents(new ChildOfZeus(), this);

        getServer().getPluginManager().registerEvents(new RollForGodInventoryEvents(), this);

        getServer().getPluginManager().registerEvents(new PlayerLevelEvents(), this);
        getServer().getPluginManager().registerEvents(new RollEvents(), this);
        getServer().getPluginManager().registerEvents(new GeneralEvents(), this);
        getServer().getPluginManager().registerEvents(new PlayerParties(), this);
        getServer().getPluginManager().registerEvents(new WeaponEvents(), this);

        Commands commands = new Commands();
        GreekGodCommands greekGodCommands = new GreekGodCommands();
        PartyCommands partyCommands = new PartyCommands();
        ItemCommands itemCommands = new ItemCommands();

        getCommand("heal").setExecutor(commands);
        getCommand("feed").setExecutor(commands);

        getCommand("lvl10").setExecutor(commands);
        getCommand("lvl20").setExecutor(commands);
        getCommand("lvl30").setExecutor(commands);
        getCommand("lvl40").setExecutor(commands);
        getCommand("lvl50").setExecutor(commands);
        getCommand("lvl0").setExecutor(commands);
        getCommand("getlvl").setExecutor(commands);

        getCommand("roll").setExecutor(commands);
        getCommand("addroll").setExecutor(commands);
        getCommand("getrolls").setExecutor(commands);
        getCommand("parent").setExecutor(commands);

        getCommand("Zeus").setExecutor(greekGodCommands);
        getCommand("Poseidon").setExecutor(greekGodCommands);
        getCommand("Hades").setExecutor(greekGodCommands);
        getCommand("Aphrodite").setExecutor(greekGodCommands);
        getCommand("Apollo").setExecutor(greekGodCommands);
        getCommand("Ares").setExecutor(greekGodCommands);
        getCommand("Artemis").setExecutor(greekGodCommands);
        getCommand("Athena").setExecutor(greekGodCommands);
        getCommand("Demeter").setExecutor(greekGodCommands);
        getCommand("Dionysus").setExecutor(greekGodCommands);
        getCommand("Hephaestus").setExecutor(greekGodCommands);
        getCommand("Hermes").setExecutor(greekGodCommands);
        getCommand("Chronos").setExecutor(greekGodCommands);

        getCommand("createParty").setExecutor(partyCommands);
        getCommand("invite").setExecutor(partyCommands);
        getCommand("remove").setExecutor(partyCommands);
        getCommand("accept").setExecutor(partyCommands);
        getCommand("deny").setExecutor(partyCommands);
        getCommand("leaveParty").setExecutor(partyCommands);
        getCommand("party").setExecutor(partyCommands);

        getCommand("giveAmbrosia").setExecutor(itemCommands);
        getCommand("giveWine").setExecutor(itemCommands);
        getCommand("giveMead").setExecutor(itemCommands);
        getCommand("giveHelmOfDarkness").setExecutor(itemCommands);
        getCommand("givePoseidonsTrident").setExecutor(itemCommands);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Greek Origins]: Functioning");

        //setup config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        CustomConfig.setup();
        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();
    }

    @Override
    public void onDisable(){
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Greek Origins]: Dying");
    }
}
