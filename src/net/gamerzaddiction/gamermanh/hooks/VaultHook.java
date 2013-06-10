package net.gamerzaddiction.gamermanh.hooks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class VaultHook extends PluginHook {
private Plugin vault = Bukkit.getPluginManager().getPlugin("Vault");

@Override
public Plugin getPlugin() {
return vault;
}

@Override
public HookSeverity getHookSeverity() {
return HookSeverity.SOFT;
}

}