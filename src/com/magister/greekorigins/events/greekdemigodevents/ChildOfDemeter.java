package com.magister.greekorigins.events.greekdemigodevents;

import com.magister.greekorigins.events.generalevents.RollEvents;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.magister.greekorigins.events.generalevents.PlayerLevelEvents.PlayerLevel;
import static com.magister.greekorigins.events.generalevents.RollEvents.GodlyParent;

public class ChildOfDemeter implements Listener {
    private static final PotionEffect negateHunger = new PotionEffect(PotionEffectType.SATURATION, 300, 255, true, false, true);
    private static final PotionEffect cooldown = new PotionEffect(PotionEffectType.LUCK, 1200, 0, true, false, true);

    @EventHandler
    public static void negateHunger(PlayerMoveEvent event){
        assert GodlyParent != null;
        assert PlayerLevel != null;
        Player player = event.getPlayer();
        if(GodlyParent.get(player.getUniqueId()).equals("Demeter")){
            player.addPotionEffect(negateHunger);
        } else {
            RollEvents.hasNotTheGodYoureLookingFor = true;
        }
    }

    @EventHandler
    public static void onLeftClickGrowth(PlayerInteractEvent event){
        assert GodlyParent != null;
        assert PlayerLevel != null;
        Player player = event.getPlayer();
        if(GodlyParent.get(player.getUniqueId()).equals("Demeter")) {
            if (player.isSneaking()) {
                if ((event.getAction() == Action.LEFT_CLICK_AIR)) {
                    if (event.getItem() == null) {
                        if (!(player.hasPotionEffect(cooldown.getType()))) {
                            int radius = 0;
                            if (PlayerLevel.get(player.getUniqueId()) >= 50) {
                                radius = 50;
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 40) {
                                radius = 40;
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 30) {
                                radius = 30;
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 20) {
                                radius = 20;
                            } else if (PlayerLevel.get(player.getUniqueId()) >= 10) {
                                radius = 10;
                            }
                            for (Block b : getNearbyBlocks(player.getLocation(), radius)) {
                                for(int i = 0; i < 10; i++) {
                                    b.applyBoneMeal(BlockFace.DOWN);
                                }
                            }
                            player.addPotionEffect(cooldown);
                        }
                    }
                }
            }
        } else {
            RollEvents.hasNotTheGodYoureLookingFor = true;
        }
    }

    public static List<Block> getNearbyBlocks(Location location, int radius) {
        List<Block> blocks = new ArrayList<>();
        for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for(int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    blocks.add(Objects.requireNonNull(location.getWorld()).getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }
}
