/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.level.LevelAccessor
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.init.InsideTheSystemModEntities;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;

public class CoolPlayer303PriGibieliSushchnostiProcedure {
    public static void execute(LevelAccessor world) {
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Inworld && !InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Angry) {
            if (!world.isClientSide() && world.getServer() != null) {
                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"CoolPlayer303 \u6b7b\u4e8e \u00a7k:#\u2116;%;?"), false);
            }
            if (world instanceof ServerLevel) {
                ServerLevel _level = (ServerLevel)world;
                Entity entityToSpawn = ((EntityType)InsideTheSystemModEntities.COOL_PLAYER_303.get()).spawn(_level, BlockPos.containing((double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX, (double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY, (double)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ), MobSpawnType.MOB_SUMMONED);
                if (entityToSpawn != null) {
                    // empty if block
                }
            }
        }
    }
}

