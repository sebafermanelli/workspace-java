package net.diverse.mc.events;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import net.diverse.mc.Core;

public class Motd implements Listener {
	
	@EventHandler
	public void onPing(ServerListPingEvent event) {
		Random rand = new Random();
	    int random = rand.nextInt(2);
	    
	    if (random == 0) {
	    	event.setMotd(Core.Color("&r           &3&lDiverseMC &e1.8/1.9/1.10/1.11/1.12\n&r     &c&oDescuento Bienvenida 20% OFF! &8&o(&a&oNO PREMIUM&8&o)"));
	    }
	    else if (random == 1) {
	    	event.setMotd(Core.Color("&r           &3&lDiverseMC &e1.8/1.9/1.10/1.11/1.12\n&r         &c&oServidor FreeForAll PVP &8&o(&a&oNO PREMIUM&8&o)"));
	    }
	}
}
