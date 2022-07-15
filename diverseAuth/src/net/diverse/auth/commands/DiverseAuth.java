package net.diverse.auth.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.auth.Core;

public class DiverseAuth implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			if(cmd.getName().equalsIgnoreCase("diverseauth")) {
				if(args.length == 0) {
					sender.sendMessage(Core.Color("&8======== &aDiverseAuth &8========"));
					sender.sendMessage(Core.Color("&e/register -Contraseña &8- &fRegistrarse&7."));
					sender.sendMessage(Core.Color("&e/login -Contraseña &8- &fConectarse&7."));
					sender.sendMessage(Core.Color("&e/changepass -ContraseñaVieja -ContraseñaNueva &8- &fCambiar la contraseña&7."));
					sender.sendMessage(Core.Color("&e/logout &8- &fDesconectarse&7."));
					return true;
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/diverseauth&7."));
					return true;
				}
			}
		}
		return false;
	}
}