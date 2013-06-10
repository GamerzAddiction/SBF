package net.gamerzaddiction.gamermanh;


import java.util.logging.Level;
import java.util.logging.Logger;

import net.gamerzaddiction.gamermanh.Configs.ArenasLoader;
import net.gamerzaddiction.gamermanh.Configs.ConfigLoader;
import net.gamerzaddiction.gamermanh.Configs.StringsLoader;
import net.gamerzaddiction.gamermanh.commands.BaseCommand;

import org.bukkit.plugin.java.JavaPlugin;

public class SnowballFight extends JavaPlugin {
	public ConfigLoader loader1;
	public StringsLoader loader2;
	public ArenasLoader loader3;

    private Logger log = Logger.getLogger("Minecraft");

	public Logger getMainLogger(){
		return log;
	}
	
	public void logInfo(String msg){
		getMainLogger().log(Level.INFO, "[SBF] "+msg);
	}
	
	@Override
	public void onEnable() {
		loader1 = new ConfigLoader(this);
		loader2 = new StringsLoader(this);
		loader3 = new ArenasLoader(this);
		// TODO Try updating maybe here?
		loader1.saveDefaultConfig();
		logInfo("Config successfully loaded.");
		loader2.saveDefaultConfig1();
		logInfo("Strings file successfully loaded.");
		loader3.saveDefaultConfig2();
		logInfo("Arenas file successfully loaded.");
		logInfo("Config successfully loaded.");

		getCommand("sbf").setExecutor(new BaseCommand(this));
		logInfo("Registered commands.");
		
		// Call last methods
		logInfo("Running v."+this.getDescription().getVersion()+" smoothly.");
		logInfo("Snowball Fight has been enabled!");
	}
	@Override
	public void onDisable(){
		getLogger().info("Snowball Fight has been disabled!");

	}

}
