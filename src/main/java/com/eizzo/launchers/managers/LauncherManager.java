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
            String particle = section.getString(key + ".particle", "FLAME");
            String sound = section.getString(key + ".sound", "ENTITY_FIREWORK_ROCKET_LAUNCH");
            launchers.put(key.toUpperCase(), new LauncherType(key.toUpperCase(), v, h, boat, particle, sound));
        }
    }

    public void saveLaunchers() {
        for (LauncherType type : launchers.values()) {
            plugin.getConfig().set("launchers." + type.getMaterial() + ".vertical", type.getVertical());
            plugin.getConfig().set("launchers." + type.getMaterial() + ".horizontal", type.getHorizontal());
            plugin.getConfig().set("launchers." + type.getMaterial() + ".boat", type.isBoat());
            plugin.getConfig().set("launchers." + type.getMaterial() + ".particle", type.getParticle());
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
