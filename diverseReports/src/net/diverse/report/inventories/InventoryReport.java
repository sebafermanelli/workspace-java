package net.diverse.report.inventories;

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
import net.diverse.report.Core;
import net.diverse.report.files.PlayerData;
import net.diverse.report.utils.ItemBuildAPI;

public class InventoryReport implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player p = (Player)event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inv = event.getInventory();
		String rname = event.getInventory().getName().replace("Reportar a ", "");
	    Player r = Bukkit.getPlayerExact(rname);
		
		if(clicked != null) {
			if((inv.getName().startsWith("Reportar a ")) && (clicked.getType() == Material.WOOL) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.closeInventory();
				return;
			}
			if((inv.getName().startsWith("Reportar a ")) && (clicked.getType() == Material.BOOK_AND_QUILL) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.sendMessage(Core.Color(Core.prefix + "&7Tu reporte hacia &e" + r.getName() + " &7fue enviado."));
				for(Player p1 : Bukkit.getOnlinePlayers()) {
					if(p1.hasPermission("diverse.report.admin")) {
						p1.sendMessage(Core.Color(Core.prefix + "&e" + p.getName() + " &7reportó a &e" + r.getName() + " &7por &bSpam/Flooding&7."));
					}
				}
				PlayerData.createReport(p, r, "Spam/Flooding");
				p.closeInventory();
				return;
			}
			if((inv.getName().startsWith("Reportar a ")) && (clicked.getType() == Material.IRON_SWORD) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.sendMessage(Core.Color(Core.prefix + "&7Tu reporte hacia &e" + r.getName() + " &7fue enviado."));
				for(Player p1 : Bukkit.getOnlinePlayers()) {
					if(p1.hasPermission("diverse.report.admin")) {
						p1.sendMessage(Core.Color(Core.prefix + "&e" + p.getName() + " &7reportó a &e" + r.getName() + " &7por &bHack&7."));
					}
				}
				PlayerData.createReport(p, r, "Hack");
				p.closeInventory();
				return;
			}
			if((inv.getName().startsWith("Reportar a ")) && (clicked.getType() == Material.BARRIER) && (clicked.getAmount() == 1)) {
				event.setCancelled(true);
				p.sendMessage(Core.Color(Core.prefix + "&7Tu reporte hacia &e" + r.getName() + " &7fue enviado."));
				for(Player p1 : Bukkit.getOnlinePlayers()) {
					if(p1.hasPermission("diverse.report.admin")) {
						p1.sendMessage(Core.Color(Core.prefix + "&e" + p.getName() + " &7reportó a &e" + r.getName() + " &7por &bBug&7."));
					}
				}
				PlayerData.createReport(p, r, "Bug");
				p.closeInventory();
				return;
			}
		}
	}
	
	public static void openReportInventory(final Player p, final Player r) {
	    final Inventory report = Bukkit.createInventory(null, 27, "Reportar a " + r.getName());
	    
		List<String> spamitem = new ArrayList<String>();
		spamitem.add(Core.Color("&7¿&e" + r.getName() + " &7ha estado enviando &cmensajes publicitarios"));
		spamitem.add(Core.Color("&7o &cmensajes rapidamente&7?"));
        ItemBuildAPI.createItem(Material.BOOK_AND_QUILL, 1, 0, report, 11, Core.Color("&aChat (Spam/Flood)"), spamitem);
        
        List<String> hackitem = new ArrayList<String>();
		hackitem.add(Core.Color("&7¿&e" + r.getName() + " &7está utilizando un &ccliente externo&7?"));
        ItemBuildAPI.createItem(Material.IRON_SWORD, 1, 0, report, 13, Core.Color("&aCliente externo (Hack)"), hackitem);
        
        List<String> bugitem = new ArrayList<String>();
		bugitem.add(Core.Color("&7¿&e" + r.getName() + " &7ha estado abusando de los &cerrores del servidor&7?"));
        ItemBuildAPI.createItem(Material.BARRIER, 1, 0, report, 15, Core.Color("&aAbuso de errores (Bugs)"), bugitem);
		
		List<String> closeitem = new ArrayList<String>();
        ItemBuildAPI.createItem(Material.WOOL, 1, 14, report, 26, Core.Color("&aCerrar"), closeitem);
        
        p.openInventory(report);
	}
}