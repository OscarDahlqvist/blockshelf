package me.wilux.blockshelf.items;

import me.benfah.cu.api.CustomBlock;
import me.benfah.cu.api.CustomItem;
import me.benfah.cu.api.CustomRegistry;
import me.wilux.blockshelf.Main;
import me.wilux.blockshelf.blocks.ElectricFurnace;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class WireSpool extends CustomItem {
    public WireSpool(String name, String modelPath, String title) {
        super(name, modelPath, title);
    }

    public static void register(){
        CustomItem placeableWireSpool = new WireSpool("wire_spool","pseudoitem/wire_spool", "Wire Spool");
        CustomRegistry.register(placeableWireSpool, Main.plugin);
    }

    @Override
    public void onInteract(PlayerInteractEvent e, EquipmentSlot es) {
        e.getPlayer().sendMessage("You did a thing");
        if(e.getClickedBlock() != null){
            Material m = e.getClickedBlock().getType();
            if(Tag.FENCES.getValues().contains(m)){
                e.getPlayer().sendMessage("You clicked a fence with the wire spool");
            }
        }
    }
}
