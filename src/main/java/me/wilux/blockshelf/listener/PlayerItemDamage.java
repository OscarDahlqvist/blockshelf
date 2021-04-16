package me.wilux.blockshelf.listener;

import me.wilux.blockshelf.api.store.CustomRegistry;
import me.wilux.blockshelf.api.item.CustomItem;
import me.wilux.blockshelf.main.Blockshelf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerItemDamage {
    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent e) {
        ItemStack damageItem = e.getItem();
        CustomItem ci = CustomRegistry.getCustomItem(damageItem);
        if (ci != null) {
            Blockshelf.getLog().warning("damage event");
            ci.onPlayerItemDamageEvent(e);
        }
    }
}
