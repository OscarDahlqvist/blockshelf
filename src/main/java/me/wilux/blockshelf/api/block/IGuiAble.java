package me.wilux.blockshelf.api.block;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;

public interface IGuiAble {
    void onCloseGui(InventoryCloseEvent e);
}
