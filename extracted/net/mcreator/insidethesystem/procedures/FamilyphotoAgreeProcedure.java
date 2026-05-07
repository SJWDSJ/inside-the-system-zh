/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.LevelAccessor
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.world.level.LevelAccessor;

public class FamilyphotoAgreeProcedure {
    public static boolean execute(LevelAccessor world) {
        return InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Family;
    }
}

