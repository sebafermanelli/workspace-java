package net.diverse.trade;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.diverse.trade.commands.*;
import net.diverse.trade.inventories.*;

public class Core extends JavaPlugin {
	
	public static HashMap<Player, Player> requesttrade = new HashMap<Player, Player>();
	public static HashMap<Player, Player> playertrading = new HashMap<Player, Player>();
	
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
		
		for (Player all : Bukkit.getOnlinePlayers()) {
        	Core.requesttrade.remove(all);
        	Core.playertrading.remove(all);
        }
		
	    Core.getCore().getConfig();
	    saveDefaultConfig();
        enableEvents();
        enableCommands();
        
		Bukkit.getConsoleSender().sendMessage("[" + Core.getCore().getDescription().getName() + "] Has been successfully activated");
	}
	
	public void enableEvents() {
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new InventoryTrade(), this);
	}
	
	public void enableCommands() {
		getCommand("diversetrade").setExecutor(new DiverseTrade());
		getCommand("trade").setExecutor(new Trade());
	}
	
	public static String Color(String text) {
		String finaltext = ChatColor.translateAlternateColorCodes('&', text);
	    return finaltext;
	}
}
