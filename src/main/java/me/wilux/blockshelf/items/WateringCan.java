package me.wilux.blockshelf.items;

import me.benfah.cu.api.CustomItem;
import me.benfah.cu.api.CustomRegistry;
import me.wilux.blockshelf.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
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
    public void onPlayerInteractEvent(PlayerInteractEvent e)	{
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