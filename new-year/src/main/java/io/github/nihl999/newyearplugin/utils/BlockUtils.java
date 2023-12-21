package io.github.nihl999.newyearplugin.utils;

import io.github.nihl999.newyearplugin.NewYear;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;

public class BlockUtils {

    public static BlockFace getBlockFaceToLocation(Block block, Location loc) {
        Location blockLocation = block.getLocation();

        float xOffset = loc.getBlockX() - blockLocation.getBlockX();
        float zOffset = loc.getBlockZ() - blockLocation.getBlockZ();
        if (Math.abs(xOffset) > Math.abs(zOffset)) {
            NewYear.logger.info(xOffset > 0 ? "EAST" : "WEST");
            return xOffset > 0 ? BlockFace.EAST : BlockFace.WEST;
        }
        NewYear.logger.info(zOffset > 0 ? "SOUTH" : "NORTH");
        return zOffset > 0 ? BlockFace.SOUTH : BlockFace.NORTH;
    }
}
