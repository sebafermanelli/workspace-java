package net.diverse.auth.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.diverse.auth.Core;

public class Quit implements Listener{
	
	@EventHandler
	public void onLogout(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		
		Core.removePlayerList(p);
		Core.waitcooldown.remove(p.getName());
		Core.attempts.remove(p.getName());
		Core.beforeLogin.remove(p.getName());
		
		event.setQuitMessage(null);
	}
	
	@EventHandler
	public void onLogout(PlayerKickEvent event) {
		Player p = event.getPlayer();
		
		Core.removePlayerList(p);
		Core.waitcooldown.remove(p.getName());
		Core.attempts.remove(p.getName());
		Core.beforeLogin.remove(p.getName());
		
		event.setLeaveMessage(null);
	}
}
