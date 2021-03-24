package me.wilux.blockshelf;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.benfah.cu.api.CustomBase;
import me.benfah.cu.api.CustomBlock;
import me.benfah.cu.api.CustomItem;
import me.benfah.cu.api.CustomRegistry;
import me.wilux.blockshelf.blocks.*;
import me.wilux.blockshelf.commands.DebugCommand;
import me.wilux.blockshelf.commands.DebugCommand2;
import me.wilux.blockshelf.commands.SetAbility;
import me.wilux.blockshelf.items.WireSpool;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Logger;

public class Main extends me.benfah.cu.main.CustomUtils {

public static Logger logger;
public static ProtocolManager protocolManager;
public static JavaPlugin plugin;

    public static final String UNOBTAINABLE_BLOCK = "ILLEGAL ITEM";
    public static final String CBLOCKNAME_EXDIRT = "exdirt";
    public static final String CBLOCKNAME_INDEXER = "indexer";
    public static HashMap<Player, GuiAble> currentlyOpenInventory;

    @Override
    public void onEnable() {
        //TODO fix GUI registry
        //TODO fix setblock in CustomBlock
        //TODO make armorstands marker ad invisible
        plugin = this;
        protocolManager = ProtocolLibrary.getProtocolManager();
        logger = getLogger();

        currentlyOpenInventory = new HashMap<Player, GuiAble>();
        super.onEnable();

        this.getCommand("rpsend").setExecutor(new DebugCommand());
        this.getCommand("rpdelete").setExecutor(new DebugCommand2());
        this.getCommand("setability").setExecutor(new SetAbility());
        //getServer().getPluginManager().registerEvents(new PlaceBlockListener(), this);

        CustomBlock exDirt = new CustomBlock(CBLOCKNAME_EXDIRT,"pseudoblock/exdirt","Exdirt");
        CustomRegistry.register(exDirt,this);

        ElectricFurnace.register();
        CoalGenerator.register();
        Press.register();
        Grinder.register();

        WireSpool.register();

        PacketTesting.init();
        CustomRecipes.init();
        //NMStesting.init();

    }

    @Override
    public void onDisable() {
    }

    static final int GUI_CHEST = 2;
    static final int GUI_STONECUTTER = 22;
}
