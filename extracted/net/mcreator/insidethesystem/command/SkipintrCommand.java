/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.core.Direction
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.common.util.FakePlayerFactory
 *  net.neoforged.neoforge.event.RegisterCommandsEvent
 */
package net.mcreator.insidethesystem.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.mcreator.insidethesystem.procedures.SkipProcedure;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.FakePlayerFactory;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber
public class SkipintrCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register((LiteralArgumentBuilder)Commands.literal((String)"skip").executes(arguments -> {
            Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
            double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
            double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
            double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
            Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
            if (entity == null && world instanceof ServerLevel) {
                ServerLevel _servLevel = (ServerLevel)world;
                entity = FakePlayerFactory.getMinecraft((ServerLevel)_servLevel);
            }
            Direction direction = Direction.DOWN;
            if (entity != null) {
                direction = entity.getDirection();
            }
            SkipProcedure.execute((LevelAccessor)world, x, y, z);
            return 0;
        }));
    }
}

