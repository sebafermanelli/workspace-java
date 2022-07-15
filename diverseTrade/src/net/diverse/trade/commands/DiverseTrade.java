package net.diverse.trade.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.trade.Core;

public class DiverseTrade implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			
			if(cmd.getName().equalsIgnoreCase("diversetrade")) {
				if(args.length == 0) {
					sender.sendMessage(Core.Color("&8======== &aDiverseTrade &8========"));
					sender.sendMessage(Core.Color("&e/trade request -Jugador &8- &fIntercambia con un jugador&7."));
					sender.sendMessage(Core.Color("&e/trade accept -Jugador &8- &fAcepta el intercambio con un jugador&7."));
					return true;
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/diversetrade&7."));
					return true;
				}
			}
		}
		return false;
	}
}