package net.diverse.mc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.mc.Core;

public class Help implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			
			if(cmd.getName().equalsIgnoreCase("help")) {
				if(args.length == 0) {
					sender.sendMessage(Core.Color("&8======== &a1/1 &8========"));
					sender.sendMessage(Core.Color("&e/diversemc &8- &fObten ayuda sobre el plugin &bDiverseMC&7."));
					sender.sendMessage(Core.Color("&e/diverseauth &8- &fObten ayuda sobre el plugin &bDiverseAuth&7."));
					sender.sendMessage(Core.Color("&e/diversechat &8- &fObten ayuda sobre el plugin &bDiverseChat&7."));
					sender.sendMessage(Core.Color("&e/diverseffa &8- &fObten ayuda sobre el plugin &bDiverseFFA&7."));
					sender.sendMessage(Core.Color("&e/diversereport &8- &fObten ayuda sobre el plugin &bDiverseReport&7."));
					sender.sendMessage(Core.Color("&e/diverseyoutuber &8- &fObten ayuda sobre el plugin &bDiverseYoutuber&7."));
					sender.sendMessage(Core.Color("&e/diversetrade &8- &fObten ayuda sobre el plugin &bDiverseTrade&7."));
					return true;
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/help&7."));
					return true;
				}
			}
		}
		return false;
	}
}