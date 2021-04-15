package me.benfah.cu.listener;

import me.benfah.cu.api.CustomBlock;
import me.benfah.cu.api.CustomRegistry;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceBlockListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent evt) {

        CustomBlock cb = CustomRegistry.getCustomBlockByStack(evt.getItemInHand());
        if(cb != null){
            cb.placeBlock(evt.getBlockPlaced().getLocation(),evt);
            if(evt.getPlayer().getGameMode() != GameMode.CREATIVE) evt.getItemInHand().setAmount(evt.getItemInHand().getAmount() - 1);
        }
/*
        if(block.getType() != Material.BARRIER) return;
        ItemStack stack = evt.getItemInHand();
        ItemMeta itemMeta = stack.getItemMeta();
        if(itemMeta==null) return;

        int modelId = itemMeta.getCustomModelData();
        //block.setType(Material.SPAWNER);

        /*
        CraftWorld cw = (CraftWorld)evt.getPlayer().getWorld();
        TileEntityMobSpawner tileEntity = (TileEntityMobSpawner)cw.getHandle().getTileEntity(new BlockPosition(block.getX(), block.getY(), block.getZ()));
        tileEntity.getSpawner().requiredPlayerRange = 0;
        MobSpawnerData data = tileEntity.getSpawner().spawnData;
        data.getEntity().setString("id","minecraft:armor_stand");
        Main.logger.warning(data.getEntity().getString("id"));

        /*
        String command = String.format(
                "setblock %s %s %s minecraft:spawner{MaxNearbyEntities:0,RequiredPlayerRange:0,SpawnData:{id:\"minecraft:armor_stand\",Invisible:1b,ArmorItems:[{},{},{},{id:\"minecraft:barrier\",Count:1b,tag:{CustomModelData:%s}}]}}"
                ,block.getX(),block.getY(),block.getZ(),modelId);
        Main.logger.warning(command);
        Bukkit.dispatchCommand(evt.getPlayer(),command);

        //blockMeta.setRequiredPlayerRange(0);
        //blockMeta.setSpawnedType(EntityType.ARMOR_STAND);
        //blockMeta.update();

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new SetInstantSpawnerData(block, (CraftWorld)evt.getPlayer().getWorld(), modelId), 0);
        */
    }
}


