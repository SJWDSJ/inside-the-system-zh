/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.Unpooled
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 */
package net.mcreator.insidethesystem.procedures;

import io.netty.buffer.Unpooled;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.mcreator.insidethesystem.world.inventory.TipsOpen1Menu;
import net.mcreator.insidethesystem.world.inventory.TipsOpen2Menu;
import net.mcreator.insidethesystem.world.inventory.TipsOpen3Menu;
import net.mcreator.insidethesystem.world.inventory.TipsOpenMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class TipsOpenItemProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tips) {
            if (world instanceof Level) {
                Level _level = (Level)world;
                if (!_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:photo")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                } else {
                    _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:photo")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                }
            }
            if (entity instanceof ServerPlayer) {
                ServerPlayer _ent = (ServerPlayer)entity;
                final BlockPos _bpos = BlockPos.containing((double)x, (double)y, (double)z);
                _ent.openMenu(new MenuProvider(){

                    public Component getDisplayName() {
                        return Component.literal((String)"TipsOpen");
                    }

                    public boolean shouldTriggerClientSideContainerClosingOnOpen() {
                        return false;
                    }

                    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                        return new TipsOpenMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
                    }
                }, _bpos);
            }
        } else if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tips1) {
            if (world instanceof Level) {
                Level _level = (Level)world;
                if (!_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:photo")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                } else {
                    _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:photo")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                }
            }
            if (entity instanceof ServerPlayer) {
                ServerPlayer _ent = (ServerPlayer)entity;
                final BlockPos _bpos = BlockPos.containing((double)x, (double)y, (double)z);
                _ent.openMenu(new MenuProvider(){

                    public Component getDisplayName() {
                        return Component.literal((String)"TipsOpen1");
                    }

                    public boolean shouldTriggerClientSideContainerClosingOnOpen() {
                        return false;
                    }

                    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                        return new TipsOpen1Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
                    }
                }, _bpos);
            }
        } else if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tips2) {
            if (world instanceof Level) {
                Level _level = (Level)world;
                if (!_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:photo")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                } else {
                    _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:photo")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                }
            }
            if (entity instanceof ServerPlayer) {
                ServerPlayer _ent = (ServerPlayer)entity;
                final BlockPos _bpos = BlockPos.containing((double)x, (double)y, (double)z);
                _ent.openMenu(new MenuProvider(){

                    public Component getDisplayName() {
                        return Component.literal((String)"TipsOpen2");
                    }

                    public boolean shouldTriggerClientSideContainerClosingOnOpen() {
                        return false;
                    }

                    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                        return new TipsOpen2Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
                    }
                }, _bpos);
            }
        } else if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tips3) {
            if (world instanceof Level) {
                Level _level = (Level)world;
                if (!_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:photo")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                } else {
                    _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:photo")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                }
            }
            if (entity instanceof ServerPlayer) {
                ServerPlayer _ent = (ServerPlayer)entity;
                final BlockPos _bpos = BlockPos.containing((double)x, (double)y, (double)z);
                _ent.openMenu(new MenuProvider(){

                    public Component getDisplayName() {
                        return Component.literal((String)"TipsOpen3");
                    }

                    public boolean shouldTriggerClientSideContainerClosingOnOpen() {
                        return false;
                    }

                    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                        return new TipsOpen3Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
                    }
                }, _bpos);
            }
        } else if (!world.isClientSide() && world.getServer() != null) {
            world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u6211\u6ca1\u4ec0\u4e48\u8981\u8bb0\u4e0b\u7684"), false);
        }
    }
}

