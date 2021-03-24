package me.wilux.blockshelf.commands;

import me.wilux.blockshelf.Register.FakeRecipe;
import me.wilux.blockshelf.Register._DummyText;
import net.minecraft.server.v1_16_R2.IRecipe;
import net.minecraft.server.v1_16_R2.PacketPlayOutRecipeUpdate;
import net.minecraft.server.v1_16_R2.PacketPlayOutRecipes;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class DebugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player)commandSender;

        commandSender.sendMessage("§bSend Recipes");

        //Get declared recipes
        Collection<IRecipe<?>> realRegistredRecipes = ((CraftServer)Bukkit.getServer()).getServer().getCraftingManager().b();
        PacketPlayOutRecipeUpdate packet = new PacketPlayOutRecipeUpdate(realRegistredRecipes);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);

        //TODO: keep recipe book settings

        //this somehow updates the bookkm which makes no sense but sure
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(FakeRecipe.PACKET_RECIPES_EMPTY);


        //removed code, left for reference
        {
            if(true);
        /*
        PacketContainer recipes = PacketTesting.superBadUnsafeRecipesAtBoot;
        try {
            Main.protocolManager.sendServerPacket(player, recipes);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Cannot send packet " + recipes, e);
        }
        //TODO make this work
        /*
        PacketContainer pokePlayerRecipeBook = new PacketContainer(PacketType.Play.Server.RECIPES);
        try {
            Main.protocolManager.sendServerPacket(player, pokePlayerRecipeBook);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Cannot send packet " + pokePlayerRecipeBook, e);
        }
        //Workaround for ^
        Bukkit.dispatchCommand(commandSender,"recipe take "+player.getName()+" lavatime:magmacreamfromlava");

        /*
        Inventory inv = Bukkit.createInventory(null, InventoryType.FURNACE);
        inv.setItem(0,new ItemStack(Material.STONE,1));
        inv.setItem(1,new ItemStack(Material.DIRT,1));
        playerSender.openInventory(inv);
        */
            //ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

        /*
        PacketContainer fakeInv = new PacketContainer(PacketType.Play.Server.OPEN_WINDOW);
        for(int i : fakeInv.getIntegers().getValues()){
            Main.logger.warning("INT " + i);
        }
        for(WrappedChatComponent i : fakeInv.getChatComponents().getValues()){
            Main.logger.warning("CHAT " + i);
        }
        fakeInv.getChatComponents().write(0,Main.superBadUnsafeRecipesAtBoot);
        */

        /*
        try {
            protocolManager.sendServerPacket((Player)commandSender, fakeInv);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(
                    "Cannot send packet " + fakeInv, e);
        }
        */

            //proto.createPacket(PacketType.Play.Server.OPEN_WINDOW,1,InventoryType.FURNACE,)
        }

        return true;
    }
}
