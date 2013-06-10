package net.gamerzaddiction.gamermanh.Configs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import net.gamerzaddiction.gamermanh.SnowballFight;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigLoader {
	private SnowballFight plugin;

	public ConfigLoader(SnowballFight plugin) {
		this.plugin = plugin;
	}
	//this class is dedicated to the config.yml
	private FileConfiguration customConfig = null;
    private File customConfigFile = null;
    
    
    public void reloadCustomConfig() {
		if(customConfigFile == null) {
			customConfigFile = new File(plugin.getDataFolder(), "config.yml");
		}
		customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
		
		InputStream defConfigStream = plugin.getResource("config.yml");
		if(defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			customConfig.setDefaults(defConfig);
		}
	}
	public FileConfiguration getCustomConfig() {
		if(customConfig == null) {
			this.reloadCustomConfig();
		}
		return customConfig;
	}
    public void saveCustomConfig() {
        if (customConfig == null || customConfigFile == null) {
        return;
        }
        try {
            getCustomConfig().save(customConfigFile);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
        }
    }
    public void saveDefaultConfig() {
        if (customConfigFile == null) {
            customConfigFile = new File(plugin.getDataFolder(), "config.yml");
        }
        if (!customConfigFile.exists()) {            
             plugin.saveResource("config.yml", false);
         }
    }

}
