package me.wilux.blockshelf.listener;

import me.wilux.blockshelf.main.Blockshelf;
import me.wilux.blockshelf.extentions.GuiAble;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
public class InventoryCloseListener implements Listener
{
	
	@EventHandler
	public void onClose(InventoryCloseEvent e)
	{
		/*
		GuiAble x = Blockshelf.getCurrentlyOpenInventories().get(e.getPlayer());
		if(x != null){
			x.onCloseGui(e);
		}*/
		//TODO detect inventory some other way (preferability fix customGuis)
		/*
		if(CustomRegistry.isCustomGUI(e.getInventory()))
		{
			CustomRegistry.getCustomGUIByInventory(e.getInventory()).onClose(e);
		}*/
	}
	
}
