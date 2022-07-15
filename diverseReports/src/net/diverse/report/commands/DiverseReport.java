package net.diverse.report.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.report.Core;

public class DiverseReport implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("diversereport")) {
				if(args.length == 0) {
					sender.sendMessage(Core.Color("&8======== &aDiverseReport &8========"));
					sender.sendMessage(Core.Color("&e/report -Jugador &8- &fReporta un jugador&7."));
					if(p.hasPermission("diverse.report.admin")) {
						sender.sendMessage(Core.Color("&c/reportlist &8- &fMira la lista de reportes&7."));
						sender.sendMessage(Core.Color("&c/reportlist remove -ID &8- &fElimina un reporte&7."));
					}
					return true;
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/diversereport&7."));
					return true;
				}
			}
		}
		return false;
	}
}