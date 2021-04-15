package me.benfah.cu.api;

import me.benfah.cu.util.InformationEntry;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class CustomBase
{
	protected Plugin plugin;
	
	final private ArrayList<InformationEntry> mpe = new ArrayList<>();
	
	
	

	
	


	public Material baseMaterial;

	
	
	
	
	
	
	public CustomBase(InformationEntry entry, Material baseMaterial) {
		mpe.add(entry);
		this.baseMaterial = baseMaterial;
	}
	
	public CustomBase(String name, String modelPath, Material baseMaterial) 	{
		mpe.add(new InformationEntry(modelPath, name));
		this.baseMaterial = baseMaterial;
	}
	
	public Material getBaseMaterial()
	{
		return baseMaterial;
	}

	public short getId() {
		return getMainModelPathEntry().getId();
	}

	public InformationEntry getMainModelPathEntry()	{
		return mpe.get(0);
	}
	
	public List<InformationEntry> getModelPathEntry()
	{
		return mpe;
	}
	
	public String getName()
	{
		return getMainModelPathEntry().getName();
	}

	public Plugin getPlugin()
	{
		return plugin;
	}

	public void setPlugin(Plugin plugin)
	{
		this.plugin = plugin;
	}
	
}
