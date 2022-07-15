package net.diverse.chat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.chat.Core;
import net.diverse.chat.utils.TitlesAPI;

public class Reply implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
	    }
		else {
		    Player p = (Player)sender;
		    
		    if(cmd.getName().equalsIgnoreCase("reply")) {
		    	if (args.length == 0) {
		    		sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/reply -Mensaje&7."));
		    		return true;
		        }
		    	else {
		    		CommandSender recreply = Bukkit.getServer().getPlayerExact((String)Core.tell.get(sender.getName()));
		    		
			        if(recreply == null) {
			        	sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + (String)Core.tell.get(sender.getName()) + " &7no existe."));
			        }
			        else {
			        	String msg = "";
			        	int i = 0;
			        	while (i < args.length) {
			        		msg = msg + args[i] + " ";
			        		i++;
			        	}
			        	
			        	Core.tell.put(sender.getName(), recreply.getName());
			        	Core.tell.put(recreply.getName(), sender.getName());
			        	sender.sendMessage(Core.Color("&6" + p.getName() + " &8> &6" + recreply.getName() + "&7: &f" + msg));
			        	recreply.sendMessage(Core.Color("&6" + p.getName() + " &8> &6" + recreply.getName() + "&7: &f" + msg));
			        	TitlesAPI.sendTitle(p, "&a&lMensaje enviado", "&7Receptor: &e" + recreply.getName() + "&7.", 20, 20, 20);
			        }
		    	}
			    return true;
		    }
		}
		return false;
	}
}
