package me.benfah.cu.api;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;

import me.benfah.cu.util.Utils;

public class CustomItem extends CustomBase {
	
	protected String title;
	protected List<String> lore;
	public List<String> getLore() {
		return lore;
	}

	public void setLore(List<String> lore) {
		this.lore = lore;
	}

	public CustomItem(String name, String modelPath) {
		super(name, modelPath, Material.CLOCK);
		this.title = title;
	}

	public CustomItem(String name, String modelPath, String title) {
		super(name, modelPath, Material.CLOCK);
		this.title = title;
	}
	
	public CustomItem(String name, String modelPath, String title, List<String> lore) {
		super(name, modelPath, Material.CLOCK);
		this.title = title;
		this.lore = lore;
	}
	
	public CustomItem(String name, String modelPath, String title, Material baseMat) {
		super(name, modelPath, baseMat);
		this.title = title;
	}
	
	public CustomItem(String name, String modelPath, String title, List<String> lore, Material baseMat)	{
		super(name, modelPath, baseMat);
		this.title = title;
		this.lore = lore;
	}
	
	public ItemStack getItem()	{
		ItemStack is = new ItemStack(this.baseMaterial);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.RESET + title);
		im.setCustomModelData((int)getId());
		is.setItemMeta(im);
		return is;
	}
	
	public void onUpdate(Player p, int slot)	{
		
	}
	
	public void onInteract(PlayerInteractEvent e, EquipmentSlot es)	{
		
	}

	public void onUseItem(PlayerInteractEvent e, EquipmentSlot es){

	}
	
	public Recipe getRecipe()
	{
		return null;
	}
	
	public void onInteractEntity(PlayerInteractEntityEvent e)	{
		
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
	
	
}
