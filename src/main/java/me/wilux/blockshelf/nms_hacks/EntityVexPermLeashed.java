package me.wilux.blockshelf.nms_hacks;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

public class EntityVexPermLeashed extends EntityVex {
    public EntityVexPermLeashed(EntityTypes<? extends EntityVex> entitytypes, World world) {
        super(entitytypes, world);
    }
    @Override
    public void unleash(boolean flag, boolean flag1) {
        return;
    }

    public static EntityVexPermLeashed create(Location location) {
        //Get the world from our location argument, and cast it to it's NMS-equivalent object,
        //then get it's handle. This is the NMS WorldServer for the specified world.
        WorldServer nmsWorld = ((CraftWorld) location.getWorld()).getHandle();

        //Now we call our constructor. We give it an EntityType of Villager, give it the
        // WorldServer that will be handling it, and then specify the type of villager.
        EntityVexPermLeashed pvex = new EntityVexPermLeashed(EntityTypes.VEX, nmsWorld);

        pvex.setNoAI(true);
        pvex.setLocation(location.getX(), location.getY(), location.getZ(),0,0);
        pvex.setInvisible(true);
        MobEffect mobEffect = new MobEffect(MobEffects.INVISIBILITY,Integer.MAX_VALUE,0,false,false);
        pvex.addEffect(mobEffect);
        //pvex.getItemInMainHand().setCount(0);

        //TODO declare the other good things
        nmsWorld.addEntity(pvex);
        return pvex;
    }
}
