package net.diverse.ffa.events;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import net.diverse.ffa.Core;
import net.diverse.ffa.inventories.InventoryTopStreaks;

public class SignTopStreaks implements Listener {
	
	@EventHandler
	public void onCreateSign(SignChangeEvent event) {
		Player p = event.getPlayer();
		
		if(p.hasPermission("diverse.ffa.admin")) {
			if(event.getLine(0).equalsIgnoreCase("[ffa]") && (event.getLine(1).equalsIgnoreCase("topstreaks"))) {
				event.setLine(0, Core.Color("&a&lFFA"));
				event.setLine(1, Core.Color(""));
				event.setLine(2, Core.Color("&0&oPodio"));
				event.setLine(3, Core.Color("&0&oRachas"));
				p.sendMessage(Core.Color(Core.prefix + "&7Se ha a�adido el cartel correctamente."));
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
			if(s.getLine(0).contains(Core.Color("&a&lFFA")) && s.getLine(3).contains("Rachas")) {
				InventoryTopStreaks.openTopStreaksInventory(p);
			}
		}
	}
}
