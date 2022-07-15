package net.diverse.mc.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.diverse.mc.Core;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;

public class TablistAPI {
	
	static int headnumber = 1;
	static int footnumber = 1;
	
	public static void changeTab() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Core.getCore(), new Runnable() {
			public void run() {
				if(Bukkit.getOnlinePlayers().size() >= 1) {
					List<String> tablistHeadline = new ArrayList<String>();
					List<String> tablistFooterline = new ArrayList<String>();
					
					tablistHeadline.add(Core.Color("&7Estás jugando en &a&lPLAY.DIVERSEMC.COM"));
					tablistFooterline.add(Core.Color("&7Rangos y más! &d&lSHOP.DIVERSEMC.COM"));
					
					if(TablistAPI.headnumber > tablistHeadline.size()) {
						TablistAPI.headnumber = 1;
					}
					if(TablistAPI.footnumber > tablistFooterline.size()) {
						TablistAPI.footnumber = 1;
					}
					String headline = Core.Color((String)tablistHeadline.get(TablistAPI.headnumber - 1));
					String footer = Core.Color((String)tablistFooterline.get(TablistAPI.footnumber - 1));
					TablistAPI.headnumber += 1;
					TablistAPI.footnumber += 1;
					TablistAPI.updateTablist(headline, footer);
				}
			}
		}, 0L, 10L);
	}
	
	public static void updateTablist(String headline, String footer) {
		if(Bukkit.getOnlinePlayers().size() >= 1) {
			for(Player all : Bukkit.getOnlinePlayers()) {
				CraftPlayer cp = (CraftPlayer)all;
				PacketPlayOutPlayerListHeaderFooter tablist = new PacketPlayOutPlayerListHeaderFooter(
						IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + headline + "\"}"));
				try {
					Field field = tablist.getClass().getDeclaredField("b");
					field.setAccessible(true);
					field.set(tablist, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + footer + "\"}"));
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				cp.getHandle().playerConnection.sendPacket(tablist);
			}
		}
	}
}
