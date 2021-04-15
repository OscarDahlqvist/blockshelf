package me.wilux.blockshelf.init;

import java.util.HashMap;

public class InitializationMethodRegistry
{
	private static HashMap<String, IInitializationMethod> test = new HashMap<>();
	
	public static void register(String name, IInitializationMethod method)
	{
		test.put(name, method);
	}
	
	public static void unregister(String name)
	{
		test.remove(name);
	}

	/*
	public static void initMethods()
	{
		register("dropbox", new DropBoxInitializationMethod());
		register("minepack", new MinePackInitializationMethod());
	}*/
	
	public static IInitializationMethod getMethod(String name)
	{
		return test.get(name);
	}
	
}
