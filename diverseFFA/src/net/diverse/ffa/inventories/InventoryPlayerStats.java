package net.diverse.ffa.inventories;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import net.diverse.ffa.Core;
import net.diverse.ffa.files.PlayerData;
import net.diverse.ffa.utils.ItemBuildAPI;

public class InventoryPlayerStats implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player p = (Player)event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inv = event.getInventory();
	    String rname = event.getInventory().getName().replace("Inventario de ", "");
	    Player r = Bukkit.getPlayerExact(rname);
		
	    if(clicked != null) {
			if((inv.getName().startsWith("Inventario de ")) && (clicked.getType() == Material.ENDER_CHEST) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.closeInventory();
				InventoryPlayerEnderchest.openInvEnderchest(p, r);
				return;
			}
			if((inv.getName().startsWith("Inventario de ")) && (clicked.getType() == Material.WOOL) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.closeInventory();
				return;
			}
			if((inv.getName().startsWith("Inventario de "))) {
				event.setCancelled(true);
				return;
			}
	    }
	}
	
	public static void openInvInventory(final Player p, final Player r) {
		
		final PlayerInventory pinv = r.getInventory();
	    final Inventory inventory = Bukkit.createInventory(null, 54, "Inventario de " + r.getName());
	    
	    new BukkitRunnable() {
	    	boolean firstTime = true;
	    	public void run() {
		        List<String> enderchestitem = new ArrayList<String>();
		        ItemBuildAPI.createItem(Material.ENDER_CHEST, 1, 0, inventory, 0, Core.Color("&aCofre privado de &e" + r.getName()), enderchestitem);
		        
		        List<String> live = new ArrayList<String>();
		        live.add(Core.Color("&7" + r.getHealth() / 2.0D + "&8/&7" + r.getMaxHealth() / 2.0D));
		        ItemBuildAPI.createItem(Material.SPECKLED_MELON, (int)r.getHealth() / 2, 0, inventory, 2, Core.Color("&aVida de &e" + r.getName()), live);
		        
		        List<String> hunger = new ArrayList<String>();
		        hunger.add(Core.Color("&7" + r.getFoodLevel() / 2.0D + "&8/&710.0"));
		        ItemBuildAPI.createItem(Material.APPLE, (int)r.getFoodLevel() / 2, 0, inventory, 3, Core.Color("&aComida de &e" + r.getName()), hunger);
		        
		        List<String> coords = new ArrayList<String>();
		        coords.add(Core.Color("&bX: &7" + r.getLocation().getBlockX()));
		        coords.add(Core.Color("&bY: &7" + r.getLocation().getBlockY()));
		        coords.add(Core.Color("&bZ: &7" + r.getLocation().getBlockZ()));
		        ItemBuildAPI.createItem(Material.COMPASS, 1, 0, inventory, 4, Core.Color("&aCoordenadas de &e" + r.getName()), coords);
		        
		        List<String> pots = new ArrayList<String>();
		        List<PotionEffect> potsEf = (List<PotionEffect>)r.getActivePotionEffects();
		        int i = 0;
		        while (i < r.getActivePotionEffects().size()) {
		        	pots.add(ChatColor.translateAlternateColorCodes('&', Core.Color("&b" + ((PotionEffect)potsEf.get(i)).getType().getName().replace("_", " ")) + " &8- &c" + ItemBuildAPI.ConvertSecondToHHMMString(((PotionEffect)potsEf.get(i)).getDuration() / 20) + " &8- &6" + ((PotionEffect)potsEf.get(i)).getAmplifier() + 1));
		        	i++;
		        }
		        ItemBuildAPI.createItem(Material.POTION, 1, 0, inventory, 5, Core.Color("&aEfectos de &e" + r.getName()), pots);
		        
		        List<String> statsitem = new ArrayList<String>();
		        statsitem.add(Core.Color("&bCoins: &7" + PlayerData.getCoins(r.getUniqueId())));
		        statsitem.add(Core.Color("&bBajas: &7" + PlayerData.getKills(r.getUniqueId())));
		        statsitem.add(Core.Color("&bMuertes: &7" + PlayerData.getDeaths(r.getUniqueId())));
		        statsitem.add(Core.Color("&bKDR: &7" + PlayerData.getKDR(r.getUniqueId())));
		        statsitem.add(Core.Color("&bRacha: &7" + PlayerData.getStreak(r.getUniqueId())));
		        statsitem.add(Core.Color("&bMejor Racha: &7" + PlayerData.getBestStreak(r.getUniqueId())));
		        ItemBuildAPI.createItem(Material.PAPER, 1, 0, inventory, 6, Core.Color("&aEstadísticas de &e" + r.getName()), statsitem);
		        
		        List<String> closeitem = new ArrayList<String>();
		        ItemBuildAPI.createItem(Material.WOOL, 1, 14, inventory, 8, Core.Color("&aCerrar"), closeitem);
		        
		        ItemBuildAPI.createItem(pinv.getHelmet(), inventory, 10);
		        ItemBuildAPI.createItem(pinv.getChestplate(), inventory, 12);
		        ItemBuildAPI.createItem(pinv.getLeggings(), inventory, 14);
		        ItemBuildAPI.createItem(pinv.getBoots(), inventory, 16);
		        
		        ItemBuildAPI.createItem(pinv.getItem(0), inventory, 18);
		        ItemBuildAPI.createItem(pinv.getItem(1), inventory, 19);
		        ItemBuildAPI.createItem(pinv.getItem(2), inventory, 20);
		        ItemBuildAPI.createItem(pinv.getItem(3), inventory, 21);
		        ItemBuildAPI.createItem(pinv.getItem(4), inventory, 22);
		        ItemBuildAPI.createItem(pinv.getItem(5), inventory, 23);
		        ItemBuildAPI.createItem(pinv.getItem(6), inventory, 24);
		        ItemBuildAPI.createItem(pinv.getItem(7), inventory, 25);
		        ItemBuildAPI.createItem(pinv.getItem(8), inventory, 26);
		        
		        int c = 9;
		        int c2 = 27;
		        while (c2 < 54) {
		        	ItemBuildAPI.createItem(pinv.getItem(c), inventory, c2);
		        	c++;
		        	c2++;
		        }
		        
		        if (this.firstTime) {
		        	p.openInventory(inventory);
		        }
		        this.firstTime = false;
	    	}
	    }.runTaskTimer(Core.getCore(), 0, 10);
	}
}
