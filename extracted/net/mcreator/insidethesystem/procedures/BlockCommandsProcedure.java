/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.CommandEvent
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.CommandEvent;

@EventBusSubscriber
public class BlockCommandsProcedure {
    @SubscribeEvent
    public static void onCommand(CommandEvent event) {
        Entity entity;
        if (!((CommandSourceStack)event.getParseResults().getContext().getSource()).getLevel().isClientSide() && InsideTheSystemModVariables.MapVariables.get((LevelAccessor)((CommandSourceStack)event.getParseResults().getContext().getSource()).getLevel()).BlockCommands && (entity = ((CommandSourceStack)event.getParseResults().getContext().getSource()).getEntity()) instanceof Player) {
            Player player = (Player)entity;
            event.setCanceled(true);
            player.displayClientMessage((Component)Component.literal((String)"\u00a7c\u73fe\u5728\u3001\u30b3\u30de\u30f3\u30c9\u306f\u4f7f\u3048\u307e\u305b\u3093\u3002"), false);
        }
    }
}

