package net.diverse.report.commands;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.report.Core;
import net.diverse.report.files.PlayerData;

public class ReportList implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
		
			if(cmd.getName().equalsIgnoreCase("reportlist")) {
				if(p.hasPermission("diverse.report.admin")) {
					if(args.length == 0) {
						for(int i : PlayerData.playerID()) {
							sender.sendMessage(Core.Color("&7ID: &f" + i + "&7. &e" + PlayerData.getReporter(i) + " &7reportó a &e" + PlayerData.getReported(i) + " &7por &b" + PlayerData.getType(i)));
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("remove")) {
						if(args.length == 2) {
							if(StringUtils.isNumericSpace(args[1])) {
								int id = Integer.parseInt(args[1]);
								PlayerData.deleteReport(id);
								sender.sendMessage(Core.Color(Core.prefix + "&7Has &celiminado &7el reporte ID: &f" + id + "&7."));
							}
							else {
								sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/reportlist remove -ID&7."));
							}
						}
						else {
							sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/reportlist remove -ID&7."));
						}
					}
					else {
						sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/reportlist&7."));
					}
				}
				else {
					sender.sendMessage(Core.Color("&cERROR: &7No tienes permiso."));
				}
				return true;
			}
		}
		return false;
	}
}
