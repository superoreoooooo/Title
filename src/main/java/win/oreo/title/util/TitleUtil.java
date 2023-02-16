package win.oreo.title.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import win.oreo.hexchatcolor.HEXChatColor;
import win.oreo.title.Main;

import java.util.HashSet;
import java.util.Set;

public class TitleUtil implements Listener {
    public static Set<Title> titles = new HashSet<>();

    public void initialize() {
        Main plugin = JavaPlugin.getPlugin(Main.class);
        for (String name :  plugin.ymlManager.getConfig().getConfigurationSection("title.").getKeys(false)) {
            String title = plugin.ymlManager.getConfig().getString("title." + name + ".title");
            titles.add(new Title(Bukkit.getOfflinePlayer(name), title));
        }

        update();
    }

    public void save() {
        Main plugin = JavaPlugin.getPlugin(Main.class);
        for (Title title : titles) {
            plugin.ymlManager.getConfig().set("title." + title.getPlayer().getName() + ".title", title.getTitle());
            Bukkit.getConsoleSender().sendMessage("player" + title.getPlayer().getName() + " set title to " + title.getTitle());
        }
        plugin.ymlManager.saveConfig();
    }

    public void update() {
        for (Title title : titles) {
            OfflinePlayer player = title.getPlayer();
            String str = ChatColor.WHITE + "[" + HEXChatColor.format(title.getTitle()) + ChatColor.WHITE + "] ";
            if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayerExact(player.getName()))) {
                NameTagChanger.changePlayerName(Bukkit.getPlayerExact(player.getName()),  str, "", TeamAction.UPDATE);
            }
            //Bukkit.getConsoleSender().sendMessage("player" + player + " set name to " + str + player.getName());
        }
    }

    public void remove(Title title) {
        Main plugin = JavaPlugin.getPlugin(Main.class);
        titles.remove(title);
        plugin.ymlManager.getConfig().set("title." + title.getPlayer().getName(), null);
        plugin.ymlManager.saveConfig();
    }

    public Title getTitle(OfflinePlayer player) {
        for (Title title : titles) {
            if (title.getPlayer().equals(player)) {
                return title;
            }
        }
        return null;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        update();
    }
}
