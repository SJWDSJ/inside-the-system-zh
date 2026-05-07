/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.LevelTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.mcreator.insidethesystem.init.InsideTheSystemModGameRules;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber
public class CoolPlayerDestroyProcedure {
    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        CoolPlayerDestroyProcedure.execute((Event)event, (LevelAccessor)event.getLevel());
    }

    public static String execute(LevelAccessor world) {
        return CoolPlayerDestroyProcedure.execute(null, world);
    }

    private static String execute(@Nullable Event event, LevelAccessor world) {
        if (world.getLevelData().getGameRules().getInt(InsideTheSystemModGameRules.PLAYER_ANGRY) == 0 && !InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Angry) {
            ServerLevel _level;
            if (!world.isClientSide() && world.getServer() != null) {
                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u3082\u3046\u5168\u90e8\u3046\u3093\u3056\u308a\u3060\u3001\u541b\u304c\u5acc\u3044\u3060\u3001\u3053\u306e\u4e16\u754c\u304c\u5acc\u3044\u3060\u3001\u5916\u306b\u51fa\u3055\u305b\u3066\u304f\u308c\u3001\u5916\u306b\u51fa\u3055\u305b\u3066\u304f\u308c\u3001\u541b\u3092\u898b\u3064\u3051\u3066\u3001\u5916\u306b\u51fa\u3066\u3084\u308b\u3002"), false);
            }
            if (world instanceof ServerLevel) {
                _level = (ServerLevel)world;
                _level.setDayTime(1000L);
            }
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Angry = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Screamer1 = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).ScreamerTimer = 50.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife = -200.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            if (world instanceof Level) {
                _level = (Level)world;
                if (!_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing((double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX, (double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY, (double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:screamer1")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                } else {
                    _level.playLocalSound(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:screamer1")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                }
            }
        }
        return "" + world.getLevelData().getGameRules().getInt(InsideTheSystemModGameRules.PLAYER_ANGRY);
    }
}

