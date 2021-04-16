package me.wilux.blockshelf.api.block;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockPlaceEvent;

public abstract class OrientableCustomBlock extends CustomBlock {
    public OrientableCustomBlock(String name, String northModelPath, String title) {
        super(name, northModelPath, title);
    }

    public OrientableCustomBlock north;
    public OrientableCustomBlock south;
    public OrientableCustomBlock west;
    public OrientableCustomBlock east;

    public CustomBlock getDefaultRotation(){
        return north;
    }

    @Override
    public void onBlockPlaceEvent(Location l, BlockPlaceEvent evt) {
        float yaw = evt.getPlayer().getLocation().getYaw();
        BlockFace f = axis[Math.round(yaw / 90f) & 0x3].getOppositeFace();
        switch (f){
            case NORTH: north.setBlock(l); break;
            case SOUTH: south.setBlock(l); break;
            case EAST: east.setBlock(l); break;
            case WEST: west.setBlock(l); break;
        }
    }

    private static final BlockFace[] axis = { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
}
