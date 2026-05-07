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
public class BuilderSpawnTimerProcedure {
    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        BuilderSpawnTimerProcedure.execute((Event)event, (LevelAccessor)event.getLevel());
    }

    public static String execute(LevelAccessor world) {
        return BuilderSpawnTimerProcedure.execute(null, world);
    }

    private static String execute(@Nullable Event event, LevelAccessor world) {
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).builderSpawnTimer <= 32000.0 && InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Angry) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).builderSpawnTimer -= 1.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).builderSpawnTimer == 0.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).summon = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).builderSpawnTimer = 32000.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        return "" + InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).builderSpawnTimer;
    }
}

