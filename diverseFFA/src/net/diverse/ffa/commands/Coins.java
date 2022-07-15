package net.diverse.ffa.commands;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.ffa.Core;
import net.diverse.ffa.files.PlayerData;

public class Coins implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("coins")) {
				if(args.length == 0) {
					sender.sendMessage(Core.Color(Core.prefix + "&7Tienes &f" + PlayerData.getCoins(p.getUniqueId()) + " &7monedas."));
					return true;
				}
				
				if(args.length == 1) {
					Player playercoins = Bukkit.getPlayerExact(args[0]);
					
					if(playercoins == null) {
						sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[0] + " &7no existe."));
					}
					else {
						sender.sendMessage(Core.Color(Core.prefix + "&7El jugador &e" + playercoins.getName() +" &7tiene &f" + PlayerData.getCoins(playercoins.getUniqueId()) + " &7monedas."));
					}
					return true;
				}
				
				if(args[1].equalsIgnoreCase("add")) {
					if(sender.hasPermission("diverse.ffa.admin")) {
						if(args.length == 3) {
							Player playercoins = Bukkit.getPlayerExact(args[0]);
							
							if(playercoins == null) {
								sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[0] + " &7no existe."));
							}
							else {
								if(StringUtils.isNumericSpace(args[2])) {
									int coins = Integer.parseInt(args[2]);
									
									if(Integer.parseInt(args[2]) > 10000) {
										sender.sendMessage(Core.Color("&c&lERROR: &7El máximo de monedas que se puede agregar es &f10000&7."));
									}
									else {
										if(Integer.parseInt(args[2]) == 0) {
											sender.sendMessage(Core.Color("&c&lERROR: &7Introduce un numero mayor que &f0 &7para agregar monedas."));
										}
										else {
											Bukkit.getConsoleSender().sendMessage("[" + Core.getCore().getDescription().getName() + "] " + sender.getName() + " added " + coins + " coins to " + playercoins.getName());
											sender.sendMessage(Core.Color(Core.prefix + "&7Has agregado &f" + coins + " &7monedas a &e" + playercoins.getName() + "&7."));
											PlayerData.updateCoins(playercoins.getUniqueId(), PlayerData.getCoins(playercoins.getUniqueId()) + coins);
											playercoins.sendMessage(Core.Color("&a+" + coins + " Monedas"));
										}
									}
								}
								else {
									sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/coins -Jugador -Add/Remove -Cantidad&7."));
								}
							}
						}
						else {
							sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/coins -Jugador -Add/Remove -Cantidad&7."));
						}
					}
					else {
						sender.sendMessage(Core.Color("&c&lERROR: &7No tienes permiso."));
					}
					return true;
				}
				
				if(args[1].equalsIgnoreCase("remove")) {
					if(sender.hasPermission("diverse.ffa.admin")) {
						if(args.length == 3) {
							Player playercoins = Bukkit.getPlayerExact(args[0]);
							
							if(playercoins == null) {
								sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[0] + " &7no existe."));
							}
							else {
								if(StringUtils.isNumericSpace(args[2])) {
									
									if(Integer.parseInt(args[2]) > 10000) {
										sender.sendMessage(Core.Color("&c&lERROR: &7El máximo de monedas que se puede quitar es &f10000&7."));
									}
									else {
										if(Integer.parseInt(args[2]) == 0) {
											sender.sendMessage(Core.Color("&c&lERROR: &7Introduce un numero mayor que &f0 &7para quitar monedas."));
										}
										else if(PlayerData.getCoins(playercoins.getUniqueId()) < Integer.parseInt(args[2])) {
											
											if(PlayerData.getCoins(playercoins.getUniqueId()) == 0) {
												sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + playercoins.getName() + " &7no tiene monedas."));
											}
											else {
												Bukkit.getConsoleSender().sendMessage("[" + Core.getCore().getDescription().getName() + "] " + sender.getName() + " removed " + PlayerData.getCoins(playercoins.getUniqueId()) + " coins to " + playercoins.getName());
												sender.sendMessage(Core.Color(Core.prefix + "&7Has quitado &f" + PlayerData.getCoins(playercoins.getUniqueId()) + " &7monedas a &e" + playercoins.getName() + "&7."));
												playercoins.sendMessage(Core.Color("&c-" + PlayerData.getCoins(playercoins.getUniqueId()) + " Monedas"));
												PlayerData.updateCoins(playercoins.getUniqueId(), 0);
											}
										}
										else {
											int coins = Integer.parseInt(args[2]);
											
											Bukkit.getConsoleSender().sendMessage("[" + Core.getCore().getDescription().getName() + "] " + sender.getName() + " removed " + coins + " coins to " + playercoins.getName());
											sender.sendMessage(Core.Color(Core.prefix + "&7Has quitado &f" + coins + " &7monedas a &e" + playercoins.getName() + "&7."));
											playercoins.sendMessage(Core.Color("&c-" + coins + " Monedas"));
											PlayerData.updateCoins(playercoins.getUniqueId(), PlayerData.getCoins(playercoins.getUniqueId()) - coins);
										}
									}
								}
								else {
									sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/coins -Jugador -Add/Remove -Cantidad&7."));
								}
							}
						}
						else {
							sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/coins -Jugador -Add/Remove -Cantidad&7."));
						}
					}
					else {
						sender.sendMessage(Core.Color("&c&lERROR: &7No tienes permiso."));
					}
					return true;
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/coins -Jugador -Add/Remove -Cantidad&7."));
				}
				return true;
			}
		}
		return false;
	}
}
