/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.InsideTheSystemMod;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class Quest2Procedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        if (!world.isClientSide() && world.getServer() != null) {
            world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u54c7\uff0c\u662f\u683c\u5c14\u5fb7\uff01\u5feb\u7ed9\u6211\uff01\uff01\uff01"), false);
        }
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest1 = true;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        InsideTheSystemMod.queueServerWork(70, () -> {
            if (world instanceof ServerLevel) {
                ServerLevel _level = (ServerLevel)world;
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a actionbar [\"\u628a\u5b83\u4ea4\u7ed9\u4ed6\"]");
            }
        });
    }
}

