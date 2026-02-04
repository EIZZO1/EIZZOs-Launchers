package com.eizzo.launchers.listeners;

import com.eizzo.launchers.managers.LauncherManager;
import com.eizzo.launchers.models.LauncherType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class LaunchListener implements Listener {

    private final LauncherManager manager;
    private final Set<UUID> cooldown = new HashSet<>();

    public LaunchListener(LauncherManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!event.hasChangedBlock()) return;

        Player player = event.getPlayer();
        
        // Block launch if GUI is open (Using Metadata for 100% reliability)
        if (player.hasMetadata("eizzolaunchers_gui")) {
            return;
        }

        Location loc = player.getLocation();
        Block topBlock = loc.clone().subtract(0, 1, 0).getBlock();
        Block bottomBlock = loc.clone().subtract(0, 2, 0).getBlock();
        
        if (topBlock.getType() == bottomBlock.getType() && topBlock.getType() != Material.AIR) {
            LauncherType type = manager.getLauncher(topBlock.getType().name());
            if (type != null) {
                launchPlayer(player, type);
            }
        }
    }

    private void launchPlayer(Player player, LauncherType type) {
        if (cooldown.contains(player.getUniqueId())) return;

        double vPower = (type.getVertical() / 100.0) * 2.5;
        double hPower = (type.getHorizontal() / 100.0) * 3.0;

        Vector direction = player.getLocation().getDirection().setY(0);
        if (direction.lengthSquared() > 0) {
            direction.normalize();
        } else {
            direction = new Vector(0, 0, 0);
        }
        
        Vector velocity = direction.multiply(hPower).setY(vPower);
        if (velocity.lengthSquared() < 0.01) return;

        player.setVelocity(velocity);
        
        cooldown.add(player.getUniqueId());
        org.bukkit.Bukkit.getScheduler().runTaskLater(com.eizzo.launchers.EizzoLaunchers.get(), 
            () -> cooldown.remove(player.getUniqueId()), 10L);
    }
}
