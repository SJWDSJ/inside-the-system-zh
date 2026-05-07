/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class FollowerSpawnProcedure {
    public static void execute(LevelAccessor world) {
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Second = false;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        if (world instanceof Level) {
            Level _level = (Level)world;
            if (!_level.isClientSide()) {
                _level.playSound(null, BlockPos.containing((double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX, (double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY, (double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:spawnfollower")), SoundSource.NEUTRAL, 1.0f, 1.0f);
            } else {
                _level.playLocalSound(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:spawnfollower")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
            }
        }
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).followerdied = 2000.0;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
    }
}

