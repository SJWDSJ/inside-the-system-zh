/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.LevelAccessor
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;

public class CoolPlayer303PriObnovlieniiTikaSushchnostiProcedure {
    private static final double FOLLOW_RADIUS = 7.0;

    public static void execute(LevelAccessor world, Entity entity) {
        if (entity == null) {
            return;
        }
        entity.setCustomName((Component)Component.literal((String)"CoolPlayer303"));
        entity.setCustomNameVisible(true);
        String targetSkin = InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).CoolPlayer303Skin;
        if (targetSkin != null && !targetSkin.isEmpty()) {
            entity.getPersistentData().putString("CoolPlayer303Skin", targetSkin);
        }
        double playerX = InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX;
        double playerY = InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY;
        double playerZ = InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ;
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).follow && entity instanceof Mob) {
            double dz;
            Mob _mob = (Mob)entity;
            double dx = playerX - _mob.getX();
            double distanceSq = dx * dx + (dz = playerZ - _mob.getZ()) * dz;
            if (distanceSq > 49.0) {
                _mob.getNavigation().moveTo(playerX, playerY, playerZ, 1.0);
            } else {
                _mob.getNavigation().stop();
            }
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Angry && !entity.level().isClientSide()) {
            entity.discard();
        }
    }
}

