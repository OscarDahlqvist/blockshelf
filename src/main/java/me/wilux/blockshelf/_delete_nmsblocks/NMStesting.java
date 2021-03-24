package me.wilux.blockshelf._delete_nmsblocks;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.WrappedBlockData;
import me.wilux.blockshelf.Main;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Material;

public class NMStesting {
/*
    //public static final RegistryMaterials REGISTRY = new RegistryBlocks("air");
    public static void init(){
        //farbricRef
        // .b = .IdList
        // .a(Blocks.Spawner) = .copy(Blocks.Spawner)

        OverideSpawner blockOverrideSpawner = new OverideSpawner(
                BlockMobSpawner.Info.a(Blocks.SPAWNER));
        RegistryBlocks.BLOCK.a(new MinecraftKey("override_spawner"),blockOverrideSpawner);


        for (Object k:RegistryBlocks.BLOCK.keySet()) {
            Main.logger.severe(k.toString());
        //REGISTRY.a(new MinecraftKey("exspawner"),b); this does nothing
        }



        //Block Change event
        Main.protocolManager.addPacketListener(
                new PacketAdapter(Main.plugin, ListenerPriority.NORMAL, PacketType.Play.Server.BLOCK_CHANGE) {
                    @Override
                    public void onPacketSending(PacketEvent event) {
                        if (event.getPacketType() == PacketType.Play.Server.BLOCK_CHANGE) {
                            UnusedProtocol.printAllEventProperties(event);

                            StructureModifier<WrappedBlockData> structModifier = event.getPacket().getBlockData();

                            for (int i = 0; i < structModifier.getValues().size(); i++) {
                                WrappedBlockData block = structModifier.getValues().get(i);
                                if(block.getHandle().equals(blockOverrideSpawner.getBlockData())){
                                    Main.logger.warning("isCustom");
                                    block.setType(Material.SPAWNER);
                                    structModifier.write(i,block);

                                }
                            }
                            UnusedProtocol.printAllEventProperties(event);
                        }
                    }
                });
    }
    */
}
