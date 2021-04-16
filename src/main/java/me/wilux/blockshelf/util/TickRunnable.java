package me.wilux.blockshelf.util;

import me.wilux.blockshelf.api.store.BlockInstance;
import me.wilux.blockshelf.api.block.ITickable;
import me.wilux.blockshelf.api.item.CustomItem;
import me.wilux.blockshelf.api.store.CustomRegistry;
import me.wilux.blockshelf.api.store.WorldStore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TickRunnable implements Runnable
{

	@Override
	public void run() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			ItemStack[] contents = p.getInventory().getContents();
			for(int i = 0; i < contents.length; i++) {
				ItemStack stack = contents[i];
				if(stack != null && stack.getType() != Material.AIR){
					CustomItem ci = CustomRegistry.getCustomItem(stack);
					if(ci!=null){
						ci.onTick(p,i);
					}
				}
			}
		}
		
		for(WorldStore ws : CustomRegistry.worldStoreList) {
			for(Location loc : ws.getBlockLocationsForKey("shouldUpdate")) {
				
				BlockInstance bi = BlockInstance.getBlockInstance(loc.getBlock());
				if(bi != null)
				if(bi.getChunk().isLoaded())
				if(bi instanceof ITickable)
				((ITickable)bi).onUpdate();
				
//				CustomRegistry.getCustomBlockByBlock(loc.getBlock()).onUpdate(loc);
			}
		}
	}

}
