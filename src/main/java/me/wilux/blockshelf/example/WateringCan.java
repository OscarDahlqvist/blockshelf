package me.wilux.blockshelf.example;

import me.wilux.blockshelf.api.item.DamageableCustomItem;
import me.wilux.blockshelf.main.Blockshelf;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class WateringCan extends DamageableCustomItem {
    public WateringCan(String name, String modelPath, String displayName, Material baseMat, int maxDurability) {
        super(name, modelPath, displayName, baseMat, maxDurability);
    }

    @Override
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        damageItem(e.getItem(), 1);
    }

    @Override
    public boolean whenNoDurabilityLeft(ItemStack itemInstance) {
        return super.whenNoDurabilityLeft(itemInstance);
    }
}
