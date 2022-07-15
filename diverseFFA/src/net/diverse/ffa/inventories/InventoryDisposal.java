package net.diverse.ffa.inventories;

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

import net.diverse.ffa.Core;
import net.diverse.ffa.utils.ItemBuildAPI;

public class InventoryDisposal implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player p = (Player)event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inv = event.getInventory();
		
		if(clicked != null) {
			if((inv.getName().startsWith("Eliminación de desechos")) && (clicked.getType() == Material.WOOL) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.closeInventory();
				return;
			}
		}
	}
	
	public static void openDisposalInventory(final Player p) {
	    final Inventory disposal = Bukkit.createInventory(null, 36, "Eliminación de desechos");
	    
	    new BukkitRunnable() {
	    	boolean firstTime = true;
	    	
	    	public void run() {
		        
	    		List<String> closeitem = new ArrayList<String>();
		        ItemBuildAPI.createItem(Material.WOOL, 1, 14, disposal, 35, Core.Color("&aCerrar"), closeitem);
	    		
		        if (this.firstTime) {
		        	p.openInventory(disposal);
		        }
		        this.firstTime = false;
	    	}
	    }.runTaskTimer(Core.getCore(), 0, 10);
	}
}