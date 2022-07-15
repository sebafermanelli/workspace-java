package net.diverse.ffa.events;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.diverse.ffa.Core;
import net.diverse.ffa.files.PlayerData;
import net.diverse.ffa.utils.ActionBarAPI;
import net.diverse.ffa.utils.Kit;
import net.diverse.ffa.utils.LocationAPI;
import net.diverse.ffa.utils.TitlesAPI;

public class Death implements Listener {
	
	@EventHandler
	public void onDamageArrow(EntityDamageByEntityEvent event) {
		
		if(event.getDamager() instanceof Arrow) {
			Player damaged = (Player)event.getEntity();
			Arrow arr = (Arrow)event.getDamager();
			Player damager = (Player)arr.getShooter();
			
			if(damager != damaged) {
				damager.sendMessage(Core.Color(Core.prefix + "&7Has golpeado a &e" + damaged.getName() + " &7desde &f" + (int)damager.getLocation().distance(damaged.getLocation()) + " &7bloques."));
			}
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player damaged = event.getEntity();
		Player damager = event.getEntity().getKiller();
		
		event.setDeathMessage(null);
		if(damaged instanceof Player) {
			if(damager instanceof Player) {
				if(damager != damaged) {
					double damagerhealth = Math.round(damager.getHealth() / 2.0);
					double damagermaxhealth = Math.round(damager.getMaxHealth() / 2.0);
					
					PlayerData.updateKills(damager.getUniqueId(), PlayerData.getKills(damager.getUniqueId()) + 1);
					PlayerData.updateStreak(damager.getUniqueId(), PlayerData.getStreak(damager.getUniqueId()) + 1);
					PlayerData.updateCoins(damager.getUniqueId(), PlayerData.getCoins(damager.getUniqueId()) + 1);
					
					PlayerData.updateStreak(damaged.getUniqueId(), 0);
					PlayerData.updateDeaths(damaged.getUniqueId(), PlayerData.getDeaths(damaged.getUniqueId()) + 1);
					
					if(PlayerData.getStreak(damager.getUniqueId()) > PlayerData.getBestStreak(damager.getUniqueId())) {
						PlayerData.updateBestStreak(damager.getUniqueId());
					}
					
					damager.sendMessage(Core.Color("&7Mataste a &e" + damaged.getName() + " &7con &c" + damagerhealth + "&8/&c" + damagermaxhealth));
					damaged.sendMessage(Core.Color("&e" + damager.getName() + " &7te mató con &c" + damagerhealth + "&8/&c" + damagermaxhealth));
					damager.sendMessage(Core.Color("&a+1 Monedas"));
					
					if(damager.hasPermission("diverse.ffa.donor")) {
						PlayerData.updateCoins(damager.getUniqueId(), PlayerData.getCoins(damager.getUniqueId()) + 2);
						damager.sendMessage(Core.Color("&b+2 Monedas bonificación"));
					}
					
					damager.playSound(damager.getEyeLocation(), Sound.SUCCESSFUL_HIT, 5.0F, 12.0F);
					damaged.playSound(damaged.getEyeLocation(), Sound.BLAZE_HIT, 50, 0);
					
					new BukkitRunnable() {
						@Override
						public void run() {
							if(PlayerData.getStreak(damager.getUniqueId()) != 0) {
								ActionBarAPI.sendActionBar(damager, "&e" + PlayerData.getStreak(damager.getUniqueId()));
							}
							else {
								cancel();
							}
						}
					}.runTaskTimerAsynchronously(Core.getCore(), 0, 10);
					
					if(PlayerData.getStreak(damager.getUniqueId()) == 2) {
						TitlesAPI.sendTitle(damager, "&6DOBLE BAJA", "&7¡&e" + damager.getName() + " &7, estás en racha!", 20, 20, 20);
					}
					else if(PlayerData.getStreak(damager.getUniqueId()) == 3) {
						TitlesAPI.sendTitle(damager, "&6TRIPLE BAJA", "&7¡&e" + damager.getName() + " &7, estás en racha!", 20, 20, 20);
					}
					else if(PlayerData.getStreak(damager.getUniqueId()) == 4) {
						TitlesAPI.sendTitle(damager, "&6CUADRUPLE BAJA", "&7¡&e" + damager.getName() + " &7, estás en racha!", 20, 20, 20);
					}
					else if(PlayerData.getStreak(damager.getUniqueId()) == 5) {
						TitlesAPI.sendTitle(damager, "&6QUINTUPLE BAJA", "&7¡&e" + damager.getName() + " &7, estás en racha!", 20, 20, 20);
					}
					
					if(damager.hasPermission("diverse.ffa.donor")) {
						damager.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200, 1));
						damager.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 2));
					}
					else {
						damager.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, 1));
						damager.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
					}
				}
				else {
					PlayerData.updateStreak(damaged.getUniqueId(), 0);
					PlayerData.updateDeaths(damaged.getUniqueId(), PlayerData.getDeaths(damaged.getUniqueId()) + 1);
					damaged.sendMessage(Core.Color("&cMoriste."));
					damaged.playSound(damaged.getEyeLocation(), Sound.BLAZE_HIT, 50, 0);
				}
			}
			else {
				PlayerData.updateStreak(damaged.getUniqueId(), 0);
				PlayerData.updateDeaths(damaged.getUniqueId(), PlayerData.getDeaths(damaged.getUniqueId()) + 1);
				damaged.sendMessage(Core.Color("&cMoriste."));
				damaged.playSound(damaged.getEyeLocation(), Sound.BLAZE_HIT, 50, 0);
			}
		}
	}
	
	@EventHandler
	public void onAutoRespawn(PlayerDeathEvent event) {
		Player p = (Player)event.getEntity();
		
		if(Core.getCore().getConfig().getBoolean("AutoRespawn", true)) {
			new BukkitRunnable() {
				@Override
				public void run() {
					p.spigot().respawn();
					new LocationAPI();
					p.teleport(LocationAPI.getLoc(Core.getCore().getConfig().getString("Spawn")));
				}
			}.runTaskLater(Core.getCore(), 10L);
		}
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event){	
		Player damaged = event.getPlayer();
		
		if(!Core.getCore().getConfig().contains("Spawn")){
			damaged.sendMessage(Core.Color("&c&lERROR: &7El spawn no existe, usa: &e/diverseffa setspawn&7."));
		}
		
		new LocationAPI();
		damaged.teleport(LocationAPI.getLoc(Core.getCore().getConfig().getString("Spawn")));
		damaged.setGameMode(GameMode.SURVIVAL);
		damaged.setExp(0.0F);
	    damaged.setLevel(0);
		Kit.addKit(damaged);
		damaged.setHealth(damaged.getMaxHealth());
		damaged.getInventory().setHeldItemSlot(0);
		damaged.setFoodLevel(damaged.getFoodLevel());
		damaged.setHealth(damaged.getMaxHealth());
	    damaged.setFireTicks(0);
	    damaged.setFireTicks(0);
	}
	
	@EventHandler
	public void InstantDeath(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		
		if(p.getLocation().getY() <= -10) {
			event.setCancelled(true);
			if(p.getHealth() > 0) {
				p.setHealth(0);
			}
			else {
				return;
			}
			
		}
	}
}
