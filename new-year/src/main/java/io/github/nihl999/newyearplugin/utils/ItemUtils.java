package io.github.nihl999.newyearplugin.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

    public static ItemStack CreateTrident() {
        ItemStack trident = new ItemStack(Material.TRIDENT);
        ItemMeta tridentMeta = trident.getItemMeta();
        Component tridentName = Component.text("Tridente de Poseidon").color(NamedTextColor.GOLD);
        List<Component> tridentLore = new ArrayList<>();
        tridentLore.add(Component.text("Tridente ex-possuido pelo rei dos oceanos, deus dos mares, o MAIORAL."));
        tridentLore.add(Component.text("Contém o poder de voar!"));
        tridentMeta.displayName(tridentName);
        tridentMeta.lore(tridentLore);
        tridentMeta.addEnchant(Enchantment.RIPTIDE, 5, true);
        tridentMeta.setUnbreakable(true);
        trident.setItemMeta(tridentMeta);

        return trident;
    }

    public static ItemStack CreateElytra() {
        ItemStack elytra = new ItemStack(Material.ELYTRA);
        ItemMeta elytraMeta = elytra.getItemMeta();
        Component elytraName = Component.text("Asas do Anjo Gabriel").color(NamedTextColor.GOLD);
        List<Component> elytraLore = new ArrayList<>();
        elytraLore.add(Component.text("Asas provindas dos céus, entregues pelo próprio anjo Gabriel!"));
        elytraLore.add(Component.text("Contém o poder de voar!"));
        elytraMeta.displayName(elytraName);
        elytraMeta.lore(elytraLore);
//        elytraMeta.addEnchant(Enchantment.LUCK, 5, true);
        elytraMeta.setUnbreakable(true);
        elytra.setItemMeta(elytraMeta);
        return elytra;
    }

}
