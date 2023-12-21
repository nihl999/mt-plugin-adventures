package io.github.nihl999.newyearplugin.utils;

import io.github.nihl999.newyearplugin.NewYear;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class WorldUtils {

    private static FireworkEffect fireworkEffect = FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withFade(Color.AQUA, Color.FUCHSIA, Color.NAVY, Color.YELLOW).withColor(Color.ORANGE, Color.BLUE, Color.RED, Color.LIME).withFlicker().withTrail().build();

    public static void StrikeLightningOnPlayersUUID(World world, List<UUID> players, boolean damage) {
        List<Location> locs = new ArrayList<>();
        for (UUID playerUUID : players) {
            Player pl = Bukkit.getPlayer(playerUUID);
            if (pl != null) locs.add(pl.getLocation());
        }
        StrikeLightingOnLocations(world, locs, damage);
        return;
    }

    public static void StrikeLightningOnPlayers(World world, List<Player> players, boolean damage) {
        List<Location> locs = players.stream().map(Entity::getLocation).toList();
        StrikeLightingOnLocations(world, locs, damage);
        return;
    }

    public static void StrikeLightingOnLocations(World world, List<Location> locs, boolean damage) {
        for (Location loc : locs) {
            if (damage) {
                //provavelmente seria legal ter um logger utils algo assim
                world.strikeLightning(loc);
                continue;
            }
            world.strikeLightningEffect(loc);
        }
    }

    public static void ExplodeFireworkInLocation(Location loc) {
        Firework fw = loc.getWorld().spawn(loc, Firework.class);
        FireworkMeta fwmeta = fw.getFireworkMeta();
        fwmeta.addEffect(fireworkEffect);
        fwmeta.setPower(3);
        fw.setFireworkMeta(fwmeta);
        fw.setTicksLived(3);
        fw.detonate();
        NewYear.logger.info("FW DETONATED");
        NewYear.instance.host.sendMessage("FW Dt");
    }
}
