package SuperJump;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import SuperJump.listeners.MovementListener;

public class SuperJump extends JavaPlugin {

  @Override
  public void onEnable() {
    getLogger().info("onEnable called");
    Bukkit.getPluginManager().registerEvents(new MovementListener(this), this);
  }

  @Override
  public void onDisable() {

  }

}
