/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.mcreator.insidethesystem.InsideTheSystemMod;
import net.mcreator.insidethesystem.init.InsideTheSystemModEntities;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class Build3Procedure {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Build3Procedure.execute((Event)event, (LevelAccessor)event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), (Entity)event.getEntity());
    }

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        Build3Procedure.execute(null, world, x, y, z, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).story2) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).story2 = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            InsideTheSystemMod.queueServerWork(20, () -> {
                Level _level;
                if (world instanceof Level) {
                    _level = (Level)world;
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                    } else {
                        _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                    }
                }
                if (world instanceof ServerLevel) {
                    _level = (ServerLevel)world;
                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, (ServerLevel)_level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/setblock 7 10 55 inside_the_system:base");
                }
                if (world instanceof ServerLevel) {
                    _level = (ServerLevel)world;
                    _level.sendParticles((ParticleOptions)ParticleTypes.SOUL, x, y, z, 5, 7.0, 10.0, 22.0, 1.0);
                }
                InsideTheSystemMod.queueServerWork(20, () -> {
                    Level _level;
                    if (world instanceof Level) {
                        _level = (Level)world;
                        if (!_level.isClientSide()) {
                            _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                        } else {
                            _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                        }
                    }
                    if (world instanceof ServerLevel) {
                        _level = (ServerLevel)world;
                        _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, (ServerLevel)_level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/setblock 7 10 56 inside_the_system:base");
                    }
                    if (world instanceof ServerLevel) {
                        _level = (ServerLevel)world;
                        _level.sendParticles((ParticleOptions)ParticleTypes.SOUL, x, y, z, 5, 7.0, 10.0, 23.0, 1.0);
                    }
                    InsideTheSystemMod.queueServerWork(20, () -> {
                        Level _level;
                        if (world instanceof Level) {
                            _level = (Level)world;
                            if (!_level.isClientSide()) {
                                _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                            } else {
                                _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                            }
                        }
                        if (world instanceof ServerLevel) {
                            _level = (ServerLevel)world;
                            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, (ServerLevel)_level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/setblock 7 10 57 inside_the_system:base");
                        }
                        if (world instanceof ServerLevel) {
                            _level = (ServerLevel)world;
                            _level.sendParticles((ParticleOptions)ParticleTypes.SOUL, x, y, z, 5, 7.0, 10.0, 24.0, 1.0);
                        }
                        InsideTheSystemMod.queueServerWork(20, () -> {
                            Level _level;
                            if (world instanceof Level) {
                                _level = (Level)world;
                                if (!_level.isClientSide()) {
                                    _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                                } else {
                                    _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                                }
                            }
                            if (world instanceof ServerLevel) {
                                _level = (ServerLevel)world;
                                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, (ServerLevel)_level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/setblock 7 10 58 inside_the_system:base");
                            }
                            if (world instanceof ServerLevel) {
                                _level = (ServerLevel)world;
                                _level.sendParticles((ParticleOptions)ParticleTypes.SOUL, x, y, z, 5, 7.0, 10.0, 25.0, 1.0);
                            }
                            InsideTheSystemMod.queueServerWork(20, () -> {
                                Level _level;
                                if (world instanceof Level) {
                                    _level = (Level)world;
                                    if (!_level.isClientSide()) {
                                        _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                                    } else {
                                        _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                                    }
                                }
                                if (world instanceof ServerLevel) {
                                    _level = (ServerLevel)world;
                                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, (ServerLevel)_level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/setblock 7 10 59 inside_the_system:base");
                                }
                                if (world instanceof ServerLevel) {
                                    _level = (ServerLevel)world;
                                    _level.sendParticles((ParticleOptions)ParticleTypes.SOUL, x, y, z, 5, 7.0, 10.0, 26.0, 1.0);
                                }
                                InsideTheSystemMod.queueServerWork(20, () -> {
                                    Level _level;
                                    if (world instanceof Level) {
                                        _level = (Level)world;
                                        if (!_level.isClientSide()) {
                                            _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                                        } else {
                                            _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                                        }
                                    }
                                    if (world instanceof ServerLevel) {
                                        _level = (ServerLevel)world;
                                        _level.sendParticles((ParticleOptions)ParticleTypes.SOUL, x, y, z, 5, 7.0, 10.0, 27.0, 1.0);
                                    }
                                    if (world instanceof ServerLevel) {
                                        _level = (ServerLevel)world;
                                        _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, (ServerLevel)_level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/setblock 7 10 60 inside_the_system:base");
                                    }
                                    InsideTheSystemMod.queueServerWork(20, () -> {
                                        Level _level;
                                        if (world instanceof Level) {
                                            _level = (Level)world;
                                            if (!_level.isClientSide()) {
                                                _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                                            } else {
                                                _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                                            }
                                        }
                                        if (world instanceof ServerLevel) {
                                            _level = (ServerLevel)world;
                                            _level.sendParticles((ParticleOptions)ParticleTypes.SQUID_INK, x, y, z, 5, 7.0, 10.0, 28.0, 1.0);
                                        }
                                        if (world instanceof ServerLevel) {
                                            _level = (ServerLevel)world;
                                            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, (ServerLevel)_level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/setblock 7 10 61 inside_the_system:base");
                                        }
                                        InsideTheSystemMod.queueServerWork(20, () -> {
                                            Level _level;
                                            if (world instanceof Level) {
                                                _level = (Level)world;
                                                if (!_level.isClientSide()) {
                                                    _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                                                } else {
                                                    _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                                                }
                                            }
                                            if (world instanceof ServerLevel) {
                                                _level = (ServerLevel)world;
                                                _level.sendParticles((ParticleOptions)ParticleTypes.SQUID_INK, x, y, z, 5, 7.0, 10.0, 28.0, 1.0);
                                            }
                                            if (world instanceof ServerLevel) {
                                                _level = (ServerLevel)world;
                                                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, (ServerLevel)_level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/setblock 7 10 62 inside_the_system:base");
                                            }
                                            InsideTheSystemMod.queueServerWork(20, () -> {
                                                Level _level;
                                                if (world instanceof Level) {
                                                    _level = (Level)world;
                                                    if (!_level.isClientSide()) {
                                                        _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                                                    } else {
                                                        _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                                                    }
                                                }
                                                if (world instanceof ServerLevel) {
                                                    _level = (ServerLevel)world;
                                                    _level.sendParticles((ParticleOptions)ParticleTypes.SQUID_INK, x, y, z, 5, 7.0, 10.0, 28.0, 1.0);
                                                }
                                                if (world instanceof ServerLevel) {
                                                    _level = (ServerLevel)world;
                                                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, (ServerLevel)_level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/setblock 7 10 63 inside_the_system:base");
                                                }
                                                InsideTheSystemMod.queueServerWork(20, () -> {
                                                    Level _level;
                                                    if (world instanceof Level) {
                                                        _level = (Level)world;
                                                        if (!_level.isClientSide()) {
                                                            _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                                                        } else {
                                                            _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                                                        }
                                                    }
                                                    if (world instanceof ServerLevel) {
                                                        _level = (ServerLevel)world;
                                                        _level.sendParticles((ParticleOptions)ParticleTypes.SQUID_INK, x, y, z, 5, 7.0, 10.0, 28.0, 1.0);
                                                    }
                                                    if (world instanceof ServerLevel) {
                                                        _level = (ServerLevel)world;
                                                        _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, (ServerLevel)_level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/setblock 7 10 64 inside_the_system:base");
                                                    }
                                                    InsideTheSystemMod.queueServerWork(20, () -> {
                                                        ServerLevel _serverworld;
                                                        StructureTemplate template;
                                                        if (world instanceof Level) {
                                                            Level _level = (Level)world;
                                                            if (!_level.isClientSide()) {
                                                                _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                                                            } else {
                                                                _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"block.amethyst_block.step")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                                                            }
                                                        }
                                                        if (world instanceof ServerLevel && (template = (_serverworld = (ServerLevel)world).getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"story3"))) != null) {
                                                            template.placeInWorld((ServerLevelAccessor)_serverworld, new BlockPos(5, 10, 65), new BlockPos(5, 10, 65), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random, 3);
                                                        }
                                                        InsideTheSystemMod.queueServerWork(30, () -> {
                                                            LivingEntity _entity;
                                                            if (!world.isClientSide() && world.getServer() != null) {
                                                                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u5c31\u8fd9\u6837\u2026\u2026\u5979\u6765\u5230\u4e86\u96a7\u9053\u524d\u3002\u4e00\u80a1\u8150\u81ed\u7684\u6c14\u5473\u4ece\u91cc\u9762\u98d8\u6765\uff0c\u8bc9\u8bf4\u7740\u88ab\u9057\u5f03\u5df2\u4e45\u7684\u5f80\u4e8b\u3002\u4e00\u4e1d\u6050\u60e7\u7b3c\u7f69\u7740\u5973\u5b69"), false);
                                                            }
                                                            if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).level().isClientSide()) {
                                                                _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 9999, 3, false, false));
                                                            }
                                                            if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).level().isClientSide()) {
                                                                _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 9999, 3, false, false));
                                                            }
                                                            InsideTheSystemMod.queueServerWork(100, () -> {
                                                                if (!world.isClientSide() && world.getServer() != null) {
                                                                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u4ed6\u4e3a\u4ec0\u4e48\u6ca1\u6709\u5728\u5916\u9762\u63a5\u6211\uff1f\u4ed6\u4e3a\u4ec0\u4e48\u4e0d\u60f3\u8ddf\u6211\u8d70\uff1f\u2014\u2014\u8fd9\u6837\u7684\u60f3\u6cd5\u6d8c\u5165\u4e86\u5979\u7684\u8111\u6d77"), false);
                                                                }
                                                                InsideTheSystemMod.queueServerWork(100, () -> {
                                                                    LivingEntity _entity;
                                                                    if (!world.isClientSide() && world.getServer() != null) {
                                                                        world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u5979\u5411\u524d\u8d70\u53bb\uff0c\u80f8\u53e3\u7684\u4e00\u5207\u90fd\u7ef7\u7d27\u4e86"), false);
                                                                    }
                                                                    if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).level().isClientSide()) {
                                                                        _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 9999, 4, false, false));
                                                                    }
                                                                    InsideTheSystemMod.queueServerWork(100, () -> {
                                                                        LivingEntity _entity;
                                                                        if (!world.isClientSide() && world.getServer() != null) {
                                                                            world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u5979\u7684\u8eab\u4f53\u5728\u98a4\u6296\u2026\u2026"), false);
                                                                        }
                                                                        if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).level().isClientSide()) {
                                                                            _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 9999, 5, false, false));
                                                                        }
                                                                        InsideTheSystemMod.queueServerWork(100, () -> {
                                                                            LivingEntity _entity;
                                                                            if (!world.isClientSide() && world.getServer() != null) {
                                                                                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u7136\u800c\uff0c\u5c3d\u7ba1\u6050\u60e7\u4fb5\u8680\u7740\u5979\uff0c\u5973\u5b69\u8fd8\u662f\u7ee7\u7eed\u524d\u884c"), false);
                                                                            }
                                                                            if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).level().isClientSide()) {
                                                                                _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 9999, 5, false, false));
                                                                            }
                                                                            InsideTheSystemMod.queueServerWork(100, () -> {
                                                                                ServerLevel _level;
                                                                                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).story3 = true;
                                                                                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                                                                                if (world instanceof ServerLevel) {
                                                                                    _level = (ServerLevel)world;
                                                                                    Entity entityToSpawn = ((EntityType)InsideTheSystemModEntities.FATHER.get()).spawn(_level, BlockPos.containing((double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX, (double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY, (double)(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ - 4.0)), MobSpawnType.MOB_SUMMONED);
                                                                                    if (entityToSpawn != null) {
                                                                                        entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0f);
                                                                                    }
                                                                                }
                                                                                if (world instanceof ServerLevel) {
                                                                                    _level = (ServerLevel)world;
                                                                                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/setblock ~ ~ ~ light[level=3]");
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
                    });
                });
            });
        }
    }
}

