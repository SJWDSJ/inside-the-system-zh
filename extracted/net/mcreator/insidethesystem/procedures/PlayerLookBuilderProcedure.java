/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.commands.arguments.EntityAnchorArgument$Anchor
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.Vec3
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.mcreator.insidethesystem.InsideTheSystemMod;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class PlayerLookBuilderProcedure {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        PlayerLookBuilderProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level(), (Entity)event.getEntity());
    }

    public static void execute(LevelAccessor world, Entity entity) {
        PlayerLookBuilderProcedure.execute(null, world, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
        if (entity == null) {
            return;
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).builderLook) {
            entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).builderX, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).builderY + 1.0, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).builderZ));
            InsideTheSystemMod.queueServerWork(60, () -> {
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).builderLook = false;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            });
        }
    }
}

