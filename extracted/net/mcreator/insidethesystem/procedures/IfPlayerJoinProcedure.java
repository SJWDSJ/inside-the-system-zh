/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.Unpooled
 *  javax.annotation.Nullable
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
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
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.entity.player.PlayerEvent$PlayerLoggedInEvent
 */
package net.mcreator.insidethesystem.procedures;

import io.netty.buffer.Unpooled;
import javax.annotation.Nullable;
import net.mcreator.insidethesystem.InsideTheSystemMod;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.mcreator.insidethesystem.world.inventory.WarningMenu;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber
public class IfPlayerJoinProcedure {
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        IfPlayerJoinProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), (Entity)event.getEntity());
    }

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        IfPlayerJoinProcedure.execute(null, world, x, y, z, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
        ServerLevel _level;
        if (entity == null) {
            return;
        }
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Colljoin = true;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Inworld = true;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).BlockCommands = false;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).TimerBuild = 100.0;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Angrybuild = 0.0;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).build = true;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).builderSpawnTimer = 32000.0;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).summon = false;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldDied < 192000.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldDied = InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).worldDiedTemp;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife < 600000.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife = InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLifeTemp;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldDied == 0.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldDied = 192000.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife == 0.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife = 600000.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).eventfollover) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).followerdied = 1690.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Disc) {
            if (entity instanceof ServerPlayer) {
                ServerPlayer _ent = (ServerPlayer)entity;
                final BlockPos _bpos = BlockPos.containing((double)x, (double)y, (double)z);
                _ent.openMenu(new MenuProvider(){

                    public Component getDisplayName() {
                        return Component.literal((String)"Warning");
                    }

                    public boolean shouldTriggerClientSideContainerClosingOnOpen() {
                        return false;
                    }

                    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                        return new WarningMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
                    }
                }, _bpos);
            }
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Disc = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (entity.level().dimension() == ResourceKey.create((ResourceKey)Registries.DIMENSION, (ResourceLocation)ResourceLocation.parse((String)"inside_the_system:password"))) {
            if (world instanceof ServerLevel) {
                _level = (ServerLevel)world;
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
            }
            if (world instanceof ServerLevel) {
                _level = (ServerLevel)world;
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a subtitle {\"text\":\"\u5c01\u95ed\u7684\u7b54\u6848\",\"italic\":true,\"color\":\"#DADADA\"}");
            }
            if (world instanceof ServerLevel) {
                _level = (ServerLevel)world;
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"\u7b2c\u56db\u5e55\"}");
            }
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).DialogueNum == 4.0) {
            if (world instanceof Level) {
                _level = (Level)world;
                if (!_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing((double)x, (double)y, (double)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:endingd")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                } else {
                    _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:endingd")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
                }
            }
            if (world instanceof ServerLevel) {
                _level = (ServerLevel)world;
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
            }
            if (world instanceof ServerLevel) {
                _level = (ServerLevel)world;
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a subtitle {\"text\":\"\u7cbe\u795e\u6df7\u4e71\",\"italic\":true,\"color\":\"#DADADA\"}");
            }
            if (world instanceof ServerLevel) {
                _level = (ServerLevel)world;
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"\u7ed3\u5c40D\"}");
            }
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).DialogueNum = 20.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (!InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Note) {
            InsideTheSystemMod.queueServerWork(5000, () -> {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u5982\u679c\u4f60\u60f3\u7684\u8bdd\uff0c\u53ef\u4ee5\u6233\u6211\uff0c\u563f\u563f\uff0c\u6211\u5c31\u5728\u8fd9\u91cc\u7b49"), false);
                }
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Note = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            });
        }
        InsideTheSystemMod.queueServerWork(100, () -> {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).TimerN = 30000.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        });
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).EndingDtext) {
            if (world instanceof ServerLevel) {
                _level = (ServerLevel)world;
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
            }
            if (world instanceof ServerLevel) {
                _level = (ServerLevel)world;
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"\u7ed3\u5c40D\"}");
            }
            if (world instanceof ServerLevel) {
                _level = (ServerLevel)world;
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a subtitle {\"text\":\"\u7cbe\u795e\u6df7\u4e71\",\"italic\":true,\"color\":\"#DADADA\"}");
            }
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).EndingDtext = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
    }
}

