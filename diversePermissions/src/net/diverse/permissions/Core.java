package net.diverse.permissions;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import net.diverse.permissions.events.*;

public class Core extends JavaPlugin {
	
	public static HashMap<UUID, PermissionAttachment> playerPermissions = new HashMap<UUID, PermissionAttachment>();	
	public static Core core;
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
	    Core.getCore().getConfig();
	    saveDefaultConfig();
        enableEvents();
        enableCommands();
        
		Bukkit.getConsoleSender().sendMessage("[" + Core.getCore().getDescription().getName() + "] Has been successfully activated");
	}
	
	public void onDisable() {
		playerPermissions.clear();
	}
	
	public void enableEvents() {
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new Join(), this);
		pm.registerEvents(new Quit(), this);
	}
	
	public void enableCommands() {
		//getCommand("help").setExecutor(new Help());
	}
	
	public static String Color(String text) {
		String finaltext = ChatColor.translateAlternateColorCodes('&', text);
	    return finaltext;
	}
	
	public static void setupPermissions(Player p) {
		PermissionAttachment attachment = p.addAttachment(Core.core);
		playerPermissions.put(p.getUniqueId(), attachment);
		permissionsSetter(p.getUniqueId());
	}

	private static void permissionsSetter(UUID uuid) {
		PermissionAttachment attachment = playerPermissions.get(uuid);
		
		for(String groups : Core.getCore().getConfig().getConfigurationSection("groups").getKeys(false)) {
			for(String permissions : Core.getCore().getConfig().getStringList("groups." + groups + ".permissions")) {
				attachment.setPermission(permissions, true);
			}
		}
	}
}
