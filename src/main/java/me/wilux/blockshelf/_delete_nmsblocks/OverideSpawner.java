package me.wilux.blockshelf._delete_nmsblocks;

import net.minecraft.server.v1_16_R2.BlockMobSpawner;
import net.minecraft.server.v1_16_R2.Blocks;
import net.minecraft.server.v1_16_R2.Item;

public class OverideSpawner extends BlockMobSpawner {
    public OverideSpawner(net.minecraft.server.v1_16_R2.Block.Info block_info) {
        super(block_info);
    }

    public Item getItem(){
        return Item.getItemOf(Blocks.SPONGE);
    }

}
