package me.wilux.blockshelf.util;

import me.wilux.blockshelf.api.*;
import net.minecraft.server.v1_16_R3.BlockPosition;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.NBTTagList;
import net.minecraft.server.v1_16_R3.TileEntityMobSpawner;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map.Entry;

public class Utils
{
	public final class NBTTagListGetterArguments{
		public static final int END=0;
		public static final int BYTE=1;
		public static final int SHORT=2;
		public static final int INT=3;
		public static final int LONG=4;
		public static final int FLOAT=5;
		public static final int DOUBLE=6;
		public static final int BYTEARR=7;
		public static final int STRING=8;
		public static final int LIST=9;
		public static final int NBTCOMPOUND=10;
		public static final int INTARR=11;
	}
	public static boolean hasCustomModelDataArmorstand(Block b){
		try {
			Location loc = b.getLocation();
			CraftWorld cw = (CraftWorld)loc.getWorld();
			TileEntityMobSpawner tileEntity = (TileEntityMobSpawner)cw.getHandle().getTileEntity(
					new BlockPosition(loc.getX(), loc.getY(), loc.getZ()));
			NBTTagCompound entityNBT = tileEntity.getSpawner().spawnData.getEntity();
			if(entityNBT.getString("id").equals("minecraft:armor_stand")){

				NBTTagList armorItems  = entityNBT.getList("ArmorItems",NBTTagListGetterArguments.NBTCOMPOUND);

				return ((NBTTagCompound)armorItems.get(3)).getCompound("tag").hasKey("CustomModelData");
			}
		} catch (Exception e) {
			return false;
		}
		return  false;
	}
	public static boolean compareBlocks(CustomBlock cb, Block b)
	{
		try
		{
			Location loc = b.getLocation();
			CraftWorld cw = (CraftWorld)loc.getWorld();
			TileEntityMobSpawner tileEntity = (TileEntityMobSpawner)cw.getHandle().getTileEntity(
					new BlockPosition(loc.getX(), loc.getY(), loc.getZ()));
			NBTTagCompound entityNBT = tileEntity.getSpawner().spawnData.getEntity();
			if(entityNBT.getString("id").equals("minecraft:armor_stand")){

				NBTTagList armorItems  = entityNBT.getList("ArmorItems",NBTTagListGetterArguments.NBTCOMPOUND);

				int customModelData = ((NBTTagCompound)armorItems.get(3)).getCompound("tag").getInt("CustomModelData");
				if(customModelData == cb.getId()) return true;
			}
			return  false;

					/*
			Class<?> craftCreatureSpawnerClass = ReflectionUtils.getRefClass("{cb}.block.CraftCreatureSpawner");
			Class<?> mobSpawnerAbstractClass = ReflectionUtils.getRefClass("{nms}.MobSpawnerAbstract");
			ReflectionUtils.getRefClass("{nms}.MobSpawnerData");
			ReflectionUtils.getRefClass("{nms}.NBTTagList");

			CreatureSpawner cs = (CreatureSpawner) b.getState();

			Object tileEntity = setAccessible(
					craftCreatureSpawnerClass.getSuperclass().getDeclaredMethod("getTileEntity")).invoke(cs);
			Object msa = tileEntity.getClass().getDeclaredMethod("getSpawner").invoke(tileEntity);
			Field f = mobSpawnerAbstractClass.getDeclaredField("spawnData");

			f.setAccessible(true);
			Object msd = f.get(msa);
			Object nbtTagCompound = msd.getClass().getDeclaredMethod("b").invoke(msd);
			Object nbtList = nbtTagCompound.getClass().getMethod("get", String.class).invoke(nbtTagCompound,
					"ArmorItems");
			Object nbtTagCompound2 = nbtList.getClass().getDeclaredMethod("get", int.class).invoke(nbtList, 3);
			String itemtype = (String) nbtTagCompound2.getClass().getMethod("getString", String.class)
					.invoke(nbtTagCompound2, "id");
			short damage = (short) nbtTagCompound2.getClass().getMethod("getShort", String.class)
					.invoke(nbtTagCompound2, "Damage");
			if (itemtype.contains(Utils.getUnlocalizedName(cb.getBaseMaterial())))
			{
				if (damage == cb.getId())
				{
					return true;
				}
			}*/

		} catch (Exception e)
		{
			return false;
		}

		//return false;
	}

	public static CustomItem getMainOrOffHandCI(PlayerEvent e)
	{
		if (CustomRegistry.isCustomItem(e.getPlayer().getInventory().getItemInMainHand()))
		{
			return CustomRegistry.getCustomItem(e.getPlayer().getInventory().getItemInMainHand());
		} else if (CustomRegistry.isCustomItem(e.getPlayer().getInventory().getItemInOffHand()))
		{
			return CustomRegistry.getCustomItem(e.getPlayer().getInventory().getItemInOffHand());
		}
		return null;
	}

	public static ItemStack getMainOrOffHandStack(PlayerEvent e)
	{
		if (CustomRegistry.isCustomItem(e.getPlayer().getInventory().getItemInMainHand()))
		{
			return e.getPlayer().getInventory().getItemInMainHand();
		} else if (CustomRegistry.isCustomItem(e.getPlayer().getInventory().getItemInOffHand()))
		{
			return e.getPlayer().getInventory().getItemInOffHand();
		}
		return null;
	}

	public static void setMainOrOffHandStack(PlayerEvent e, ItemStack stack)
	{
		if (CustomRegistry.isCustomItem(e.getPlayer().getInventory().getItemInMainHand()))
		{
			e.getPlayer().getInventory().setItemInMainHand(stack);
		} else if (CustomRegistry.isCustomItem(e.getPlayer().getInventory().getItemInOffHand()))
		{
			e.getPlayer().getInventory().setItemInOffHand(stack);
		}
	}

