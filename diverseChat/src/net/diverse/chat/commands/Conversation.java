package net.diverse.chat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.diverse.chat.Core;

public class Conversation implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
	    }
		else {
			Player p = (Player)sender;
		    
		    if(cmd.getName().equalsIgnoreCase("conversation")) {
		    	if(p.hasPermission("diverse.chat.donor")) {
			    	if(args.length == 0) {
			    		if(((String)Core.conv.get(sender.getName())).split(":")[1].equalsIgnoreCase("true")) {
			    			sender.sendMessage(Core.Color(Core.prefix + "&7Has &cdesactivado &7el modo conversación."));
				            Core.conv.put(sender.getName(), "none:false");
			    		}
			    		else {
				            sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/conversation -Jugador&7."));
			    		}
			    		return true;
			        }
			    	if(args.length == 1) {
			    		CommandSender rec = Bukkit.getServer().getPlayerExact(args[0]);
			    		if(rec == sender) {
				            sender.sendMessage(Core.Color("&c&lERROR: &7No puedes iniciar una conversación contigo mismo."));
			    		}
			    		else {
			    			if(rec == null) {
			    				sender.sendMessage(Core.Color("&c&lERROR: &7El jugador &e" + args[0] + " &7no existe."));
			    			}
			    			if(rec != null) {
			    				if(((String)Core.conv.get(sender.getName())).split(":")[1].equalsIgnoreCase("false")) {
			    					sender.sendMessage(Core.Color(Core.prefix + "&7Has &ainiciado &7una conversación con &e" + rec.getName() + " &7, utiliza el comando &e/conversation &7para salir."));
			    					
			    					if(((String)Core.conv.get(rec.getName())).split(":")[1].equalsIgnoreCase("false")) {
			    						rec.sendMessage(Core.Color(Core.prefix + "&7El jugador &e" + sender.getName() + " &7ha &ainiciado &7una conversación contigo. Para unirte utiliza &e/conversation -Jugador&7."));
			    					}
			    					
			    					Core.conv.put(sender.getName(), rec.getName() + ":" + "true");
			    				}
			    				else if(((String)Core.conv.get(sender.getName())).split(":")[1].equalsIgnoreCase("true")) {
			    					sender.sendMessage(Core.Color("&c&lERROR: &7Ya estás en una conversación, utiliza el comando &e/conversation &7para salir."));
			    				}
			    			}
			    		}
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
