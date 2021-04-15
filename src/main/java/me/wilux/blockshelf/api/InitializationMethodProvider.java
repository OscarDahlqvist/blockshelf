package me.wilux.blockshelf.api;

import me.wilux.blockshelf.init.IInitializationMethod;
import me.wilux.blockshelf.init.InitializationMethodRegistry;
import me.wilux.blockshelf.util.Config;


public class InitializationMethodProvider
{

	public static IInitializationMethod getCurrentMethod()	{
		return InitializationMethodRegistry.getMethod(Config.getConfiguration().getString("init-method"));
	}

	public static void setCurrentMethod(String name)
	{
		Config.getConfiguration().set("init-method", name);
	}
	
	public static void init(boolean forceUpload)
	{
		getCurrentMethod().init(forceUpload);
	}
	
	public static void initRP(boolean forceUpload)
	{
		getCurrentMethod().initResourcePack(forceUpload);
	}
	
	
}
