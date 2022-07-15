package net.diverse.ffa.events;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.diverse.ffa.Core;
import net.diverse.ffa.files.PlayerData;

public class SignSell implements Listener {
	
	public boolean isInventoryFull(Player p) {
		return p.getInventory().firstEmpty() == -1;
	}
	
	@EventHandler
	public void onCreateSign(SignChangeEvent event) {
		Player p = event.getPlayer();
		
		if(p.hasPermission("diverse.ffa.admin")) {
			if(StringUtils.isNumericSpace(event.getLine(2).replaceAll("[^\\d.]", "")) && StringUtils.isNumericSpace(event.getLine(3).replaceAll("[^\\d.]", ""))) {
				if(event.getLine(0).equalsIgnoreCase("[ffa]") && (event.getLine(1).equalsIgnoreCase("iron") && event.getLine(2).equalsIgnoreCase("C" + Integer.parseInt(event.getLine(3).replaceAll("[^\\d.]", ""))) && event.getLine(3).equalsIgnoreCase("S" + Integer.parseInt(event.getLine(3).replaceAll("[^\\d.]", ""))))) {
					int cantiron = Integer.parseInt(event.getLine(2).replaceAll("[^\\d.]", ""));
					int coinscost = Integer.parseInt(event.getLine(3).replaceAll("[^\\d.]", ""));
					
					event.setLine(0, Core.Color("&6&lCOMPRAR"));
					event.setLine(1, Core.Color("&0&oHierro"));
					event.setLine(2, Core.Color("&0&oCant &f" + cantiron));
					event.setLine(3, Core.Color("&0&oCoins &f" + coinscost));
					p.sendMessage(Core.Color(Core.prefix + "&7Se ha añadido el cartel correctamente."));
				}
			}
		}
		else {
			p.sendMessage(Core.Color("&c&lERROR: &7No tienes permiso."));
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		
		if(event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		if((event.getClickedBlock().getState() instanceof Sign)) {
			Sign s = (Sign)event.getClickedBlock().getState();
			if(s.getLine(2).contains("Cant") && s.getLine(3).contains("Coins")) {
				if(isInventoryFull(p)) {
					p.sendMessage(Core.Color("&c&lERROR: &7No tienes espacio suficiente en tu inventario."));
				}
				else {
					int cantiron = Integer.parseInt(s.getLine(2).replaceAll("[^\\d.]", ""));
					int coinscost = Integer.parseInt(s.getLine(3).replaceAll("[^\\d.]", ""));
					
					if(PlayerData.getCoins(p.getUniqueId()) >= coinscost) {
						PlayerData.updateCoins(p.getUniqueId(), PlayerData.getCoins(p.getUniqueId()) - coinscost);
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, cantiron));
						p.updateInventory();
					}
					else {
						p.sendMessage(Core.Color("&c&lERROR: &7No tienes suficientes monedas."));
					}
				}
			}
		}
	}
}
