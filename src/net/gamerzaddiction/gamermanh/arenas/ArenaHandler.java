package net.gamerzaddiction.gamermanh.arenas;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.gamerzaddiction.gamermanh.SnowballFight;
import net.gamerzaddiction.gamermanh.Configs.ArenasLoader;
import net.gamerzaddiction.gamermanh.extras.ArenaInUsageException;
import net.gamerzaddiction.gamermanh.extras.ArenaNotFoundException;

public class ArenaHandler {
	private ArenasLoader loader;
	private SnowballFight sbf;
	private List<Arena> arenas = new ArrayList<Arena>();
	
	public List<Arena> getArenas(){
		return arenas;
	}
	
	public void createArena(String name) throws ArenaInUsageException {
		for (Arena a : arenas){
			if (a.getName().equals(name)){
				throw new ArenaInUsageException();
			}
		}
		Arena arena = new Arena(name);
		arenas.add(arena);
	}
	
	public Arena getArenaByName(String name) throws ArenaNotFoundException {
		for (Arena a : arenas){
			if (a.getName().equals(name)){
				return arenas.get(arenas.indexOf(a));
			}
		}
		throw new ArenaNotFoundException();
	}
	
	
	public void startup(SnowballFight plugin){
		sbf = plugin;
		Logger.getLogger("Minecraft").log(Level.INFO, "[SBF] [Arenas] Quening currently.");
		sbf.getServer().getScheduler().runTaskTimer(sbf, new Runnable(){

			@Override
			public void run() {
				if (loader.getCustomConfig2().getStringList("arenas.list") != null) {
					for (String s : loader.getCustomConfig2().getStringList("arenas.list")){
						try {
							createArena(s);
						} catch (ArenaInUsageException e) {
							e.printStackTrace();
							// Print it because the arena list should be null right ...
						}
					}
				}
			}
			
		}, 0, 1);
		
		for (Arena a : arenas){
			a.setStatus(ArenaStatus.DISABLED);
		}
		
		Logger.getLogger("Minecraft").log(Level.INFO, "[SBF] [Arenas] Loaded the following arenas on startup:");
		Logger.getLogger("Minecraft").log(Level.INFO, arenas.toString());
	}
	
	public void shutdown(){
		arenas.clear();
		loader.saveCustomConfig2();
		Logger.getLogger("Minecraft").log(Level.INFO, "[SBF] [Arenas] Saved arenas onto flatfile storage.");
		sbf.getServer().getScheduler().cancelTasks(sbf);
	}
	
	public void setEnabled(Arena a){
		a.setStatus(ArenaStatus.WAITING);
	}
	
	public void setDisabled(Arena a){
		a.setStatus(ArenaStatus.DISABLED);
	}
	
}
