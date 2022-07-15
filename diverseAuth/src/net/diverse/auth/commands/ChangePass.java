package net.diverse.auth.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.auth.Core;
import net.diverse.auth.files.PlayerData;

public class ChangePass implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
		
			if(cmd.getName().equalsIgnoreCase("changepass")) {
				if(args.length == 0) {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/changepass -ContraseñaVieja -ContraseñaNueva&7."));
					return true;
				}
				else if(args.length == 1) {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/changepass -ContraseñaVieja -ContraseñaNueva&7."));
					return true;
				}
				else if(args.length == 2) {
					if(args[0].equals(args[1])) {
						sender.sendMessage(Core.Color("&c&lERROR: &7La nueva contraseña es igual a la anterior."));
					}
					else {
						if(args[1].length() > 5) {
							if(args[1].length() < 17) {
								String oldpassword = args[0];
								String newpassword = args[1];
								
								if(PlayerData.correctPassword(p.getUniqueId(), oldpassword)) {
									sender.sendMessage(Core.Color(Core.prefix + "&7Cambiaste la contraseña correctamente. Tu nueva contraseña es: &e" + newpassword + "&7."));
									PlayerData.updatePassword(p.getUniqueId(), newpassword);
								}
								else {
									sender.sendMessage(Core.Color("&c&lERROR: &7Tu contraseña actual no coincide, intenta nuevamente."));
								}
							}
							else {
								sender.sendMessage(Core.Color("&c&lERROR: &7La contraseña debe tener como máximo &f16 &7carácteres."));
							}
						}
						else {
							sender.sendMessage(Core.Color("&c&lERROR: &7La contraseña debe tener al menos &f6 &7carácteres."));
						}
					}
					return true;
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/changepass -ContraseñaVieja -ContraseñaNueva&7."));
					return true;
				}
			}
		}
		return false;
	}
}