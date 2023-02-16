package win.oreo.title.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TitleCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        switch (args.length) {
            case 1 -> {
                completions.add("set");
                completions.add("color");
                completions.add("remove");
            }
            case 2 -> {
                switch (args[0]) {
                    case "set", "color", "remove" -> {
                        Bukkit.getOnlinePlayers().forEach(player -> completions.add(player.getName()));
                    }
                }
            }
        }
        return completions;
    }
}
