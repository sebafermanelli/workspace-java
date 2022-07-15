package net.diverse.ffa.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.diverse.ffa.Core;
import net.diverse.ffa.commands.DiverseFFA;
import net.diverse.ffa.inventories.InventoryPlayerStats;
import net.diverse.ffa.inventories.InventoryVillager;

public class GameManager implements Listener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
		
		if(Core.getCore().getConfig().getBoolean("BuildMode", true)) {
			if(p.hasPermission("diverse.ffa.admin")) {
				return;
			}
			else {
				if((p.getLocation().getX() < 273 && p.getLocation().getX() > 237) && (p.getLocation().getY() < 61 && p.getLocation().getY() > 52) && (p.getLocation().getZ() < 276 && p.getLocation().getZ() > 240)) {
					event.setCancelled(true);
				}
				else {
					if(event.getBlock().getType() == Material.FIRE) {
						return;
					}
					else {
						event.setCancelled(true);
					}
				}
			}
		}
		else {
			if((p.getLocation().getX() < 273 && p.getLocation().getX() > 237) && (p.getLocation().getY() < 61 && p.getLocation().getY() > 52) && (p.getLocation().getZ() < 276 && p.getLocation().getZ() > 240)) {
				event.setCancelled(true);
			}
			else {
				if(event.getBlock().getType() == Material.FIRE) {
					return;
				}
				else {
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player p = event.getPlayer();
		
		if(Core.getCore().getConfig().getBoolean("BuildMode", true)) {
			if(p.hasPermission("diverse.ffa.admin")) {
				return;
			}
			else {
				if((p.getLocation().getX() < 273 && p.getLocation().getX() > 237) && (p.getLocation().getY() < 61 && p.getLocation().getY() > 52) && (p.getLocation().getZ() < 276 && p.getLocation().getZ() > 240)) {
					event.setCancelled(true);
				}
				else {
					if(event.getBlock().getType() == Material.FIRE) {
						return;
					}
					else {
						event.setCancelled(true);
					}
				}
			}
		}
		else {
			if((p.getLocation().getX() < 273 && p.getLocation().getX() > 237) && (p.getLocation().getY() < 61 && p.getLocation().getY() > 52) && (p.getLocation().getZ() < 276 && p.getLocation().getZ() > 240)) {
				event.setCancelled(true);
			}
			else {
				if(event.getBlock().getType() == Material.FIRE) {
					return;
				}
				else {
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onBlockBurn(BlockBurnEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		if(event.toWeatherState()) {
			event.setCancelled(true);
			event.getWorld().setThundering(false);
			event.getWorld().setStorm(false);
		}
	}
	
	@EventHandler
	public void gameRules(WorldLoadEvent event) {
		event.getWorld().setGameRuleValue("doMobSpawning", "false");
		event.getWorld().setGameRuleValue("doDaylightCycle", "false");
		event.getWorld().setGameRuleValue("mobGriefing", "false");
		event.getWorld().setGameRuleValue("doFireTick", "false");
	}
	
	@EventHandler
	public void onSpread(BlockIgniteEvent event) {
		if(event.getCause().equals(IgniteCause.SPREAD)) {
			event.setCancelled(true);
		}
	}
	
	 @EventHandler
	 public void onIceMelt(BlockFadeEvent event) {
		 Block affectedBlock = event.getBlock();
		 
		 if(affectedBlock.getType() == Material.ICE) {
			 event.setCancelled(true);
			 affectedBlock.setType(Material.ICE);
		 }
	 }
	 
	 @EventHandler
	 public void onSnowMelt(BlockFadeEvent event) {
		 Block affectedBlock = event.getBlock();
		 
		 if(affectedBlock.getType() == Material.SNOW) {
			 event.setCancelled(true);
			 affectedBlock.setType(Material.SNOW);
		 }
	 }
	
	@EventHandler
	public void noFallDamage(EntityDamageEvent event) {
		if(event.getEntity().getType() == EntityType.PLAYER) {
			if(Core.getCore().getConfig().getBoolean("FallDamage", true)) {
				if((event.getCause() == DamageCause.FALL) || event.getCause() == DamageCause.FALLING_BLOCK) {
					event.setCancelled(false);
				}
			}
			else {
				if((event.getCause() == DamageCause.FALL) || event.getCause() == DamageCause.FALLING_BLOCK) {
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerDamageVillager(EntityDamageEvent event) {
	    if(event.getEntity() instanceof Villager) {
	    	Villager v = (Villager)event.getEntity();
	    	
	    	if(v.getLocation().getWorld().getName().equalsIgnoreCase(Core.getCore().getConfig().getString("WorldFFA"))) {
	    		event.setCancelled(true);
	    	}
	    }
	}
	
	@EventHandler
	public void onPlayerInteractVillager(PlayerInteractEntityEvent event) {
		Player p = event.getPlayer();
		
		if(event.getRightClicked() instanceof Villager) {
			Villager v = (Villager)event.getRightClicked();
			
			event.setCancelled(true);
			
			if(v.getCustomName().equalsIgnoreCase(Core.Color("&aTienda"))) {
				if(p.getGameMode() == GameMode.SPECTATOR) {
			    	event.setCancelled(true);
			    }
				else {
					if(!DiverseFFA.removevillager.contains(p.getName())) {
						InventoryVillager.openVillagerInventory(p);
					}
					else {
						Location loc = v.getLocation();
						v.remove();
						p.sendMessage(Core.Color(Core.prefix + "&7Se ha removido una tienda en: &f" + loc.getBlockX() + "&7,&f" + loc.getBlockY() + "&7,&f" + loc.getBlockZ() + "&7,&f" + loc.getYaw() + "&7,&f" + loc.getPitch() + "&7."));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onInteractSpectator(PlayerInteractEvent event) {
	    Player p = event.getPlayer();
	    
	    if(p.getGameMode() == GameMode.SPECTATOR) {
	    	event.setCancelled(true);
	    }
	}
	
	@EventHandler
	public void onPlayerInteractPlayerSpectator(PlayerInteractEntityEvent event) {
		Player p = event.getPlayer();
	    
		if(event.getRightClicked() instanceof Player) {
			Player r = (Player)event.getRightClicked();
			
			event.setCancelled(true);
			
		    if(p.getGameMode() == GameMode.SPECTATOR) {
		    	InventoryPlayerStats.openInvInventory(p, r);
		    }
	    }
	}
	
	@EventHandler
	public void onDamagePlayer(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			if(event.getDamager() instanceof Player) {
				Player damaged = (Player)event.getEntity();
				
				if((damaged.getLocation().getX() < 273 && damaged.getLocation().getX() > 237) && (damaged.getLocation().getY() < 61 && damaged.getLocation().getY() > 52) && (damaged.getLocation().getZ() < 276 && damaged.getLocation().getZ() > 240)) {
						event.setCancelled(true);
				}
				else {
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerDrop(final PlayerDropItemEvent event) {
	    new BukkitRunnable() {
	    	public void run() {
	    		event.getItemDrop().remove();
	    	}
	    }.runTaskTimer(Core.getCore(), 6000L, 6000L);
	}
}
