package me.benfah.cu.listener;

import java.lang.reflect.Field;
import java.util.Map;

import me.benfah.cu.util.Utils;
import me.wilux.blockshelf.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataStoreBase;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import me.benfah.cu.api.BlockInstance;
import me.benfah.cu.api.CustomBlock;
import me.benfah.cu.api.CustomRegistry;
import me.benfah.cu.api.WorldStore;
import me.benfah.cu.util.CustomBreakEvent;
import me.benfah.cu.util.ReflectionUtils;

public class BlockBreakListener implements Listener
{
	@EventHandler
	public void onBreakBlock(BlockBreakEvent e)
	{
		Block b = e.getBlock();

		//TODO do so it uses better customBLock testing?
		if(Utils.hasCustomModelDataArmorstand(b))
		{
			
			CustomBlock cb = CustomRegistry.getCustomBlockByBlock(b);
			cb.onBlockBroken(e);
			if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
				for(ItemStack i : cb.getLoot(e.getBlock()))
				{
					if(i != null && i.getType() != Material.AIR)
						e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
				}
			}
			BlockInstance.removeBlockInstance(b);
			
			
			WorldStore ws = CustomRegistry.getWorldStore(e.getBlock().getWorld());
			ws.handleBlockBreak(e);
			
			e.setExpToDrop(0);

			
		}
		
	}
	
}
