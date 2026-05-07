/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.EntityTickEvent$Pre
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.mcreator.insidethesystem.entity.AngryBuilderEntity;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class ThirdProcedure {
    private static boolean messageSent = false;

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Pre event) {
        ThirdProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level(), event.getEntity());
    }

    public static void execute(LevelAccessor world, Entity entity) {
        ThirdProcedure.execute(null, world, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
        if (entity == null) {
            return;
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Third) {
            if (!messageSent) {
                Level level;
                if (!world.isClientSide() && world instanceof Level && (level = (Level)world).getServer() != null) {
                    level.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u4f60\u72ec\u81ea\u4e00\u4eba\u5728\u8fd9\u4e2a\u4e16\u754c\u91cc\uff0c\u4e0d\u5b64\u72ec\u5417\uff1f"), false);
                }
                messageSent = true;
            }
            if (!(entity instanceof ServerPlayer || entity instanceof AngryBuilderEntity || world.isClientSide())) {
                entity.discard();
            }
        }
    }
}

