package net.diverse.mc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.mc.Core;

public class Fly implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("fly")) {
				if(sender.hasPermission("diverse.mc.admin")) {
					if(args.length == 0) {
						if(p.getAllowFlight()) {
							p.setAllowFlight(false);
							sender.sendMessage(Core.Color(Core.prefix + "&7Has &cdesactivado &7el modo vuelo."));
						}
						else {
							p.setAllowFlight(true);
							sender.sendMessage(Core.Color(Core.prefix + "&7Has &aactivado &7el modo vuelo."));
						}
						return true;
					}
					
					if(args.length == 1) {
						Player player = Bukkit.getPlayerExact(args[0]);
						
						if(player == null) {
							sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[0] + " &7no existe."));
						}
						else {
							if(player.getAllowFlight()) {
								player.setAllowFlight(false);
								sender.sendMessage(Core.Color(Core.prefix + "&7Has &cdesactivado &7el modo vuelo a &e" + player.getName() + "&7."));
								player.sendMessage(Core.Color(Core.prefix + "&7El jugador &e" + p.getName() + " &7te ha &cdesactivado &7el modo vuelo."));
							}
							else {
								player.setAllowFlight(true);
								sender.sendMessage(Core.Color(Core.prefix + "&7Has &aactivado &7el modo vuelo a &e" + player.getName() + "&7."));
								player.sendMessage(Core.Color(Core.prefix + "&7El jugador &e" + p.getName() + " &7te ha &aactivado &7el modo vuelo."));
							}
						}
						return true;
					}
					else {
						sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/fly -Jugador&7."));
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