/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.Unpooled
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import io.netty.buffer.Unpooled;
import javax.annotation.Nullable;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.mcreator.insidethesystem.world.inventory.Survey1Menu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class SurveyProcedure {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        SurveyProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), (Entity)event.getEntity());
    }

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        SurveyProcedure.execute(null, world, x, y, z, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Survey) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).NumberOpen = 0.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            if (entity instanceof ServerPlayer) {
                ServerPlayer _ent = (ServerPlayer)entity;
                final BlockPos _bpos = BlockPos.containing((double)x, (double)y, (double)z);
                _ent.openMenu(new MenuProvider(){

                    public Component getDisplayName() {
                        return Component.literal((String)"Survey1");
                    }

                    public boolean shouldTriggerClientSideContainerClosingOnOpen() {
                        return false;
                    }

                    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                        return new Survey1Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
                    }
                }, _bpos);
            }
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Survey = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
    }
}

