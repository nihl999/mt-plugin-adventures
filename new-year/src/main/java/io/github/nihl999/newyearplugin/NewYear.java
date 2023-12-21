package io.github.nihl999.newyearplugin;

import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

import dev.triumphteam.cmd.bukkit.BukkitCommandManager;
import io.github.nihl999.newyearplugin.commands.NewYearStartCommand;
import io.github.nihl999.newyearplugin.listeners.InventoryEvents;
import io.github.nihl999.newyearplugin.listeners.JoinEvents;
import io.github.nihl999.newyearplugin.listeners.WorldEvents;
import io.github.nihl999.newyearplugin.listeners.PlayerEvents;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;

public final class NewYear extends JavaPlugin {

    //TERRIVEL PROGRAMAÇAO CHEEEEEEIO DE PUBLICKKKKK Deus negue os getters e setters
    public static NewYear instance;
    public static Logger logger;
    public static Config config;

    public Player host;

    public ArrayList<UUID> friends = new ArrayList<UUID>();

    public InventoryHolder chest;
    public boolean itemDelivered = false;
    public boolean eventStarted = false;

    public boolean eventFailed = false;


    @Override
    public void onEnable() {
        instance = this;
        logger = getLogger();
        saveDefaultConfig();
        config = new Config(this.getConfig());
        instance.getServer().getPluginManager().registerEvents(new JoinEvents(), this);
        instance.getServer().getPluginManager().registerEvents(new WorldEvents(), this);
        instance.getServer().getPluginManager().registerEvents(new InventoryEvents(), this);
        instance.getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        BukkitCommandManager<CommandSender> manager = BukkitCommandManager.create(this);
        manager.registerCommand(new NewYearStartCommand());
        logger.info("[New Year] Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        logger.info("[New Year] Plugin Disabled!");
    }

}
