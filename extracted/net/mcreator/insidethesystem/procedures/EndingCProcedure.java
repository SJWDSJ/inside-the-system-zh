/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundGameEventPacket
 *  net.minecraft.network.protocol.game.ClientboundLevelEventPacket
 *  net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket
 *  net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.InsideTheSystemMod;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class EndingCProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity sourceentity) {
        ServerLevel _level;
        LivingEntity _entity;
        if (sourceentity == null) {
            return;
        }
        if (sourceentity instanceof LivingEntity && !(_entity = (LivingEntity)sourceentity).level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 300, 20, false, false));
        }
        if (sourceentity instanceof LivingEntity && !(_entity = (LivingEntity)sourceentity).level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 20, false, false));
        }
        if (world instanceof ServerLevel) {
            _level = (ServerLevel)world;
            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/stopsound");
        }
        if (world instanceof Level) {
            _level = (Level)world;
            if (!_level.isClientSide()) {
                _level.playSound(null, BlockPos.containing((double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX, (double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY, (double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:endingc")), SoundSource.NEUTRAL, 1.0f, 1.0f);
            } else {
                _level.playLocalSound(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:endingc")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
            }
        }
        InsideTheSystemMod.queueServerWork(100, () -> {
            ServerLevel _level;
            if (world instanceof ServerLevel) {
                _level = (ServerLevel)world;
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
            }
            if (world instanceof ServerLevel) {
                _level = (ServerLevel)world;
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a subtitle {\"text\":\"\u6211\u7684\u590d\u4ec7\u2026\u2026\",\"italic\":true,\"color\":\"#DADADA\"}");
            }
            if (world instanceof ServerLevel) {
                _level = (ServerLevel)world;
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"\u7ed3\u5c40C\"}");
            }
            InsideTheSystemMod.queueServerWork(100, () -> {
                ServerLevel _level;
                if (world instanceof ServerLevel) {
                    _level = (ServerLevel)world;
                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
                }
                if (world instanceof ServerLevel) {
                    _level = (ServerLevel)world;
                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"\u611f\u8c22\u6e38\u73a9\"}");
                }
                InsideTheSystemMod.queueServerWork(100, () -> {
                    ServerLevel _level;
                    if (world instanceof ServerLevel) {
                        _level = (ServerLevel)world;
                        _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
                    }
                    if (world instanceof ServerLevel) {
                        _level = (ServerLevel)world;
                        _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"\u4f46\u662f\u2026\u2026\"}");
                    }
                    InsideTheSystemMod.queueServerWork(100, () -> {
                        ServerLevel _level;
                        if (world instanceof ServerLevel) {
                            _level = (ServerLevel)world;
                            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
                        }
                        if (world instanceof ServerLevel) {
                            _level = (ServerLevel)world;
                            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"\u80fd\u6539\u53d8\u5417\uff1f\"}");
                        }
                        InsideTheSystemMod.queueServerWork(260, () -> {
                            ServerPlayer _player;
                            if (sourceentity instanceof ServerPlayer && !(_player = (ServerPlayer)sourceentity).level().isClientSide()) {
                                ResourceKey destinationType = Level.OVERWORLD;
                                if (_player.level().dimension() == destinationType) {
                                    return;
                                }
                                ServerLevel nextLevel = _player.server.getLevel(destinationType);
                                if (nextLevel != null) {
                                    _player.connection.send((Packet)new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0.0f));
                                    _player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
                                    _player.connection.send((Packet)new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
                                    for (MobEffectInstance _effectinstance : _player.getActiveEffects()) {
                                        _player.connection.send((Packet)new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance, false));
                                    }
                                    _player.connection.send((Packet)new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
                                }
                            }
                        });
                    });
                });
            });
        });
    }
}

