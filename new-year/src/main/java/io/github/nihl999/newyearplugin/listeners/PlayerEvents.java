package io.github.nihl999.newyearplugin.listeners;

import io.github.nihl999.newyearplugin.NewYear;
import io.github.nihl999.newyearplugin.utils.ItemUtils;
import io.github.nihl999.newyearplugin.utils.WorldUtils;
import org.bukkit.*;
import org.bukkit.block.data.Directional;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRiptideEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class PlayerEvents implements Listener {
    private boolean firstRiptide = true;
    private Random random = new Random();
    
    @EventHandler
    public void onPlayerRiptide(PlayerRiptideEvent event) {
        Player player = event.getPlayer();
        Location playerLoc = player.getLocation();
        if (!player.equals(NewYear.instance.host)) return;
        if (!firstRiptide) return;
        List<Player> players = new ArrayList<>();
        for (UUID playerUUID : NewYear.instance.friends) {
            Player pl = Bukkit.getPlayer(playerUUID);
            if (pl != null) players.add(pl);
        }
        WorldUtils.StrikeLightningOnPlayers(player.getWorld(), players, false);
        ItemStack elytra = ItemUtils.CreateElytra();
        ItemStack firework = new ItemStack(Material.FIREWORK_ROCKET, 128);
        for (Player pl : players) {
            Location loc = pl.getLocation();
            Vector direction = playerLoc.getDirection();
            direction.setY(0.4);
            loc.setY(playerLoc.getY() + 50);
            loc.setDirection(direction);
            PlayerInventory plInv = pl.getInventory();
            pl.teleport(loc);
            plInv.setChestplate(elytra);
            plInv.addItem(firework);
            plInv.setItemInOffHand(firework);
            pl.setFlying(false);
            pl.setGliding(true);

        }
        firstRiptide = false;
        player.getInventory().setChestplate(elytra);
        player.setGliding(true);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(NewYear.instance, () -> {
            if (Bukkit.getServer().getOnlinePlayers().isEmpty()) return;
            Location hostLoc = NewYear.instance.host.getLocation();
            Location fwLoc = new Location(hostLoc.getWorld(), hostLoc.getX() + random.nextDouble(-30, 31), hostLoc.getY() + random.nextFloat(-10, 11), hostLoc.getZ() + random.nextFloat(-30, 31));
            Location fwLoc2 = new Location(hostLoc.getWorld(), hostLoc.getX() + random.nextDouble(-30, 31), hostLoc.getY() + random.nextFloat(-10, 11), hostLoc.getZ() + random.nextFloat(-30, 31));
            Location fwLoc3 = new Location(hostLoc.getWorld(), hostLoc.getX() + random.nextDouble(-30, 31), hostLoc.getY() + random.nextFloat(-10, 11), hostLoc.getZ() + random.nextFloat(-30, 31));
            WorldUtils.ExplodeFireworkInLocation(fwLoc);
            WorldUtils.ExplodeFireworkInLocation(fwLoc2);
            WorldUtils.ExplodeFireworkInLocation(fwLoc3);
        }, 0L, 10L);
    }
}
