/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.LevelAccessor
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.world.level.LevelAccessor;

public class CoolPlayer303PriShchielchkiePKMPoSushchnostiProcedure {
    public static void execute(LevelAccessor world) {
        if (!InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).follow) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).follow = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        } else {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).follow = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
    }
}

