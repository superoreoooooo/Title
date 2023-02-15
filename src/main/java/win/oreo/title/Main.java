package win.oreo.title;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import win.oreo.hexchatcolor.HEXChatColor;
import win.oreo.title.command.TitleCommand;
import win.oreo.title.util.TitleUtil;
import win.oreo.title.util.YmlManager;

public final class Main extends JavaPlugin {
    public YmlManager ymlManager;
    private TitleUtil titleUtil;

    public Main() {
        this.titleUtil = new TitleUtil();
    }

    @Override
    public void onEnable() {
        this.ymlManager = new YmlManager(this);

        Bukkit.getConsoleSender().sendMessage(HEXChatColor.toRainBow("[Title] Plugin Loaded!"));
        getCommand("title").setExecutor(new TitleCommand());

        titleUtil.initialize();
    }

    @Override
    public void onDisable() {
        titleUtil.save();
    }
}
