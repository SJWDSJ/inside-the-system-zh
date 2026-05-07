/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.LevelTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.mcreator.insidethesystem.InsideTheSystemMod;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber
public class Scr2PProcedure {
    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        Scr2PProcedure.execute((Event)event, (LevelAccessor)event.getLevel());
    }

    public static boolean execute(LevelAccessor world) {
        return Scr2PProcedure.execute(null, world);
    }

    private static boolean execute(@Nullable Event event, LevelAccessor world) {
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Scr2) {
            InsideTheSystemMod.queueServerWork(15, () -> {
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Scr2 = false;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            });
            return true;
        }
        return false;
    }
}

