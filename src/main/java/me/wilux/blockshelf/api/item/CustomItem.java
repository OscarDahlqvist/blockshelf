package me.wilux.blockshelf.api.item;

import me.wilux.blockshelf.api.CustomBase;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CustomItem extends CustomBase {
	
	protected String displayName;
	protected List<String> lore;
	public List<String> getLore() {
		return lore;
	}

	public void setLore(List<String> lore) {
		this.lore = lore;
	}

	public CustomItem(String name, String modelPath) {
		super(name, modelPath, Material.CLOCK);
		this.displayName = name;
	}

	public CustomItem(String name, String modelPath, String displayName) {
		super(name, modelPath, Material.CLOCK);
		this.displayName = displayName;
	}
	
	public CustomItem(String name, String modelPath, String displayName, Material baseMat) {
		super(name, modelPath, baseMat);
		this.displayName = displayName;
	}
	
	public ItemStack getItem()	{
		ItemStack is = new ItemStack(this.baseMaterial);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.RESET + displayName);
		im.setCustomModelData((int)getId());
		is.setItemMeta(im);
		return is;
	}
	
	public void onTick(Player p, int slot)	{
		
	}

	public void onPlayerInteractEvent(PlayerInteractEvent e)	{
		
	}

	public void onPlayerItemDamageEvent(PlayerItemDamageEvent e){

	}
	
	public Recipe getRecipe()
	{
		return null;
	}
	
	public void onInteractEntity(PlayerInteractEntityEvent e)	{
		
	}
	
	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}
	
	
}
