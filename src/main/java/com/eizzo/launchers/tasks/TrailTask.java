package com.eizzo.launchers.tasks;

import com.eizzo.launchers.models.LauncherType;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

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
        Vector dir = loc.getDirection().setY(0);
        if (dir.lengthSquared() < 0.01) {
            dir = new Vector(0, 0, 1);
        } else {
            dir.normalize();
        }

        // Perpendicular vector for the "feet" offset (0.3 blocks left and right)
        Vector sideOffset = new Vector(-dir.getZ(), 0, dir.getX()).normalize().multiply(0.3);

        spawnParticles(loc.clone().add(sideOffset));
        spawnParticles(loc.clone().subtract(sideOffset));
    }

    private void spawnParticles(Location loc) {
        try {
            loc.getWorld().spawnParticle(Particle.valueOf(type.getTrailParticle1()), loc, 1, 0.02, 0.02, 0.02, 0.01);
        } catch (Exception ignored) {}

        try {
            loc.getWorld().spawnParticle(Particle.valueOf(type.getTrailParticle2()), loc, 1, 0.02, 0.02, 0.02, 0.01);
        } catch (Exception ignored) {}
    }
}