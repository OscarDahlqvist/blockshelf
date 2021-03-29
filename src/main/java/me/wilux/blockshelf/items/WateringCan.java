package me.wilux.blockshelf.items;

import me.benfah.cu.api.CustomItem;
import me.benfah.cu.api.CustomRegistry;
import me.wilux.blockshelf.Main;
import me.wilux.blockshelf.TempStorage;
import me.wilux.blockshelf.nms_hacks.EntityFixedLeashTarget;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRiptideEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class WateringCan extends CustomItem {

    public static final String DISPLAY_NAME = "Watering Can";
    public static final String ID = "watering_can";

    public WateringCan(String name, String modelPath, String title, Material baseMat) {
        super(name, modelPath, title, baseMat);
    }

    public static void register(){
        CustomItem placeableWireSpool = new WateringCan(ID,"pseudoitem/watering_can", DISPLAY_NAME, Material.CARROT_ON_A_STICK);
        CustomRegistry.register(placeableWireSpool, Main.plugin);
    }

    @Override
    public void onInteract(PlayerInteractEvent e, EquipmentSlot es)	{
        ItemStack is = e.getItem();
        Damageable im = (Damageable)(is.getItemMeta());

        Player ply = e.getPlayer();

        ply.spawnParticle(Particle.WATER_DROP, ply.getLocation(), 20,1,0.1,1, 1);

        Main.logger.warning(
            "isInWaterOrRain:"+((CraftPlayer)ply).getHandle().isInWaterOrRain()
        );

        if (ply.getGameMode() != GameMode.CREATIVE){
            im.setDamage(im.getDamage()+1);
            is.setItemMeta((ItemMeta) im);
        }
    }
}