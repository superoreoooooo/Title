package win.oreo.title.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import win.oreo.title.util.Title;
import win.oreo.title.util.TitleUtil;

import java.awt.*;

public class TitleListener implements Listener {
    private TitleUtil util;

    public TitleListener() {
        this.util = new TitleUtil();
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (util.getTitle(player) != null) {
            Title title = util.getTitle(player);
            e.setFormat("<" + title.getTitle() + ChatColor.WHITE + ">" + " %2$s");
        }
    }
}
