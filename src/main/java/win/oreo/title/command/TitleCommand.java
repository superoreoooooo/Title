package win.oreo.title.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import win.oreo.hexchatcolor.HEXChatColor;
import win.oreo.title.util.NameTagChanger;
import win.oreo.title.util.TeamAction;
import win.oreo.title.util.Title;
import win.oreo.title.util.TitleUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TitleCommand implements CommandExecutor {
    private TitleUtil titleUtil;

    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
    private static final Pattern pattern1 = Pattern.compile("§[a-zA-Z0-9]{1}");

    public TitleCommand() {
        this.titleUtil = new TitleUtil();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("administrators")) {
            if (args.length > 0) {
                switch (args[0]) {
                    case "set" -> {
                        if (args.length >= 3) {
                            if (Bukkit.getPlayerExact(args[1]) != null) {
                                Player player = Bukkit.getPlayerExact(args[1]);
                                String str = args[2];
                                Title title;

                                if (titleUtil.getTitle(player) == null) {
                                    title = new Title(player, "");
                                    TitleUtil.titles.add(title);
                                } else {
                                    title = titleUtil.getTitle(player);
                                }

                                if (args.length == 4 && args[3].equalsIgnoreCase("rainbow")) {
                                    title.setTitle(HEXChatColor.toRainBow(str));
                                } else {
                                    title.setTitle(str);
                                }
                                sender.sendMessage(HEXChatColor.toRainBow("set!"));
                            }
                        }
                    }
                    case "color" -> {
                        if (args.length == 3) {
                            if (Bukkit.getPlayerExact(args[1]) != null) {
                                Player player = Bukkit.getPlayerExact(args[1]);
                                String str = args[2];
                                Title title;

                                if (str.length() != 7 && str.charAt(0) != '#') {
                                    sender.sendMessage("not hex code!");
                                    return false;
                                }

                                if (titleUtil.getTitle(player) == null) {
                                    sender.sendMessage(HEXChatColor.toRainBow("Player don't exist!"));
                                    return false;
                                } else {
                                    title = titleUtil.getTitle(player);
                                }

                                String s = title.getTitle();

                                if (s.contains("§")) {
                                    Matcher matcher = pattern1.matcher(s);
                                    while(matcher.find()) {
                                        String c = s.substring(matcher.start(), matcher.end());
                                        s = s.replace(c, "");
                                        matcher = pattern1.matcher(s);
                                    }
                                } else {
                                    Matcher matcher = pattern.matcher(s);
                                    while(matcher.find()) {
                                        String c = s.substring(matcher.start(), matcher.end());
                                        s = s.replace(c, "");
                                        matcher = pattern.matcher(s);
                                    }
                                }

                                title.setTitle(HEXChatColor.format(str + s));
                            }
                        }
                    }
                    case "remove" -> {
                        if (args.length == 2) {
                            if (Bukkit.getPlayerExact(args[1]) != null) {
                                Player player = Bukkit.getPlayerExact(args[1]);

                                if (titleUtil.getTitle(player) == null) {
                                    sender.sendMessage(HEXChatColor.toRainBow("Player don't exist!"));
                                    return false;
                                } else {
                                    titleUtil.remove(titleUtil.getTitle(player));
                                    NameTagChanger.changePlayerName(player, "", "", TeamAction.DESTROY);
                                    sender.sendMessage(HEXChatColor.toRainBow("removed!"));
                                }
                            }
                        }
                    }
                    case "list" -> {
                        for (Title title : TitleUtil.titles) {
                            sender.sendMessage(title.getPlayer() + " " + title.getTitle());
                        }
                    }
                }
                titleUtil.update();
            }
        }
        return false;
    }
}
