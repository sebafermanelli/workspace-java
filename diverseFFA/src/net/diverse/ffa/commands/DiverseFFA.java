package net.diverse.ffa.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import net.diverse.ffa.Core;
import net.diverse.ffa.files.PlayerData;
import net.diverse.ffa.utils.LocationAPI;
import net.diverse.ffa.villager.NoAi;

public class DiverseFFA implements CommandExecutor {
	
	public static List<String> removevillager = new ArrayList<String>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("diverseffa")) {
				if(args.length == 0) {
					sender.sendMessage(Core.Color("&8======== &aDiverseFFA &8========"));
					sender.sendMessage(Core.Color("&e/coins -Jugador &8- &fCantidad de monedas&7."));
					sender.sendMessage(Core.Color("&e/spawn &8- &fVe al spawn&7."));
					sender.sendMessage(Core.Color("&e/stats -Jugador &8- &fEstadísticas de un jugador&7."));
					sender.sendMessage(Core.Color("&e/spectate &8- &fObserva desde otro punto de vista&7."));
					sender.sendMessage(Core.Color("&e/diverseffa topkills &8- &fPodio de bajas&7."));
					sender.sendMessage(Core.Color("&e/diverseffa topstreaks &8- &fPodio de rachas&7."));
					if(sender.hasPermission("diverse.ffa.admin")) {
						sender.sendMessage(Core.Color("&c/diverseffa setspawn &8- &fEstablece el spawn&7."));
						sender.sendMessage(Core.Color("&c/diverseffa shop -Add/Remove &8- &fAñade/Remueve una tienda&7."));
						sender.sendMessage(Core.Color("&c/coins -Jugador -Add/Remove -Cantidad &8- &fAñade/Remueve monedas&7."));
						sender.sendMessage(Core.Color("&c/enderchest -Jugador &8- &fMira el cofre privado&7."));
						sender.sendMessage(Core.Color("&c/diverseffa cleardrops &8- &fElimina los elementos en el suelo&7."));
						sender.sendMessage(Core.Color("&c[ffa] | disposal &8- &fAñade un cartel de desechos&7."));
						sender.sendMessage(Core.Color("&c[ffa] | iron | C(1) | S(1) &8- &fAñade un cartel de venta de hierro&7."));
					}
					return true;
				}
				
				else if(args[0].equalsIgnoreCase("topkills")) {
					sender.sendMessage(Core.Color("&8======== &aPodio de bajas (10) &8========"));
					sender.sendMessage(Core.Color("&6&lPosición 1: &e" + PlayerData.playerTopKills().get(0) + " &8- &f" + PlayerData.killsTopKills().get(0) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 2: &e" + PlayerData.playerTopKills().get(1) + " &8- &f" + PlayerData.killsTopKills().get(1) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 3: &e" + PlayerData.playerTopKills().get(2) + " &8- &f" + PlayerData.killsTopKills().get(2) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 4: &e" + PlayerData.playerTopKills().get(3) + " &8- &f" + PlayerData.killsTopKills().get(3) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 5: &e" + PlayerData.playerTopKills().get(4) + " &8- &f" + PlayerData.killsTopKills().get(4) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 6: &e" + PlayerData.playerTopKills().get(5) + " &8- &f" + PlayerData.killsTopKills().get(5) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 7: &e" + PlayerData.playerTopKills().get(6) + " &8- &f" + PlayerData.killsTopKills().get(6) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 8: &e" + PlayerData.playerTopKills().get(7) + " &8- &f" + PlayerData.killsTopKills().get(7) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 9: &e" + PlayerData.playerTopKills().get(8) + " &8- &f" + PlayerData.killsTopKills().get(8) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 10: &e" + PlayerData.playerTopKills().get(9) + " &8- &f" + PlayerData.killsTopKills().get(9) + " &7Bajas."));
					
					return true;
				}
				
				else if(args[0].equalsIgnoreCase("topstreaks")) {
					sender.sendMessage(Core.Color("&8======== &aPodio de rachas (10) &8========"));
					sender.sendMessage(Core.Color("&6&lPosición 1: &e" + PlayerData.playerTopStreaks().get(0) + " &8- &f" + PlayerData.streakTopStreaks().get(0) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 2: &e" + PlayerData.playerTopStreaks().get(1) + " &8- &f" + PlayerData.streakTopStreaks().get(1) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 3: &e" + PlayerData.playerTopStreaks().get(2) + " &8- &f" + PlayerData.streakTopStreaks().get(2) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 4: &e" + PlayerData.playerTopStreaks().get(3) + " &8- &f" + PlayerData.streakTopStreaks().get(3) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 5: &e" + PlayerData.playerTopStreaks().get(4) + " &8- &f" + PlayerData.streakTopStreaks().get(4) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 6: &e" + PlayerData.playerTopStreaks().get(5) + " &8- &f" + PlayerData.streakTopStreaks().get(5) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 7: &e" + PlayerData.playerTopStreaks().get(6) + " &8- &f" + PlayerData.streakTopStreaks().get(6) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 8: &e" + PlayerData.playerTopStreaks().get(7) + " &8- &f" + PlayerData.streakTopStreaks().get(7) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 9: &e" + PlayerData.playerTopStreaks().get(8) + " &8- &f" + PlayerData.streakTopStreaks().get(8) + " &7Bajas."));
					sender.sendMessage(Core.Color("&6&lPosición 10: &e" + PlayerData.playerTopStreaks().get(9) + " &8- &f" + PlayerData.streakTopStreaks().get(9) + " &7Bajas."));
					
					return true;
				}
				
				else if(args[0].equalsIgnoreCase("cleardrops")) {
					if(sender.hasPermission("diverse.ffa.admin")) {
						World world = Bukkit.getServer().getWorld(Core.getCore().getConfig().getString("WorldFFA"));
						List<Entity> entList = world.getEntities();
						for(Entity current : entList) {
							if((current instanceof Item)) {
								current.remove();
				            }
						}
						for(Player p1 : Bukkit.getOnlinePlayers()) {
							p1.sendMessage(Core.Color(Core.prefix + "&7Se han removido los elementos del suelo."));
						}
					}
					else {
						sender.sendMessage(Core.Color("&cERROR: &7No tienes permiso."));
					}
					return true;
				}
				
				else if(args[0].equalsIgnoreCase("setspawn")) {
					if(sender.hasPermission("diverse.ffa.admin")) {
						Location loc = p.getLocation();
						
						Core.getCore().getConfig().set("Spawn", LocationAPI.setLoc(loc));
						Core.getCore().saveConfig();
						
						sender.sendMessage(Core.Color(Core.prefix + "&7Se ha establecido el spawn en: &f" + loc.getBlockX() + "&7,&f" + loc.getBlockY() + "&7,&f" + loc.getBlockZ() + "&7,&f" + loc.getYaw() + "&7,&f" + loc.getPitch() + "&7."));
					}
					else {
						sender.sendMessage(Core.Color("&cERROR: &7No tienes permiso."));
					}
					return true;
				}
				
				else if(args[0].equalsIgnoreCase("shop")) {
					if(sender.hasPermission("diverse.ffa.admin")) {
						if(args.length == 1) {
							sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/ffa shop -Add/Remove&7."));
							return true;
						}
						
						if(args[1].equalsIgnoreCase("add")) {
							Villager v = (Villager)p.getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
							
					        v.setCustomName(Core.Color("&aTienda"));
					        v.resetMaxHealth();
					        v.setVelocity(v.getVelocity().multiply(0));
					        v.setMaximumNoDamageTicks(-1);
					        v.setProfession(Profession.BLACKSMITH);
					        v.setCustomNameVisible(true);
					        v.setCanPickupItems(false);
					        
					        NoAi.setNoAI(v);
					        sender.sendMessage(Core.Color(Core.prefix + "&7Se ha añadido una tienda correctamente."));
							return true;
						}
						else if(args[1].equalsIgnoreCase("remove")) {
							if(!removevillager.contains(p.getName())) {
								removevillager.add(p.getName());
								sender.sendMessage(Core.Color(Core.prefix + "&7Has &aactivado &7el modo remover tienda, haz click derecho sobre una tienda para eliminarla."));
							}
							else {
								removevillager.remove(p.getName());
								sender.sendMessage(Core.Color(Core.prefix + "&7Has &cdesactivado &7el modo remover tienda."));
							}
							return true;
						}
					}
					else {
						sender.sendMessage(Core.Color("&c&lERROR: &7No tienes permiso."));
					}
					return true;
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/diverseffa&7."));
				}
				return true;
			}
		}
		return false;
	}
}
