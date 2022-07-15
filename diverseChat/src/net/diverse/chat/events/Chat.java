package net.diverse.chat.events;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.diverse.chat.Core;

public class Chat implements Listener {
	
	@EventHandler
	public void onConversationChat(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
	    CommandSender rec = Bukkit.getServer().getPlayerExact(((String)Core.conv.get(event.getPlayer().getName())).split(":")[0]);
	    if((((String)Core.conv.get(event.getPlayer().getName())).split(":")[0].equalsIgnoreCase("none")) || (((String)Core.conv.get(event.getPlayer().getName())).split(":")[0].equalsIgnoreCase("false"))) {
	    	return;
	    }
	    if(rec == null) {
	    	p.sendMessage(Core.Color(Core.prefix + "&7Se ha &cdesactivado &7el modo conversación porque &e" + ((String)Core.conv.get(event.getPlayer().getName())).split(":")[0] + " &7se desconectó."));
	    	Core.conv.put(p.getName(), "none:false");
	    	event.setCancelled(true);
	    	return;
	    }
	    
	    p.sendMessage(Core.Color("&6" + p.getName() + " &8> &6" + rec.getName() + "&7: &f" + event.getMessage()));
	    rec.sendMessage(Core.Color("&6" + p.getName() + " &8> &6" + rec.getName() + "&7: &f" + event.getMessage()));
	    event.setCancelled(true);
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
	    String message = event.getMessage();
	    
	    event.setFormat(Core.Color("&e" + p.getName() + "&7: &f" + message));
	}
}
