package net.gamerzaddiction.gamermanh.hooks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class TagAPIHook extends PluginHook {
private Plugin tagAPI = Bukkit.getPluginManager().getPlugin("TagAPI");

@Override
public Plugin getPlugin() {
return tagAPI;
}

@Override
public HookSeverity getHookSeverity() {
return HookSeverity.SOFT;
}

}