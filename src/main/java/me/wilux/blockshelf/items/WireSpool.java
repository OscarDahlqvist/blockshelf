package me.wilux.blockshelf.items;

import com.sun.istack.internal.NotNull;
import me.benfah.cu.api.CustomBlock;
import me.benfah.cu.api.CustomItem;
import me.benfah.cu.api.CustomRegistry;
import me.benfah.cu.listener.PlayerJoinListener;
import me.benfah.cu.listener.SlotChangeListener;
import me.benfah.cu.main.CustomUtils;
import me.wilux.blockshelf.Main;
import me.wilux.blockshelf.nms_hacks.EntityVexPermLeashed;
import net.minecraft.server.v1_16_R3.BlockPosition;
import net.minecraft.server.v1_16_R3.EntityArmorStand;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftVex;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityUnleashEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;
import java.util.function.Consumer;

public class WireSpool extends CustomItem {
    public WireSpool(String name, String modelPath, String title) {
        super(name, modelPath, title);
    }

    public static final String DISPLAY_NAME = "Wire Spool";
    public static final String ID = "wire_spool";

    public static void register(){
        CustomItem placeableWireSpool = new WireSpool(ID,"pseudoitem/wire_spool", DISPLAY_NAME);
        CustomRegistry.register(placeableWireSpool, Main.plugin);
    }

    @Override
    public void onInteract(PlayerInteractEvent e, EquipmentSlot es) {
        Player ply = e.getPlayer();
        if(e.getClickedBlock() != null){
            Block clickedBlock = e.getClickedBlock();
            Material m = clickedBlock.getType();
            if(Tag.FENCES.getValues().contains(m) || m == Material.SPAWNER){
                ply.sendMessage("You clicked a component with the wire spool");

                World w = ply.getWorld();
                Location destination = clickedBlock.getLocation().add(0.5,0.02,0.5);
                EntityVexPermLeashed pvex = EntityVexPermLeashed.create(destination);

                //startEntity.setSilent(true);
                //startEntity.getEquipment().setItemInMainHand(null);
                //startEntity.setInvisible(true);
                //startEntity.teleport(ply);
                //startEntity.setAI(false);

                Bukkit.getScheduler().runTaskLater(Main.plugin,  new Runnable() {
                    @Override
                    public void run() {
                        net.minecraft.server.v1_16_R3.Entity nmsPlayer = ((CraftEntity)ply).getHandle();
                        pvex.setLeashHolder(nmsPlayer,true);
                    }
                }, 1L);
            }
        }
    }
}