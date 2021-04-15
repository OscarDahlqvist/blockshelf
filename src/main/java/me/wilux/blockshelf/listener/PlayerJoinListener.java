package me.wilux.blockshelf.listener;

import me.wilux.blockshelf.api.InitializationMethodProvider;
import me.wilux.blockshelf.main.CustomUtils;
import me.wilux.blockshelf.util.Config;
import me.wilux.blockshelf.util.InitializationState;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener
{
	
	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent e)
	{
		if(InitializationMethodProvider.getCurrentMethod().getInitializationState() == InitializationState.INITIALIZING)
		e.getPlayer().kickPlayer("Wait until you can join");	
		Bukkit.getScheduler().runTaskLater(CustomUtils.instance, new Runnable() {
			
			
			public void run()
			{
				if(Config.getConfiguration().getBoolean("send-resourcepack-request"))
				e.getPlayer().setResourcePack(Config.getConfiguration().getString("resourcepack-link"));
				if(InitializationMethodProvider.getCurrentMethod().getInitializationState().equals(InitializationState.INITIALIZING))
				e.getPlayer().kickPlayer("Wait until the server initialized!");	
			}
		}, 40);
	}
}
