package net.diverse.auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.diverse.auth.commands.*;
import net.diverse.auth.events.*;
import net.diverse.auth.files.*;
import net.diverse.auth.utils.*;

public class Core extends JavaPlugin {
	
	public static ArrayList<String> beforeLogin = new ArrayList<String>();
	public static HashMap<String, Integer> attempts = new HashMap<String, Integer>();
	public static Map<String, Integer> waitcooldown = new HashMap<String, Integer>();
	public static Map<String, Integer> logoutcooldown = new HashMap<String, Integer>();
	public static Connection connection;
	public String host, database, username, password;
	public static String prefix = "";
	public int port;
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
        enableEvents();
        enableCommands();
        mysqlSetup();
        PlayerData.createTable();
		Core.getCore().getConfig();
	    saveDefaultConfig();
        
        for(Player all : Bukkit.getOnlinePlayers()) {
        	Core.addPlayerList(all);
        	
        	if(PlayerData.playerExists(all.getUniqueId())) {
        		TitlesAPI.sendTitle(all, "&a&lInicia Sesión", "&7Utiliza: &e/login -Contraseña&7.", 20, 60, 20);
        		all.sendMessage(Core.Color(Core.prefix + "&7Inicia sesión utilizando: &e/login -Contraseña&7."));
        		Core.attempts.put(all.getName(), 2);
        	}
        	else {
        		TitlesAPI.sendTitle(all, "&a&lRegistrate", "&7Utiliza: &e/register -Contraseña&7.", 20, 60, 20);
        		all.sendMessage(Core.Color(Core.prefix + "&7Registrate utilizando: &e/register -Contraseña&7."));
        	}
        	
        	waitcooldown.put(all.getName(), 30);
    		new BukkitRunnable() {
    			@Override
    			public void run() {
    				if(Core.isPlayerList(all)) {
    					if(Core.waitcooldown.get(all.getName()) > 20) {
    						if(Core.waitcooldown.containsKey(all.getName())) {
    							ActionBarAPI.sendActionBar(all, "&7Tiempo restante: &a" + Core.waitcooldown.get(all.getName()));
    							Core.waitcooldown.put(all.getName(), Core.waitcooldown.get(all.getName()) - 1);
    						}
    					}
    					else if(Core.waitcooldown.get(all.getName()) > 10) {
    						if(Core.waitcooldown.containsKey(all.getName())) {
    							ActionBarAPI.sendActionBar(all, "&7Tiempo restante: &6" + Core.waitcooldown.get(all.getName()));
    							Core.waitcooldown.put(all.getName(), Core.waitcooldown.get(all.getName()) - 1);
    						}
    					}
    					else if(Core.waitcooldown.get(all.getName()) > 5) {
    						if(Core.waitcooldown.containsKey(all.getName())) {
    							ActionBarAPI.sendActionBar(all, "&7Tiempo restante: &e" + Core.waitcooldown.get(all.getName()));
    							Core.waitcooldown.put(all.getName(), Core.waitcooldown.get(all.getName()) - 1);
    						}
    					}
    					else if(Core.waitcooldown.get(all.getName()) > 0) {
    						if(Core.waitcooldown.containsKey(all.getName())) {
    							ActionBarAPI.sendActionBar(all, "&7Tiempo restante: &c" + Core.waitcooldown.get(all.getName()));
    							Core.waitcooldown.put(all.getName(), Core.waitcooldown.get(all.getName()) - 1);
    						}
    					}
    					else {
    						Core.removePlayerList(all);
    						Core.waitcooldown.remove(all.getName());
    						Core.attempts.remove(all.getName());
    						Core.beforeLogin.remove(all.getName());
    						cancel();
    						all.kickPlayer(Core.Color(Core.prefix + "&7Tiempo de espera agotado."));
    					}
    				}
    				else {
    					waitcooldown.remove(all.getName());
    					cancel();
    				}
    			}
    		}.runTaskTimer(Core.getCore(), 0, 20);
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
					
					Bukkit.getConsoleSender().sendMessage("[" + Core.getCore().getDescription().getName() + "] MySQL of DiverseAuth connected");
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
	}
	
	public void enableCommands() {
		getCommand("diverseauth").setExecutor(new DiverseAuth());
		getCommand("register").setExecutor(new Register());
		getCommand("login").setExecutor(new Login());
		getCommand("changepass").setExecutor(new ChangePass());
		getCommand("logout").setExecutor(new Logout());
	}
	
	public static String Color(String text) {
		String finaltext = ChatColor.translateAlternateColorCodes('&', text);
	    return finaltext;
	}
	
	public static void Firework(Player p){
        Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
        FireworkMeta fwmeta = fw.getFireworkMeta();
        FireworkEffect.Builder builder = FireworkEffect.builder();
        builder.withFlicker();
        builder.withFade(Color.RED);
        builder.withColor(Color.ORANGE);
        builder.withColor(Color.YELLOW);
        builder.withColor(Color.LIME);
        builder.withColor(Color.TEAL);
        builder.withColor(Color.PURPLE);
        builder.withColor(Color.FUCHSIA);
        builder.with(FireworkEffect.Type.BALL);
        fwmeta.addEffects(builder.build());
        fwmeta.setPower(0);
        fw.setFireworkMeta(fwmeta);
    }
	
	public static void addPlayerList(Player p) {
		Core.beforeLogin.add(p.getName());
	}
	
	public static boolean isPlayerList(Player p) {
		return Core.beforeLogin.contains(p.getName());
	}
	
	public static void removePlayerList(Player p) {
		Core.beforeLogin.remove(p.getName());
	}
}
