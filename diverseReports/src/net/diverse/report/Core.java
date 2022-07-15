package net.diverse.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import net.diverse.report.commands.*;
import net.diverse.report.files.PlayerData;
import net.diverse.report.inventories.*;

public class Core extends JavaPlugin {
	
	public static Connection connection;
	public String host, database, username, password;
	public int port;
	
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
		Core.getCore().getConfig();
		saveDefaultConfig();
		enableEvents();
		enableCommands();
		mysqlSetup();
		PlayerData.createTable();
        
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
					
					Bukkit.getConsoleSender().sendMessage("[" + Core.getCore().getDescription().getName() + "] MySQL of DiverseReport connected");
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
		
		/*--------------------------------------------------*/
		pm.registerEvents(new InventoryReport(), this);
	}
	
	public void enableCommands() {
		getCommand("diversereport").setExecutor(new DiverseReport());
		getCommand("report").setExecutor(new Report());
		getCommand("reportlist").setExecutor(new ReportList());
	}
	
	public static String Color(String text) {
		String finaltext = ChatColor.translateAlternateColorCodes('&', text);
	    return finaltext;
	}
}
