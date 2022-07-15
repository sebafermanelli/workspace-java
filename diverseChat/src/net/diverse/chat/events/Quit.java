package net.diverse.chat.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.diverse.chat.Core;

public class Quit implements Listener {
	
	@EventHandler
	public void onPlayerConversationQuit(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		
		Core.tell.remove(p.getName(), "");
		
	    List<String> pls = new ArrayList<String>();
	    if(Core.conv.containsValue(p.getName() + ":" + "true")) {
	    	for(String key : Core.conv.keySet()) {
	    		if(((String)Core.conv.get(key)).equalsIgnoreCase(p.getName() + ":" + "true")) {
	    			pls.add(key);
	  	        }
	    	}
	    	for(String pl : pls) {
	    		CommandSender rec = Bukkit.getServer().getPlayerExact(pl);
	    		if(rec == null) {
	    			Core.conv.put(pl, "none:false");
	    		}
	    	}
	    }
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
	    Player p = event.getPlayer();
	    
	    Core.tell.remove(p.getName(), "");
	  }
}
