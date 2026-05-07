/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.components.EditBox
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 */
package net.mcreator.insidethesystem.procedures;

import java.util.HashMap;
import net.mcreator.insidethesystem.InsideTheSystemMod;
import net.mcreator.insidethesystem.init.InsideTheSystemModEntities;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class PasswordProProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, HashMap guistate) {
        if (guistate == null) {
            return;
        }
        if ("\u75af\u72c2\u7684\u7236\u4eb2".equals(guistate.containsKey("text:Password") ? ((EditBox)guistate.get("text:Password")).getValue() : "")) {
            Level _level;
            if (world instanceof Level) {
                _level = (Level)world;
                if (!_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:accept")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                } else {
                    _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:accept")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                }
            }
            if (world instanceof ServerLevel) {
                _level = (ServerLevel)world;
                Entity entityToSpawn = ((EntityType)InsideTheSystemModEntities.AYANAMI_AIKO.get()).spawn((ServerLevel)_level, new BlockPos(3, 1, 10), MobSpawnType.MOB_SUMMONED);
                if (entityToSpawn != null) {
                    entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0f);
                }
            }
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).breakb = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            InsideTheSystemMod.queueServerWork(40, () -> {
                world.destroyBlock(BlockPos.containing((double)x, (double)y, (double)z), false);
                InsideTheSystemMod.queueServerWork(20, () -> {
                    world.destroyBlock(BlockPos.containing((double)x, (double)(y - 1.0), (double)z), false);
                    InsideTheSystemMod.queueServerWork(20, () -> {
                        world.destroyBlock(BlockPos.containing((double)x, (double)(y + 1.0), (double)z), false);
                        if (world instanceof Level) {
                            Level _level = (Level)world;
                            if (!_level.isClientSide()) {
                                _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:aiko")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                            } else {
                                _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:aiko")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                            }
                        }
                    });
                });
            });
        } else if (!"\u75af\u72c2\u7684\u7236\u4eb2".equals(guistate.containsKey("text:Password") ? ((EditBox)guistate.get("text:Password")).getValue() : "")) {
            Object v = guistate.get("text:Password");
            if (v instanceof EditBox) {
                EditBox _tf = (EditBox)v;
                _tf.setValue("");
            }
            if (world instanceof Level) {
                Level _level = (Level)world;
                if (!_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:error")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                } else {
                    _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:error")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                }
            }
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).errors -= 1.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
    }
}

