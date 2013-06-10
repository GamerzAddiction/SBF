package net.gamerzaddiction.gamermanh.Configs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.gamerzaddiction.gamermanh.SnowballFight;

public class ArenasLoader {
	private SnowballFight plugin;

	public ArenasLoader(SnowballFight plugin) {
		this.plugin = plugin;
	}
	//this class is dedicated to the arenas.yml
    private FileConfiguration customConfig2 = null;
    private File customConfigFile2 = null;
    
    public void reloadCustomConfig2() {
		if(customConfigFile2 == null) {
			customConfigFile2 = new File(plugin.getDataFolder(), "arenas.yml");
		}
		customConfig2 = YamlConfiguration.loadConfiguration(customConfigFile2);
		
		InputStream defConfigStream = plugin.getResource("arenas.yml");
		if(defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			customConfig2.setDefaults(defConfig);
		}
	}
	public FileConfiguration getCustomConfig2() {
		if(customConfig2 == null) {
			this.reloadCustomConfig2();
		}
		return customConfig2;
	}
    public void saveCustomConfig2() {
        if (customConfig2 == null || customConfigFile2 == null) {
        return;
        }
        try {
            getCustomConfig2().save(customConfigFile2);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save arenas file to " + customConfigFile2, ex);
        }
    }
    public void saveDefaultConfig2() {
        if (customConfigFile2 == null) {
            customConfigFile2 = new File(plugin.getDataFolder(), "arenas.yml");
        }
        if (!customConfigFile2.exists()) {            
             plugin.saveResource("arenas.yml", false);
         }
    }

}
