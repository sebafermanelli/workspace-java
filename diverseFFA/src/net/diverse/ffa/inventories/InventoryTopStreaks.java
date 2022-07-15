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
import net.diverse.ffa.files.PlayerData;
import net.diverse.ffa.utils.ItemBuildAPI;

public class InventoryTopStreaks implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player p = (Player)event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inv = event.getInventory();
		
		if(clicked != null) {
			if((inv.getName().startsWith("Podio de rachas")) && (clicked.getType() == Material.WOOL) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.closeInventory();
				return;
			}
			if(inv.getName().startsWith("Podio de rachas")) {
				event.setCancelled(true);
			}
		}
	}
	
	public static void openTopStreaksInventory(final Player p) {
	    final Inventory top = Bukkit.createInventory(null, 27, "Podio de rachas");
	    
	    new BukkitRunnable() {
	    	boolean firstTime = true;
	    	
	    	public void run() {
	    		
	    		//1
	    		List<String> top1item = new ArrayList<String>();
	    		top1item.add(Core.Color("&bRacha: &7" + PlayerData.streakTopStreaks().get(0)));
	    		ItemBuildAPI.createSkull(PlayerData.playerTopStreaks().get(0), top, 10, Core.Color("&e" + PlayerData.playerTopStreaks().get(0) + " &8- &7POS: &f1"), top1item);
	    		//2
	    		List<String> top2item = new ArrayList<String>();
	    		top2item.add(Core.Color("&bRacha: &7" + PlayerData.streakTopStreaks().get(1)));
	    		ItemBuildAPI.createSkull(PlayerData.playerTopStreaks().get(1), top, 12, Core.Color("&e" + PlayerData.playerTopStreaks().get(1) + " &8- &7POS: &f2"), top2item);
	    		//3
	    		List<String> top3item = new ArrayList<String>();
	    		top3item.add(Core.Color("&bRacha: &7" + PlayerData.streakTopStreaks().get(2)));
	    		ItemBuildAPI.createSkull(PlayerData.playerTopStreaks().get(2), top, 14, Core.Color("&e" + PlayerData.playerTopStreaks().get(2) + " &8- &7POS: &f3"), top3item);
	    		//4
	    		List<String> top4item = new ArrayList<String>();
	    		top4item.add(Core.Color("&bRacha: &7" + PlayerData.streakTopStreaks().get(3)));
	    		ItemBuildAPI.createSkull(PlayerData.playerTopStreaks().get(3), top, 16, Core.Color("&e" + PlayerData.playerTopStreaks().get(3) + " &8- &7POS: &f4"), top4item);
	    		
	    		List<String> closeitem = new ArrayList<String>();
		        ItemBuildAPI.createItem(Material.WOOL, 1, 14, top, 26, Core.Color("&aCerrar"), closeitem);
	    		
		        if (this.firstTime) {
		        	p.openInventory(top);
		        }
		        this.firstTime = false;
	    	}
	    }.runTaskTimer(Core.getCore(), 0, 10);
	}
}