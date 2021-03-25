package me.wilux.blockshelf.nms_hacks;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

public class EntityFixedLeashKnot extends EntityLeash {
    public EntityFixedLeashKnot(EntityTypes<? extends EntityLeash> entitytypes, World world) {
        super(entitytypes, world);
    }

    public static EntityFixedLeashKnot create(Location location) {
        //Get the world from our location argument, and cast it to it's NMS-equivalent object,
        //then get it's handle. This is the NMS WorldServer for the specified world.
        WorldServer nmsWorld = ((CraftWorld) location.getWorld()).getHandle();

        //Now we call our constructor. We give it an EntityType of Villager, give it the
        // WorldServer that will be handling it, and then specify the type of villager.
        EntityFixedLeashKnot leashKnot = new EntityFixedLeashKnot(EntityTypes.LEASH_KNOT, nmsWorld);

        leashKnot.setLocation(location.getX(), location.getY(), location.getZ(),0,0);

        nmsWorld.addEntity(leashKnot);
        return leashKnot;
    }
    //@Override
    //public void tick() { return; }
    //@Override
    //public void entityBaseTick() { return; }
}
