package me.wilux.blockshelf.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockInteractListener implements Listener
{

	@EventHandler
	public void onInteract(PlayerInteractEvent e)
	{
		/*if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{	
			
			if(CustomRegistry.isCustomBlock(e.getClickedBlock()) && e.getHand().equals(EquipmentSlot.HAND))
			{
				e.setCancelled(true);
				CustomRegistry.getCustomBlockByBlock(e.getClickedBlock()).onInteract(e);
			}
			if(e.getHand().equals(EquipmentSlot.HAND) && e.getItem() != null && CustomRegistry.isItemStackItemStackOfCB(e.getItem()) && e.getAction() == Action.RIGHT_CLICK_BLOCK)
			{
				e.setCancelled(true);
				CustomBlock cb = CustomRegistry.getCustomBlockByStack(e.getItem());
				int x = e.getClickedBlock().getX() + e.getBlockFace().getModX();
				int y = e.getClickedBlock().getY() + e.getBlockFace().getModY();
				int z = e.getClickedBlock().getZ() + e.getBlockFace().getModZ();
				Block b = new Location(e.getClickedBlock().getWorld(), x, y, z).getBlock();
				if(b.isEmpty()) cb.setBlock(b);
				
				
				
				e.getItem().setAmount(e.getItem().getAmount() - 1);
				
			}
		}*/
	}
}
