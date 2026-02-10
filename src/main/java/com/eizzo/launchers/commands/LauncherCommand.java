package com.eizzo.launchers.commands;
import com.eizzo.launchers.gui.LauncherGUI;
import com.eizzo.launchers.models.LauncherType;
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
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("help")) {
                sendHelp(player);
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                com.eizzo.launchers.EizzoLaunchers.get().reloadConfig();
                com.eizzo.launchers.EizzoLaunchers.get().getLauncherManager().loadLaunchers();
                ChatUtils.sendMessage(player, "<green>Configuration reloaded successfully!");
                return true;
            }
            if (args[0].equalsIgnoreCase("set") && args.length >= 4) {
                String material = args[1].toUpperCase();
                String prop = args[2].toLowerCase();
                String value = args[3];
                LauncherType type = com.eizzo.launchers.EizzoLaunchers.get().getLauncherManager().getLauncher(material);
                if (type == null) {
                    ChatUtils.sendMessage(player, "<red>Launcher for " + material + " not found.");
                    return true;
                }
                try {
                    switch (prop) {
                        case "vertical":
                            type.setVertical(Double.parseDouble(value));
                            break;
                        case "horizontal":
                            type.setHorizontal(Double.parseDouble(value));
                            break;
                        case "boat":
                            type.setBoat(Boolean.parseBoolean(value));
                            break;
                        case "particle1":
                            org.bukkit.Particle.valueOf(value.toUpperCase());
                            type.setParticle1(value.toUpperCase());
                            break;
                        case "particle2":
                            org.bukkit.Particle.valueOf(value.toUpperCase());
                            type.setParticle2(value.toUpperCase());
                            break;
                        case "trail_particle1":
                            org.bukkit.Particle.valueOf(value.toUpperCase());
                            type.setTrailParticle1(value.toUpperCase());
                            break;
                        case "trail_particle2":
                            org.bukkit.Particle.valueOf(value.toUpperCase());
                            type.setTrailParticle2(value.toUpperCase());
                            break;
                        case "sound":
                            org.bukkit.Sound.valueOf(value.toUpperCase());
                            type.setSound(value.toUpperCase());
                            break;
                        default:
                            ChatUtils.sendMessage(player, "<red>Unknown property: " + prop);
                            return true;
                    }
                    com.eizzo.launchers.EizzoLaunchers.get().getLauncherManager().saveLaunchers();
                    ChatUtils.sendMessage(player, "<green>Set " + prop + " for " + material + " to " + value);
                } catch (Exception e) {
                    ChatUtils.sendMessage(player, "<red>Invalid value for " + prop + ": " + value);
                }
                return true;
            }
        }
        gui.openMainMenu(player);
        return true;
    }

    private void sendHelp(Player player) {
        ChatUtils.sendHelpHeader(player);
        ChatUtils.sendHelpMessage(player, "", "Open the Launcher Manager GUI.");
        ChatUtils.sendHelpMessage(player, "set <material> <prop> <val>", "Set a property for a launcher.");
        ChatUtils.sendHelpMessage(player, "reload", "Reload the plugin configuration.");
        ChatUtils.sendHelpMessage(player, "help", "Show this help menu.");
    }

}
