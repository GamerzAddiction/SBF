package net.gamerzaddiction.gamermanh.Configs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import net.gamerzaddiction.gamermanh.SnowballFight;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class StringsLoader {
	private SnowballFight plugin;

	public StringsLoader(SnowballFight plugin) {
		this.plugin = plugin;
	}
	//this class is dedicated to the strings.yml
    private FileConfiguration customConfig1 = null;
    private File customConfigFile1 = null;
    
    
    public void reloadCustomConfig1() {
		if(customConfigFile1 == null) {
			customConfigFile1 = new File(plugin.getDataFolder(), "strings.yml");
		}
		customConfig1 = YamlConfiguration.loadConfiguration(customConfigFile1);
		
		InputStream defConfigStream = plugin.getResource("strings.yml");
		if(defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			customConfig1.setDefaults(defConfig);
		}
	}
	public FileConfiguration getCustomConfig1() {
		if(customConfig1 == null) {
			this.reloadCustomConfig1();
		}
		return customConfig1;
	}
    public void saveCustomConfig1() {
        if (customConfig1 == null || customConfigFile1 == null) {
        return;
        }
        try {
            getCustomConfig1().save(customConfigFile1);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save staff config file to " + customConfigFile1, ex);
        }
    }
    public void saveDefaultConfig1() {
        if (customConfigFile1 == null) {
            customConfigFile1 = new File(plugin.getDataFolder(), "strings.yml");
        }
        if (!customConfigFile1.exists()) {            
             plugin.saveResource("strings.yml", false);
         }
    }
}
