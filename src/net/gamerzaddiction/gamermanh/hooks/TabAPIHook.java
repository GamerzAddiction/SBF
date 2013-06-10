package net.gamerzaddiction.gamermanh.hooks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class TabAPIHook extends PluginHook {
private Plugin tabAPI = Bukkit.getPluginManager().getPlugin("TabAPI");

@Override
public Plugin getPlugin() {
return tabAPI;
}

@Override
public HookSeverity getHookSeverity() {
return HookSeverity.SOFT;
}

}