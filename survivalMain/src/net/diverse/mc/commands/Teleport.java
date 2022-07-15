package net.diverse.mc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.mc.Core;

public class Teleport implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("teleport")) {
				if(sender.hasPermission("diverse.mc.admin")) {
					if(args.length == 0) {
						sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/teleport -DeJugador -AJugador&7."));
						return true;
					}
					
					if(args.length == 1) {
						Player player = Bukkit.getPlayerExact(args[0]);
						
						if(player == null) {
							sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[0] + " &7no existe."));
						}
						else {
							if(p == player) {
								 sender.sendMessage(Core.Color("&c&lERROR: &7No puedes teletransportarte a tu ubicación."));
							}
							else {
								p.teleport(player.getLocation());
								player.sendMessage(Core.Color(Core.prefix + "&7Te has teletransportado a la ubicación de &e" + p.getName() + "&7."));
							}
						}
						return true;
					}
					
					if(args.length == 2) {
						Player deplayer = Bukkit.getPlayerExact(args[0]);
						Player toplayer = Bukkit.getPlayerExact(args[1]);
						
						if(deplayer == null || toplayer == null) {
							sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[0] + " &7no existe."));
						}
						else {
							if(deplayer == toplayer) {
								 sender.sendMessage(Core.Color("&c&lERROR: &7No puedes teletransportar a &e" + deplayer.getName() + " &7a la ubicación de &e" + toplayer.getName() + "&7."));
							}
							else {
								deplayer.teleport(toplayer.getLocation());
								deplayer.sendMessage(Core.Color(Core.prefix + "&7Te has teletransportado a la ubicación de &e" + toplayer.getName() + "&7."));
								toplayer.sendMessage(Core.Color(Core.prefix + "&e" + deplayer.getName() + " &7se ha teletransportado a tu ubicación."));
							}
						}
						return true;
					}
					else {
						sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/teleport -DeJugador -AJugador&7."));
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