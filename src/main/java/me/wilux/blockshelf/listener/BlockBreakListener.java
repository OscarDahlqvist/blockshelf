package me.wilux.blockshelf.listener;

import me.wilux.blockshelf.api.store.BlockInstance;
import me.wilux.blockshelf.api.block.CustomBlock;
import me.wilux.blockshelf.api.store.CustomRegistry;
import me.wilux.blockshelf.util.Utils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener
{
	@EventHandler
	public void onBreakBlock(BlockBreakEvent e)	{
		Block b = e.getBlock();

		//TODO do so it uses better customBLock testing?
		if(Utils.hasCustomModelDataArmorstand(b)) {
			CustomBlock cb = CustomRegistry.getCustomBlockByBlock(b);
			cb.onBlockBreakEvent(e);
			if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
				for(ItemStack i : cb.getLoot(e.getBlock())) {
					if(i != null && i.getType() != Material.AIR)
						e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
				}
			}

			BlockInstance.removeBlockInstance(b);

			/*
			WorldStore ws = CustomRegistry.getWorldStore(e.getBlock().getWorld());
			ws.handleBlockBreak(e);
			 */
			
			e.setExpToDrop(0);
		}

	}
	
}
