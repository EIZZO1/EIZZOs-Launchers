package com.eizzo.launchers.tasks;

import com.eizzo.launchers.models.LauncherType;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TrailTask extends BukkitRunnable {

    private final Entity entity;
    private final LauncherType type;

    public TrailTask(Entity entity, LauncherType type) {
        this.entity = entity;
        this.type = type;
    }

    @Override
    public void run() {
        if (!entity.isValid() || entity.isDead()) {
            this.cancel();
            return;
        }

        if (entity instanceof Player player && player.isOnline()) {
            if (player.isOnGround() || player.getLocation().getBlock().isLiquid()) {
                this.cancel();
                return;
            }
        } else if (entity.isOnGround() || entity.getLocation().getBlock().isLiquid()) {
            this.cancel();
            return;
        }

        Location loc = entity.getLocation().add(0, 0.2, 0);
        
        try {
            loc.getWorld().spawnParticle(Particle.valueOf(type.getTrailParticle1()), loc, 3, 0.1, 0.1, 0.1, 0.02);
        } catch (Exception ignored) {}

        try {
            loc.getWorld().spawnParticle(Particle.valueOf(type.getTrailParticle2()), loc, 2, 0.1, 0.1, 0.1, 0.02);
        } catch (Exception ignored) {}
    }
}
