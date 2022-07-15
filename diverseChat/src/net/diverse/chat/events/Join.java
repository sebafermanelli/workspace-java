package net.diverse.chat.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import net.diverse.chat.Core;

public class Join implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		
		event.setJoinMessage(null);
		Core.tell.put(p.getName(), "-");
		Core.conv.put(p.getName(), "none:false");
	}
}
