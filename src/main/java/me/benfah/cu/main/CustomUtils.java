package me.benfah.cu.main;

import me.benfah.cu.api.BlockInstance;
import me.benfah.cu.api.CustomRegistry;
import me.benfah.cu.api.InitializationMethodProvider;
import me.benfah.cu.cmd.CommandRegistry;
import me.benfah.cu.cmd.CustomUtilsCommandExecutor;
import me.benfah.cu.init.InitializationMethodRegistry;
import me.benfah.cu.listener.*;
import me.benfah.cu.util.Config;
import me.benfah.cu.util.JavassistUtil;
import me.benfah.cu.util.TickRunnable;
import me.wilux.blockshelf.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Map.Entry;
public class CustomUtils extends JavaPlugin
{
	public static CustomUtils instance;
	
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
		CommandRegistry.initSubCommands();
		Bukkit.getPluginCommand("customutils").setExecutor(new CustomUtilsCommandExecutor());
		System.out.println("####### CUSTOMUTILS 2 #######");
		
		
		Bukkit.getScheduler().runTask(instance, new Runnable() {
				
			@Override
			public void run() {
				InitializationMethodProvider.init(false);
			}
		});
		

        Bukkit.getScheduler().runTaskTimer(instance, new TickRunnable(), 1, 1);
	}
	
	
	
	
	
	@Override
	public void onDisable()
	{
		
		
		for(Entry<Location, BlockInstance> entr : BlockInstance.getBlockInstanceMap().entrySet())
		{
			if(entr.getValue() != null)
			entr.getValue().write();
		}
		
		CustomRegistry.saveIDNameMap();
		CustomRegistry.saveWorldStores();
		
		Config.onDisable();
	}





	public static CustomUtils getInstance()
	{
		return instance;
	}
	
	
	
	
}
