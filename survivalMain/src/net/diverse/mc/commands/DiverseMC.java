package net.diverse.mc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.mc.Core;

public class DiverseMC implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("diversemc")) {
				if(args.length == 0) {
					sender.sendMessage(Core.Color("&8======== &aDiverseMC &8========"));
					sender.sendMessage(Core.Color("&e/help &8- &fAbre la ayuda general de plugins &bDiverse&7."));
					if(p.hasPermission("diverse.mc.admin")) {
						sender.sendMessage(Core.Color("&c/gamemode -Creative/Survival/Adventure/Spectator -Jugador &8- &fCambia el modo de juego&7."));
						sender.sendMessage(Core.Color("&c/fly -Jugador &8- &fActiva el modo vuelo&7."));
						sender.sendMessage(Core.Color("&c/vanish -Jugador &8- &fActiva el modo desaparecer&7."));
						sender.sendMessage(Core.Color("&c/teleport -DeJugador -AJugador &8- &fTeletransporte&7."));
					}
					return true;
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/diversemc&7."));
					return true;
				}
			}
		}
		return false;
	}
}