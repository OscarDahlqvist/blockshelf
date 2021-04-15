package me.benfah.cu.listener;

import me.wilux.blockshelf.Main;
import me.wilux.blockshelf.blocks.GuiAble;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
public class InventoryCloseListener implements Listener
{
	
	@EventHandler
	public void onClose(InventoryCloseEvent e)
	{
		GuiAble x = Main.currentlyOpenInventory.get(e.getPlayer());
		if(x != null){
			x.onCloseGui(e);
		}
		//TODO detect inventory some other way (preferability fix customGuis)
		/*
		if(CustomRegistry.isCustomGUI(e.getInventory()))
		{
			CustomRegistry.getCustomGUIByInventory(e.getInventory()).onClose(e);
		}*/
	}
	
}
