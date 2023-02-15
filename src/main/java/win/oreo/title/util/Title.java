package win.oreo.title.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Title {
    private Player player;
    private String title;

    public Title(Player player, String title) {
        this.player = player;
        this.title = title;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
