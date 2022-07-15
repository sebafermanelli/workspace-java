package net.diverse.ffa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.diverse.ffa.commands.*;
import net.diverse.ffa.events.*;
import net.diverse.ffa.files.PlayerData;
import net.diverse.ffa.inventories.*;
import net.diverse.ffa.utils.*;

public class Core extends JavaPlugin {
	
	public static Connection connection;
	public String host, database, username, password;
	public int port;
	public static Core core;
	public static String prefix = "";
//	public static SettingsManager spawn,language;
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
//		spawn = new SettingsManager(this, "spawn");
//		language = new SettingsManager(this, "language");
		enableEvents();
		enableCommands();
		mysqlSetup();
		PlayerData.createTable();
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			if(!PlayerData.playerExists(all.getUniqueId())) {
				PlayerData.createPlayer(all.getUniqueId(), all);
			}
			
			new BukkitRunnable() {
				@Override
				public void run() {
					for(Player online : Bukkit.getOnlinePlayers()) {
						ScoreboardManage sm = new ScoreboardManage(online);
						sm.loadScoreboard();
					}
				}
			}.runTaskTimer(Core.getCore(), 0, 20);
			
			if(all.hasPlayedBefore()) {
				if(!Core.getCore().getConfig().contains("Spawn")){
					all.sendMessage(Core.Color("&c&lERROR: &7El spawn no existe, usa: &e/diverseffa setspawn&7."));
				}
				
				new LocationAPI();
				all.teleport(LocationAPI.getLoc(Core.getCore().getConfig().getString("Spawn")));
				all.playSound(all.getEyeLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
				all.getInventory().setHeldItemSlot(0);
				all.setFoodLevel(all.getFoodLevel());
				all.setHealth(all.getMaxHealth());
				all.setGameMode(GameMode.SURVIVAL);
				all.setExp(0.0F);
			    all.setLevel(0);
			    all.setFireTicks(0);
				PlayerData.updateStreak(all.getUniqueId(), 0);
				all.setPlayerListName(Core.Color("&e" + all.getName()));
				
				if(Join.isEmpty(all.getInventory())) {
					Kit.addKit(all);
				}
			}
			
			if(!all.hasPlayedBefore()) {
				
				if(!Core.getCore().getConfig().contains("Spawn")){
					all.sendMessage(Core.Color("&c&lERROR: &7El spawn no existe, usa: &e/diverseffa setspawn&7."));
				}
				
				new LocationAPI();
				all.teleport(LocationAPI.getLoc(Core.getCore().getConfig().getString("Spawn")));
				all.playSound(all.getEyeLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
				all.getInventory().setHeldItemSlot(0);
				all.setFoodLevel(all.getFoodLevel());
				all.setHealth(all.getMaxHealth());
				all.setGameMode(GameMode.SURVIVAL);
				all.setExp(0.0F);
			    all.setLevel(0);
			    all.setFireTicks(0);
				Kit.addKit(all);
				PlayerData.updateStreak(all.getUniqueId(), 0);
				all.setPlayerListName(Core.Color("&e" + all.getName()));
			}
		}
        
		Bukkit.getConsoleSender().sendMessage("[" + Core.getCore().getDescription().getName() + "] Has been successfully activated");
	}
	
	public void mysqlSetup() {
		host = Core.getCore().getConfig().getString("MySQL.Host");
		port = Core.getCore().getConfig().getInt("MySQL.Port");
		database = Core.getCore().getConfig().getString("MySQL.Database");
		username = Core.getCore().getConfig().getString("MySQL.Username");
		password = Core.getCore().getConfig().getString("MySQL.Password");
		
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				synchronized(this) {
					if(getConnection() != null && !getConnection().isClosed()) {
						return;
					}
					
					Class.forName("com.mysql.jdbc.Driver");
					setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password));
					
					Bukkit.getConsoleSender().sendMessage("[" + Core.getCore().getDescription().getName() + "] MySQL of DiverseFFA connected");
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			return;
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		Core.connection = connection;
	}
	
	public void enableEvents() {
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new Join(), this);
		pm.registerEvents(new Quit(), this);
		pm.registerEvents(new EnderchestData(), this);
		pm.registerEvents(new SignDisposal(), this);
		pm.registerEvents(new SignSell(), this);
		pm.registerEvents(new SignTopKills(), this);
		pm.registerEvents(new SignTopStreaks(), this);
		pm.registerEvents(new Death(), this);
		pm.registerEvents(new GameManager(), this);
		/*--------------------------------------------------*/
		pm.registerEvents(new InventoryDisposal(), this);
		pm.registerEvents(new InventoryPlayerEnderchest(), this);
		pm.registerEvents(new InventoryEnderchest(), this);
		pm.registerEvents(new InventoryPlayerStats(), this);
		pm.registerEvents(new InventoryVillager(), this);
		pm.registerEvents(new InventoryTopKills(), this);
		pm.registerEvents(new InventoryTopStreaks(), this);
	}
	
	public void enableCommands() {
		getCommand("diverseffa").setExecutor(new DiverseFFA());
		getCommand("coins").setExecutor(new Coins());
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("spectate").setExecutor(new Spectate());
		getCommand("stats").setExecutor(new Stats());
		getCommand("enderchest").setExecutor(new Enderchest());
	}
	
	public static String Color(String text) {
		String finaltext = ChatColor.translateAlternateColorCodes('&', text);
	    return finaltext;
	}
}
