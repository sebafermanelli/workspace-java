package net.diverse.chat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.chat.Core;
import net.diverse.chat.utils.TitlesAPI;

public class Tell implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
	    }
		else {
		    Player p = (Player)sender;
		    
		    if(cmd.getName().equalsIgnoreCase("tell")) {
		    	if(args.length < 2) {
		    		sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/tell -Jugador -Mensaje&7."));
		    		return true;
		        }
		    	else {
			        CommandSender rec = Bukkit.getServer().getPlayerExact(args[0]);
			        
			        if(rec == sender) {
			          sender.sendMessage(Core.Color("&c&lERROR: &7No puedes enviarte mensajes."));
			        }
			        
			        if(rec != sender) {
			        	if(rec == null) {
			        		sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[0] + " &7no existe."));
			        	}
			        	else {
			        		String msg = "";
			        		for (int i = 1; i < args.length; i++) {
			        			msg = msg + args[i] + " ";
			        		}
			        		Core.tell.put(sender.getName(), rec.getName());
			        		Core.tell.put(rec.getName(), sender.getName());
			        		sender.sendMessage(Core.Color("&6" + p.getName() + " &8> &6" + rec.getName() + "&7: &f" + msg));
			        		rec.sendMessage(Core.Color("&6" + p.getName() + " &8> &6" + rec.getName() + "&7: &f" + msg));
			        		TitlesAPI.sendTitle(p, "&a&lMensaje enviado", "&7Receptor: &e" + rec.getName() + "&7.", 20, 20, 20);
			        	}
			        }
		    	}
		        return true;
		    }
		}
		return false;
	}
}
