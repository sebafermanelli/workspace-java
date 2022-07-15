package net.diverse.mc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.diverse.mc.Core;

public class Vanish implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("vanish")) {
				if(sender.hasPermission("diverse.mc.admin")) {
					if(args.length == 0) {
						if(Core.vanishPlayers.contains(p.getName())) {
							for(Player p1 : Bukkit.getOnlinePlayers()) {
								p1.showPlayer(p);
							}
							p.removePotionEffect(PotionEffectType.INVISIBILITY);
							Core.vanishPlayers.remove(p.getName());
							sender.sendMessage(Core.Color(Core.prefix + "&7Has &cdesactivado &7el modo desaparecer."));
						}
						else {
							for(Player p1 : Bukkit.getOnlinePlayers()) {
								p1.hidePlayer(p);
							}
							p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 5));
							Core.vanishPlayers.add(p.getName());
							sender.sendMessage(Core.Color(Core.prefix + "&7Has &aactivado &7el modo desaparecer."));
						}
						return true;
					}
					
					if(args.length == 1) {
						Player player = Bukkit.getPlayerExact(args[0]);
						
						if(player == null) {
							sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[0] + " &7no existe."));
						}
						else {
							if(Core.vanishPlayers.contains(player.getName())) {
								for(Player p1 : Bukkit.getOnlinePlayers()) {
									p1.showPlayer(player);
								}
								player.removePotionEffect(PotionEffectType.INVISIBILITY);
								Core.vanishPlayers.remove(player.getName());
								sender.sendMessage(Core.Color(Core.prefix + "&7Has &cdesactivado &7el modo desaparecer a &e" + player.getName() + "&7."));
								player.sendMessage(Core.Color(Core.prefix + "&7El jugador &e" + p.getName() + " &7te ha &cdesactivado &7el modo desaparecer."));
							}
							else {
								for(Player p1 : Bukkit.getOnlinePlayers()) {
									p1.hidePlayer(player);
								}
								player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 5));
								Core.vanishPlayers.add(player.getName());
								sender.sendMessage(Core.Color(Core.prefix + "&7Has &aactivado &7el modo desaparecer a &e" + player.getName() + "&7."));
								player.sendMessage(Core.Color(Core.prefix + "&7El jugador &e" + p.getName() + " &7te ha &aactivado &7el modo desaparecer."));
							}
						}
						return true;
					}
					else {
						sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/vanish -Jugador&7."));
						return true;
					}
				}
				else {
					sender.sendMessage(Core.Color("&cERROR: &7No tienes permiso."));
				}
			}
		}
		return false;
	}
}