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
public class TaskkkProcedure {
    private static boolean isDialogueScheduled = false;
    private static final int INITIAL_DELAY = 1900;
    private static final int MESSAGE_INTERVAL = 70;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        TaskkkProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level());
    }

    public static void execute(LevelAccessor world) {
        TaskkkProcedure.execute(null, world);
    }

    private static void execute(@Nullable Event event, LevelAccessor world) {
        InsideTheSystemModVariables.MapVariables mapVars = InsideTheSystemModVariables.MapVariables.get(world);
        if (mapVars.Taskkk && !isDialogueScheduled) {
            isDialogueScheduled = true;
            InsideTheSystemMod.queueServerWork(1900, () -> {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u4f60\u77e5\u9053\u5417\u2026\u2026\u6211\u4eec\u5728\u4e00\u8d77\u8fd9\u4e48\u4e45\u4e86\u2026\u2026"), false);
                }
            });
            InsideTheSystemMod.queueServerWork(1970, () -> {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u8bf4\u5b9e\u8bdd\uff0c\u5468\u56f4\u7684\u4e00\u5207\u90fd\u611f\u89c9\u2026\u2026\u51e0\u4e4e\u662f\u4e0d\u771f\u5b9e\u7684"), false);
                }
            });
            InsideTheSystemMod.queueServerWork(2040, () -> {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u6211\u4e0d\u77e5\u9053\u4e3a\u4ec0\u4e48\u2026\u2026"), false);
                }
            });
            InsideTheSystemMod.queueServerWork(2110, () -> {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u4f60\u624b\u91cc\u7684\u6b66\u5668\u2026\u2026\u611f\u89c9\u4e0d\u662f\u7528\u6765\u5bf9\u4ed8\u6211\u7684\u3002\u5c31\u50cf\u2026\u2026\u5b83\u4f24\u4e0d\u4e86\u6211"), false);
                }
            });
            InsideTheSystemMod.queueServerWork(2180, () -> {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u5728\u6211\u7684\u8bb0\u5fc6\u91cc\u2026\u2026\u6709\u4e00\u628a\u5200\u3002\u662f\u7684\u2026\u2026\u4e00\u628a\u5200\u3002\u5b83\u80fd\u8ba9\u4eba\u6d41\u8840\u2026\u2026"), false);
                }
            });
            InsideTheSystemMod.queueServerWork(2250, () -> {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u6211\u60f3\u77e5\u9053\u6211\u662f\u5426\u771f\u7684\u5b58\u5728"), false);
                }
            });
            InsideTheSystemMod.queueServerWork(2320, () -> {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u6c42\u4f60\u4e86\u2026\u2026\u505a\u4e00\u628a\u5200\u8bd5\u8bd5\u770b\u2026\u2026"), false);
                }
                isDialogueScheduled = false;
                InsideTheSystemModVariables.MapVariables finalMapVars = InsideTheSystemModVariables.MapVariables.get(world);
                finalMapVars.Taskkk = false;
                finalMapVars.syncData(world);
            });
        }
    }
}

