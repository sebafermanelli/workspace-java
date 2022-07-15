package net.diverse.auth.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.diverse.auth.Core;
import net.diverse.auth.files.PlayerData;
import net.diverse.auth.utils.TitlesAPI;

public class Register implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
		
			if(cmd.getName().equalsIgnoreCase("register")) {
				if(args.length == 0) {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/register -Contraseña&7."));
					return true;
				}
				else if(args.length == 1) {
					if(args[0].length() > 5) {
						if(args[0].length() < 17) {
							String password = args[0];
							
							if(PlayerData.playerExists(p.getUniqueId())) {
								sender.sendMessage(Core.Color("&c&lERROR: &7Ya estás registrado, inicia sesión: &e/login -Contraseña&7."));
							}
							else {
								sender.sendMessage(Core.Color(Core.prefix + "&7Te has registrado correctamente."));
								TitlesAPI.sendTitle(p, "&a&lRegistrado", "&7Bienvenido &e" + p.getName() + " &7a &3&lDiverseMC&7.", 20, 60, 20);
								
								p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 5.0F, 12.0F);
								
								Core.Firework(p);
								Core.removePlayerList(p);
								
								PlayerData.registerPlayer(p.getUniqueId(), p, password);
							}
						}
						else {
							sender.sendMessage(Core.Color("&c&lERROR: &7La contraseña debe tener como máximo &f16 &7carácteres."));
						}
					}
					else {
						sender.sendMessage(Core.Color("&c&lERROR: &7La contraseña debe tener al menos &f6 &7carácteres."));
					}
					return true;
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/register -Contraseña&7."));
					return true;
				}
			}
		}
		return false;
	}
}