package com.eizzo.launchers.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;

public class ChatUtils {
    private static final MiniMessage mm = MiniMessage.miniMessage();

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(mm.deserialize("<gradient:#55ffff:#5555ff><b>[EizzoLaunchers]</b></gradient> <gray>" + message));
    }
    
    public static void sendHelpMessage(CommandSender sender, String command, String description) {
         sender.sendMessage(mm.deserialize("<dark_gray>» <aqua>/launcher " + command + " <dark_gray>- <gray>" + description));
    }

    public static Component format(String message) {
        return mm.deserialize(message);
    }
}
