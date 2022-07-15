package net.diverse.ffa.utils;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import net.diverse.ffa.Core;
import net.diverse.ffa.files.PlayerData;

import java.util.HashMap;
import java.util.Map;

public class ScoreboardManage {
	
	public static Map<String, ScoreboardAPI> scoreboardSignMap = new HashMap<String, ScoreboardAPI>();
	
	public Player p;
	public ScoreboardAPI scoreboardSign;
	
	public ScoreboardManage(Player player) {
		p = player;
	}
	
	public void loadScoreboard() {
    	if (!scoreboardSignMap.containsKey(p.getName())) {
    		scoreboardSign = new ScoreboardAPI(p, p.getName());
    		
            scoreboardSign.setObjectiveName(Core.Color("&3&lDiverseMC"));
            scoreboardSign.create();

            if(PlayerData.playerExists(p.getUniqueId())) {
    		    scoreboardSign.setLine(11, Core.Color("&7Ping: &a" + ((CraftPlayer)p).getHandle().ping));
    		    scoreboardSign.setLine(10, Core.Color("&7Monedas: &a" + PlayerData.getCoins(p.getUniqueId())));
    		    scoreboardSign.setLine(9, " ");
    		    scoreboardSign.setLine(8, Core.Color("&7Bajas: &a" + PlayerData.getKills(p.getUniqueId())));
    		    scoreboardSign.setLine(7, Core.Color("&7Muertes: &a" + PlayerData.getDeaths(p.getUniqueId())));
    		    scoreboardSign.setLine(6, Core.Color("&7KDR: &a" + PlayerData.getKDR(p.getUniqueId())));
    		    scoreboardSign.setLine(5, " ");
    		    scoreboardSign.setLine(4, Core.Color("&7Racha:"));
    		    scoreboardSign.setLine(3, Core.Color("  &7Actual: &a" + PlayerData.getStreak(p.getUniqueId())));
    		    scoreboardSign.setLine(2, Core.Color("  &7Mejor: &a" +  PlayerData.getBestStreak(p.getUniqueId())));
    		    scoreboardSign.setLine(1, " ");
    		    scoreboardSign.setLine(0, Core.Color("&ediversemc.com"));
    		}
            else {
            	scoreboardSign.setLine(11, Core.Color("&7Ping: &a0"));
    		    scoreboardSign.setLine(10, Core.Color("&7Monedas: &a0"));
    		    scoreboardSign.setLine(9, " ");
    		    scoreboardSign.setLine(8, Core.Color("&7Bajas: &a0"));
    		    scoreboardSign.setLine(7, Core.Color("&7Muertes: &a0"));
    		    scoreboardSign.setLine(6, Core.Color("&7KDR: &a1.0"));
    		    scoreboardSign.setLine(5, " ");
    		    scoreboardSign.setLine(4, Core.Color("&7Racha:"));
    		    scoreboardSign.setLine(3, Core.Color("  &7Actual: &a0"));
    		    scoreboardSign.setLine(2, Core.Color("  &7Mejor: &a0"));
    		    scoreboardSign.setLine(1, " ");
    		    scoreboardSign.setLine(0, Core.Color("&ediversemc.com"));
            }
    		
            scoreboardSignMap.put(p.getName(), scoreboardSign);
		}
    	else {
	        scoreboardSign = scoreboardSignMap.get(p.getName());
			
		    scoreboardSign.setLine(11, Core.Color("&7Ping: &a" + ((CraftPlayer)p).getHandle().ping));
		    scoreboardSign.setLine(10, Core.Color("&7Monedas: &a" + PlayerData.getCoins(p.getUniqueId())));
		    scoreboardSign.setLine(9, " ");
		    scoreboardSign.setLine(8, Core.Color("&7Bajas: &a" + PlayerData.getKills(p.getUniqueId())));
		    scoreboardSign.setLine(7, Core.Color("&7Muertes: &a" + PlayerData.getDeaths(p.getUniqueId())));
		    scoreboardSign.setLine(6, Core.Color("&7KDR: &a" + PlayerData.getKDR(p.getUniqueId())));
		    scoreboardSign.setLine(5, " ");
		    scoreboardSign.setLine(4, Core.Color("&7Racha:"));
		    scoreboardSign.setLine(3, Core.Color("  &7Actual: &a" + PlayerData.getStreak(p.getUniqueId())));
		    scoreboardSign.setLine(2, Core.Color("  &7Mejor: &a" +  PlayerData.getBestStreak(p.getUniqueId())));
		    scoreboardSign.setLine(1, " ");
		    scoreboardSign.setLine(0, Core.Color("&ediversemc.com"));
		}
	}
}
