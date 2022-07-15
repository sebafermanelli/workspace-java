package net.diverse.chat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.chat.Core;

public class DiverseChat implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("diversechat")) {
				if(args.length == 0) {
					sender.sendMessage(Core.Color("&8======== &aDiverseChat &8========"));
					sender.sendMessage(Core.Color("&e/tell -Jugador -Mensaje &8- &fEnvia mensaje privado&7."));
					sender.sendMessage(Core.Color("&e/reply -Mensaje &8- &fContesta mensaje privado&7."));
					sender.sendMessage(Core.Color("&a/conversation -Jugador &8- &fComienza una conversación&7."));
					if(p.hasPermission("diverse.chat.admin")) {
						sender.sendMessage(Core.Color("&c/broadcast -Mensaje &8- &fEnvía un anuncio&7."));
						sender.sendMessage(Core.Color("&c/staffchat -Mensaje &8- &fChat privado del Equipo Administrativo&7."));
					}
					return true;
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/diversechat&7."));
					return true;
				}
			}
		}
		return false;
	}
}