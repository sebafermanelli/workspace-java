package net.diverse.chat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.chat.Core;
import net.diverse.chat.utils.TitlesAPI;

public class StaffChat implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
	    }
		else {
		    Player p = (Player)sender;
		    
		    if(cmd.getName().equalsIgnoreCase("staffchat")) {
		    	if(p.hasPermission("diverse.chat.admin")) {
			    	if (args.length == 0) {
			    		sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/staffchat -Mensaje&7."));
			    		return true;
			        }
			    	else {
			        	String msg = "";
			        	int i = 0;
			        	while (i < args.length) {
			        		msg = msg + args[i] + " ";
			        		i++;
			        	}
			        	
			        	for (Player p1 : Bukkit.getOnlinePlayers()) {
			        		if(p.hasPermission("diverse.chat.admin")) {
			                  p1.sendMessage(Core.Color("&6" + p.getName() + " &8> &6EA &3DiverseMC&7: &f" + msg));
			                }
			        	}
			        	TitlesAPI.sendTitle(p, "&a&lMensaje enviado", "&7Receptor: &eEquipo administrativo &3&lDiverseMC&7.", 20, 20, 20);
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
