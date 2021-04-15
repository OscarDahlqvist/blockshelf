package me.wilux.blockshelf.cmd;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import me.wilux.blockshelf.api.CustomBlock;
import me.wilux.blockshelf.api.CustomItem;
import me.wilux.blockshelf.api.CustomRegistry;

public class BrigaderCompleter {
    public static LiteralCommandNode get(String name){

        LiteralArgumentBuilder<Object> blockBuilder = LiteralArgumentBuilder.literal("block");
        for (CustomBlock cb : CustomRegistry.CUSTOM_BLOCK_REGISTRY){
            blockBuilder = blockBuilder.then(LiteralArgumentBuilder.literal(cb.getName()));
        }
        LiteralArgumentBuilder<Object> itemBuilder = LiteralArgumentBuilder.literal("item");
        for (CustomItem ci : CustomRegistry.CUSTOM_ITEM_REGISTRY){
            itemBuilder = itemBuilder.then(LiteralArgumentBuilder.literal(ci.getName()));
        }

        LiteralCommandNode<?> xCommand = LiteralArgumentBuilder.literal(name)
                .then(blockBuilder)
                .then(itemBuilder)
                .then(LiteralArgumentBuilder.literal("reload"))
                .then(LiteralArgumentBuilder.literal("repackage"))
                .then(LiteralArgumentBuilder.literal("method")
                        .then(LiteralArgumentBuilder.literal("dropbox"))
                        .then(LiteralArgumentBuilder.literal("minepack"))
                ).build();

        return xCommand;
    }
}
