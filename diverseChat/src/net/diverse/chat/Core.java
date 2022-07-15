package net.diverse.chat;

import java.util.HashMap;

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

import net.diverse.chat.commands.*;
import net.diverse.chat.events.*;
import net.diverse.chat.utils.*;

public class Core extends JavaPlugin {
	
	public static HashMap<String, String> tell = new HashMap<String, String>();
	public static HashMap<String, String> conv = new HashMap<String, String>();
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
        NewsAPI.autoBroadcast();
        
        for (Player all : Bukkit.getOnlinePlayers()) {
        	Core.tell.put(all.getName(), "-");
        	Core.conv.put(all.getName(), "none:false");
        }
        
		Bukkit.getConsoleSender().sendMessage("[" + Core.getCore().getDescription().getName() + "] Has been successfully activated");
	}
	
	public void enableEvents() {
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new Join(), this);
		pm.registerEvents(new Chat(), this);
		pm.registerEvents(new Quit(), this);
	}
	
	public void enableCommands() {
		getCommand("diversechat").setExecutor(new DiverseChat());
		getCommand("tell").setExecutor(new Tell());
		getCommand("reply").setExecutor(new Reply());
		getCommand("conversation").setExecutor(new Conversation());
		getCommand("staffchat").setExecutor(new StaffChat());
		getCommand("broadcast").setExecutor(new Broadcast());
	}
	
	public static String Color(String text) {
		String finaltext = ChatColor.translateAlternateColorCodes('&', text);
	    return finaltext;
	}
	
	public static void Firework(Player player){
        Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
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
}
