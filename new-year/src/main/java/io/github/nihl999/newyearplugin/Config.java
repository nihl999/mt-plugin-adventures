package io.github.nihl999.newyearplugin;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    public String hostPlayerNick = null;
    public Config(FileConfiguration config) {
        hostPlayerNick = config.getString("HostPlayerNick");
    }
}
