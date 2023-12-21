package io.github.nihl999.newyearplugin.commands;

import com.destroystokyo.paper.ParticleBuilder;
import dev.triumphteam.cmd.core.BaseCommand;
import dev.triumphteam.cmd.core.annotation.Command;
import dev.triumphteam.cmd.core.annotation.Default;
import io.github.nihl999.newyearplugin.NewYear;
import io.github.nihl999.newyearplugin.utils.BlockUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.time.Duration;

@Command("start")
public class NewYearStartCommand extends BaseCommand {
    private float seconds = 0;
    private float minutes = 0;

    public static BukkitTask timer = null;

    @Default()
    public void executor(Player sender) {
        seconds = 0;
        minutes = 0;
        NewYear.instance.itemDelivered = false;
        NewYear.instance.eventFailed = false;
        Location objectsSpawnLoc = sender.getLocation().add(sender.getLocation().getDirection().setY(0).multiply(2)).getBlock().getLocation().add(0.5, 0, 0.5);

        Location diamondSpawnLoc = objectsSpawnLoc.add(new Vector(0, 1.5, 0));
        ItemStack diamond = new ItemStack(Material.DIAMOND, 1);

        ParticleBuilder particleBuilder = new ParticleBuilder(Particle.REDSTONE);
        particleBuilder.location(diamondSpawnLoc);
        particleBuilder.color(Color.BLUE);
        particleBuilder.spawn();

        Item item = diamondSpawnLoc.getWorld().dropItem(diamondSpawnLoc, diamond);
        item.setCanPlayerPickup(false);
        item.setGravity(false);
        item.setVelocity(new Vector(0, 0, 0));
        item.setCanMobPickup(false);


        objectsSpawnLoc.add(0, -2.1, 0).getBlock().setType(Material.GLOWSTONE);
        Block chest = objectsSpawnLoc.add(0, 1.1, 0).getBlock();
        chest.setType(Material.CHEST);
        Directional chestDirection = (Directional) chest.getBlockData();
        chestDirection.setFacing(BlockUtils.getBlockFaceToLocation(chest, sender.getLocation()));
        chest.setBlockData(chestDirection);
        NewYear.instance.chest = ((Chest) chest.getState()).getBlockInventory().getHolder();

        Title.Times times = Title.Times.times(Duration.ofMillis(500), Duration.ofMillis(2000), Duration.ofMillis(500));
        Title eventStartTitle = Title.title(Component.text("Caçe este item!!").color(NamedTextColor.RED), Component.text("Você tem 10 minutos!").color(NamedTextColor.GOLD), times);
        sender.showTitle(eventStartTitle);
        NewYear.instance.eventStarted = true;
        timer = new BukkitRunnable() {
            @Override
            public void run() {
                seconds += 1;
                if (seconds % 60 == 0 && !NewYear.instance.eventFailed) {
                    sender.sendMessage((seconds / 60) + (seconds / 60 == 1 ? ChatColor.GREEN + " Minuto passou" : ChatColor.GREEN + " Minutos se passaram"));
                    minutes += 1;
                }
                if (minutes >= 10 && !NewYear.instance.itemDelivered) {
                    NewYear.instance.eventFailed = true;
                    Title.Times times = Title.Times.times(Duration.ofMillis(500), Duration.ofMillis(2000), Duration.ofMillis(500));
                    Title failedEventTitle = Title.title(Component.text("Papai noel está triste com você!!").color(NamedTextColor.RED), Component.text("Você falhou ao entregar o presente dele!").color(NamedTextColor.GOLD), times);
                    sender.showTitle(failedEventTitle);
                    timer.cancel();
                }
            }
        }.runTaskTimer(NewYear.instance, 0L, 20L);

    }
}
