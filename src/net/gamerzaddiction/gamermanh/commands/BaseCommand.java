package net.gamerzaddiction.gamermanh.commands;

import net.gamerzaddiction.gamermanh.SnowballFight;
import net.gamerzaddiction.gamermanh.Configs.StringsLoader;
import net.gamerzaddiction.gamermanh.arenas.ArenaHandler;
import net.gamerzaddiction.gamermanh.extras.ArenaInUsageException;
import net.gamerzaddiction.gamermanh.extras.ArenaNotFoundException;
import net.gamerzaddiction.gamermanh.extras.StringsContainer;
import net.gamerzaddiction.gamermanh.arenas.Arena;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BaseCommand implements CommandExecutor {

	private SnowballFight plugin;
	private StringsLoader loader2;
	private ArenaHandler ah;

	public BaseCommand(SnowballFight plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		loader2 = plugin.loader2;
		String prefix = StringsContainer.getPrefix();
		String test = StringsContainer.getTest();
		String teststring = loader2.getCustomConfig1().getString("Test-Message");
		
		if(cmd.getName().equalsIgnoreCase("sbf")) {
			if(args.length >= 4) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cI think you need to check your arguments lenth..."));
				return true;
			}
			else if(args.length == 2) {
				//gonna get rid of this test command eventually
				if(args[0].equalsIgnoreCase("test")) {
					if(args[1].equalsIgnoreCase("string")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + teststring));
						return true;
						}
				}
				if(args[0].equalsIgnoreCase("help")) {
					if(args[1].equalsIgnoreCase("admin")) {
						sender.sendMessage("§4§l--------- §bSBF §cAdmin §bCommands§4§l ---------");
						sender.sendMessage(prefix + "§3/sbf help admin");
						sender.sendMessage(prefix + "§d/sbf reload");
						sender.sendMessage(prefix + "§d/sbf version");
						sender.sendMessage(prefix + "§d/sbf debug");
						return true;
					}
					if(args[1].equalsIgnoreCase("arena")) {
						sender.sendMessage("§4§l--------- §bSBF §2Arena §bCommands§4 §l---------");
						sender.sendMessage("§3/sbf arena");
						sender.sendMessage("§d/sbf createarena <arena ID>");
						sender.sendMessage("§d/sbf delarena <arena ID>");
						sender.sendMessage("§d/sbf start <arena ID>");
						sender.sendMessage("§d/sbf stop <arena ID>");
						sender.sendMessage("§d/sbf enable <arena ID>");
						sender.sendMessage("§d/sbf disable <arena ID>");
						return true;
					}
					if(args[1].equalsIgnoreCase("user")) {
						sender.sendMessage("§4§l--------- §bSBF §6User §bCommands§4 §l---------");
						sender.sendMessage(prefix + "§3/sbf help user");
						sender.sendMessage(prefix + "§d/sbf join <arena#>");
						sender.sendMessage(prefix + "§d/sbf spectate <arena#>");
						sender.sendMessage(prefix + "§d/sbf lobby");
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("reload")) {
					if(args[1].equalsIgnoreCase("config")) {
						this.plugin.reloadConfig();
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&bSnowballFight's &econfig &chas been &areloaded&c!"));
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("arena")) {
				}
				if(args[0].equalsIgnoreCase("disable")) {
					try {
						ah.setDisabled(ah.getArenaByName(args[1]));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&bArena&c " + args[1] + " &bhas been disabled!"));
					} catch (ArenaNotFoundException e) {
						e.printStackTrace();
					}
					return true;
				}
				if(args[0].equalsIgnoreCase("enable")) {
					try {
						ah.setEnabled(ah.getArenaByName(args[1]));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&bArena&a " + args[1] + " &bhas been enabled!"));
						return true;
					} catch (ArenaNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(args[0].equalsIgnoreCase("create")) {
					try {
						ah.createArena(args[1]);
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&bArena &a" + args[1] + " &bhas been created!"));
					} catch (ArenaInUsageException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return true;
				}
				
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("reload")) {
					this.plugin.getPluginLoader().disablePlugin(plugin);
					this.plugin.getPluginLoader().enablePlugin(plugin);
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&bSnowballFight &c has been &areloaded&b!"));
					return true;
				}
				if(args[0].equalsIgnoreCase("help")) {
					sender.sendMessage(prefix + "Do §e/sbf help <arena | user | admin> &ffor commands");
					return true;
				}
				if(args[0].equalsIgnoreCase("join")) {
					//Send a message like "Which arena?!" followed by a list of arenas
					sender.sendMessage(test);
					return true;
				}
				if(args[0].equalsIgnoreCase("leave")) {
					//leave the arena, duh
					sender.sendMessage(test);
					return true;
					
				}
				if(args[0].equalsIgnoreCase("list")) {
					//list currently active players who are "In Game", "In Lobby", and have been "Knocked Out"
					return true;
				}
			}
			if(args.length == 3) {
				if(args[0].equalsIgnoreCase("arena")) {
					//will create the arenas
					if(args[1].equalsIgnoreCase("create")) {
						try {
							ah.createArena(args[2]);
						} catch (ArenaInUsageException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return true;
					}
				}
			}
				else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cI have no idea what you want me to do"));
					return true;
				}
				
			}
			else if(args.length == 0) {
				sender.sendMessage(prefix + "§4§k ---§fSnowball Fight§4§k ---");
				sender.sendMessage(prefix + "§cv"+plugin.getDescription().getVersion()+" by gamermanh and skyrimfan1");
				sender.sendMessage(prefix + "Do §e/sbf help <arena | user | admin> &ffor commands");
				return true;
			}
		return false;
	}
}