package net.diverse.ffa.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import net.diverse.ffa.Core;
import net.diverse.ffa.files.PlayerData;
import net.diverse.ffa.utils.Kit;
import net.diverse.ffa.utils.LocationAPI;
import net.diverse.ffa.utils.ScoreboardManage;

public class Join implements Listener {
	
	public static boolean isEmpty(Inventory inv) {
		for(int i = 0;i<inv.getSize();i++) {
			if(inv.getItem(i) != null) {
				return false;
			}
		}
		return true;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		
		if(!PlayerData.playerExists(p.getUniqueId())) {
			PlayerData.createPlayer(p.getUniqueId(), p);
		}
		
		new BukkitRunnable() {
			@Override
			public void run() {
				for (Player online : Bukkit.getOnlinePlayers()) {
					ScoreboardManage sm = new ScoreboardManage(online);
					sm.loadScoreboard();
				}
			}
		}.runTaskTimer(Core.getCore(), 0, 20);
		
		if(p.hasPlayedBefore()) {
			event.setJoinMessage(null);
			
			if(!Core.getCore().getConfig().contains("Spawn")){
				p.sendMessage(Core.Color("&c&lERROR: &7El spawn no existe, usa: &e/diverseffa setspawn&7."));
			}
			
			new LocationAPI();
			p.teleport(LocationAPI.getLoc(Core.getCore().getConfig().getString("Spawn")));
			p.playSound(p.getEyeLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
			p.getInventory().setHeldItemSlot(0);
			p.setFoodLevel(p.getFoodLevel());
			p.setHealth(p.getMaxHealth());
			p.setGameMode(GameMode.SURVIVAL);
			p.setExp(0.0F);
		    p.setLevel(0);
		    p.setFireTicks(0);
			PlayerData.updateStreak(p.getUniqueId(), 0);
			p.setPlayerListName(Core.Color("&e" + p.getName()));
			
			if(isEmpty(p.getInventory())) {
				Kit.addKit(p);
			}
		}
		
		if(!p.hasPlayedBefore()) {
			event.setJoinMessage(null);
			
			if(!Core.getCore().getConfig().contains("Spawn")){
				p.sendMessage(Core.Color("&c&lERROR: &7El spawn no existe, usa: &e/diverseffa setspawn&7."));
			}
			
			new LocationAPI();
			p.teleport(LocationAPI.getLoc(Core.getCore().getConfig().getString("Spawn")));
			p.playSound(p.getEyeLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
			p.getInventory().setHeldItemSlot(0);
			p.setFoodLevel(p.getFoodLevel());
			p.setHealth(p.getMaxHealth());
			p.setGameMode(GameMode.SURVIVAL);
			p.setExp(0.0F);
		    p.setLevel(0);
		    p.setFireTicks(0);
		    p.setFireTicks(0);
			Kit.addKit(p);
			PlayerData.updateStreak(p.getUniqueId(), 0);
			p.setPlayerListName(Core.Color("&e" + p.getName()));
		}
	}
}
