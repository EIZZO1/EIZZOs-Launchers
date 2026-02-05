package com.eizzo.launchers.commands;

import com.eizzo.launchers.EizzoLaunchers;
import com.eizzo.launchers.models.LauncherType;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LauncherTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            String input = args[0].toLowerCase();
            if ("reload".startsWith(input)) completions.add("reload");
            if ("help".startsWith(input)) completions.add("help");
            if ("set".startsWith(input)) completions.add("set");
            if ("gui".startsWith(input)) completions.add("gui");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
            String input = args[1].toUpperCase();
            for (String material : EizzoLaunchers.get().getLauncherManager().getLaunchers().keySet()) {
                if (material.startsWith(input)) completions.add(material);
            }
        } else if (args.length == 3 && args[0].equalsIgnoreCase("set")) {
            String input = args[2].toLowerCase();
            List<String> props = Arrays.asList("vertical", "horizontal", "boat", "particle1", "particle2", "sound");
            for (String prop : props) {
                if (prop.startsWith(input)) completions.add(prop);
            }
        } else if (args.length == 4 && args[0].equalsIgnoreCase("set")) {
            String prop = args[2].toLowerCase();
            String input = args[3].toUpperCase();
            if (prop.equals("boat")) {
                if ("true".startsWith(input.toLowerCase())) completions.add("true");
                if ("false".startsWith(input.toLowerCase())) completions.add("false");
            } else if (prop.equals("particle1") || prop.equals("particle2")) {
                return Arrays.stream(Particle.values())
                        .map(Enum::name)
                        .filter(name -> name.startsWith(input))
                        .limit(20)
                        .collect(Collectors.toList());
            } else if (prop.equals("sound")) {
                return Arrays.stream(Sound.values())
                        .map(Enum::name)
                        .filter(name -> name.startsWith(input))
                        .limit(20)
                        .collect(Collectors.toList());
            } else if (prop.equals("vertical") || prop.equals("horizontal")) {
                completions.add("0");
                completions.add("25");
                completions.add("50");
                completions.add("75");
                completions.add("100");
            }
        }
        return completions;
    }
}