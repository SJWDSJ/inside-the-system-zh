/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.mcreator.insidethesystem.init.InsideTheSystemModBlocks;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class TunnelConstrictionProcedure {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        TunnelConstrictionProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), (Entity)event.getEntity());
    }

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        TunnelConstrictionProcedure.execute(null, world, x, y, z, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        if (entity.level().dimension() == ResourceKey.create((ResourceKey)Registries.DIMENSION, (ResourceLocation)ResourceLocation.parse((String)"inside_the_system:ending_tunnel"))) {
            ServerLevel _level;
            if (Math.round(entity.getX()) == 12L && InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tunnel == 0.0) {
                if (world instanceof ServerLevel) {
                    _level = (ServerLevel)world;
                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "setblock 3 1 1 inside_the_system:base");
                }
                if (world instanceof ServerLevel) {
                    _level = (ServerLevel)world;
                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "setblock 3 2 1 inside_the_system:base");
                }
                if (world instanceof Level) {
                    _level = (Level)world;
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:tunnelsound")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                    } else {
                        _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:tunnelsound")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                    }
                }
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u4ece\u524d\uff0c\u6709\u4e00\u4e2a\u5c0f\u5973\u5b69\u2026\u2026"), false);
                }
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tunnel += 1.0;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            }
            if (Math.round(entity.getX()) == 4L && InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tunnel == 1.0) {
                world.setBlock(BlockPos.containing((double)10.0, (double)y, (double)z), ((Block)InsideTheSystemModBlocks.BASE.get()).defaultBlockState(), 3);
                world.setBlock(BlockPos.containing((double)10.0, (double)(y + 1.0), (double)z), ((Block)InsideTheSystemModBlocks.BASE.get()).defaultBlockState(), 3);
                if (world instanceof Level) {
                    _level = (Level)world;
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:tunnelsound")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                    } else {
                        _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:tunnelsound")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                    }
                }
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u5979\u559c\u6b22\u8bfb\u4e66\u548c\u5403\u56e2\u5b50\u2026\u2026"), false);
                }
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tunnel += 1.0;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            }
            if (Math.round(entity.getX()) == 9L && InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tunnel == 2.0) {
                world.setBlock(BlockPos.containing((double)5.0, (double)y, (double)z), ((Block)InsideTheSystemModBlocks.BASE.get()).defaultBlockState(), 3);
                world.setBlock(BlockPos.containing((double)5.0, (double)(y + 1.0), (double)z), ((Block)InsideTheSystemModBlocks.BASE.get()).defaultBlockState(), 3);
                if (world instanceof Level) {
                    _level = (Level)world;
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:tunnelsound")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                    } else {
                        _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:tunnelsound")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                    }
                }
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u6bcf\u4e00\u5929\uff0c\u5979\u90fd\u4f1a\u7ffb\u5f00\u5979\u6700\u559c\u6b22\u7684\u5c0f\u738b\u5b50\u7684\u6545\u4e8b\u2026\u2026"), false);
                }
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tunnel += 1.0;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            }
            if (Math.round(entity.getX()) == 6L && InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tunnel == 3.0) {
                world.setBlock(BlockPos.containing((double)9.0, (double)y, (double)z), ((Block)InsideTheSystemModBlocks.BASE.get()).defaultBlockState(), 3);
                world.setBlock(BlockPos.containing((double)9.0, (double)(y + 1.0), (double)z), ((Block)InsideTheSystemModBlocks.BASE.get()).defaultBlockState(), 3);
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u4f46\u5979\u6839\u672c\u6ca1\u6709\u670b\u53cb\u2026\u2026"), false);
                }
                if (world instanceof Level) {
                    _level = (Level)world;
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:tunnelsound")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                    } else {
                        _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:tunnelsound")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                    }
                }
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tunnel += 1.0;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            }
            if (Math.round(entity.getX()) == 8L && InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tunnel == 4.0) {
                world.setBlock(BlockPos.containing((double)6.0, (double)y, (double)z), ((Block)InsideTheSystemModBlocks.BASE.get()).defaultBlockState(), 3);
                world.setBlock(BlockPos.containing((double)6.0, (double)(y + 1.0), (double)z), ((Block)InsideTheSystemModBlocks.BASE.get()).defaultBlockState(), 3);
                if (world instanceof Level) {
                    _level = (Level)world;
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:tunnelsound")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                    } else {
                        _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:tunnelsound")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                    }
                }
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u7136\u540e\u6709\u4e00\u5929\uff0c\u4ed6\u56de\u5230\u4e86\u5979\u7684\u751f\u6d3b\u4e2d\u2026\u2026"), false);
                }
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tunnel += 1.0;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            }
            if (Math.round(entity.getX()) == 8L && InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tunnel == 5.0) {
                world.setBlock(BlockPos.containing((double)8.0, (double)y, (double)z), ((Block)InsideTheSystemModBlocks.BASE.get()).defaultBlockState(), 3);
                world.setBlock(BlockPos.containing((double)8.0, (double)(y + 1.0), (double)z), ((Block)InsideTheSystemModBlocks.BASE.get()).defaultBlockState(), 3);
                if (world instanceof Level) {
                    _level = (Level)world;
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:tunnelsound")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                    } else {
                        _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:tunnelsound")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                    }
                }
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u90a3\u6b21\u56de\u5f52\u6210\u4e86\u5979\u6b7b\u4ea1\u7684\u539f\u56e0\u2026\u2026"), false);
                }
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tunnel += 1.0;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            }
            if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tunnel == 6.0) {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u73b0\u5728\uff0c\u79bb\u5f00\u5427\u2026\u2026\u8fd9\u91cc\u6ca1\u6709\u4f60\u7684\u4f4d\u7f6e\u3002"), false);
                }
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tunnel += 1.0;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Endingf = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            }
        }
    }
}

