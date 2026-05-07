/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class EngDimensProcedure {
    public static void execute(LevelAccessor world) {
        ServerLevel _level;
        if (world instanceof ServerLevel) {
            _level = (ServerLevel)world;
            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "tp @a 0 2 0");
        }
        world.setBlock(new BlockPos(0, 0, 0), Blocks.TINTED_GLASS.defaultBlockState(), 3);
        if (world instanceof ServerLevel) {
            _level = (ServerLevel)world;
            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "spawnpoint @a");
        }
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).DialogueBool = true;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
    }
}

