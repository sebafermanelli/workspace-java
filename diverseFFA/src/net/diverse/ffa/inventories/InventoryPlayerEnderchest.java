package net.diverse.ffa.inventories;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import net.diverse.ffa.Core;
import net.diverse.ffa.files.PlayerData;
import net.diverse.ffa.utils.ItemBuildAPI;

public class InventoryPlayerEnderchest implements Listener {
	
	@EventHandler
	public void onEnderchestClick(InventoryClickEvent event) {
		Player p = (Player)event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inv = event.getInventory();
	    String rname = event.getInventory().getName().replace("Cofre privado de ", "");
	    Player r = Bukkit.getPlayerExact(rname);
		
	    if(clicked != null) {
			if((inv.getName().startsWith("Cofre privado de ")) && (clicked.getType() == Material.SKULL_ITEM) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.closeInventory();
				InventoryPlayerStats.openInvInventory(p, r);
				return;
			}
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
	
	public static void openInvEnderchest(final Player p, final Player r) {
		
		final Inventory enderchest = Bukkit.createInventory(null, 54, "Cofre privado de " + r.getName());
	    
	    new BukkitRunnable() {
	    	boolean firstTime = true;
			public void run() {
				File filePlayers = new File("plugins/" + Core.getCore().getDescription().getName() + "/enderchest/" + p.getName() + ".yml");
				FileConfiguration getDataPlayers = YamlConfiguration.loadConfiguration(filePlayers);
	    		
	    		List<String> headlore = new ArrayList<String>();
		        ItemBuildAPI.createSkull(r.getName(), enderchest, 0, Core.Color("&aInventario de &e" + r.getName()), headlore);
		        
		        List<String> live = new ArrayList<String>();
		        live.add(Core.Color("&7" + r.getHealth() / 2.0D + "&8/&7" + r.getMaxHealth() / 2.0D));
		        ItemBuildAPI.createItem(Material.SPECKLED_MELON, (int)r.getHealth() / 2, 0, enderchest, 2, Core.Color("&aVida de &e" + r.getName()), live);
		        
		        List<String> hunger = new ArrayList<String>();
		        hunger.add(Core.Color(Core.Color("&7" + r.getFoodLevel() / 2.0D + "&8/&710.0")));
		        ItemBuildAPI.createItem(Material.APPLE, (int)r.getFoodLevel() / 2, 0, enderchest, 3, Core.Color("&aComida de &e" + r.getName()), hunger);
		        
		        List<String> coords = new ArrayList<String>();
		        coords.add(Core.Color("&bX: &7" + r.getLocation().getBlockX()));
		        coords.add(Core.Color("&bY: &7" + r.getLocation().getBlockY()));
		        coords.add(Core.Color("&bZ: &7" + r.getLocation().getBlockZ()));
		        ItemBuildAPI.createItem(Material.COMPASS, 1, 0, enderchest, 4, Core.Color("&aCoordenadas de &e" + r.getName()), coords);
		        
		        List<String> pots = new ArrayList<String>();
		        List<PotionEffect> potsEf = (List<PotionEffect>)r.getActivePotionEffects();
		        int i = 0;
		        while (i < r.getActivePotionEffects().size()) {
		        	pots.add(ChatColor.translateAlternateColorCodes('&', Core.Color("&b" + ((PotionEffect)potsEf.get(i)).getType().getName().replace("_", " ")) + " &8- &c" + ItemBuildAPI.ConvertSecondToHHMMString(((PotionEffect)potsEf.get(i)).getDuration() / 20) + " &8- &6" + ((PotionEffect)potsEf.get(i)).getAmplifier() + 1));
		        	i++;
		        }
		        ItemBuildAPI.createItem(Material.POTION, 1, 0, enderchest, 5, Core.Color("&aEfectos de &e" + r.getName()), pots);
		        
		        List<String> statsitem = new ArrayList<String>();
		        statsitem.add(Core.Color("&bCoins: &7" + PlayerData.getCoins(r.getUniqueId())));
		        statsitem.add(Core.Color("&bBajas: &7" + PlayerData.getKills(r.getUniqueId())));
		        statsitem.add(Core.Color("&bMuertes: &7" + PlayerData.getDeaths(r.getUniqueId())));
		        statsitem.add(Core.Color("&bKDR: &7" + PlayerData.getKDR(r.getUniqueId())));
		        statsitem.add(Core.Color("&bRacha: &7" + PlayerData.getStreak(r.getUniqueId())));
		        statsitem.add(Core.Color("&bMejor Racha: &7" + PlayerData.getBestStreak(r.getUniqueId())));
		        ItemBuildAPI.createItem(Material.PAPER, 1, 0, enderchest, 6, Core.Color("&aEstadísticas de &e" + r.getName()), statsitem);
		        
		        List<String> closeitem = new ArrayList<String>();
		        ItemBuildAPI.createItem(Material.WOOL, 1, 14, enderchest, 8, Core.Color("&aCerrar"), closeitem);
		        
		        if(filePlayers.exists()) {
					@SuppressWarnings("unchecked")
					List<ItemStack> isl = (List<ItemStack>)getDataPlayers.getList(r.getName() + ".ENDERCHEST");
		    		if(getDataPlayers.getList(r.getName() + ".ENDERCHEST") == null) {
		    			
		    		}
		    		else {
			    		int i1 = 0;
			    		while(i1 < isl.size()) {
			    			ItemBuildAPI.createItem(isl.get(i1), enderchest, i1 + 9);
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
