package net.diverse.ffa.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.ffa.Core;
import net.diverse.ffa.inventories.InventoryPlayerStats;

public class Stats implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
		
			if(cmd.getName().equalsIgnoreCase("stats")) {
				if(args.length == 0) {
					InventoryPlayerStats.openInvInventory(p, p);
					return true;
				}
				else if(args.length == 1) {
					Player pstats = Bukkit.getPlayerExact(args[0]);
					
					if(pstats == null) {
						sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[0] + " &7no existe."));
					}
					else {
						InventoryPlayerStats.openInvInventory(p, pstats);
					}
					return true;
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/stats -Jugador&7."));
				}
				return true;
			}
		}
		return false;
	}
}
