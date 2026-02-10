package com.eizzo.launchers.managers;
import com.eizzo.launchers.EizzoLaunchers;
import com.eizzo.launchers.models.LauncherType;
import org.bukkit.configuration.ConfigurationSection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class LauncherManager {
    private final EizzoLaunchers plugin;
    private final Map<String, LauncherType> launchers = new HashMap<>();
    public LauncherManager(EizzoLaunchers plugin) {
        this.plugin = plugin;
    }

    public void loadLaunchers() {
        launchers.clear();
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("launchers");
        if (section == null) return;
        for (String key : section.getKeys(false)) {
            double v = section.getDouble(key + ".vertical", 50);
            double h = section.getDouble(key + ".horizontal", 50);
            boolean boat = section.getBoolean(key + ".boat", false);
            String p1 = section.getString(key + ".particle1", "FLAME");
            String p2 = section.getString(key + ".particle2", "CLOUD");
            String tp1 = section.getString(key + ".trail_particle1", "SOUL_FIRE_FLAME");
            String tp2 = section.getString(key + ".trail_particle2", "WHITE_ASH");
            String sound = section.getString(key + ".sound", "ENTITY_FIREWORK_ROCKET_LAUNCH");
            launchers.put(key.toUpperCase(), new LauncherType(key.toUpperCase(), v, h, boat, p1, p2, tp1, tp2, sound));
        }
    }

    public void saveLaunchers() {
        for (LauncherType type : launchers.values()) {
            plugin.getConfig().set("launchers." + type.getMaterial() + ".vertical", type.getVertical());
            plugin.getConfig().set("launchers." + type.getMaterial() + ".horizontal", type.getHorizontal());
            plugin.getConfig().set("launchers." + type.getMaterial() + ".boat", type.isBoat());
            plugin.getConfig().set("launchers." + type.getMaterial() + ".particle1", type.getParticle1());
            plugin.getConfig().set("launchers." + type.getMaterial() + ".particle2", type.getParticle2());
            plugin.getConfig().set("launchers." + type.getMaterial() + ".trail_particle1", type.getTrailParticle1());
            plugin.getConfig().set("launchers." + type.getMaterial() + ".trail_particle2", type.getTrailParticle2());
            plugin.getConfig().set("launchers." + type.getMaterial() + ".sound", type.getSound());
        }
        plugin.saveConfig();
    }

    public LauncherType getLauncher(String material) {
        return launchers.get(material.toUpperCase());
    }

    public void addLauncher(String material) {
        launchers.put(material.toUpperCase(), new LauncherType(material.toUpperCase(), 50, 50, false));
        saveLaunchers();
    }

    public void removeLauncher(String material) {
        launchers.remove(material.toUpperCase());
        plugin.getConfig().set("launchers." + material.toUpperCase(), null);
        plugin.saveConfig();
    }

    public Map<String, LauncherType> getLaunchers() {
        return launchers;
    }

}

