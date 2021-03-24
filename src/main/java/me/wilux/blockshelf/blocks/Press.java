package me.wilux.blockshelf.blocks;

import me.benfah.cu.api.CustomRegistry;
import me.wilux.blockshelf.Main;
import me.wilux.blockshelf.Register._DummyText;
import net.minecraft.server.v1_16_R3.PacketPlayOutOpenWindowHorse;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Press extends OrientableCustomBlock implements GuiAble {
    public static final String DISPLAY_NAME = "Presser";
    public static final String ID = "press";
    public static final String ID_WE = "press_we";

    public static Press defaultState;

    public Press(String name, String modelPath, String title) {
        super(name, modelPath, title);
        baseMaterial = Material.BARRIER;
    }
    @Override
    public ItemStack[] getLoot(Block b) {
        return new ItemStack[] {defaultState.getBlockItem()};
    }

    public static void register(){
        Press north_south = new Press(ID,"pseudoblock/plater",DISPLAY_NAME);
        Press west_east = new Press(ID_WE,"pseudoblock/plater_we", Main.UNOBTAINABLE_BLOCK);

        defaultState = north_south;

        north_south.north_south = north_south;
        north_south.west_east = west_east;
        west_east.north_south = north_south;
        west_east.west_east = west_east;

        CustomRegistry.register(north_south,Main.plugin);
        CustomRegistry.register(west_east,Main.plugin);
    }




    //REMOVED: llama inventory reference
    @Override
    public void onInteract(PlayerInteractEvent evt) {
        evt.setCancelled(true);
        Player player = evt.getPlayer();
        Inventory inv = Bukkit.createInventory(player,InventoryType.BREWING,"§r"+_DummyText.PRESS_GUI);
        player.openInventory(inv);
        /*
        Main.logger.warning("PRESSER OPEN GUI");

        Player player = evt.getPlayer();
        Main.currentlyOpenInventory.put(player,this);

        Location playerLoc = player.getLocation();
        Location spawnLoc = new Location(playerLoc.getWorld(),playerLoc.getX(),4000,playerLoc.getZ());
        Llama llama = (Llama) player.getWorld().spawnEntity(spawnLoc,EntityType.LLAMA);
        //player.getWorld().spaw
        llama.setStrength(1);
        llama.setCarryingChest(true);
        llama.setAI(false);
        llama.setSilent(true);
        llama.setTamed(true);
        llama.setAge(-30000);
        llama.setAgeLock(true);
        llama.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,Integer.MAX_VALUE,0,false,false));
        player.addPassenger(llama);
        net.minecraft.server.v1_16_R2.EntityLlama NMSLlama = ((CraftLlama)llama).getHandle();

        Bukkit.getScheduler().scheduleSyncDelayedTask(
                Main.plugin, new OpenHorse2(NMSLlama, player), 2);


        //llama.remove();
        */
    }

    @Override
    public void onCloseGui(InventoryCloseEvent e) {
        //TODO make entity keyed somehow (maybe a new subclass for llamas?)
        Main.logger.warning("TRIED TO CLOSE");
        for(Entity entity : e.getPlayer().getPassengers()){
            if(entity instanceof Llama){
                e.getPlayer().removePassenger(entity);
                entity.remove();
            }
        }
    }

    public class OpenHorse2 implements Runnable {
        net.minecraft.server.v1_16_R3.EntityLlama NMSLlama;
        Player player;

        public OpenHorse2(net.minecraft.server.v1_16_R3.EntityLlama NMSLlama, Player player) {
            this.NMSLlama = NMSLlama;
            this.player = player;
        }

        public void run() {
            net.minecraft.server.v1_16_R3.EntityPlayer NMSplayer = ((CraftPlayer)player).getHandle();
            int x = NMSplayer.nextContainerCounter();

            NMSplayer.playerConnection.sendPacket(
                    new PacketPlayOutOpenWindowHorse(x, NMSLlama.inventoryChest.getSize(), NMSLlama.getId()));
        }
    }

}
