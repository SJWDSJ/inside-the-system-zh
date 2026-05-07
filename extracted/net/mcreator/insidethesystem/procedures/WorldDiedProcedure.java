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
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber
public class WorldDiedProcedure {
    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        WorldDiedProcedure.execute((Event)event, (LevelAccessor)event.getLevel());
    }

    public static String execute(LevelAccessor world) {
        return WorldDiedProcedure.execute(null, world);
    }

    private static String execute(@Nullable Event event, LevelAccessor world) {
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldDied <= 192000.0 && InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Angry && InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldDied != 0.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldDied -= 1.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldDied == 153000.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).First = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldDied == 133000.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Second = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldDied == 106000.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Third = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldDied == 80000.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Fourth = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldDied == 50000.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Fiveth = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldDied == 25000.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Sixth = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldDied == 0.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Died = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        return "" + InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldDied;
    }
}

