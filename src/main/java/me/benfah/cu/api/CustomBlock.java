package me.benfah.cu.api;

import java.lang.reflect.Method;

import me.benfah.cu.util.ReflectionUtils;
import me.benfah.cu.util.Utils;
import me.wilux.blockshelf.Main;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;


public class CustomBlock extends CustomBase
{
	public Plugin getPlugin(){
		return plugin;
	}

	public CustomBlock(String name, String modelPath, String title)
	{
		super(name, modelPath, Material.BARRIER);
		this.title = title;
	}
	
	public CustomBlock(String name, String modelPath, String title, Material baseMat)
	{
		super(name, modelPath, baseMat);
		this.title = title;
	}

	protected String title;

	public void setName(String name)
	{
		getMainModelPathEntry().setName(name);
	}

	public void setId(short id) {
		getMainModelPathEntry().setId(id);
	}
	
//	public void setModelPath(String path)
//	{
//		this.pathToModel1 = path;
//	}
	
	public String getModelPath()
	{
		return getMainModelPathEntry().getPathToModel();
	}

	public ItemStack getBlockItem()
	{
		ItemStack is = new ItemStack(this.baseMaterial);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.RESET + title);
		im.setCustomModelData((int)getId());
		is.setItemMeta(im);
		return is;
	}
	
	public void setBlock(World w, int x, int y, int z)
	{
		setBlock(new Location(w, x, y, z));
	}
	
	public void setBlock(Block b)
	{
		setBlock(b.getLocation());
	}
	
	public void onInteract(PlayerInteractEvent e) {
	}
	
	public Sound getPlaceSound()	{
		return Sound.BLOCK_STONE_HIT;
	}
	
	public void onBlockPlaced(BlockPlaceEvent e) {

	}

	public void onBlockBroken(BlockBreakEvent e){
		World w = e.getPlayer().getWorld();
		Location spawnLoc = e.getBlock().getLocation().add(new Vector(0.5,0.5,0.5));
		w.spawnParticle(Particle.ITEM_CRACK, spawnLoc, 30,0,0,0,0.1f, getBlockItem());
	}

	public ItemStack[] getLoot(Block b)
	{
		return new ItemStack[] {getBlockItem()};
	}

	public void placeBlock(Location l,BlockPlaceEvent evt) {
		setBlock(l);
	}

	public void setBlock(Location l) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(
				Main.plugin, new CustomBlock.SetInstantSpawnerData(this, l), 0);
	}
	public class SetInstantSpawnerData implements Runnable {
		CustomBlock customBlock;
		Location location;
		public SetInstantSpawnerData(CustomBlock customBlock, Location location) {
			this.customBlock = customBlock;
			this.location = location;
		}
		public void run() { try {
			Block bukkitBlock = location.getWorld().getBlockAt(location);
			bukkitBlock.setType(Material.SPAWNER);
			CraftWorld cw = (CraftWorld)location.getWorld();
			TileEntityMobSpawner tileEntity = (TileEntityMobSpawner)cw.getHandle().getTileEntity(
					new BlockPosition(bukkitBlock.getX(), bukkitBlock.getY(), bukkitBlock.getZ()));
			tileEntity.getSpawner().requiredPlayerRange = 0;

			MobSpawnerData data = tileEntity.getSpawner().spawnData;
			data.getEntity().setString("id","minecraft:armor_stand");
			//data.getEntity().setBoolean("Invisible",true);


			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setString("foo","bar");
			nbt.setString("id","minecraft:armor_stand");

			NBTTagCompound headItemNbt = new NBTTagCompound();
			headItemNbt.setString("id",Material.BARRIER.getKey().toString());
			headItemNbt.setInt("Count",1);
			NBTTagCompound itemTagNbt = new NBTTagCompound();
			itemTagNbt.setInt("CustomModelData", customBlock.getId());
			headItemNbt.set("tag",itemTagNbt);

			NBTTagList nbtTagList = new NBTTagList();
			nbtTagList.add(new NBTTagCompound());
			nbtTagList.add(new NBTTagCompound());
			nbtTagList.add(new NBTTagCompound());
			nbtTagList.add(headItemNbt);

			nbt.set("ArmorItems",nbtTagList);

			data.getEntity().set("ArmorItems",nbtTagList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}
	/*public void setBlock(Location l)
	{
		Block b = l.getWorld().getBlockAt(l);
		b.setType(Material.SPAWNER);

		CreatureSpawner cs = (CreatureSpawner) b.getState();
		cs.setSpawnedType(EntityType.ARMOR_STAND);
		Class<?> craftCreatureSpawnerClass = ReflectionUtils.getRefClass("{cb}.block.CraftCreatureSpawner");
		Class<?> mobSpawnerAbstractClass = ReflectionUtils.getRefClass("{nms}.MobSpawnerAbstract");
		Class<?> mojangsonParserClass = ReflectionUtils.getRefClass("{nms}.MojangsonParser");

		try {
			Object tileEntity = setAccessible(craftCreatureSpawnerClass.getSuperclass().getDeclaredMethod("getTileEntity")).invoke(cs);
			Object msa = tileEntity.getClass().getDeclaredMethod("getSpawner").invoke(tileEntity);

			Object nbtz = mojangsonParserClass.getMethod("parse", String.class).invoke(null,
					"{MaxNearbyEntities:0s,RequiredPlayerRange:0s,SpawnData:{id:\"minecraft:armor_stand\",Invisible:1,Marker:1,ArmorItems:[{},{},{},{id:\"minecraft:"
							+ Utils.getUnlocalizedName(baseMaterial)
							+ "\",Count:1b,tag:{CustomModelData:"
							+ getId()
							+ "}]}}"
			);
			mobSpawnerAbstractClass.getDeclaredMethod("a", nbtz.getClass()).invoke(msa, nbtz);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		l.getWorld().playSound(l, getPlaceSound(), 1, 1);
		if(getClass().isAssignableFrom(IInstanceProvider.class))
		{
			BlockInstance bi = BlockInstance.getBlockInstance(b);
			bi.setMetadataValue("shouldUpdate", true);
		}
	}*/
	
	public Recipe getRecipe()
	{
		return null;
	}
	
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public Method setAccessible(Method f)
	{
		f.setAccessible(true);
		return f;
	}
	
	
}
