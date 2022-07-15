package net.diverse.trade.utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.diverse.trade.Core;

public class TradeAPI implements Listener {
	
	public void addPlayerToTradeList(Player p1, Player p2) {
		Core.playertrading.put(p1, p2);
	}

	public static void acceptTrade(Player p, ItemStack item) {
		if(item.getType().equals(Material.REDSTONE_BLOCK)) {
			item.setType(Material.EMERALD_BLOCK);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(p.getName());
			item.setItemMeta(itemmeta);
		}
		else if(item.getType().equals(Material.EMERALD_BLOCK)) {
			if(item.getItemMeta().getDisplayName().equals(p.getName())) {
				item.setType(Material.REDSTONE_BLOCK);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName(null);
				item.setItemMeta(itemmeta);
			}
			else {
				TradeAPI.finishTrade(p.getOpenInventory().getTopInventory());
			}
		}
	}

	public static void finishTrade(Inventory inv) {
		List<HumanEntity> viewers = inv.getViewers();
		Player p1;
		Player p2;
		
		if(Core.playertrading.containsKey((Player) viewers.get(0))) {
			p1 = (Player)viewers.get(0);
			p2 = (Player)viewers.get(1);
		}
		else {
			p1 = (Player)viewers.get(0);
			p2 = (Player)viewers.get(1);
		}
		p1.closeInventory();
		p2.closeInventory();
		for(int i = 0; i<17; i++) {
			if(inv.getItem(i).equals(null)) {
				p2.getInventory().addItem(inv.getItem(i));
			}
			if(inv.getItem(i+ 27).equals(null)) {
				p1.getInventory().addItem(inv.getItem(i + 27));
			}
		}
		Core.playertrading.remove(p1);
	}
}
