/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.mcreator.insidethesystem.InsideTheSystemMod;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class TimesProcedure {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        TimesProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level());
    }

    public static void execute(LevelAccessor world) {
        TimesProcedure.execute(null, world);
    }

    private static void execute(@Nullable Event event, LevelAccessor world) {
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Times) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Times = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).TimerN = 1.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            if (!world.isClientSide() && world.getServer() != null) {
                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u6267\u884c\u65f6\u95f4\u5373\u5c06\u7ed3\u675f\u2026\u2026"), false);
            }
            InsideTheSystemMod.queueServerWork(80, () -> {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u5b83\u6765\u4e86\u2026\u2026"), false);
                }
            });
        } else if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Times && InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).TimerN == 1.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Times = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).TimerN = 2.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            if (!world.isClientSide() && world.getServer() != null) {
                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u6267\u884c\u65f6\u95f4\u5373\u5c06\u7ed3\u675f\u2026\u2026"), false);
            }
            InsideTheSystemMod.queueServerWork(80, () -> {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u8f6c\u8fc7\u8eab\u6765\u2026\u2026"), false);
                }
            });
        } else if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Times && InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).TimerN == 2.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Times = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).TimerN = 3.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            if (!world.isClientSide() && world.getServer() != null) {
                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u6267\u884c\u65f6\u95f4\u5373\u5c06\u7ed3\u675f\u2026\u2026"), false);
            }
            InsideTheSystemMod.queueServerWork(80, () -> {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"...."), false);
                }
            });
        }
    }
}

