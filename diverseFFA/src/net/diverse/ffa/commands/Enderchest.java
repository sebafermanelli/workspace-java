package net.diverse.ffa.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.ffa.Core;
import net.diverse.ffa.inventories.InventoryEnderchest;

public class Enderchest implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
		
			if(cmd.getName().equalsIgnoreCase("enderchest")) {
				if(args.length == 0) {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/enderchest -Jugador&7."));
					return true;
				}
				else if(args.length == 1) {
					File filePlayers = new File("plugins/" + Core.getCore().getDescription().getName() + "/enderchest/" + p.getName() + ".yml");
					
					if(!filePlayers.exists()) {
						sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[0] + " &7no existe."));
					}
					else {
						InventoryEnderchest.openInvEnderchest(p, args[0]);
					}
					return true;
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/enderchest -Jugador&7."));
				}
				return true;
			}
		}
		return false;
	}
}
