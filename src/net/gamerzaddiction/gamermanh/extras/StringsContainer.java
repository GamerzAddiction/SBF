package net.gamerzaddiction.gamermanh.extras;

import org.bukkit.ChatColor;

public class StringsContainer {
	private static String prefix = ChatColor.translateAlternateColorCodes('&', "&6[&bSBF&6]&r");
	private static String Test = ChatColor.translateAlternateColorCodes('&', prefix + "This is a temp command text until we make the real one work");
	public static String getPrefix(){
		return prefix;
		}
	public static String getTest() {
		return Test;
		}
	}