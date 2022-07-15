package net.diverse.trade.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.trade.Core;
import net.diverse.trade.inventories.InventoryTrade;

public class Trade implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("trade")) {
				if(args.length == 0) {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/trade -Request/Accept -Jugador&7."));
					return true;
				}
				if(args.length == 1) {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/trade -Request/Accept -Jugador&7."));
					return true;
				}
				if(args.length == 2) {
					if(args[0].equalsIgnoreCase("request")) {
						Player playertotrade = Bukkit.getPlayerExact(args[1]);
						
						if(playertotrade == null) {
							sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[1] + " &7no existe."));
						}
						else {
							sender.sendMessage(Core.Color(Core.prefix + "&7Has enviado una solicitud de intercambio a &e" + playertotrade.getName() + "&7."));
							Core.requesttrade.put(playertotrade, p);
							playertotrade.sendMessage(Core.Color(Core.prefix + "&7El jugador &e" + sender.getName() + " &7ha &ainiciado &7un intercambio contigo. Para aceptar utiliza &e/trade accept -Jugador&7."));
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("accept")) {
						if(Core.requesttrade.containsKey(p)) {
							Player playertotrade = Core.requesttrade.get(p);
							
							if(playertotrade == null) {
								sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[1] + " &7no existe."));
								Core.requesttrade.remove(p);
							}
							else {
								InventoryTrade.openTradeInventory(p, playertotrade);
								Core.requesttrade.remove(p);
							}
						}
						else {
							sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[1] + " &7no te ha enviado una solicitud de intercambio."));
						}
						return true;
					}
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/trade -Request/Accept -Jugador&7."));
					return true;
				}
			}
		}
		return false;
	}
}