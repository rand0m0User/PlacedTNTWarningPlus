package me.vlcik128.TNT;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    //I ❤ BOILERPLATE
    //I ❤ BOILERPLATE
    //I ❤ BOILERPLATE
    @Override
    public void onEnable() {
        this.getLogger().info("Plugin is enabled.");
        this.getServer().getPluginManager().registerEvents(new Events(), this);
    }
    //I ❤ BOILERPLATE
    //I ❤ BOILERPLATE
    //I ❤ BOILERPLATE
}
