package net.diverse.mc;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.diverse.mc.commands.*;
import net.diverse.mc.events.*;
import net.diverse.mc.utils.TablistAPI;

public class Core extends JavaPlugin {
	
	public static ArrayList<String> vanishPlayers = new ArrayList<String>();
	
	public static Core core;

	public static String prefix = "";
	public Player p;
	FileConfiguration config = getConfig();
	PluginDescriptionFile pdf = getDescription();
	
	public Core() {
		core = this;
	}
	
	public static Core getCore() {
		return core;
	}

	public void onEnable() {
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			Core.vanishPlayers.remove(all.getName());
			all.setAllowFlight(false);
		}
		
	    Core.getCore().getConfig();
	    saveDefaultConfig();
        enableEvents();
        enableCommands();
        TablistAPI.changeTab();
        
		Bukkit.getConsoleSender().sendMessage("[" + Core.getCore().getDescription().getName() + "] Has been successfully activated");
	}
	
	public void enableEvents() {
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new NoExistCommand(), this);
		pm.registerEvents(new Motd(), this);
	}
	
	public void enableCommands() {
		getCommand("diversemc").setExecutor(new DiverseMC());
		getCommand("help").setExecutor(new Help());
		getCommand("gamemode").setExecutor(new Gamemode());
		getCommand("fly").setExecutor(new Fly());
		getCommand("vanish").setExecutor(new Vanish());
		getCommand("teleport").setExecutor(new Teleport());
	}
	
	public static String Color(String text) {
		String finaltext = ChatColor.translateAlternateColorCodes('&', text);
	    return finaltext;
	}
}
