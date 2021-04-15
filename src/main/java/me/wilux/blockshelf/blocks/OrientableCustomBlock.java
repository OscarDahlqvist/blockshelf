package me.wilux.blockshelf.blocks;

import me.benfah.cu.api.CustomBlock;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockPlaceEvent;

public abstract class OrientableCustomBlock extends CustomBlock {
    public OrientableCustomBlock(String name, String modelPath, String title) {
        super(name, modelPath, title);
    }

    public OrientableCustomBlock north_south;
    public OrientableCustomBlock west_east;

    public OrientableCustomBlock getDefaultRotation(){
        return north_south;
    }

    @Override
    public void placeBlock(Location l, BlockPlaceEvent evt) {
        float yaw = evt.getPlayer().getLocation().getYaw();
        BlockFace f = axis[Math.round(yaw / 90f) & 0x3].getOppositeFace();
        switch (f){
            case NORTH: case SOUTH:
                north_south.setBlock(l);
                break;
            case EAST: case WEST:
                west_east.setBlock(l);
                break;
        }
    }

    private static final BlockFace[] axis = { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
}
