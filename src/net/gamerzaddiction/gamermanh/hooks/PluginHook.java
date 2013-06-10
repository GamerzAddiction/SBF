package net.gamerzaddiction.gamermanh.hooks;

import org.bukkit.plugin.Plugin;

public abstract class PluginHook {

public abstract Plugin getPlugin();

public abstract HookSeverity getHookSeverity();

}