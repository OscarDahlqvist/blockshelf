package me.wilux.blockshelf.nms_hacks;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

public class EntityFixedLeashTarget extends EntityChicken {
    public EntityFixedLeashTarget(EntityTypes<? extends EntityChicken> entitytypes, World world) {
        super(entitytypes, world);
    }

    public static EntityFixedLeashTarget create(Location location) {
        //Get the world from our location argument, and cast it to it's NMS-equivalent object,
        //then get it's handle. This is the NMS WorldServer for the specified world.
        WorldServer nmsWorld = ((CraftWorld) location.getWorld()).getHandle();

        //Now we call our constructor. We give it an EntityType of Villager, give it the
        // WorldServer that will be handling it, and then specify the type of villager.
        EntityFixedLeashTarget leashMob = new EntityFixedLeashTarget(EntityTypes.CHICKEN, nmsWorld);

        leashMob.setNoAI(true);
        leashMob.setLocation(location.getX(), location.getY(), location.getZ(),0,-90f);
        leashMob.setAge(-32768);
        leashMob.setInvisible(true);
        leashMob.setInvulnerable(true);
        MobEffect mobEffect = new MobEffect(MobEffects.INVISIBILITY,Integer.MAX_VALUE,0,false,false);
        leashMob.addEffect(mobEffect);

        //TODO declare the other good things
        nmsWorld.addEntity(leashMob);
        return leashMob;
    }
    @Override
    public void unleash(boolean flag, boolean flag1) {
        return;
    }
    @Override
    public void movementTick() { return; }
    @Override
    protected int getExpValue(EntityHuman entityhuman) { return 0; }
    @Override
    protected void mobTick() { return; }

    @Override
    public boolean inBlock(){
        return false;
    }
}
