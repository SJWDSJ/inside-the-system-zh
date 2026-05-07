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
public class TaskDialogueProcedure {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        TaskDialogueProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level());
    }

    public static void execute(LevelAccessor world) {
        TaskDialogueProcedure.execute(null, world);
    }

    private static void execute(@Nullable Event event, LevelAccessor world) {
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).TaskDialogue) {
            if (!world.isClientSide() && world.getServer() != null) {
                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u5bf9\u4e0d\u8d77\uff0c\u6211\u63a7\u5236\u4e0d\u4f4f\u81ea\u5df1\u2026\u2026"), false);
            }
            InsideTheSystemMod.queueServerWork(80, () -> {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u6211\u4eec\u53bb\u627e\u9700\u8981\u7684\u7269\u54c1\u5427\uff01"), false);
                }
                InsideTheSystemMod.queueServerWork(80, () -> {
                    if (!world.isClientSide() && world.getServer() != null) {
                        world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u6211\u4eec\u8fd8\u6709\u5f88\u591a\u4e8b\u8981\u505a\uff0c\u670b\u53cb\uff01"), false);
                    }
                });
            });
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).TaskDialogue = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
    }
}

