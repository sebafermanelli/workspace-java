package net.diverse.report.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.report.Core;
import net.diverse.report.inventories.InventoryReport;

public class Report implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
		
			if(cmd.getName().equalsIgnoreCase("report")) {
				if(args.length == 0) {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/report -Jugador&7."));
					return true;
				}
				else if(args.length == 1) {
					Player reported = Bukkit.getPlayerExact(args[0]);
					
					if(reported == sender) {
			            sender.sendMessage(Core.Color("&c&lERROR: &7No puedes reportarte a ti mismo."));
		    		}
					else {
						if(reported == null) {
							sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[0] + " &7no existe."));
						}
						else {
							InventoryReport.openReportInventory(p, reported);
						}
					}
					return true;
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/report -Jugador&7."));
				}
				return true;
			}
		}
		return false;
	}
}
