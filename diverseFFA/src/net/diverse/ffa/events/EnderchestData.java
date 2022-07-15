package net.diverse.ffa.events;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.diverse.ffa.Core;

public class EnderchestData implements Listener {
	
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event) {
		HumanEntity p = event.getPlayer();
	    Inventory oldInv = event.getInventory();
	    File filePlayers = new File("plugins/" + Core.getCore().getDescription().getName() + "/enderchest/" + p.getName() + ".yml");
		FileConfiguration getDataPlayers = YamlConfiguration.loadConfiguration(filePlayers);
	    
		if (oldInv.getType() == InventoryType.ENDER_CHEST) {
			Inventory newInv = Bukkit.getServer().createInventory(p, 45, "Cofre privado");
	  	
			if(getDataPlayers.isSet(p.getName() + ".ENDERCHEST")) {
				newInv.setContents((ItemStack[])getDataPlayers.getList(p.getName() + ".ENDERCHEST").toArray(new ItemStack[0]));
			}
			else {
				newInv.setContents(p.getEnderChest().getContents());
			}
			p.openInventory(newInv);
			event.setCancelled(true);
	    }
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		HumanEntity p = event.getPlayer();
		Inventory inventory = event.getInventory();
		File filePlayers = new File("plugins/" + Core.getCore().getDescription().getName() + "/enderchest/" + p.getName() + ".yml");
		FileConfiguration getDataPlayers = YamlConfiguration.loadConfiguration(filePlayers);
		
		if(inventory.getSize() == 45) {
	    	if (inventory.getName().equals("Cofre privado")) {
	    		getDataPlayers.set(p.getName() + ".ENDERCHEST", Arrays.asList(inventory.getContents()));
	    		try {
					getDataPlayers.save(filePlayers);
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    }
	}
}
