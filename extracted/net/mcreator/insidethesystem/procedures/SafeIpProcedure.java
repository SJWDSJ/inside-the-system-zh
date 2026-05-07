/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.level.LevelAccessor
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;

public class SafeIpProcedure {
    public static void execute(LevelAccessor world) {
        if (!InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).safeip) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).safeip = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            if (!world.isClientSide() && world.getServer() != null) {
                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u5b89\u5168IP\u5df2\u542f\u7528"), false);
            }
        } else {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).safeip = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            if (!world.isClientSide() && world.getServer() != null) {
                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u5b89\u5168IP\u5df2\u7981\u7528"), false);
            }
        }
    }
}

