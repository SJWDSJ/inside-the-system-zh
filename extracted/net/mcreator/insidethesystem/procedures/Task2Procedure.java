/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.arguments.EntityAnchorArgument$Anchor
 *  net.minecraft.core.BlockPos
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
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
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
import net.mcreator.insidethesystem.entity.Father2Entity;
import net.mcreator.insidethesystem.init.InsideTheSystemModEntities;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class Task2Procedure {
    private static volatile boolean dialogTriggered = false;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Task2Procedure.execute((Event)event, (LevelAccessor)event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), (Entity)event.getEntity());
    }

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        Task2Procedure.execute(null, world, x, y, z, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task2 && !dialogTriggered) {
            dialogTriggered = true;
            if (!world.isClientSide() && world.getServer() != null) {
                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)";*?)%?"), false);
            }
            InsideTheSystemMod.queueServerWork(20, () -> {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u4ed6\u593a\u53d6\u4e86\u63a7\u5236\u6743"), false);
                }
                InsideTheSystemMod.queueServerWork(20, () -> {
                    Level _level;
                    if (world instanceof Level) {
                        _level = (Level)world;
                        if (!_level.isClientSide()) {
                            _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:thesun")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                        } else {
                            _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:thesun")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                        }
                    }
                    if (world instanceof ServerLevel) {
                        _level = (ServerLevel)world;
                        _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, (ServerLevel)_level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "time set night");
                    }
                    InsideTheSystemMod.queueServerWork(50, () -> {
                        if (world instanceof ServerLevel) {
                            ServerLevel _level = (ServerLevel)world;
                            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "time set day");
                        }
                        InsideTheSystemMod.queueServerWork(45, () -> {
                            if (world instanceof ServerLevel) {
                                ServerLevel _level = (ServerLevel)world;
                                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "time set night");
                            }
                            InsideTheSystemMod.queueServerWork(40, () -> {
                                if (world instanceof ServerLevel) {
                                    ServerLevel _level = (ServerLevel)world;
                                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "time set day");
                                }
                                InsideTheSystemMod.queueServerWork(35, () -> {
                                    if (world instanceof ServerLevel) {
                                        ServerLevel _level = (ServerLevel)world;
                                        _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "time set night");
                                    }
                                    InsideTheSystemMod.queueServerWork(30, () -> {
                                        if (world instanceof ServerLevel) {
                                            ServerLevel _level = (ServerLevel)world;
                                            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "time set day");
                                        }
                                        InsideTheSystemMod.queueServerWork(25, () -> {
                                            if (world instanceof ServerLevel) {
                                                ServerLevel _level = (ServerLevel)world;
                                                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "time set night");
                                            }
                                            InsideTheSystemMod.queueServerWork(20, () -> {
                                                if (world instanceof ServerLevel) {
                                                    ServerLevel _level = (ServerLevel)world;
                                                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "time set day");
                                                }
                                                InsideTheSystemMod.queueServerWork(15, () -> {
                                                    if (world instanceof ServerLevel) {
                                                        ServerLevel _level = (ServerLevel)world;
                                                        _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "time set night");
                                                    }
                                                    InsideTheSystemMod.queueServerWork(10, () -> {
                                                        if (world instanceof ServerLevel) {
                                                            ServerLevel _level = (ServerLevel)world;
                                                            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "time set day");
                                                        }
                                                        InsideTheSystemMod.queueServerWork(5, () -> {
                                                            LivingEntity _entity;
                                                            ServerLevel _level;
                                                            if (world instanceof ServerLevel) {
                                                                _level = (ServerLevel)world;
                                                                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "time set night");
                                                            }
                                                            if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).level().isClientSide()) {
                                                                _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 2000, 3, false, false));
                                                            }
                                                            if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).level().isClientSide()) {
                                                                _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2000, 3, false, false));
                                                            }
                                                            if (world instanceof ServerLevel) {
                                                                _level = (ServerLevel)world;
                                                                Father2Entity entityToSpawn = new Father2Entity((EntityType<Father2Entity>)((EntityType)InsideTheSystemModEntities.FATHER_2.get()), (Level)_level);
                                                                entityToSpawn.moveTo(x, y, z + 2.0, world.getRandom().nextFloat() * 360.0f, 0.0f);
                                                                if (entityToSpawn instanceof Mob) {
                                                                    Mob _mobToSpawn = (Mob)entityToSpawn;
                                                                    _mobToSpawn.finalizeSpawn((ServerLevelAccessor)_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null);
                                                                }
                                                                world.addFreshEntity((Entity)entityToSpawn);
                                                            }
                                                            entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(x, y + 1.0, z + 2.0));
                                                            InsideTheSystemMod.queueServerWork(20, () -> {
                                                                if (!world.isClientSide() && world.getServer() != null) {
                                                                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<\u00a7k:#\u2116;%;?\u00a7r> \u54ce\u5440\u5440\u2026\u2026\u4f60\u597d\u554a\u2026\u2026"), false);
                                                                }
                                                                InsideTheSystemMod.queueServerWork(80, () -> {
                                                                    if (!world.isClientSide() && world.getServer() != null) {
                                                                        world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<\u00a7k:#\u2116;%;?\u00a7r> \u6211\u770b\u4f60\u7b2c\u4e8c\u4e2a\u4efb\u52a1\u4e5f\u5931\u8d25\u4e86\uff1f"), false);
                                                                    }
                                                                    InsideTheSystemMod.queueServerWork(80, () -> {
                                                                        if (!world.isClientSide() && world.getServer() != null) {
                                                                            world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<\u00a7k:#\u2116;%;?\u00a7r> \u8c22\u8c22\u4f60\u2026\u2026\u5979\u4e00\u76f4\u5728\u4e00\u7247\u4e00\u7247\u5730\u5931\u53bb\u8bb0\u5fc6\u2026\u2026\u8fd9\u8ba9\u6211\u80fd\u66f4\u6df1\u5165\u5730\u6e17\u5165\u8fd9\u4e2a\u4e16\u754c\u3002"), false);
                                                                        }
                                                                        InsideTheSystemMod.queueServerWork(80, () -> {
                                                                            if (!world.isClientSide() && world.getServer() != null) {
                                                                                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<\u00a7k:#\u2116;%;?\u00a7r> \u6211\u6709\u4e2a\u63d0\u8bae\u7ed9\u4f60\u2026\u2026"), false);
                                                                            }
                                                                            InsideTheSystemMod.queueServerWork(80, () -> {
                                                                                if (!world.isClientSide() && world.getServer() != null) {
                                                                                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<\u00a7k:#\u2116;%;?\u00a7r> \u6bcf\u4e2a\u4efb\u52a1\u90fd\u5931\u8d25\u2026\u2026\u8ba9\u5979\u5fd8\u8bb0\u81ea\u5df1\u5230\u5e95\u662f\u8c01\u3002"), false);
                                                                                }
                                                                                InsideTheSystemMod.queueServerWork(80, () -> {
                                                                                    if (!world.isClientSide() && world.getServer() != null) {
                                                                                        world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<\u00a7k:#\u2116;%;?\u00a7r> \u7136\u540e\u2026\u2026"), false);
                                                                                    }
                                                                                    InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).OBS = true;
                                                                                    InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                                                                                });
                                                                            });
                                                                        });
                                                                    });
                                                                });
                                                            });
                                                            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task2 = false;
                                                            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                                                            dialogTriggered = false;
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

