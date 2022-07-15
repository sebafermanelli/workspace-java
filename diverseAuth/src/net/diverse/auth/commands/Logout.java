package net.diverse.auth.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.diverse.auth.Core;
import net.diverse.auth.utils.TitlesAPI;

public class Logout implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Core.Color("&c&lERROR: &7Necesitas ser un jugador."));
			return true;
		}
		else {
			Player p = (Player)sender;
		
			if(cmd.getName().equalsIgnoreCase("logout")) {
				if(args.length == 0) {
					Core.logoutcooldown.put(p.getName(), 5);
					new BukkitRunnable() {
						@Override
						public void run() {
								if(Core.logoutcooldown.get(p.getName()) > 0) {
									if(Core.logoutcooldown.containsKey(p.getName())) {
										TitlesAPI.sendTitle(p, "&c&lDesconectando", "&7Espera &f" + Core.logoutcooldown.get(p.getName()) + " &7segundos.", 20, 20, 20);
										
										Core.logoutcooldown.put(p.getName(), Core.logoutcooldown.get(p.getName()) - 1);
									}
								}
								else {
									Core.logoutcooldown.remove(p.getName());
						        	
									p.kickPlayer(Core.Color(Core.prefix + "&7Te has &cdesconectado&7, vuelve pronto.\n&eplay.diversemc.com"));
									
									cancel();
								}
						}
					}.runTaskTimer(Core.getCore(), 0, 20);
					return true;
				}
				else {
					sender.sendMessage(Core.Color("&c&lERROR: &7Uso adecuado: &e/logout&7."));
					return true;
				}
			}
		}
		return false;
	}
}
