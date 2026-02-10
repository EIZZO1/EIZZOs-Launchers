package com.eizzo.launchers;
import com.eizzo.launchers.commands.LauncherCommand;
import com.eizzo.launchers.gui.LauncherGUI;
import com.eizzo.launchers.listeners.LaunchListener;
import com.eizzo.launchers.managers.LauncherManager;
import org.bukkit.plugin.java.JavaPlugin;
public class EizzoLaunchers extends JavaPlugin {
    private static EizzoLaunchers instance;
    private LauncherManager launcherManager;
    private LauncherGUI launcherGUI;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        updateConfig();
        launcherManager = new LauncherManager(this);
        launcherManager.loadLaunchers();
        launcherGUI = new LauncherGUI(this, launcherManager);
        getCommand("launchers").setExecutor(new LauncherCommand(launcherGUI));
        getCommand("launchers").setTabCompleter(new com.eizzo.launchers.commands.LauncherTabCompleter());
        getServer().getPluginManager().registerEvents(new LaunchListener(launcherManager), this);
        getServer().getPluginManager().registerEvents(launcherGUI, this);
        getLogger().info("EIZZOs-Launchers enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("EIZZOs-Launchers disabled.");
    }

    private void updateConfig() {
        double version = getConfig().getDouble("config-version", 1.0);
        if (version < 1.1) {
            getLogger().info("Updating config.yml to version 1.1...");
            getConfig().options().copyDefaults(true);
            getConfig().set("config-version", 1.1);
            saveConfig();
        }
    }

    public static EizzoLaunchers get() { return instance; }
    public LauncherManager getLauncherManager() { return launcherManager; }
}

