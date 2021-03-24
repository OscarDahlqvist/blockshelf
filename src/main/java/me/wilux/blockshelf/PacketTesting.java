package me.wilux.blockshelf;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

import static me.wilux.blockshelf._delete_nmsblocks.UnusedProtocol.printAllEventProperties;

public class PacketTesting {
    //public static PacketContainer superBadUnsafeRecipesAtBoot;
    public static void init(){
        Main.protocolManager.addPacketListener(
                new PacketAdapter(Main.plugin, ListenerPriority.NORMAL, PacketType.Play.Client.AUTO_RECIPE) {
                    @Override
                    public void onPacketReceiving(PacketEvent event) {
                        if (event.getPacketType() == PacketType.Play.Client.AUTO_RECIPE) {
                            printAllEventProperties(event);
                            //event.setCancelled(true);
                        }
                    }
                });
        Main.protocolManager.addPacketListener(
                new PacketAdapter(Main.plugin, ListenerPriority.NORMAL,PacketType.Play.Server.OPEN_WINDOW) {
                    @Override
                    public void onPacketSending(PacketEvent event) {
                        if (event.getPacketType() == PacketType.Play.Server.OPEN_WINDOW) {
                            printAllEventProperties(event);
                        }
                    }
                });
        Main.protocolManager.addPacketListener(
                new PacketAdapter(Main.plugin, ListenerPriority.NORMAL,PacketType.Play.Server.RECIPES) {
                    @Override
                    public void onPacketSending(PacketEvent event) {
                        if (event.getPacketType() == PacketType.Play.Server.RECIPES) {
                            printAllEventProperties(event);
                        }
                    }
                });
        Main.protocolManager.addPacketListener(
                new PacketAdapter(Main.plugin, ListenerPriority.NORMAL,PacketType.Play.Server.OPEN_WINDOW_HORSE) {
                    @Override
                    public void onPacketSending(PacketEvent event) {
                        if (event.getPacketType() == PacketType.Play.Server.OPEN_WINDOW_HORSE) {
                            printAllEventProperties(event);
                        }
                    }
                });
        Main.protocolManager.addPacketListener(
                new PacketAdapter(Main.plugin, ListenerPriority.NORMAL,PacketType.Play.Server.RECIPE_UPDATE) {
                    @Override
                    public void onPacketSending(PacketEvent event) {
                        if (event.getPacketType() == PacketType.Play.Server.RECIPE_UPDATE) {
                            //printAllEventProperties(event);
                            Main.logger.warning(event.toString());
                            /*if(superBadUnsafeRecipesAtBoot == null){
                                superBadUnsafeRecipesAtBoot = event.getPacket();
                            }*/
                        }
                    }
                });
        /*Main.protocolManager.addPacketListener(
                new PacketAdapter(Main.plugin, ListenerPriority.NORMAL, PacketType.Play.Client.BLOCK_DIG) {
                    @Override
                    public void onPacketReceiving(PacketEvent event) {
                        if (event.getPacketType() == PacketType.Play.Client.BLOCK_DIG) {
                            printAllEventProperties(event);
                        }
                    }
                });*/
    }
    /*
    public static void unlockRecipeWithoutToast(Player player){
        PacketContainer recipePack = new PacketContainer(PacketType.Play.Server.RECIPES);

        try {
            Main.protocolManager.sendServerPacket(player, recipePack);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(
                    "Cannot send packet " + recipePack, e);
        }
    }
    */
}
