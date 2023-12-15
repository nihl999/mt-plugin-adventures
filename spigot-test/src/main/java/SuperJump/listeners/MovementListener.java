package SuperJump.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import SuperJump.SuperJump;

/**
 * OnPlayerCrouch
 */

// todo Timer?

public class MovementListener implements Listener {

  SuperJump plugin;

  public MovementListener(SuperJump plugin) {
    this.plugin = plugin;
  }

  @EventHandler(priority = EventPriority.NORMAL)
  public void onPlayerCrouch(PlayerMoveEvent event) {
    if (event.isCancelled())
      return;

    Player player = event.getPlayer();
    List<MetadataValue> metadata = player.getMetadata("SuperJump.Crouched");
    plugin.getLogger().info("[SuperJump] Player " + player.getName());
    plugin.getLogger().info("[SuperJump] metadata empty: " + metadata.isEmpty());
    Vector playerVelocity = player.getVelocity();
    if (player.isSneaking()) {
      if (metadata.isEmpty()) {
        player.setMetadata("SuperJump.Crouched", new FixedMetadataValue(plugin,
            true));
      }
      if (metadata.get(0).asBoolean()) {
        return;
      }
      player.setMetadata("SuperJump.Crouched", new FixedMetadataValue(plugin,
          true));
      player.sendMessage("Ta encucurado safado?");
    } else {
      player.setMetadata("SuperJump.Crouched", new FixedMetadataValue(plugin,
          false));

    }
  }

  @EventHandler(priority = EventPriority.HIGH)
  public void onPlayerJump(PlayerMoveEvent event) {
    if (event.isCancelled())
      return;

    Player player = event.getPlayer();
    List<MetadataValue> metadata = player.getMetadata("SuperJump.Crouched");
    plugin.getLogger().info("[SuperJump] Player " + player.getName());
    plugin.getLogger().info("[SuperJump] metadata empty: " + metadata.isEmpty());
    Boolean playerCanSuperJump = !metadata.isEmpty() ? metadata.get(0).asBoolean() : false;
    // if (!metadata.isEmpty()) {
    // if (metadata.get(0).asBoolean()) {
    // player.sendMessage("Ta encucurado safado no onPlayerJump?");
    // }
    // return;
    // }

    if (player.getVelocity().getY() > 0) {
      double jumpVelocity = (double) 0.42F;
      if (player.hasPotionEffect(PotionEffectType.JUMP)) {
        jumpVelocity += (double) ((float) (player.getPotionEffect(PotionEffectType.JUMP).getAmplifier() + 1) * 0.1F);
      }
      // if (event.getPlayer().getLocation().getBlock().getType() != Material.LADDER
      if (!player.isClimbing()
          && playerCanSuperJump) {
        if (Double.compare(player.getVelocity().getY(),
            jumpVelocity) == 0) {
          player.sendMessage("Jumping!");
          player.setMetadata("SuperJump.Jumped", new FixedMetadataValue(plugin, true));
          player.setMetadata("SuperJump.Crouched", new FixedMetadataValue(plugin, false));
          player.setVelocity(player.getLocation().getDirection().multiply(3));
        }
      }
    }
  }

  // todo DamageListener
  @EventHandler(priority = EventPriority.HIGH)
  public void onPlayerDamage(EntityDamageEvent event) {
    if (event.isCancelled())
      return;
    if (!(event.getEntity() instanceof Player)) {
      return;
    }
    Player player = (Player) event.getEntity();
    List<MetadataValue> metadata = player.getMetadata("SuperJump.Jumped");
    plugin.getLogger().info("[SuperJump] Player " + player.getName());
    plugin.getLogger().info("[SuperJump] metadata empty: " + metadata.isEmpty());
    Boolean playerSuperJumped = !metadata.isEmpty() ? metadata.get(0).asBoolean() : false;
    // if (!metadata.isEmpty()) {
    // if (metadata.get(0).asBoolean()) {
    // player.sendMessage("Ta encucurado safado no onPlayerJump?");
    // }
    // return;
    // }

    if (playerSuperJumped) {
      event.setCancelled(true);
    }
  }
}
