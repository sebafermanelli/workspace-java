package net.diverse.ffa.inventories;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.diverse.ffa.Core;
import net.diverse.ffa.utils.ItemBuildAPI;

public class InventoryEnderchest implements Listener {
	
	@EventHandler
	public void onEnderchestClick(InventoryClickEvent event) {
		Player p = (Player)event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inv = event.getInventory();
		
		if(clicked != null) {
			if((inv.getName().startsWith("Cofre privado de ")) && (clicked.getType() == Material.WOOL) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.closeInventory();
				return;
			}
			if((inv.getName().startsWith("Cofre privado de "))) {
				event.setCancelled(true);
				return;
			}
	    }
	}
	
	public static void openInvEnderchest(final Player p, final String r) {
		
		final Inventory enderchest = Bukkit.createInventory(null, 54, "Cofre privado de " + r);
	    
	    new BukkitRunnable() {
	    	boolean firstTime = true;
			public void run() {
				File filePlayers = new File("plugins/" + Core.getCore().getDescription().getName() + "/enderchest/" + r + ".yml");
				FileConfiguration getDataPlayers = YamlConfiguration.loadConfiguration(filePlayers);
	    		
		        List<String> closeitem = new ArrayList<String>();
		        ItemBuildAPI.createItem(Material.WOOL, 1, 14, enderchest, 53, Core.Color("&aCerrar"), closeitem);
		        
		        if(filePlayers.exists()) {
					@SuppressWarnings("unchecked")
					List<ItemStack> isl = (List<ItemStack>)getDataPlayers.getList(r + ".ENDERCHEST");
		    		if(getDataPlayers.getList(r + ".ENDERCHEST") == null) {
		    			
		    		}
		    		else {
			    		int i1 = 0;
			    		while(i1 < isl.size()) {
			    			ItemBuildAPI.createItem(isl.get(i1), enderchest, i1);
			    			i1++;
			    		}
		    		}
	    		}
		        
		        if (this.firstTime) {
		        	p.openInventory(enderchest);
		        }
		        this.firstTime = false;
	    	}
	    }.runTaskTimer(Core.getCore(), 0, 10);
	}
}
