package net.diverse.auth.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.diverse.auth.Core;
import net.diverse.auth.files.PlayerData;
import net.diverse.auth.utils.TitlesAPI;

public class Login implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
		
			if(cmd.getName().equalsIgnoreCase("login")) {
				if(args.length == 0) {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/login -Contraseña&7."));
					return true;
				}
				else if(args.length == 1) {
					String correctpass = args[0];
					
					if(PlayerData.playerExists(p.getUniqueId())) {
						if(!Core.isPlayerList(p)) {
							sender.sendMessage(Core.Color("&c&lERROR: &7Ya estás conectado."));
						}
						else {
							if(PlayerData.correctPassword(p.getUniqueId(), correctpass)) {
								sender.sendMessage(Core.Color(Core.prefix + "&7Has iniciado sesión correctamente."));
								TitlesAPI.sendTitle(p, "&a&lConectado", "&7Bienvenido nuevamente &e" + p.getName() + "&7.", 20, 60, 20);
								
								p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 5.0F, 12.0F);
								
								Core.Firework(p);
								Core.removePlayerList(p);
							    Core.attempts.remove(p.getName());
							    Core.waitcooldown.remove(p.getName());
							}
							else {
								if(Core.attempts.get(p.getName()) > 0) {
									sender.sendMessage(Core.Color("&c&lERROR: &7La contraseña no coincide."));
									sender.sendMessage(Core.Color(Core.prefix + "&7Tienes &f" + Core.attempts.get(p.getName()) + " &7intentos más."));
									
									Core.attempts.put(p.getName(), Core.attempts.get(p.getName()) - 1);
								}
								else {
									Core.attempts.remove(p.getName());
									Core.waitcooldown.remove(p.getName());
									
									p.kickPlayer(Core.Color("&c&lERROR: &7Has fallado &f3 &7intentos, vuelve a intentarlo más tarde."));
								}
							}
						}
					}
					else {
						sender.sendMessage(Core.Color("&c&lERROR: &7Primero debes registrarte: &e/register -Contraseña&7."));
					}
					return true;
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/login -Contraseña&7."));
					return true;
				}
			}
		}
		return false;
	}
}