/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.mcreator.insidethesystem.entity.CoolPlayer303Entity;
import net.mcreator.insidethesystem.init.InsideTheSystemModEntities;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class SpawnProcedure {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        SpawnProcedure.execute(event, (LevelAccessor)event.getEntity().level());
    }

    private static void execute(@Nullable Object event, LevelAccessor world) {
        InsideTheSystemModVariables.MapVariables vars = InsideTheSystemModVariables.MapVariables.get(world);
        if (vars.spawn) {
            if (world instanceof ServerLevel) {
                ServerLevel _level = (ServerLevel)world;
                CoolPlayer303Entity entityToSpawn = new CoolPlayer303Entity((EntityType<CoolPlayer303Entity>)((EntityType)InsideTheSystemModEntities.COOL_PLAYER_303.get()), (Level)_level);
                entityToSpawn.moveTo(vars.PlayerX, vars.PlayerY, vars.PlayerZ, world.getRandom().nextFloat() * 360.0f, 0.0f);
                if (entityToSpawn instanceof Mob) {
                    Mob _mobToSpawn = (Mob)entityToSpawn;
                    _mobToSpawn.finalizeSpawn((ServerLevelAccessor)_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null);
                }
                world.addFreshEntity((Entity)entityToSpawn);
            }
            vars.spawn = false;
            vars.syncData(world);
        }
    }
}

