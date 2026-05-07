/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.Unpooled
 *  net.minecraft.client.Minecraft
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
 *  net.neoforged.api.distmarker.Dist
 *  net.neoforged.api.distmarker.OnlyIn
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.loading.FMLEnvironment
 *  net.neoforged.neoforge.common.NeoForge
 *  net.neoforged.neoforge.event.tick.ServerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import io.netty.buffer.Unpooled;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.mcreator.insidethesystem.world.inventory.PasswordWriteMenu;
import net.minecraft.client.Minecraft;
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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

public class PasswordBlockPriShchielchkiePKMPoBlokuProcedure {
    public static void execute(final LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).errors == 4.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).errors = 3.0;
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).screamer3 = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            if (world instanceof Level) {
                Level _level = (Level)world;
                if (!_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:pswscreamer")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                } else {
                    _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:pswscreamer")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                }
            }
            NeoForge.EVENT_BUS.register(new Object(){
                int ticks = 0;

                @SubscribeEvent
                public void onTick(ServerTickEvent.Post event) {
                    ++this.ticks;
                    if (this.ticks >= 50) {
                        Level lvl;
                        try {
                            Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "start cmd /k dir /s"});
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (FMLEnvironment.dist.isClient()) {
                            this.stopClient();
                        } else if (world instanceof Level && !(lvl = (Level)world).isClientSide()) {
                            lvl.getServer().halt(false);
                        }
                        NeoForge.EVENT_BUS.unregister((Object)this);
                    }
                }

                @OnlyIn(value=Dist.CLIENT)
                private void stopClient() {
                    Minecraft.getInstance().stop();
                }
            });
        } else if (entity instanceof ServerPlayer) {
            ServerPlayer _ent = (ServerPlayer)entity;
            final BlockPos _bpos = BlockPos.containing((double)x, (double)y, (double)z);
            _ent.openMenu(new MenuProvider(){

                public Component getDisplayName() {
                    return Component.literal((String)"PasswordWrite");
                }

                public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                    return new PasswordWriteMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
                }
            });
        }
    }
}

