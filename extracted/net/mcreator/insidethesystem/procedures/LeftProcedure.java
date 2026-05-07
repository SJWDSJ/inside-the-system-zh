/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.LevelAccessor
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.world.level.LevelAccessor;

public class LeftProcedure {
    public static void execute(LevelAccessor world) {
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).buildblock = true;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).EndingDtext = true;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
    }
}

