/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 *  net.neoforged.neoforge.items.ItemHandlerHelper
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.InsideTheSystemMod;
import net.mcreator.insidethesystem.init.InsideTheSystemModItems;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.items.ItemHandlerHelper;

@EventBusSubscriber
public class GiveTipsProcedure {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        GiveTipsProcedure.execute((LevelAccessor)event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), (Entity)event.getEntity());
    }

    private static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tips) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tips = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            InsideTheSystemMod.queueServerWork(700, () -> {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u563f\uff01\u6211\u5728\u80cc\u5305\u91cc\u53d1\u73b0\u4e86\u4e00\u5f20\u5947\u602a\u7684\u7eb8"), false);
                }
                InsideTheSystemMod.queueServerWork(70, () -> {
                    if (!world.isClientSide() && world.getServer() != null) {
                        world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u6765\uff0c\u4f60\u80fd\u628a\u6211\u753b\u8fdb\u53bb\u5417\uff1f:3"), false);
                    }
                    if (entity instanceof Player) {
                        Player _player = (Player)entity;
                        ItemStack _setstack = new ItemStack((ItemLike)InsideTheSystemModItems.TIPS.get());
                        _setstack.setCount(1);
                        ItemHandlerHelper.giveItemToPlayer((Player)_player, (ItemStack)_setstack);
                    }
                });
            });
        } else if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task1T) {
            InsideTheSystemMod.queueServerWork(700, () -> {
                ServerLevel _level;
                if (world instanceof ServerLevel) {
                    _level = (ServerLevel)world;
                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @p actionbar {\"text\":\"\u6211\u5df2\u7ecf\u8bb0\u4e0b\u4e86\u63d0\u793a\"}");
                }
                if (world instanceof Level && !(_level = (Level)world).isClientSide()) {
                    _level.playSeededSound(null, x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:write")), SoundSource.NEUTRAL, 1.0f, 1.0f, _level.getRandom().nextLong());
                }
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tips1 = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            });
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task1T = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        } else if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task2T) {
            InsideTheSystemMod.queueServerWork(1900, () -> {
                ServerLevel _level;
                if (world instanceof ServerLevel) {
                    _level = (ServerLevel)world;
                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @p actionbar {\"text\":\"\u6211\u5df2\u7ecf\u8bb0\u4e0b\u4e86\u63d0\u793a\"}");
                }
                if (world instanceof Level && !(_level = (Level)world).isClientSide()) {
                    _level.playSeededSound(null, x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:write")), SoundSource.NEUTRAL, 1.0f, 1.0f, _level.getRandom().nextLong());
                }
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tips1 = false;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tips2 = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            });
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task2T = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        } else if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task3T) {
            InsideTheSystemMod.queueServerWork(1900, () -> {
                ServerLevel _level;
                if (world instanceof ServerLevel) {
                    _level = (ServerLevel)world;
                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @p actionbar {\"text\":\"\u6211\u5df2\u7ecf\u8bb0\u4e0b\u4e86\u63d0\u793a\"}");
                }
                if (world instanceof Level && !(_level = (Level)world).isClientSide()) {
                    _level.playSeededSound(null, x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:write")), SoundSource.NEUTRAL, 1.0f, 1.0f, _level.getRandom().nextLong());
                }
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tips2 = false;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tips3 = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            });
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task3T = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
    }
}

