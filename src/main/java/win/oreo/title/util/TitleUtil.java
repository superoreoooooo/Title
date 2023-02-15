package win.oreo.title.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import win.oreo.hexchatcolor.HEXChatColor;
import win.oreo.title.Main;

import java.util.HashSet;
import java.util.Set;

public class TitleUtil {
    public static Set<Title> titles = new HashSet<>();

    public void initialize() {
        Main plugin = JavaPlugin.getPlugin(Main.class);
        for (String name :  plugin.ymlManager.getConfig().getConfigurationSection("title.").getKeys(false)) {
            String title = plugin.ymlManager.getConfig().getString("title." + name + ".title");
            titles.add(new Title(Bukkit.getPlayerExact(name), title));
        }

        update();
    }

    public void save() {
        Main plugin = JavaPlugin.getPlugin(Main.class);
        for (Title title : titles) {
            plugin.ymlManager.getConfig().set("title." + title.getPlayer().getName() + ".title", title.getTitle());
        }
        plugin.ymlManager.saveConfig();
    }

    public void update() {
        for (Title title : titles) {
            Player player = title.getPlayer();
            String str = ChatColor.WHITE + "[" + HEXChatColor.format(title.getTitle()) + ChatColor.WHITE + "] ";
            NameTagChanger.changePlayerName(player,  str, "", TeamAction.UPDATE);
            //Bukkit.getConsoleSender().sendMessage("player" + player + " set name to " + str + player.getName());
        }
    }

    public Title getTitle(Player player) {
        for (Title title : titles) {
            if (title.getPlayer().equals(player)) {
                return title;
            }
        }
        return null;
    }
}
