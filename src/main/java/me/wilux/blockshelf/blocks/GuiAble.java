package me.wilux.blockshelf.blocks;

import org.bukkit.event.inventory.InventoryCloseEvent;

public interface GuiAble {
    void onCloseGui(InventoryCloseEvent e);
}
