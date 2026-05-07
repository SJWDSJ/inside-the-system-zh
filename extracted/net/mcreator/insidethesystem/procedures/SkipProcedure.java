/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.init.InsideTheSystemModItems;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class SkipProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife > 550000.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife = 560000.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            if (world instanceof ServerLevel) {
                ServerLevel _level = (ServerLevel)world;
                ItemEntity entityToSpawn = new ItemEntity((Level)_level, x, y, z, new ItemStack((ItemLike)InsideTheSystemModItems.TIPS.get()));
                entityToSpawn.setPickUpDelay(10);
                _level.addFreshEntity((Entity)entityToSpawn);
            }
        } else if (!world.isClientSide() && world.getServer() != null) {
            world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u00a7c\u5e8f\u7ae0\u5df2\u7ecf\u7ed3\u675f\u4e86\u00a7r"), false);
        }
    }
}

