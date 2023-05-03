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
}
