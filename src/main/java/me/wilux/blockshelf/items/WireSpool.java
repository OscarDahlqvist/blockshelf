package me.wilux.blockshelf.items;

import me.benfah.cu.api.CustomItem;
import me.benfah.cu.api.CustomRegistry;
import me.wilux.blockshelf.Config;
import me.wilux.blockshelf.Main;
import me.wilux.blockshelf.TempStorage;
import me.wilux.blockshelf.nms_hacks.EntityFixedLeashKnot;
import me.wilux.blockshelf.nms_hacks.EntityFixedLeashTarget;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftLivingEntity;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class WireSpool extends CustomItem {
    //TODO as knots on fences

    public WireSpool(String name, String modelPath, String title) {
        super(name, modelPath, title);
    }

    public static final String DISPLAY_NAME = "Wire Spool";
    public static final String ID = "wire_spool";

    public static void register(){
        CustomItem placeableWireSpool = new WireSpool(ID,"pseudoitem/wire_spool", DISPLAY_NAME);
        CustomRegistry.register(placeableWireSpool, Main.plugin);
        Bukkit.getPluginManager().registerEvents(new HangingPlaceListener(), Main.plugin);
    }

    @Override
    public void onInteract(PlayerInteractEvent e, EquipmentSlot es) {
        Player ply = e.getPlayer();
        if(e.getClickedBlock() != null){
            Block clickedBlock = e.getClickedBlock();
            Material m = clickedBlock.getType();
            if(isBlockWireable(clickedBlock)){

                World w = ply.getWorld();
                Location destination = clickedBlock.getLocation().add(0.5,0.5,0.5);

                if(!TempStorage.leashSource.containsKey(ply)){
                    EntityFixedLeashTarget leashSource = EntityFixedLeashTarget.create(destination);
                    TempStorage.leashSource.put(ply, leashSource);

                    Bukkit.getScheduler().runTaskLater(Main.plugin,  new Runnable() {
                        @Override
                        public void run() {
                            net.minecraft.server.v1_16_R3.Entity nmsPlayer = ((CraftEntity)ply).getHandle();
                            leashSource.setLeashHolder(nmsPlayer,true);
                            actionbarUpdater(ply);
                        }
                    }, 1L);

                } else {
                    EntityFixedLeashTarget leashSource = TempStorage.leashSource.get(ply);
                    EntityFixedLeashTarget leashDest = EntityFixedLeashTarget.create(destination);

                    double distaceFromSrcToTarget = leashSource.getBukkitEntity().getLocation().distance(
                            leashDest.getBukkitEntity().getLocation()
                    );
                    if(distaceFromSrcToTarget < Config.maxWireLength){
                        leashSource.setLeashHolder(leashDest,true);

                        if(ply.getGameMode() != GameMode.CREATIVE) {
                            ItemStack is = e.getItem();
                            is.setAmount(is.getAmount()-1);
                        }

                        Bukkit.getScheduler().runTaskLater(Main.plugin,  new Runnable() {
                            @Override public void run() { TempStorage.leashSource.remove(ply); }
                        }, 1L);
                        Bukkit.getScheduler().runTaskLater(Main.plugin,  new Runnable() {
                            @Override public void run() { leashDest.getBukkitEntity().remove(); }
                        }, 2L);
                    }
                }
            }
        }
    }

    public void actionbarUpdater(Player player) {
        EntityFixedLeashTarget nmsEntity = TempStorage.leashSource.get(player);
        if(nmsEntity != null) {
            //Loop this function every tick until not targeting
            Bukkit.getScheduler().runTaskLater(Main.plugin,  new Runnable() {
                @Override public void run() { actionbarUpdater(player); }
            },1L);

            Entity leashSource = nmsEntity.getBukkitEntity();
            Block target = player.getTargetBlock(null, 5);

            BaseComponent[] actionBarDisplay = null;
            if(target == null){
                actionBarDisplay = TextComponent.fromLegacyText("§cNo block targeted");
            } else if(isBlockWireable(target)) {
                double distaceFromSrcToTarget = leashSource.getLocation().distance(target.getLocation());

                DecimalFormat df = new DecimalFormat("#.#");
                df.setRoundingMode(RoundingMode.CEILING);
                String distString = df.format(distaceFromSrcToTarget);

                if(distaceFromSrcToTarget > Config.maxWireLength){
                    actionBarDisplay = TextComponent.fromLegacyText("§cLength = "+distString+"/"+Config.maxWireLength+" WIRE TOO LONG");
                } else {
                    actionBarDisplay = TextComponent.fromLegacyText("§aLength = "+distString+"/"+Config.maxWireLength);
                }
            } else {
                actionBarDisplay = TextComponent.fromLegacyText("§cYou can not attach a wire to this block");
            }
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, actionBarDisplay);
        }
    }

    public static boolean isBlockWireable(Block b){
        Material m = b.getType();
        return (Tag.FENCES.getValues().contains(m) || m == Material.SPAWNER);
    }
}
class HangingPlaceListener implements Listener {
    @EventHandler
    public void onHangingPlaceEvent(HangingPlaceEvent e) {
        Hanging hangingEntity = e.getEntity();

        if(hangingEntity.getType() == EntityType.LEASH_HITCH){
            Player ply = e.getPlayer();

            EntityFixedLeashTarget leashSource = TempStorage.leashSource.get(ply);
            if(leashSource != null){
                Main.logger.warning("Player is in leash mode, action blocked");
                e.setCancelled(true);
            }
        }
    }
}