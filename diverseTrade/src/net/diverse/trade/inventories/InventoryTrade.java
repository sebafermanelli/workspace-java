package net.diverse.trade.inventories;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.diverse.trade.Core;
import net.diverse.trade.utils.ItemBuildAPI;

public class InventoryTrade implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player p = (Player)event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inv = event.getInventory();
		
		if(clicked != null) {
			if((inv.getName().startsWith("Tradeo ")) && (clicked.getType() == Material.STAINED_GLASS_PANE) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				return;
			}
			if((inv.getName().startsWith("Tradeo ")) && (clicked.getType() == Material.SKULL_ITEM) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				return;
			}
			if((inv.getName().startsWith("Tradeo ")) && (clicked.getType() == Material.EMERALD) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				return;
			}
			if((inv.getName().startsWith("Tradeo "))) {
				if(Core.playertrading.containsKey(p)) {
					//
					if(event.getSlot() >= 14 && event.getSlot() <= 16) {
						event.setCancelled(true);
					}
					if(event.getSlot() >= 23 && event.getSlot() <= 25) {
						event.setCancelled(true);
					}
					if(event.getSlot() >= 32 && event.getSlot() <= 34) {
						event.setCancelled(true);
					}
					//
					if(event.getSlot() >= 10 && event.getSlot() <= 12) {
						event.setCancelled(false);
					}
					if(event.getSlot() >= 19 && event.getSlot() <= 21) {
						event.setCancelled(false);
					}
					if(event.getSlot() >= 28 && event.getSlot() <= 30) {
						event.setCancelled(false);
					}
					//
					if(event.getSlot() == 50) {
						event.setCancelled(true);
					}
					//
					if(event.getSlot() == 48) {
						p.sendMessage("ACCEPT TRADE");
						event.setCancelled(true);
					}
					if(event.getSlot() == 49) {
						event.setCancelled(true);
						p.sendMessage("CANCEL TRADE");
						p.closeInventory();
					}
				}
				else {
					//
					if(event.getSlot() >= 10 && event.getSlot() <= 12) {
						event.setCancelled(true);
					}
					if(event.getSlot() >= 19 && event.getSlot() <= 21) {
						event.setCancelled(true);
					}
					if(event.getSlot() >= 28 && event.getSlot() <= 30) {
						event.setCancelled(true);
					}
					//
					if(event.getSlot() >= 14 && event.getSlot() <= 16) {
						event.setCancelled(false);
					}
					if(event.getSlot() >= 23 && event.getSlot() <= 25) {
						event.setCancelled(false);
					}
					if(event.getSlot() >= 32 && event.getSlot() <= 34) {
						event.setCancelled(false);
					}
					//
					if(event.getSlot() == 48) {
						event.setCancelled(true);
					}
					//
					if(event.getSlot() == 50) {
						p.sendMessage("ACCEPT TRADE");
						event.setCancelled(true);
					}
					if(event.getSlot() == 49) {
						event.setCancelled(true);
						p.sendMessage("CANCEL TRADE");
						p.closeInventory();
					}
				}
				return;
			}
		}
	}
	
	public static void openTradeInventory(final Player p, final Player r) {
	    final Inventory trade = Bukkit.createInventory(null, 54, "Tradeo " + p.getName() + "/" + r.getName());
	    
	    new BukkitRunnable() {
	    	boolean firstTime = true;
	    	
	    	public void run() {
	    		List<String> separatoritem = new ArrayList<String>();
	    		//Line 1
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 0, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 1, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 2, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 3, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 4, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 5, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 6, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 7, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 8, Core.Color(""), separatoritem);
		        //Line 2
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 9, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 13, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 17, Core.Color(""), separatoritem);
		        //Line 3
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 18, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 22, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 26, Core.Color(""), separatoritem);
		        //Line 4
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 27, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 31, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 35, Core.Color(""), separatoritem);
		        //Line 5
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 36, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 37, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 38, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 39, Core.Color(""), separatoritem);
		        
		        List<String> timeitem = new ArrayList<String>();
		        timeitem.add(Core.Color("&bEl trato se cierra en: &70 segundos"));
		        ItemBuildAPI.createItem(Material.EMERALD, 1, 0, trade, 40, Core.Color("&aTiempo restante:"), timeitem);
		        
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 41, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 42, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 43, Core.Color(""), separatoritem);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 44, Core.Color(""), separatoritem);
		        //Line 6
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 45, Core.Color(""), separatoritem);
		        List<String> headlore = new ArrayList<String>();
		        ItemBuildAPI.createSkull(p.getName(), trade, 46, Core.Color("&e" + p.getName()), headlore);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 47, Core.Color(""), separatoritem);
		        
		        ItemBuildAPI.createItem(Material.REDSTONE_BLOCK, 1, 0, trade, 48, Core.Color("&aAceptar trato con &e" +  r.getName()), separatoritem);
	    		List<String> closeitem = new ArrayList<String>();
		        ItemBuildAPI.createItem(Material.WOOL, 1, 14, trade, 49, Core.Color("&aCancelar"), closeitem);
		        ItemBuildAPI.createItem(Material.REDSTONE_BLOCK, 1, 0, trade, 50, Core.Color("&aAceptar trato con &e" + p.getName()), separatoritem);
		        
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 51, Core.Color(""), separatoritem);
		        List<String> headlore1 = new ArrayList<String>();
		        ItemBuildAPI.createSkull(r.getName(), trade, 52, Core.Color("&e" + r.getName()), headlore1);
		        ItemBuildAPI.createItem(Material.STAINED_GLASS_PANE, 1, 15, trade, 53, Core.Color(""), separatoritem);
	    		
		        if (this.firstTime) {
		        	p.openInventory(trade);
		        	r.openInventory(trade);
		        }
		        this.firstTime = false;
	    	}
	    }.runTaskTimer(Core.getCore(), 0, 10);
	}
}