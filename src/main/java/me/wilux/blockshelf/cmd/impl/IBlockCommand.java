package me.wilux.blockshelf.cmd.impl;

import me.wilux.blockshelf.api.CustomBlock;
import me.wilux.blockshelf.api.CustomRegistry;
import me.wilux.blockshelf.cmd.ISubCommand;
import me.wilux.blockshelf.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class IBlockCommand implements ISubCommand
{

	@Override
	public String commandName()
	{
		return "block";
	}

	@Override
	public String[] argumentNames()
	{
		return new String[] { "{BLOCK_ID}" };
	}

	@Override
	public String description()
	{
		return "Gives the player the specific custom block.";
	}

	@Override
	public boolean playerOnly()
	{
		return true;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(args.length == 1)
		{
			Utils.sendDetailedList(sender, CustomRegistry.CUSTOM_BLOCK_REGISTRY);
			return true;
		}
		
		
		
		Player p = (Player) sender;
		if(args[0].equalsIgnoreCase("block"))
		{
			for(CustomBlock cb : CustomRegistry.CUSTOM_BLOCK_REGISTRY)
			{
				if(args[1].equals(cb.getName()))
				{
					if(p.hasPermission(new Permission("cu.block." + cb.getName(), PermissionDefault.OP)))
					p.getInventory().addItem(cb.getBlockItem());
					else
					p.sendMessage("No permission!");
				}
			}
		}
		return false;
	}

}
