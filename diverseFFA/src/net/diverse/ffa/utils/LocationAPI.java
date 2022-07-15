package net.diverse.ffa.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationAPI {
	
	public static String setLoc(Location loc) {
		
		return loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + "," + loc.getYaw() + "," + loc.getPitch();
	}
	
	public static Location getLoc(String path){
		Location loc = null;
		String[] locs = path.split(",");

		loc = new Location(Bukkit.getWorld(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]), Integer.parseInt(locs[3]), (float) Double.parseDouble(locs[4]), (float) Double.parseDouble(locs[5]));
        loc.add(0.5D,0.0,0.5D);
        
		return loc;
	}
}
