package com.eizzo.launchers.gui;

import com.eizzo.launchers.EizzoLaunchers;
import com.eizzo.launchers.managers.LauncherManager;
import com.eizzo.launchers.models.LauncherType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LauncherGUI implements Listener {

    private final EizzoLaunchers plugin;
    private final LauncherManager manager;

    public LauncherGUI(EizzoLaunchers plugin, LauncherManager manager) {
        this.plugin = plugin;
        this.manager = manager;
    }

    public static class LauncherHolder implements InventoryHolder {
        private final String material;
        public LauncherHolder(String material) { this.material = material; }
        public String getMaterial() { return material; }
        public boolean isMain() { return material == null; }
        @Override public @NotNull Inventory getInventory() { return null; }
    }

    public void openMainMenu(Player player) {
        Inventory inv = Bukkit.createInventory(new LauncherHolder(null), 27, Component.text("Launcher Manager"));

        int slot = 0;
        for (LauncherType type : manager.getLaunchers().values()) {
            if (slot >= 25) break;
            inv.setItem(slot++, createLauncherItem(type));
        }

        inv.setItem(25, createSimpleItem(Material.EMERALD, "§aAdd Launcher", "§7Hold block in hand."));
        inv.setItem(26, createSimpleItem(Material.BARRIER, "§cClose Menu", "§7Exit."));

        // Use a task to ensure it opens on the next tick
        Bukkit.getScheduler().runTask(plugin, () -> player.openInventory(inv));
    }

    private ItemStack createLauncherItem(LauncherType type) {
        Material mat = Material.matchMaterial(type.getMaterial());
        ItemStack item = new ItemStack(mat != null ? mat : Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(type.getMaterial(), NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("Vertical: " + (int)type.getVertical(), NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("Horizontal: " + (int)type.getHorizontal(), NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("Boat Mode: " + (type.isBoat() ? "§aEnabled" : "§cDisabled"), NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.empty());
        lore.add(Component.text("Left-Click: Edit", NamedTextColor.AQUA).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("Right-Click: Remove", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public void openEditor(Player player, LauncherType type) {
        Inventory inv = Bukkit.createInventory(new LauncherHolder(type.getMaterial()), 27, Component.text("Editing: " + type.getMaterial()));

        inv.setItem(10, createControlItem(Material.LEVER, "Vertical Height", (int)type.getVertical()));
        inv.setItem(13, createSimpleItem(Material.OAK_BOAT, "§eBoat Mode: " + (type.isBoat() ? "§aEnabled" : "§cDisabled"), "§7Toggle boat launching."));
        inv.setItem(16, createControlItem(Material.FEATHER, "Horizontal Flick", (int)type.getHorizontal()));
        
        inv.setItem(21, createSimpleItem(Material.ARROW, "§7Back to Menu", "§8Return."));
        inv.setItem(23, createSimpleItem(Material.BARRIER, "§cClose Editor", "§7Exit."));

        Bukkit.getScheduler().runTask(plugin, () -> player.openInventory(inv));
    }

    private ItemStack createControlItem(Material mat, String name, int val) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name + ": " + val, NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("+10 (Left-Click)", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("-10 (Right-Click)", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack createSimpleItem(Material mat, String name, String loreLine) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name).decoration(TextDecoration.ITALIC, false));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(loreLine).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof LauncherHolder holder)) return;
        event.setCancelled(true);
        
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        if (item == null || item.getType() == Material.AIR) return;

        int slot = event.getRawSlot();
        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 0.5f, 1.0f);

        if (holder.isMain()) {
            if (slot == 25) {
                Material hand = player.getInventory().getItemInMainHand().getType();
                if (hand != Material.AIR && hand.isBlock()) {
                    manager.addLauncher(hand.name());
                    openMainMenu(player);
                }
            } else if (slot == 26) {
                player.closeInventory();
            } else {
                LauncherType type = manager.getLauncher(item.getType().name());
                if (type != null) {
                    if (event.isRightClick()) {
                        manager.removeLauncher(item.getType().name());
                        openMainMenu(player);
                    } else {
                        openEditor(player, type);
                    }
                }
            }
        } else {
            LauncherType type = manager.getLauncher(holder.getMaterial());
            if (type == null) return;

            if (slot == 10 || slot == 16) {
                int change = event.isLeftClick() ? 10 : -10;
                if (slot == 10) {
                    type.setVertical(Math.max(0, Math.min(100, type.getVertical() + change)));
                } else {
                    type.setHorizontal(Math.max(0, Math.min(100, type.getHorizontal() + change)));
                }
                openEditor(player, type);
            } else if (slot == 13) {
                type.setBoat(!type.isBoat());
                openEditor(player, type);
            } else if (slot == 21) {
                openMainMenu(player);
            } else if (slot == 23) {
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof LauncherHolder holder && !holder.isMain()) {
            manager.saveLaunchers();
        }
    }
}