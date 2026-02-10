package com.eizzo.launchers.utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
public class ChatUtils {
    private static final MiniMessage mm = MiniMessage.miniMessage();
    private static final String PREFIX = "<gradient:#FF5555:#FF0000><b>[EIZZOs-Launchers]</b></gradient> <gray>» </gray>";
    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(mm.deserialize(PREFIX + "<white>" + message));
    }

    public static void sendHelpHeader(CommandSender sender) {
        sender.sendMessage(mm.deserialize("<gradient:#FF5555:#FF0000><b>--- EIZZOs-Launchers ---</b></gradient>"));
    }

    public static void sendHelpMessage(CommandSender sender, String command, String description) {
         sender.sendMessage(mm.deserialize("<gradient:#FF5555:#FF0000><b>»</b></gradient> <red>/launcher " + command + "</red> <gray>-- " + description));
    }

    public static Component format(String message) {
        return mm.deserialize(message);
    }

}
