/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.EntityTickEvent$Pre
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.mcreator.insidethesystem.entity.AngryBuilderEntity;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class BuilderXYZProcedure {
    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Pre event) {
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        if (event.getEntity().level().isClientSide()) {
            return;
        }
        BuilderXYZProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level(), event.getEntity());
    }

    public static void execute(LevelAccessor world, Entity entity) {
        BuilderXYZProcedure.execute(null, world, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
        BlockPos checkPos;
        if (entity == null) {
            return;
        }
        if (entity instanceof AngryBuilderEntity) {
            InsideTheSystemModVariables.MapVariables mapVariables = InsideTheSystemModVariables.MapVariables.get(world);
            mapVariables.builderX = entity.getX();
            mapVariables.builderY = entity.getY();
            mapVariables.builderZ = entity.getZ();
            mapVariables.builderLook = false;
            mapVariables.syncData(world);
            if (world instanceof Level) {
                Level _level = (Level)world;
                _level.playSound(null, BlockPos.containing((double)mapVariables.PlayerX, (double)mapVariables.PlayerY, (double)mapVariables.PlayerZ), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:builderspawn")), SoundSource.NEUTRAL, 1.0f, 1.0f);
            }
        }
        boolean isBlockCurrentlyEmpty = (double)world.getBlockState(checkPos = BlockPos.containing((double)(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX + 3.0), (double)(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY + 1.0), (double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ)).getDestroySpeed((BlockGetter)world, checkPos) > 0.1;
        InsideTheSystemModVariables.MapVariables mapVariables = InsideTheSystemModVariables.MapVariables.get(world);
        if (mapVariables.isBlockEmpty != isBlockCurrentlyEmpty) {
            mapVariables.isBlockEmpty = isBlockCurrentlyEmpty;
            mapVariables.syncData(world);
        }
    }
}

