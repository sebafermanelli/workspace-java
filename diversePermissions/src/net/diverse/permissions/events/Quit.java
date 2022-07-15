package net.diverse.permissions.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener{
	
	@EventHandler
	public void onLogout(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		
	    net.diverse.permissions.Core.playerPermissions.remove(p.getUniqueId());
	}
	
	@EventHandler
	public void onLogout(PlayerKickEvent event) {
		Player p = event.getPlayer();
		
		net.diverse.permissions.Core.playerPermissions.remove(p.getUniqueId());
	}
}
