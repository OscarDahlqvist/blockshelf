package me.wilux.blockshelf.listener;

import me.wilux.blockshelf.api.CustomItem;
import me.wilux.blockshelf.api.CustomRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		//TODO make the priority of actions correct

		ItemStack clickItem = e.getItem();
		CustomItem ci = CustomRegistry.getCustomItem(clickItem);
		if (ci!=null) {
			ci.onPlayerInteractEvent(e);
		}

		/*
		//WIRE SPOOL always has priority
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			ItemStack clickItem = e.getItem();
			CustomItem ci = CustomRegistry.getCustomItem(clickItem);
			if (ci!=null) {
				if (ci.getName().equals(WireSpool.ID)){
					ci.onInteract(e, e.getHand());
					e.setCancelled(true);
					return;
				}
			}
		}

		//Click on block
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && !e.getPlayer().isSneaking()){
			if(CustomRegistry.isCustomBlock(e.getClickedBlock())){
				//e.setCancelled(true);
				CustomRegistry.getCustomBlockByBlock(e.getClickedBlock()).onInteract(e);
			}
		}
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			ItemStack clickItem = e.getItem();
			CustomItem cb = CustomRegistry.getCustomItem(clickItem);
			if (cb != null) {
				cb.onInteract(e, e.getHand());
			}
		}
		/*if(((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) && e.getHand().equals(EquipmentSlot.HAND))
						|| (e.getAction() == Action.RIGHT_CLICK_AIR && e.getHand().equals(EquipmentSlot.OFF_HAND))){

			CustomItem ci = Utils.getMainOrOffHandCI(e);
			if(ci != null){
				ItemStack stack = Utils.updateTags(Utils.getMainOrOffHandStack(e));
				Utils.setMainOrOffHandStack(e, stack);
				ci.onInteract(e, Utils.getEquipmentSlot(e));
				e.setCancelled(true);
			}
			if(e.getItem() != null && CustomRegistry.isItemStackItemStackOfCB(e.getItem()) && (e.getAction() == Action.RIGHT_CLICK_BLOCK)){

				Main.logger.warning("BlockInteractListener: PLACED BLOCK");

				e.setCancelled(true);
				CustomBlock cb = CustomRegistry.getCustomBlockByStack(e.getItem());
				int x = e.getClickedBlock().getX() + e.getBlockFace().getModX();
				int y = e.getClickedBlock().getY() + e.getBlockFace().getModY();
				int z = e.getClickedBlock().getZ() + e.getBlockFace().getModZ();
				Block b = new Location(e.getClickedBlock().getWorld(), x, y, z).getBlock();
				if(b.isEmpty()) cb.setBlock(b);
				
				
				
				if(e.getPlayer().getGameMode() != GameMode.CREATIVE)
				e.getItem().setAmount(e.getItem().getAmount() - 1);

				
			}
		}*/
		
	}
	
	
}
