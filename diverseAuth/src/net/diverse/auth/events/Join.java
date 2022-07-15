package net.diverse.auth.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.diverse.auth.Core;
import net.diverse.auth.files.PlayerData;
import net.diverse.auth.utils.ActionBarAPI;
import net.diverse.auth.utils.TitlesAPI;

public class Join implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		
		event.setJoinMessage(null);
		Core.addPlayerList(p);
		
		for(int i = 0; i < 100; i++) {
			p.sendMessage("");
		}
		
		if(p.hasPermission("diverse.auth.donor")) {
			for(Player p1 : Bukkit.getOnlinePlayers()) {
				p1.sendMessage(Core.Color(Core.prefix + "&7El jugador &e" + p.getName() + " &7ha entrado."));
			}
		}
		
		if(PlayerData.playerExists(p.getUniqueId())) {
			TitlesAPI.sendTitle(p, "&a&lInicia Sesión", "&7Utiliza: &e/login -Contraseña&7.", 20, 60, 20);
    		p.sendMessage(Core.Color(Core.prefix + "&7Inicia sesión utilizando: &e/login -Contraseña&7."));
			
    		Core.attempts.put(p.getName(), 2);
		}
		else {
			TitlesAPI.sendTitle(p, "&a&lRegistrate", "&7Utiliza: &e/register -Contraseña&7.", 20, 60, 20);
    		p.sendMessage(Core.Color(Core.prefix + "&7Registrate utilizando: &e/register -Contraseña&7."));
		}
		
		Core.waitcooldown.put(p.getName(), 30);
		new BukkitRunnable() {
			@Override
			public void run() {
				if(Core.isPlayerList(p)) {
					if(Core.waitcooldown.get(p.getName()) > 20) {
						if(Core.waitcooldown.containsKey(p.getName())) {
							ActionBarAPI.sendActionBar(p, "&7Tiempo restante: &a" + Core.waitcooldown.get(p.getName()));
							Core.waitcooldown.put(p.getName(), Core.waitcooldown.get(p.getName()) - 1);
						}
					}
					else if(Core.waitcooldown.get(p.getName()) > 10) {
						if(Core.waitcooldown.containsKey(p.getName())) {
							ActionBarAPI.sendActionBar(p, "&7Tiempo restante: &6" + Core.waitcooldown.get(p.getName()));
							Core.waitcooldown.put(p.getName(), Core.waitcooldown.get(p.getName()) - 1);
						}
					}
					else if(Core.waitcooldown.get(p.getName()) > 5) {
						if(Core.waitcooldown.containsKey(p.getName())) {
							ActionBarAPI.sendActionBar(p, "&7Tiempo restante: &e" + Core.waitcooldown.get(p.getName()));
							Core.waitcooldown.put(p.getName(), Core.waitcooldown.get(p.getName()) - 1);
						}
					}
					else if(Core.waitcooldown.get(p.getName()) > 0) {
						if(Core.waitcooldown.containsKey(p.getName())) {
							ActionBarAPI.sendActionBar(p, "&7Tiempo restante: &c" + Core.waitcooldown.get(p.getName()));
							Core.waitcooldown.put(p.getName(), Core.waitcooldown.get(p.getName()) - 1);
						}
					}
					else {
						Core.removePlayerList(p);
						Core.waitcooldown.remove(p.getName());
						Core.attempts.remove(p.getName());
						Core.beforeLogin.remove(p.getName());
						
						p.kickPlayer(Core.Color(Core.prefix + "&7Tiempo de espera agotado."));
						
						cancel();
					}
				}
				else {
					Core.waitcooldown.remove(p.getName());
					
					cancel();
				}
			}
		}.runTaskTimer(Core.getCore(), 0, 20);
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
		
	    if(Core.isPlayerList(p)) {
	    	event.setCancelled(true);
	    	
	    	if(PlayerData.playerExists(p.getUniqueId())) {
	    		p.sendMessage(Core.Color(Core.prefix + "&7Inicia sesión utilizando: &e/login -Contraseña&7."));
	    	}
	    	else {
	    		p.sendMessage(Core.Color(Core.prefix + "&7Registrate utilizando: &e/register -Contraseña&7."));
	    	}
	    }
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		
	    if(Core.isPlayerList(p)) {
	    	Location to = event.getFrom();
	    	to.setPitch(event.getTo().getPitch());
	    	to.setYaw(event.getTo().getYaw());
	    	event.setTo(to);
	    }
	}
	
	@EventHandler
	public void onBlockCmds(PlayerCommandPreprocessEvent event) {
		Player p = event.getPlayer();
		
	    if(Core.isPlayerList(p)) {
	    	if((event.getMessage().toLowerCase().startsWith("/login")) || (event.getMessage().toLowerCase().startsWith("/register"))) {
	    		event.setCancelled(false);
	    	}
	    	else {
	    		event.setCancelled(true);
	    		
	    		if(PlayerData.playerExists(p.getUniqueId())) {
	    			p.sendMessage(Core.Color(Core.prefix + "&7Inicia sesión utilizando: &e/login -Contraseña&7."));
		    	}
		    	else {
		    		p.sendMessage(Core.Color(Core.prefix + "&7Registrate utilizando: &e/register -Contraseña&7."));
		    	}
	    	}
	    }
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent event) {
		Player p = event.getPlayer();
		
		if(Core.isPlayerList(p)) {
	    	event.setCancelled(true);
	    }
	}
	
	@EventHandler
	public void onInventoryDrop(PlayerDropItemEvent event) {
	    Player p = event.getPlayer();
	    
		if(Core.isPlayerList(p)) {
	    	event.setCancelled(true);
	    }
	}
	
	@EventHandler
	public void onInventoryDrag(InventoryDragEvent event) {
		Player p = (Player)event.getWhoClicked();
	    
		if(Core.isPlayerList(p)) {
			event.setCancelled(true);
			event.getView().close();
	    }
	}
	
	@EventHandler
	public void onInventorySaver(InventoryClickEvent event) {
		Player p = (Player)event.getWhoClicked();
	   
		if(Core.isPlayerList(p)) {
			event.setCancelled(true);
			event.getView().close();
	    }
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
	    
		if(Core.isPlayerList(p)) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
	    Player p = event.getPlayer();
	   
	    if(Core.isPlayerList(p)) {
	    	event.setCancelled(true);
	    }
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			Player p = (Player)event.getEntity();
			if(Core.isPlayerList(p)) {
		    	event.setCancelled(true);
		    }
	    }
	}
}
