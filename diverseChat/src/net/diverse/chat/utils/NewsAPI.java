package net.diverse.chat.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.diverse.chat.Core;

public class NewsAPI {
	
	public static void autoBroadcast() {
		int interval = 300;
	    Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getCore(), new Runnable() {
	    	public void run() {
	    		List<String> messages = new ArrayList<String>();
	    		messages.add(Core.Color("&7Usa el comando &e/help &7para obtener información sobre los comandos útiles en el servidor."));
	    		messages.add(Core.Color("&7No olvides que si tienes un canal de &cYou&fTube &7puedes obtener beneficios si grabas en el servidor."));
	    		messages.add(Core.Color("&7¿&bTienes dudas&7? Consultalas al &eEquipo Administrativo &3DiverseMC &7para así resolverlas."));
	    		messages.add(Core.Color("&7¿&bQuieres obtener más beneficios&7? Entra a la tienda &bshop.diversemc.com &7y utiliza el cupón de &f20% &7de descuento &8(&a20DESCDMC&8)&7."));
	    		messages.add(Core.Color("&7Siguenos en nuestra página de &bTwitter&7: &e@DiverseMC &7allí te enterarás de todo lo nuevo en el servidor."));
	    		
		        Random rand = new Random();
		        int choice = rand.nextInt(messages.size());
		        if(Bukkit.getOnlinePlayers().size() >= 1) {
		        	for (Player p : Bukkit.getOnlinePlayers()) {
			            p.sendMessage(Core.Color("&6&lNoticia: &7" + (String)messages.get(choice)));
		        	}
		        }
	    	}
	    }, 0L, interval * 20L);
	}
}
