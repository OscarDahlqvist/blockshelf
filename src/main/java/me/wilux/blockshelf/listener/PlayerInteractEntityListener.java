package me.wilux.blockshelf.listener;

import me.wilux.blockshelf.api.item.CustomItem;
import me.wilux.blockshelf.util.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteractEntityListener implements Listener
{
	
	
	
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e)
	{
		if(e.getHand().equals(EquipmentSlot.HAND))
		{
			CustomItem ci = Utils.getMainOrOffHandCI(e);
			if(ci != null)
			ci.onInteractEntity(e);
		}
	}
}
