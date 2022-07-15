package net.diverse.mc.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

import net.diverse.mc.Core;

public class NoExistCommand implements Listener {
	
	@EventHandler(priority=EventPriority.HIGH)	
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		if(!event.isCancelled()) {
            Player player = event.getPlayer();
            String cmd = event.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
            
            if (topic == null) {
            	player.sendMessage(Core.Color("&c&lERROR: &7El comando &e" + event.getMessage() + " &7no existe, utiliza: &e/help&7."));
                event.setCancelled(true);
            }
        }
	}
}