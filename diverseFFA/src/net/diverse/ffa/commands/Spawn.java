package net.diverse.ffa.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.diverse.ffa.Core;
import net.diverse.ffa.utils.LocationAPI;
import net.diverse.ffa.utils.TitlesAPI;

public class Spawn implements CommandExecutor {
	
	public static Map<String, Integer> spawncooldown = new HashMap<String, Integer>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
		
			if(cmd.getName().equalsIgnoreCase("spawn")) {
				if(args.length == 0) {
					if(!Core.getCore().getConfig().contains("Spawn")){
						sender.sendMessage(Core.Color("&c&lERROR: &7El spawn no existe, usa: &e/diverseffa setspawn&7."));
					}
					else {
						if(p.hasPermission("diverse.ffa.donor")) {
							spawncooldown.put(p.getName(), 2);
							new BukkitRunnable() {
								private final Location initial = p.getLocation();
								@Override
								public void run() {
									if(spawncooldown.get(p.getName()) > 0) {
										if(spawncooldown.containsKey(p.getName())) {
											p.playSound(p.getEyeLocation(), Sound.NOTE_PLING, 10, 10);
											TitlesAPI.sendTitle(p, "&a&lTeletransporte", "&7Espera &f" + spawncooldown.get(p.getName()) + " &7segundos.", 20, 20, 20);
											spawncooldown.put(p.getName(), spawncooldown.get(p.getName()) - 1);
										}
									}
									else {
										p.playSound(p.getEyeLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
										TitlesAPI.sendTitle(p, "", "", 20, 20, 20);
										spawncooldown.remove(p.getName());
										cancel();
										new LocationAPI();
										p.teleport(LocationAPI.getLoc(Core.getCore().getConfig().getString("Spawn")));
									}
									if(p.getLocation().distance(initial) > 0.1) {
										if(spawncooldown.containsKey(p.getName())) {
											if(spawncooldown.get(p.getName()) > 0) {
												p.getLastDamage();
												TitlesAPI.sendTitle(p, "&c&lTe moviste", "&7Teletransporte cancelado.", 20, 20, 20);
						                        spawncooldown.remove(p.getName());
						                        cancel();
											}
										}
									}
								}
							}.runTaskTimer(Core.getCore(), 0, 20);
						}
						else {
							spawncooldown.put(p.getName(), 5);
							new BukkitRunnable() {
								private final Location initial = p.getLocation();
								@Override
								public void run() {
									if(spawncooldown.get(p.getName()) > 0) {
										if(spawncooldown.containsKey(p.getName())) {
											p.playSound(p.getEyeLocation(), Sound.NOTE_PLING, 10, 10);
											TitlesAPI.sendTitle(p, "&a&lTeletransporte", "&7Espera &f" + spawncooldown.get(p.getName()) + " &7segundos.", 20, 20, 20);
											spawncooldown.put(p.getName(), spawncooldown.get(p.getName()) - 1);
										}
									}
									else {
										p.playSound(p.getEyeLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
										TitlesAPI.sendTitle(p, "", "", 20, 20, 20);
										spawncooldown.remove(p.getName());
										cancel();
										new LocationAPI();
										p.teleport(LocationAPI.getLoc(Core.getCore().getConfig().getString("Spawn")));
									}
									if(p.getLocation().distance(initial) > 0.1) {
										if(spawncooldown.containsKey(p.getName())) {
											if(spawncooldown.get(p.getName()) > 0) {
												p.getLastDamage();
												TitlesAPI.sendTitle(p, "&c&lTe moviste", "&7Teletransporte cancelado.", 20, 20, 20);
						                        spawncooldown.remove(p.getName());
						                        cancel();
											}
										}
									}
								}
							}.runTaskTimer(Core.getCore(), 0, 20);
						}
					}
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/spawn&7."));
				}
				return true;
			}
		}
		return false;
	}
}
