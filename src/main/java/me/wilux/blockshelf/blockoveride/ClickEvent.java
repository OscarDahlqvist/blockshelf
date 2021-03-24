package me.wilux.blockshelf.blockoveride;

import me.benfah.cu.api.CustomRegistry;
import me.wilux.blockshelf.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClickEvent implements Listener {

    @EventHandler
    public void onUseEvent(PlayerInteractEvent evt){
        if(evt.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Block block = evt.getClickedBlock();
            if(block.getType() == Material.SPAWNER){
                if(CustomRegistry.isCustomBlock(block)){ //block is custom block
                    if(CustomRegistry.getCustomBlockByBlock(block).getTitle().equals(Main.CBLOCKNAME_INDEXER)){
                        Inventory inv = createIndexerInventory(11,evt.getPlayer());
                        evt.getPlayer().openInventory(inv);
                        evt.setCancelled(true);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked(); // The player that clicked the item
        ItemStack clicked = event.getCurrentItem(); // The item that was clicked
        Inventory inventory = event.getInventory(); // The inventory that was clicked in
        if (inventory.getType() == InventoryType.FURNACE) { // The inventory is our custom Inventory
            if (clicked.getType() == Material.DIRT) { // The item that the player clicked it dirt
                event.setCancelled(true); // Make it so the dirt is back in its original spot
                //player.closeInventory(); // Closes there inventory
                //player.openInventory(createIndexerInventory(15,player));
                //player.getInventory().addItem(new ItemStack(Material.DIRT, 1)); // Adds dirt
            }
        }
    }

    public static Inventory createIndexerInventory(int count, Player owner){
/*
        String title = INDEXER_LEFT
                + microDigitsFixed(count,6)
                + INDEXER_RIGHT_6DIGITS;*/
        String title = "placeHolder";
        Main.logger.warning(title);
        return Bukkit.createInventory(owner, InventoryType.FURNACE, "§f"+title);
    }

}
