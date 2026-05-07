/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.entity.player.PlayerEvent$PlayerLoggedOutEvent
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber
public class IfPlayerLeftProcedure {
    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        IfPlayerLeftProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level());
    }

    public static void execute(LevelAccessor world) {
        IfPlayerLeftProcedure.execute(null, world);
    }

    private static void execute(@Nullable Event event, LevelAccessor world) {
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Inworld = false;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).worldDiedTemp = InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldDied;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLifeTemp = InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
    }
}

