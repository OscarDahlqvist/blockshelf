package me.wilux.blockshelf.main;

import me.wilux.blockshelf.api.BlockInstance;
import me.wilux.blockshelf.api.CustomRegistry;
import me.wilux.blockshelf.api.InitializationMethodProvider;
import me.wilux.blockshelf.cmd.CommandRegistry;
import me.wilux.blockshelf.cmd.CustomUtilsCommandExecutor;
import me.wilux.blockshelf.listener.*;
import me.wilux.blockshelf.util.Config;
import me.wilux.blockshelf.util.TickRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map.Entry;
public class CustomUtils extends JavaPlugin
{
	public static JavaPlugin instance;

	@Override
	public void onEnable()
	{
		//JavassistUtil.getCUInventoryCustomClass();
		//JavassistUtil.getCUInventoryClass();
		//JavassistUtil.getCUInventoryCustomClass();
		//JavassistUtil.getContainerClass();
		instance = this;
		CustomRegistry.initMaps();
		
		Config.onEnable();

		//File downloading goes here v
		/*
		InitializationMethodRegistry.initMethods();
		InitializationMethodProvider.setCurrentMethod(Config.getConfiguration().getString("init-method"));
		*/

		Bukkit.getPluginManager().registerEvents(new SlotChangeListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerInteractEntityListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
		Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
		Bukkit.getPluginManager().registerEvents(new ItemCraftListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlaceBlockListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);

		System.out.println("####### BLOCKSHELF #######");
		
		Bukkit.getScheduler().runTask(instance, () -> InitializationMethodProvider.init(false));

        Bukkit.getScheduler().runTaskTimer(instance, new TickRunnable(), 1, 1);
	}
	
	@Override
	public void onDisable()	{
		for(Entry<Location, BlockInstance> entr : BlockInstance.getBlockInstanceMap().entrySet()) {
			if(entr.getValue() != null)
			entr.getValue().write();
		}
		
		CustomRegistry.saveIDNameMap();
		CustomRegistry.saveWorldStores();
		
		Config.onDisable();
	}

	public static JavaPlugin getCUInstance()
	{
		return instance;
	}
}
