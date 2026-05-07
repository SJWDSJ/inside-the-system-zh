/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.network.chat.Component
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.entity.living.LivingDeathEvent
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.mcreator.insidethesystem.InsideTheSystemMod;
import net.mcreator.insidethesystem.entity.CoolPlayer303Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class DieForPlayerProcedure {
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event.getEntity() != null) {
            DieForPlayerProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level(), (Entity)event.getEntity(), event.getSource().getEntity());
        }
    }

    public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
        DieForPlayerProcedure.execute(null, world, entity, sourceentity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
        if (entity == null || sourceentity == null) {
            return;
        }
        if (entity instanceof CoolPlayer303Entity && sourceentity instanceof Player) {
            if (Mth.nextInt((RandomSource)RandomSource.create(), (int)1, (int)5) == 1) {
                InsideTheSystemMod.queueServerWork(40, () -> {
                    if (!world.isClientSide() && world.getServer() != null) {
                        world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u563f\uff0c\u5144\u5f1f\uff0c\u4f60\u4e3a\u4ec0\u4e48\u8981\u8fd9\u6837\u505a\uff1f"), false);
                    }
                });
            } else if (Mth.nextInt((RandomSource)RandomSource.create(), (int)1, (int)4) == 1) {
                InsideTheSystemMod.queueServerWork(40, () -> {
                    if (!world.isClientSide() && world.getServer() != null) {
                        world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u6211\u4e0d\u559c\u6b22\u4f60\u6740\u6211"), false);
                    }
                });
            } else if (Mth.nextInt((RandomSource)RandomSource.create(), (int)1, (int)3) == 1) {
                InsideTheSystemMod.queueServerWork(40, () -> {
                    if (!world.isClientSide() && world.getServer() != null) {
                        world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u4f4f\u624b\uff01\u6211\u662f\u4f60\u7684\u670b\u53cb"), false);
                    }
                });
            } else if (Mth.nextInt((RandomSource)RandomSource.create(), (int)1, (int)2) == 1) {
                InsideTheSystemMod.queueServerWork(40, () -> {
                    if (!world.isClientSide() && world.getServer() != null) {
                        world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u4f60\u4e00\u76f4\u6740\u6211\u8ba9\u6211\u5f88\u70e6"), false);
                    }
                });
            } else if (Mth.nextInt((RandomSource)RandomSource.create(), (int)1, (int)1) == 1) {
                InsideTheSystemMod.queueServerWork(40, () -> {
                    if (!world.isClientSide() && world.getServer() != null) {
                        world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u4f60\u60f3\u8ba9\u6211\u751f\u6c14\u5417\uff1f"), false);
                    }
                });
            }
        }
    }
}

