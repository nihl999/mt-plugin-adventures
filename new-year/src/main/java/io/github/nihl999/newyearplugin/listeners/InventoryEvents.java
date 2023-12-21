package io.github.nihl999.newyearplugin.listeners;

import io.github.nihl999.newyearplugin.NewYear;
import io.github.nihl999.newyearplugin.commands.NewYearStartCommand;
import io.github.nihl999.newyearplugin.utils.ItemUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Material;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.time.Duration;

public class InventoryEvents implements Listener {
    @EventHandler
    public void onItemInventoryClick(InventoryCloseEvent event) {
        if (NewYear.instance.eventFailed) return;
        if (NewYear.instance.itemDelivered) return;
        if (event.getView().getTopInventory().getType() != InventoryType.CHEST) return;
        if (!event.getView().getTopInventory().contains(new ItemStack(Material.DIAMOND, 1))) return;
        if (!event.getView().getTopInventory().getHolder().equals(NewYear.instance.chest)) return;
        NewYear.instance.itemDelivered = true;

        Title.Times times = Title.Times.times(Duration.ofMillis(500), Duration.ofMillis(2000), Duration.ofMillis(500));
        Title eventStartTitle = Title.title(Component.text("OS CÉUS O CHAMAM!!").color(NamedTextColor.GREEN), Component.text("T⍑ᒷ ∷╎!¡ᒷ ℸ ̣ ᔑᓭℸ ̣ ᒷ \uD835\uDE79⎓ ᓵ⍑ᒷᒷᓭᒷ ╎ᒲ!¡∷\uD835\uDE79⍊ᒷᓭ ∴╎ℸ ̣ ⍑ ᔑ⊣ᒷ. T⍑ᒷ∷ᒷ ᔑ∷ᒷ ᒲ\uD835\uDE79∷ᒷ ℸ ̣ ⍑ᔑリ ℸ ̣ ∴\uD835\uDE79 ⎓ᔑᓵℸ ̣ \uD835\uDE79∷ᓭ ⍑ᒷ∷ᒷ").color(NamedTextColor.AQUA), times);
        NewYear.instance.host.showTitle(eventStartTitle);
        NewYearStartCommand.timer.cancel();
        World world = NewYear.instance.host.getLocation().getWorld();
        world.strikeLightningEffect(NewYear.instance.host.getLocation());
        world.setStorm(true);
        ItemStack trident = ItemUtils.CreateTrident();
        NewYear.instance.host.getInventory().addItem(trident);
    }

}
