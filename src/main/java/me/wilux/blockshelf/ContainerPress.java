package me.wilux.blockshelf;

import net.minecraft.server.v1_16_R2.*;

public class ContainerPress extends ContainerBrewingStand {
    public ContainerPress(int i, PlayerInventory playerinventory, IInventory iinventory, IContainerProperties icontainerproperties) {
        super(i, playerinventory, iinventory, icontainerproperties);
    }
    public ContainerPress(int i, PlayerInventory playerinventory) {
        this(i, playerinventory, new InventorySubcontainer(5), new ContainerProperties(2));
    }
}
