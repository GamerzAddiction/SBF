package net.gamerzaddiction.gamermanh.arenas;

import java.util.ArrayList;
import java.util.List;

import net.gamerzaddiction.gamermanh.Configs.ArenasLoader;
import net.gamerzaddiction.gamermanh.extras.SnowballFightException;
import net.gamerzaddiction.gamermanh.extras.StringsContainer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Arena extends IArena {
	private ArenaStatus status;
	private ArenaType type;
	private List<Player> players = new ArrayList<Player>();
	private ArenasLoader loader;
	
	public Arena(String name) {
		super(name);
	}

	@Override
	public ArenaStatus getStatus() {
		return status;
	}

	@Override
	public ArenaType getType() {
		return type;
	}

	@Override
	public void setType(ArenaType type) {
		this.type = type;
	}

	@Override
	public void setStatus(ArenaStatus status) {
		this.status = status;
	}
	
	public Location getLobbyLocation(){
		Location location = new Location(Bukkit.getWorld(loader.getCustomConfig2().getString("arenas."+getName()+".lobby.world")), loader.getCustomConfig2().getDouble("arenas."+getName()+".lobby.x"), loader.getCustomConfig2().getDouble("arenas."+getName()+".lobby.y"), loader.getCustomConfig2().getDouble("arenas."+getName()+".lobby.z"));
		return location;
	}
	
	public Location getSpawnLocation(){
		Location location = new Location(Bukkit.getWorld(loader.getCustomConfig2().getString("arenas."+getName()+".spawn.world")), loader.getCustomConfig2().getDouble("arenas."+getName()+".spawn.x"), loader.getCustomConfig2().getDouble("arenas."+getName()+".spawn.y"), loader.getCustomConfig2().getDouble("arenas."+getName()+".spawn.z"));
		return location;
	}
	
	public List<Player> getPlayers(){
		return players;
	}
	
	protected void addPlayer(Player p){
		players.add(p);
	}
	
	protected void removePlayer(Player p){
		players.remove(p);
	}
	
	public boolean isInArena(Player p){
		if (players.contains(p)){
			return true;
		}
		return false;
	}
	
	public void joinArena(Player p){
		if (isInArena(p)){
			throw new SnowballFightException(p.getName()+" is trying to join an arena when already in one!");
		}
		switch (getStatus()){
		case CLOSING:
			p.sendMessage(StringsContainer.getPrefix()+ChatColor.GRAY+" Arena "+ChatColor.RED+getName()+ChatColor.GRAY+" is cleaning up.");
			p.sendMessage(ChatColor.YELLOW+"Please, wait for a couple of seconds.");
			break;
		case DISABLED:
			p.sendMessage(StringsContainer.getPrefix()+ChatColor.GRAY+" Arena "+ChatColor.DARK_RED+getName()+ChatColor.GRAY+" is currently not in operation!");
			break;
		case LOBBY:
			if (getPlayers().size() <= 20){ // You can change however many players should be it
				p.sendMessage(StringsContainer.getPrefix()+ChatColor.GOLD+" Arena "+getName()+" has been "+ChatColor.BOLD+"joined!");
				p.sendMessage(ChatColor.DARK_GRAY+"There are currently "+getPlayers().size()+" players in this arena.");
				onArenaJoin(p);
				return;
			}
			p.sendMessage(StringsContainer.getPrefix()+ChatColor.GRAY+" Arena "+ChatColor.RED+getName()+ChatColor.GRAY+" is full with "+getPlayers().size() +" players!");
			break;
		case PREPARING:
			p.sendMessage(StringsContainer.getPrefix()+ChatColor.GRAY+" Arena "+ChatColor.LIGHT_PURPLE+getName()+ChatColor.GRAY+" is preparing to start a new match.");
			break;
		case STARTED:
			p.sendMessage(StringsContainer.getPrefix()+ChatColor.GRAY+" Arena "+ChatColor.AQUA+getName()+ChatColor.GRAY+" is in session.");
			break;
		case WAITING:
			p.sendMessage(StringsContainer.getPrefix()+ChatColor.GOLD+" Arena "+getName()+" has been "+ChatColor.BOLD+"joined!");
			p.sendMessage(ChatColor.DARK_GRAY+"There are currently "+getPlayers().size()+" players in this arena.");
			onArenaJoin(p);
			break;
		default:
			throw new SnowballFightException(" Arena "+getName()+" messed up. Contact the SF developers pronto.");
		}
	}
	
	public void leaveArena(Player p){
		if (isInArena(p)){
			removePlayer(p);
			p.sendMessage(StringsContainer.getPrefix()+ChatColor.AQUA+" Left "+ChatColor.BOLD+"Arena "+getName());
			return;
		}
		p.sendMessage(StringsContainer.getPrefix()+ChatColor.RED+" You are not in an arena!");
	}
	
	private void onArenaJoin(Player p){
		p.teleport(getLobbyLocation());
		p.setGameMode(GameMode.ADVENTURE);
	}

}
