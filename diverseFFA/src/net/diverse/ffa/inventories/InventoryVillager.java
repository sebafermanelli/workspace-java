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
import net.diverse.ffa.villager.sections.Archery;
import net.diverse.ffa.villager.sections.Armours;
import net.diverse.ffa.villager.sections.Bank;
import net.diverse.ffa.villager.sections.Food;
import net.diverse.ffa.villager.sections.Laboratory;
import net.diverse.ffa.villager.sections.Miscellaneous;
import net.diverse.ffa.villager.sections.Weapons;

public class InventoryVillager implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player p = (Player)event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inv = event.getInventory();
		
		if(clicked != null) {
			if((inv.getName().startsWith("Tienda")) && (clicked.getType() == Material.NETHER_STAR) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.closeInventory();
				Bank.bankShop(p);
				return;
			}
			if((inv.getName().startsWith("Tienda")) && (clicked.getType() == Material.APPLE) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.closeInventory();
				Food.foodShop(p);
				return;
			}
			if((inv.getName().startsWith("Tienda")) && (clicked.getType() == Material.GOLD_SWORD) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.closeInventory();
				Weapons.weaponsShop(p);
				return;
			}
			if((inv.getName().startsWith("Tienda")) && (clicked.getType() == Material.GOLD_CHESTPLATE) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.closeInventory();
				Armours.armoursShop(p);
				return;
			}
			if((inv.getName().startsWith("Tienda")) && (clicked.getType() == Material.BOW) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.closeInventory();
				Archery.archeryShop(p);
				return;
			}
			if((inv.getName().startsWith("Tienda")) && (clicked.getType() == Material.EYE_OF_ENDER) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.closeInventory();
				Miscellaneous.miscellaneousShop(p);
				return;
			}
			if((inv.getName().startsWith("Tienda")) && (clicked.getType() == Material.POTION) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.closeInventory();
				Laboratory.laboratoryShop(p);
				return;
			}
			if((inv.getName().startsWith("Tienda")) && (clicked.getType() == Material.WOOL) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.closeInventory();
				return;
			}
			if((inv.getName().startsWith("Tienda"))) {
				event.setCancelled(true);
				return;
			}
		}
	}
	
	public static void openVillagerInventory(final Player p) {
	    final Inventory villagerinv = Bukkit.createInventory(null, 18, "Tienda");
	    
	    new BukkitRunnable() {
	    	boolean firstTime = true;
	    	
	    	public void run() {
	    		List<String> bankitem = new ArrayList<String>();
	    		bankitem.add(Core.Color("&7Intercambio:"));
	    		bankitem.add(Core.Color("&fHIERRO &8> &eORO"));
	    		bankitem.add(Core.Color("&eORO &8> &bDIAMANTE"));
	    		bankitem.add(Core.Color("&bDIAMANTE &8> &aESMERALDA"));
		        ItemBuildAPI.createItem(Material.NETHER_STAR, 1, 0, villagerinv, 1, Core.Color("&aBanco"), bankitem);
		        
		        List<String> fooditem = new ArrayList<String>();
		        fooditem.add(Core.Color("&7Compra comida para estar satisfecho"));
		        ItemBuildAPI.createItem(Material.APPLE, 1, 0, villagerinv, 2, Core.Color("&aComida"), fooditem);
		        
		        List<String> weaponsitem = new ArrayList<String>();
		        weaponsitem.add(Core.Color("&7Compra espadas de calidad"));
		        ItemBuildAPI.createItem(Material.GOLD_SWORD, 1, 0, villagerinv, 3, Core.Color("&aArmas"), weaponsitem);
		        
		        List<String> armoursitem = new ArrayList<String>();
		        armoursitem.add(Core.Color("&7Compra armadura resistente"));
		        ItemBuildAPI.createItem(Material.GOLD_CHESTPLATE, 1, 0, villagerinv, 4, Core.Color("&aArmaduras"), armoursitem);
		        
		        List<String> archeryitem = new ArrayList<String>();
		        archeryitem.add(Core.Color("&7Compra arcos y flechas"));
		        ItemBuildAPI.createItem(Material.BOW, 1, 0, villagerinv, 5, Core.Color("&aArquería"), archeryitem);
		        
		        List<String> miscellaneousitem = new ArrayList<String>();
		        miscellaneousitem.add(Core.Color("&7Compra caña, enderpearl y más"));
		        ItemBuildAPI.createItem(Material.EYE_OF_ENDER, 1, 0, villagerinv, 6, Core.Color("&aMisceláneo"), miscellaneousitem);
		        
		        List<String> laboratoryitem = new ArrayList<String>();
		        laboratoryitem.add(Core.Color("&7Compra pociones"));
		        ItemBuildAPI.createItem(Material.POTION, 1, 0, villagerinv, 7, Core.Color("&aLaboratorio"), laboratoryitem);
		        
		        List<String> closeitem = new ArrayList<String>();
		        ItemBuildAPI.createItem(Material.WOOL, 1, 14, villagerinv, 17, Core.Color("&aCerrar"), closeitem);
		        
		        if (this.firstTime) {
		        	p.openInventory(villagerinv);
		        }
		        this.firstTime = false;
	    	}
	    }.runTaskTimer(Core.getCore(), 0, 10);
	}
}