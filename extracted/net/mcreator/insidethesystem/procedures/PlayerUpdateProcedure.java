/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundGameEventPacket
 *  net.minecraft.network.protocol.game.ClientboundLevelEventPacket
 *  net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket
 *  net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class PlayerUpdateProcedure {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        PlayerUpdateProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), (Entity)event.getEntity());
    }

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        PlayerUpdateProcedure.execute(null, world, x, y, z, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
        ServerPlayer _player;
        if (entity == null) {
            return;
        }
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX = entity.getX();
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY = entity.getY();
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ = entity.getZ();
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).dimens && entity instanceof ServerPlayer && !(_player = (ServerPlayer)entity).level().isClientSide()) {
            ResourceKey destinationType = ResourceKey.create((ResourceKey)Registries.DIMENSION, (ResourceLocation)ResourceLocation.parse((String)"inside_the_system:password"));
            if (_player.level().dimension() == destinationType) {
                return;
            }
            ServerLevel nextLevel = _player.server.getLevel(destinationType);
            if (nextLevel != null) {
                _player.connection.send((Packet)new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0.0f));
                _player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
                _player.connection.send((Packet)new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
                for (MobEffectInstance _effectinstance : _player.getActiveEffects()) {
                    _player.connection.send((Packet)new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance, false));
                }
                _player.connection.send((Packet)new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
            }
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).DialogueNum == 4.0) {
            ServerLevel _level;
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
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).AllItems) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife = -200.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
    }
}

