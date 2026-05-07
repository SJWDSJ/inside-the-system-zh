/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.level.Level
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
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class BlockDIalogueProcedure {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        BlockDIalogueProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
    }

    public static void execute(LevelAccessor world, double x, double y, double z) {
        BlockDIalogueProcedure.execute(null, world, x, y, z);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).BlockDialogue) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).BlockDialogue = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            if (!world.isClientSide() && world.getServer() != null) {
                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u4f60\u77e5\u9053\u2026\u2026"), false);
            }
            InsideTheSystemMod.queueServerWork(80, () -> {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u6211\u89c9\u5f97\u8fd9\u4e0d\u516c\u5e73\u2026\u2026"), false);
                }
                InsideTheSystemMod.queueServerWork(80, () -> {
                    if (!world.isClientSide() && world.getServer() != null) {
                        world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u4f60\u4e3a\u4ec0\u4e48\u80fd\u7528\u547d\u4ee4\uff1f"), false);
                    }
                    InsideTheSystemMod.queueServerWork(80, () -> {
                        if (!world.isClientSide() && world.getServer() != null) {
                            world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u8ba9\u6211\u4eec\u628a\u5b83\u4eec\u5173\u6389\u2026\u2026"), false);
                        }
                        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).BlockCommands = true;
                        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                        InsideTheSystemMod.queueServerWork(80, () -> {
                            if (world instanceof Level) {
                                Level _level = (Level)world;
                                if (!_level.isClientSide()) {
                                    _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:smile")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                                } else {
                                    _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:smile")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                                }
                            }
                            if (!world.isClientSide() && world.getServer() != null) {
                                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u00a7c:)"), false);
                            }
                        });
                    });
                });
            });
        }
    }
}

