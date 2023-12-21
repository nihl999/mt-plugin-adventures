package io.github.nihl999.newyearplugin.listeners;

import io.github.nihl999.newyearplugin.NewYear;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.util.BiomeSearchResult;

public class WorldEvents implements Listener {
    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        World world = event.getWorld();
        if (world.getEnvironment() != World.Environment.NORMAL) {
            return;
        }
        Location spawnPoint = world.getSpawnLocation();
        Biome spawnBiome = world.getBiome(spawnPoint);
        if (spawnBiome == Biome.BEACH) {
            NewYear.logger.info("[New Year] Spawn already on beach!");
            return;
        }
        BiomeSearchResult beachBiome = world.locateNearestBiome(spawnPoint, 10000, Biome.BEACH);
        if (beachBiome == null) {
            //todo continuar buscando pra alterar o spawn point, talvez travando o join até lá? algo do tipo
            NewYear.logger.warning("[New Year] Couldn't find a beach biome at most 5000 blocks from spawn");
            return;
        }
        Location beachLocation = beachBiome.getLocation();
        NewYear.logger.info("[New Year] Found beach biome!");
        world.setSpawnLocation(beachLocation);
    }
}
