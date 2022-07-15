package net.diverse.mc.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.mc.Core;

public class Gamemode implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("gamemode")) {
				if(sender.hasPermission("diverse.mc.admin")) {
					if(args.length == 0) {
						sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/gamemode -Modo -Jugador&7."));
						sender.sendMessage(Core.Color("&c&lERROR: &7Modos de juego: &bCREATIVE(1)&7, &bSURVIVAL(2)&7, &bADVENTURE(3)&7, &bSPECTATOR(4)&7."));
						return true;
					}
					else if(args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1")) {
						if(args.length == 1) {
							if(p.getGameMode() == GameMode.CREATIVE) {
								sender.sendMessage(Core.Color("&c&lERROR: &7Actualmente te encuentras en modo de juego creativo."));
							}
							else {
								p.setGameMode(GameMode.CREATIVE);
								sender.sendMessage(Core.Color(Core.prefix + "&7Has &aactivado &7el modo de juego creativo."));
							}
							return true;
						}
						if(args.length == 2) {
							Player player = Bukkit.getPlayerExact(args[1]);
							
							if(player == null) {
								sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[1] + " &7no existe."));
							}
							else {
								if(player.getGameMode() == GameMode.CREATIVE) {
									sender.sendMessage(Core.Color("&c&lERROR: &7Actualmente &e" + player.getName() + " &7se encuentra en modo de juego creativo."));
								}
								else {
									player.setGameMode(GameMode.CREATIVE);
									sender.sendMessage(Core.Color(Core.prefix + "&7Has &aactivado &7el modo de juego creativo a &e" + player.getName() + "&7."));
									player.sendMessage(Core.Color(Core.prefix + "&7El jugador &e" + p.getName() + " &7te ha &aactivado &7el modo de juego creativo."));
								}
							}
							return true;
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("2")) {
						if(args.length == 1) {
							if(p.getGameMode() == GameMode.SURVIVAL) {
								sender.sendMessage(Core.Color("&c&lERROR: &7Actualmente te encuentras en modo de juego supervivencia."));
							}
							else {
								p.setGameMode(GameMode.SURVIVAL);
								sender.sendMessage(Core.Color(Core.prefix + "&7Has &aactivado &7el modo de juego supervivencia."));
							}
							return true;
						}
						if(args.length == 2) {
							Player player = Bukkit.getPlayerExact(args[1]);
							
							if(player == null) {
								sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[1] + " &7no existe."));
							}
							else {
								if(player.getGameMode() == GameMode.SURVIVAL) {
									sender.sendMessage(Core.Color("&c&lERROR: &7Actualmente &e" + player.getName() + " &7se encuentra en modo de juego supervivencia."));
								}
								else {
									player.setGameMode(GameMode.SURVIVAL);
									sender.sendMessage(Core.Color(Core.prefix + "&7Has &aactivado &7el modo de juego supervivencia a &e" + player.getName() + "&7."));
									player.sendMessage(Core.Color(Core.prefix + "&7El jugador &e" + p.getName() + " &7te ha &aactivado &7el modo de juego supervivencia."));
								}
							}
							return true;
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("3")) {
						if(args.length == 1) {
							if(p.getGameMode() == GameMode.ADVENTURE) {
								sender.sendMessage(Core.Color("&c&lERROR: &7Actualmente te encuentras en modo de juego aventura."));
							}
							else {
								p.setGameMode(GameMode.ADVENTURE);
								sender.sendMessage(Core.Color(Core.prefix + "&7Has &aactivado &7el modo de juego aventura."));
							}
							return true;
						}
						if(args.length == 2) {
							Player player = Bukkit.getPlayerExact(args[1]);
							
							if(player == null) {
								sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[1] + " &7no existe."));
							}
							else {
								if(player.getGameMode() == GameMode.ADVENTURE) {
									sender.sendMessage(Core.Color("&c&lERROR: &7Actualmente &e" + player.getName() + " &7se encuentra en modo de juego aventura."));
								}
								else {
									player.setGameMode(GameMode.ADVENTURE);
									sender.sendMessage(Core.Color(Core.prefix + "&7Has &aactivado &7el modo de juego aventura a &e" + player.getName() + "&7."));
									player.sendMessage(Core.Color(Core.prefix + "&7El jugador &e" + p.getName() + " &7te ha &aactivado &7el modo de juego aventura."));
								}
							}
							return true;
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("4")) {
						if(args.length == 1) {
							if(p.getGameMode() == GameMode.SPECTATOR) {
								sender.sendMessage(Core.Color("&c&lERROR: &7Actualmente te encuentras en modo de juego espectador."));
							}
							else {
								p.setGameMode(GameMode.SPECTATOR);
								sender.sendMessage(Core.Color(Core.prefix + "&7Has &aactivado &7el modo de juego espectador."));
							}
							return true;
						}
						if(args.length == 2) {
							Player player = Bukkit.getPlayerExact(args[1]);
							
							if(player == null) {
								sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[1] + " &7no existe."));
							}
							else {
								if(player.getGameMode() == GameMode.SPECTATOR) {
									sender.sendMessage(Core.Color("&c&lERROR: &7Actualmente &e" + player.getName() + " &7se encuentra en modo de juego espectador."));
								}
								else {
									player.setGameMode(GameMode.SPECTATOR);
									sender.sendMessage(Core.Color(Core.prefix + "&7Has &aactivado &7el modo de juego espectador a &e" + player.getName() + "&7."));
									player.sendMessage(Core.Color(Core.prefix + "&7El jugador &e" + p.getName() + " &7te ha &aactivado &7el modo de juego espectador."));
								}
							}
							return true;
						}
						return true;
					}
					else {
						sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/gamemode -Modo -Jugador&7."));
						sender.sendMessage(Core.Color("&c&lERROR: &7Modos de juego: &bCREATIVE(1)&7, &bSURVIVAL(2)&7, &bADVENTURE(3)&7, &bSPECTATOR(4)&7."));
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