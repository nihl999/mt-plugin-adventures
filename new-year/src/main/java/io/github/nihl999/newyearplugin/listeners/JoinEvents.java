package io.github.nihl999.newyearplugin.listeners;

import io.github.nihl999.newyearplugin.NewYear;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BiomeSearchResult;

import java.time.Duration;

public class JoinEvents implements Listener {


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player joiner = event.getPlayer();

        if (joiner.getName().equals(NewYear.config.hostPlayerNick)) {
            NewYear.instance.host = joiner;
            return;
        }
        NewYear.instance.friends.add(joiner.getUniqueId());
        return;
    }


}
