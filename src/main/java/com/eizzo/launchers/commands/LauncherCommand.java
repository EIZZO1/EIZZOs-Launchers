package com.eizzo.launchers.commands;

import com.eizzo.launchers.gui.LauncherGUI;
import com.eizzo.launchers.utils.ChatUtils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LauncherCommand implements CommandExecutor {

    private final LauncherGUI gui;

    public LauncherCommand(LauncherGUI gui) {
        this.gui = gui;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (!player.hasPermission("eizzolaunchers.admin")) {
            ChatUtils.sendMessage(player, "<red>No permission.");
            return true;
        }

        if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
            sendHelp(player);
            return true;
        }

        gui.openMainMenu(player);
        return true;
    }

    private void sendHelp(Player player) {
        player.sendMessage(MiniMessage.miniMessage().deserialize("<gradient:#55ffff:#5555ff><b>--- EIZZO Launchers Help ---</b></gradient>"));
        ChatUtils.sendHelpMessage(player, "", "Open the Launcher Manager GUI.");
        ChatUtils.sendHelpMessage(player, "help", "Show this help menu.");
    }
}