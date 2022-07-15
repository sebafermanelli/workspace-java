package net.diverse.ffa.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import net.diverse.ffa.files.PlayerData;
import net.diverse.ffa.utils.ScoreboardManage;

public class Quit implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		
		ScoreboardManage.scoreboardSignMap.remove(p.getName());
		PlayerData.updateStreak(p.getUniqueId(), 0);
		event.setQuitMessage(null);
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		Player p = event.getPlayer();
		
		ScoreboardManage.scoreboardSignMap.remove(p.getName());
		PlayerData.updateStreak(p.getUniqueId(), 0);
		event.setLeaveMessage(null);
	}
}
