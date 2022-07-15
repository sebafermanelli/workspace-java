package net.diverse.ffa.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.ffa.Core;
import net.diverse.ffa.utils.LocationAPI;

public class Spectate implements CommandExecutor {
	
	public static List<String> spec = new ArrayList<String>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
		
			if(cmd.getName().equalsIgnoreCase("spectate")) {
				if(p.hasPermission("diverse.ffa.donor")) {
					if(args.length == 0) {
						if(spec.contains(p.getName())) {
							if(!Core.getCore().getConfig().contains("Spawn")){
								sender.sendMessage(Core.Color("&c&lERROR: &7El spawn no existe, usa: &e/diverseffa setspawn&7."));
							}
							
							spec.remove(p.getName());
							p.setGameMode(GameMode.SURVIVAL);
							p.playSound(p.getEyeLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
							new LocationAPI();
							p.teleport(LocationAPI.getLoc(Core.getCore().getConfig().getString("Spawn")));
							p.sendMessage(Core.Color(Core.prefix + "&7Has &cdesactivado &7el modo espectador."));
							p.setPlayerListName(Core.Color("&e" + p.getName()));
						}
						else {
							if((p.getLocation().getX() < 273 && p.getLocation().getX() > 237) && (p.getLocation().getY() < 61 && p.getLocation().getY() > 52) && (p.getLocation().getZ() < 276 && p.getLocation().getZ() > 240)) {
								spec.add(p.getName());
								p.setGameMode(GameMode.SPECTATOR);
								p.playSound(p.getEyeLocation(), Sound.NOTE_PLING, 10, 10);
								p.sendMessage(Core.Color(Core.prefix + "&7Has &aactivado &7el modo espectador, utilizá el click derecho sobre otros jugadores para ver sus estadísticas."));
								p.setPlayerListName(Core.Color("&6" + p.getName()));
							}
							else {
								p.sendMessage(Core.Color("&c&lERROR: &7Para activar el modo espectador debes estar en el spawn&7."));
							}
						}
					}
					else {
						sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/spectate&7."));
					}
				}
				else {
		    		sender.sendMessage(Core.Color("&c&lERROR: &7No tienes permiso."));
		    	}
				return true;
			}
		}
		return false;
	}
}
