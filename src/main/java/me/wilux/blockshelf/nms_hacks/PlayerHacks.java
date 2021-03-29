package me.wilux.blockshelf.nms_hacks;

import me.wilux.blockshelf.Main;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import net.minecraft.server.v1_16_R3.Entity;

public class PlayerHacks {
    public static void hackPlayer() {
        Main.logger.warning("HELLO?");
        Class<Entity> entityClazz = Entity.class;

        ByteBuddyAgent.install();

        ByteBuddy buddy = new ByteBuddy().with(TypeValidation.DISABLED);
        buddy
                .redefine(entityClazz)
                .method(ElementMatchers.named("isInRain"))
                .intercept(FixedValue.value(true))
                .make()
                .load(
                        entityClazz.getClassLoader(),
                        ClassReloadingStrategy.fromInstalledAgent());

        /*
        ByteBuddyFactory bf = ByteBuddyFactory.getInstance();

        DynamicType.Loaded<Entity> p =
                bf.createSubclass(EntityClass)
                .method(ElementMatchers.named("isInRain"))
                .intercept(FixedValue.value(true))
                .make()
                .load(
                        bf.getClassLoader(),
                        ClassLoadingStrategy.Default.INJECTION);
        */
        /*
        ByteBuddy buddy = new ByteBuddy().with(TypeValidation.DISABLED);
        buddy
                .redefine(EntityClass)
                .method(ElementMatchers.named("isInRain"))
                .intercept(FixedValue.value(true))
                .make()
                .load(
                        EntityClass.getClassLoader(),
                        ClassLoadingStrategy.Default.INJECTION);*/
    }
}
