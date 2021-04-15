package me.benfah.cu.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class SlotChangeListener implements Listener
{
	@EventHandler
	public void onSlotChange(PlayerItemHeldEvent e)	{
		/*
		Inventory inv = e.getPlayer().getOpenInventory().getTopInventory();
		if(JavassistUtil.getCUInventoryClass().isInstance(inv) || JavassistUtil.getCUInventoryCustomClass().isInstance(inv))
		{
			e.setCancelled(true);
		}*/
	}
}
