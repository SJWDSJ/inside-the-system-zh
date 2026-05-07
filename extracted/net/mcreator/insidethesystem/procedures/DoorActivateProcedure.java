/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.core.registries.Registries
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
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.InsideTheSystemMod;
import net.mcreator.insidethesystem.init.InsideTheSystemModItems;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class DoorActivateProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).endinga) {
            world.setBlock(BlockPos.containing((double)x, (double)y, (double)z), Blocks.AIR.defaultBlockState(), 3);
            world.setBlock(BlockPos.containing((double)(x + 1.0), (double)y, (double)z), Blocks.AIR.defaultBlockState(), 3);
            world.setBlock(BlockPos.containing((double)(x - 1.0), (double)y, (double)z), Blocks.AIR.defaultBlockState(), 3);
            world.setBlock(BlockPos.containing((double)x, (double)y, (double)(z - 1.0)), Blocks.AIR.defaultBlockState(), 3);
            world.setBlock(BlockPos.containing((double)x, (double)y, (double)(z + 1.0)), Blocks.AIR.defaultBlockState(), 3);
            if (world instanceof Level) {
                Level _level = (Level)world;
                if (!_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:titles")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                } else {
                    _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:titles")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                }
            }
            InsideTheSystemMod.queueServerWork(10, () -> {
                LivingEntity _entity;
                if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).level().isClientSide()) {
                    _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 9999, 20, false, false));
                }
                if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).level().isClientSide()) {
                    _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 9999, 20, false, false));
                }
                InsideTheSystemMod.queueServerWork(80, () -> {
                    if (!world.isClientSide() && world.getServer() != null) {
                        world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<\u5988\u5988> \u554a\u2026\u2026Aiko\u2026\u2026\u662f\u771f\u7684\u5417\u2026\u2026\uff1f\u4e00\u76f4\u4ee5\u6765\u2026\u2026\u5e55\u540e\u9ed1\u624b\u90fd\u662f\u4ed6\u2026\u2026\uff1f"), false);
                    }
                    InsideTheSystemMod.queueServerWork(80, () -> {
                        if (!world.isClientSide() && world.getServer() != null) {
                            world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<\u5988\u5988> \u6211\u6709\u591a\u778e\u554a\u2026\u2026\u6211\u600e\u4e48\u80fd\u8ba9\u8fd9\u79cd\u4e8b\u5c31\u53d1\u751f\u5728\u6211\u773c\u524d\u2026\u2026\uff01"), false);
                        }
                        InsideTheSystemMod.queueServerWork(80, () -> {
                            if (!world.isClientSide() && world.getServer() != null) {
                                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<\u5988\u5988> \u4e3a\u4ec0\u4e48\u2026\u2026\u4e3a\u4ec0\u4e48\u6211\u6ca1\u6709\u4fdd\u62a4\u5979\u2026\u2026\u4e3a\u4ec0\u4e48\u6211\u6ca1\u6709\u6551\u6211\u81ea\u5df1\u7684\u5b69\u5b50\u2026\u2026\uff01"), false);
                            }
                            InsideTheSystemMod.queueServerWork(600, () -> {
                                ServerLevel _level;
                                if (world instanceof ServerLevel) {
                                    _level = (ServerLevel)world;
                                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
                                }
                                if (world instanceof ServerLevel) {
                                    _level = (ServerLevel)world;
                                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a subtitle {\"text\":\"\u7ed3\u5c40\",\"italic\":true,\"color\":\"#DADADA\"}");
                                }
                                if (world instanceof ServerLevel) {
                                    _level = (ServerLevel)world;
                                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"\u7ed3\u5c40A\"}");
                                }
                                InsideTheSystemMod.queueServerWork(100, () -> {
                                    ServerLevel _level;
                                    if (world instanceof ServerLevel) {
                                        _level = (ServerLevel)world;
                                        _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
                                    }
                                    if (world instanceof ServerLevel) {
                                        _level = (ServerLevel)world;
                                        _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"\u6a21\u7ec4\u4f5c\u8005 DregIr\"}");
                                    }
                                    InsideTheSystemMod.queueServerWork(100, () -> {
                                        ServerLevel _level;
                                        if (world instanceof ServerLevel) {
                                            _level = (ServerLevel)world;
                                            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
                                        }
                                        if (world instanceof ServerLevel) {
                                            _level = (ServerLevel)world;
                                            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"\u6f2b\u957f\u7684\u4e24\u4e2a\u6708\u2026\u2026\"}");
                                        }
                                        InsideTheSystemMod.queueServerWork(100, () -> {
                                            ServerLevel _level;
                                            if (world instanceof ServerLevel) {
                                                _level = (ServerLevel)world;
                                                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
                                            }
                                            if (world instanceof ServerLevel) {
                                                _level = (ServerLevel)world;
                                                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"\u7279\u522b\u611f\u8c22\uff1a\"}");
                                            }
                                            InsideTheSystemMod.queueServerWork(100, () -> {
                                                ServerLevel _level;
                                                if (world instanceof ServerLevel) {
                                                    _level = (ServerLevel)world;
                                                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
                                                }
                                                if (world instanceof ServerLevel) {
                                                    _level = (ServerLevel)world;
                                                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"NeoKefus\"}");
                                                }
                                                InsideTheSystemMod.queueServerWork(100, () -> {
                                                    ServerLevel _level;
                                                    if (world instanceof ServerLevel) {
                                                        _level = (ServerLevel)world;
                                                        _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
                                                    }
                                                    if (world instanceof ServerLevel) {
                                                        _level = (ServerLevel)world;
                                                        _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"Itszhaba\"}");
                                                    }
                                                    InsideTheSystemMod.queueServerWork(100, () -> {
                                                        ServerLevel _level;
                                                        if (world instanceof ServerLevel) {
                                                            _level = (ServerLevel)world;
                                                            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
                                                        }
                                                        if (world instanceof ServerLevel) {
                                                            _level = (ServerLevel)world;
                                                            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"Martshet\"}");
                                                        }
                                                        InsideTheSystemMod.queueServerWork(100, () -> {
                                                            ServerLevel _level;
                                                            if (world instanceof ServerLevel) {
                                                                _level = (ServerLevel)world;
                                                                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
                                                            }
                                                            if (world instanceof ServerLevel) {
                                                                _level = (ServerLevel)world;
                                                                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"\u4ee5\u53ca\u6240\u6709\u521b\u4f5c\u8005\"}");
                                                            }
                                                            InsideTheSystemMod.queueServerWork(100, () -> {
                                                                ServerLevel _level;
                                                                if (world instanceof ServerLevel) {
                                                                    _level = (ServerLevel)world;
                                                                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
                                                                }
                                                                if (world instanceof ServerLevel) {
                                                                    _level = (ServerLevel)world;
                                                                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"Especially Vix\"}");
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
                                                                });
                                                            });
                                                        });
                                                    });
                                                });
                                            });
                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            });
        } else {
            world.destroyBlock(BlockPos.containing((double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX, (double)(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY - 1.0), (double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ), false);
            world.destroyBlock(BlockPos.containing((double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX, (double)(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY - 2.0), (double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ), false);
            world.destroyBlock(BlockPos.containing((double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX, (double)(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY - 3.0), (double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ), false);
            if (entity instanceof LivingEntity) {
                LivingEntity _entity = (LivingEntity)entity;
                _entity.removeEffect(MobEffects.SLOW_FALLING);
            }
            InsideTheSystemMod.queueServerWork(40, () -> {
                Player _playerHasItem;
                if (entity instanceof Player && (_playerHasItem = (Player)entity).getInventory().contains(new ItemStack((ItemLike)InsideTheSystemModItems.STAROF_MEMORIES.get()))) {
                    ServerPlayer _player;
                    if (!world.isClientSide() && world.getServer() != null) {
                        world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u6545\u4e8b\u73b0\u5728\u5f00\u59cb\u2026\u2026"), false);
                    }
                    InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).endinga = true;
                    InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                    if (entity instanceof ServerPlayer && !(_player = (ServerPlayer)entity).level().isClientSide()) {
                        ResourceKey destinationType = ResourceKey.create((ResourceKey)Registries.DIMENSION, (ResourceLocation)ResourceLocation.parse((String)"inside_the_system:home"));
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
                } else {
                    ServerPlayer _player;
                    if (!world.isClientSide() && world.getServer() != null) {
                        world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u8fd9\u5c31\u662f\u6545\u4e8b\u7684\u7ed3\u5c40\u2026\u2026"), false);
                    }
                    if (entity instanceof ServerPlayer && !(_player = (ServerPlayer)entity).level().isClientSide()) {
                        ResourceKey destinationType = ResourceKey.create((ResourceKey)Registries.DIMENSION, (ResourceLocation)ResourceLocation.parse((String)"inside_the_system:home"));
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
                }
            });
        }
    }
}

