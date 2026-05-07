/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.entity.player.PlayerEvent$ItemCraftedEvent
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.mcreator.insidethesystem.InsideTheSystemMod;
import net.mcreator.insidethesystem.init.InsideTheSystemModItems;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber
public class Quest1Procedure {
    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        Quest1Procedure.execute((Event)event, (LevelAccessor)event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getCrafting());
    }

    public static void execute(LevelAccessor world, double x, double y, double z, ItemStack itemstack) {
        Quest1Procedure.execute(null, world, x, y, z, itemstack);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, ItemStack itemstack) {
        if (itemstack.getItem() == InsideTheSystemModItems.SHELLNECKLACE_CHESTPLATE.get()) {
            if (!world.isClientSide() && world.getServer() != null) {
                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u597d\u6f02\u4eae\u7684\u9879\u94fe\uff01\u8c22\u8c22\u4f60\uff0c\u5c31\u50cf\u6211\u5988\u5988\u7ed9\u6211\u505a\u7684\u90a3\u4e2a\u4e00\u6837\uff01"), false);
            }
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest2 = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            InsideTheSystemMod.queueServerWork(70, () -> {
                if (world instanceof ServerLevel) {
                    ServerLevel _level = (ServerLevel)world;
                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a actionbar [\"\u628a\u5b83\u4ea4\u7ed9\u4ed6\"]");
                }
            });
        }
    }
}

