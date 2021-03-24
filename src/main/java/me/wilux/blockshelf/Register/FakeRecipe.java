package me.wilux.blockshelf.Register;

import net.minecraft.server.v1_16_R3.MinecraftKey;
import net.minecraft.server.v1_16_R3.PacketPlayOutRecipes;
import net.minecraft.server.v1_16_R3.RecipeBookSettings;
import net.minecraft.server.v1_16_R3.RecipeBookType;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftFurnaceRecipe;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_16_R3.util.CraftNamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;

import java.util.Collections;
import java.util.Hashtable;

public class FakeRecipe {

    private static final RecipeBookSettings RECIPE_BOOK_SETTINGS_ALL_FALSE = new RecipeBookSettings();
    public static final PacketPlayOutRecipes PACKET_RECIPES_EMPTY = new PacketPlayOutRecipes(
            PacketPlayOutRecipes.Action.INIT,
            Collections.EMPTY_LIST,
            Collections.EMPTY_LIST,
            RECIPE_BOOK_SETTINGS_ALL_FALSE
    );

    public static void init(){
    }

    public static net.minecraft.server.v1_16_R3.FurnaceRecipe asVanillaRecipe(FurnaceRecipe furnaceRecipe){
        CraftFurnaceRecipe craftRecipe = CraftFurnaceRecipe.fromBukkitRecipe(furnaceRecipe);

        MinecraftKey nmsRecipeKey = CraftNamespacedKey.toMinecraft(craftRecipe.getKey());
        return new net.minecraft.server.v1_16_R3.FurnaceRecipe(
                nmsRecipeKey,
                craftRecipe.getGroup(),
                craftRecipe.toNMS(craftRecipe.getInputChoice(), false),
                CraftItemStack.asNMSCopy(craftRecipe.getResult()),
                craftRecipe.getExperience(),
                craftRecipe.getCookingTime()
        );
    }
}