	public static ItemStack updateTags(ItemStack stack)
	{
		ItemMeta meta = stack.getItemMeta();
		stack.setItemMeta(meta);
		return stack;
	}

	public static EquipmentSlot getEquipmentSlot(PlayerEvent e)
	{
		if (CustomRegistry.isCustomItem(e.getPlayer().getInventory().getItemInMainHand()))
		{
			return EquipmentSlot.HAND;
		} else if (CustomRegistry.isCustomItem(e.getPlayer().getInventory().getItemInOffHand()))
		{
			return EquipmentSlot.OFF_HAND;
		}
		return null;
	}

	public static String getUnlocalizedName(Material mat)
	{
		return mat.getKey().getKey();

		/*
		Class<?> matClass = ReflectionUtils.getRefClass("{cb}.util.CraftMagicNumbers");
		Class<?> itemClass = ReflectionUtils.getRefClass("{nms}.Item");
		try
		{

			Object item = matClass.getMethod("getItem", Material.class).invoke(null, mat);
			Object registryMaterials = itemClass.getField("REGISTRY").get(null);
			Object mcKey = registryMaterials.getClass().getMethod("b", Object.class).invoke(registryMaterials, item);
			return (String) mcKey.getClass().getMethod("getKey").invoke(mcKey);

		} catch (ReflectiveOperationException e)
		{
			e.printStackTrace();
		}
		return null;
		*/
	}

	public static int getMaxDurability(Material mat)
	{
		Class<?> matClass = ReflectionUtils.getRefClass("{cb}.util.CraftMagicNumbers");
		Class<?> itemClass = ReflectionUtils.getRefClass("{nms}.Item");
		try
		{
			Object item = matClass.getMethod("getItem", Material.class).invoke(null, mat);
			return (int) itemClass.getMethod("getMaxDurability").invoke(item);

		} catch (ReflectiveOperationException e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	public static Method setAccessible(Method f)
	{
		f.setAccessible(true);
		return f;
	}

	public static void printInitText()
	{

	}

	public static void writeModels(Entry<String, Material> mat, int size, PrintWriter pw)
	{

		CustomBase[] cbarray = new CustomBase[size + 2];

		for (CustomBlock cb : CustomRegistry.CUSTOM_BLOCK_REGISTRY)
		{
			// if(cb.getMainModelPathEntry() != null)
			cbarray[cb.getId()] = cb;
		}
		for (CustomItem ci : CustomRegistry.CUSTOM_ITEM_REGISTRY)
		{
			// if(ci.getMainModelPathEntry() != null)
			cbarray[ci.getMainModelPathEntry().getId()] = ci;
		}
		for (CustomGUI cg : CustomRegistry.CUSTOM_GUI_REGISTRY)
		{
			if (cg.getMainModelPathEntry() != null && cg.getMainModelPathEntry().getId() != null)
				cbarray[cg.getMainModelPathEntry().getId()] = cg;
			if (cg.getSecondModelPathEntry() != null && cg.getSecondModelPathEntry().getId() != null)
				cbarray[cg.getSecondModelPathEntry().getId()] = cg;
		}

		for (CustomBase cb : cbarray)
		{
			if (cb != null)
				if (cb instanceof CustomGUI)
				{
					CustomGUI cg = (CustomGUI) cb;
					if (cg.getMainModelPathEntry().getPathToModel() != null)
					{
						pw.println(",{ \"predicate\": {\"custom_model_data\": "
								+ cg.getMainModelPathEntry().getId() + "}, \"model\": \""
								+ cg.getMainModelPathEntry().getPathToModel() + "\"}");
					}

					if (cg.getSecondModelPathEntry().getPathToModel() != null)
					{
						pw.println(",");
						pw.println("{ \"predicate\": {\"custom_model_data\": "
								+ cg.getSecondModelPathEntry().getId() + "}, \"model\": \""
								+ cg.getSecondModelPathEntry().getPathToModel() + "\"}");
					}
				} else
				{
					pw.println(",");
					pw.println("{ \"predicate\": {\"custom_model_data\": "
							+ cb.getMainModelPathEntry().getId() + "}, \"model\": \""
							+ cb.getMainModelPathEntry().getPathToModel() + "\"}");
				}
		}
		//pw.println(",");
		//pw.println("{ \"predicate\": {\"custom_model_data\": 0}, \"model\": \"item/" + mat.getKey() + "\"}");
		// CustomBase[] cbList = CustomRegistry.getAllEntries();
		//
		// for(CustomBase cb : cbList)
		// {
		// for(InformationEntry ent : cb.getModelPathEntry())
		// {
		// if(ent.getPathToModel() != null)
		// {
		// pw.println(",");
		// pw.println("{ \"predicate\": {\"damaged\": 0, \"damage\": " +
		// getDamageForTool(ent.getId(), size + 1) + "}, \"model\": \"" +
		// ent.getPathToModel() + "\"}");
		// }
		// }
		// }
		// pw.println(",");
		// pw.println("{ \"predicate\": {\"damaged\": 1, \"damage\": 0}, \"model\":
		// \"item/" + mat.getKey() + "\"}");

	}

	private static String getDamageForTool(double dmg, double maxSize)
	{
		return new BigDecimal(1D / 1562D * dmg).toPlainString();
	}

	public static void sendDetailedList(CommandSender sender, ArrayList<? extends CustomBase> baselist)
	{
		for (CustomBase cb : baselist)
		{
			sender.sendMessage(ChatColor.GREEN + cb.getName() + " - " + cb.getPlugin().getName());
		}
	}

}
