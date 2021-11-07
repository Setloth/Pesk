package me.echo.pesk;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Pesk extends JavaPlugin {

    Pesk instance;
    SkriptAddon addon;

    @Override
    public void onEnable() {

        long start = System.currentTimeMillis();
//        int id = 9344;
        this.instance = this;

        addon = Skript.registerAddon(this);

        try {
//            new MetricsLite(this, id);
            Bukkit.getLogger().info("Loading classes...");
            addon.loadClasses("me.echo.pesk", "elements");
            Bukkit.getLogger().info(ChatColor.GOLD+"[PESK] "+ChatColor.BLUE+"Done - "+(System.currentTimeMillis() - start)+" ms");
        }
        catch (IOException e){
            Bukkit.getLogger().severe(ChatColor.RED+"There was a severe error while loading "+this.getName()+"...");
            e.printStackTrace();
        }

    }

    public Pesk getInstance() {
        return this.instance;
    }

    public SkriptAddon getAddonInstance() {
        return this.addon;
    }

}
