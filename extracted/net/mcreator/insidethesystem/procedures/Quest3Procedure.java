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

public class Quest3Procedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        if (!world.isClientSide() && world.getServer() != null) {
            world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u53c8\u51b7\u53c8\u950b\u5229\u2026\u2026\u4f60\u8981\u62ff\u5b83\u505a\u4ec0\u4e48\uff1f"), false);
        }
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest3 = true;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        InsideTheSystemMod.queueServerWork(70, () -> {
            if (world instanceof ServerLevel) {
                ServerLevel _level = (ServerLevel)world;
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a actionbar [\"\u6d4b\u8bd5\u4e00\u4e0b\"]");
            }
        });
    }
}

