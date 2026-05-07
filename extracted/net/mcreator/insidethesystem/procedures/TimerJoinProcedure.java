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
public class TimerJoinProcedure {
    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        TimerJoinProcedure.execute((Event)event, (LevelAccessor)event.getLevel());
    }

    private static void execute(@Nullable Event event, LevelAccessor world) {
        InsideTheSystemModVariables.MapVariables vars = InsideTheSystemModVariables.MapVariables.get(world);
        if (vars.TimerJoin > 0.0 && !vars.TimerEnd) {
            vars.TimerJoin -= 1.0;
            vars.syncData(world);
        }
    }
}

