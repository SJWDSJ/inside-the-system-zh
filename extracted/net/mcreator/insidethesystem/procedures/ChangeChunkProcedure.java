/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.mcreator.insidethesystem.InsideTheSystemMod;
import net.mcreator.insidethesystem.init.InsideTheSystemModBlocks;
import net.mcreator.insidethesystem.init.InsideTheSystemModSounds;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class ChangeChunkProcedure {
    private static final Map<UUID, Integer> countdowns = new ConcurrentHashMap<UUID, Integer>();
    private static final Random random = new Random();
    private static final int COUNTDOWN_INTERVAL = 30;

    private static BlockState getTargetBlockState() {
        return ((Block)InsideTheSystemModBlocks.BLOODY_JUKEBOX.get()).defaultBlockState();
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        ChangeChunkProcedure.execute((LevelAccessor)event.getEntity().level(), (Entity)event.getEntity());
    }

    public static void execute(LevelAccessor world, Entity entity) {
        ChangeChunkProcedure.executeInternal(world, entity);
    }

    private static void executeInternal(LevelAccessor world, Entity entity) {
        if (!(entity instanceof Player)) {
            return;
        }
        Player player = (Player)entity;
        UUID playerId = player.getUUID();
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Change) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Change = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            countdowns.put(playerId, 300);
            if (!world.isClientSide()) {
                player.sendSystemMessage((Component)Component.literal((String)":)"));
            }
            ChangeChunkProcedure.scheduleCountdown(world, player, playerId);
        }
    }

    private static void scheduleCountdown(LevelAccessor world, Player player, UUID playerId) {
        ServerLevel serverLevel;
        int tick = countdowns.getOrDefault(playerId, 0);
        if (tick == 150 && world instanceof ServerLevel) {
            serverLevel = (ServerLevel)world;
            serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), (SoundEvent)InsideTheSystemModSounds.GLITCH.get(), SoundSource.MASTER, 1.0f, 1.0f);
        }
        if (tick <= 0) {
            if (world instanceof ServerLevel) {
                serverLevel = (ServerLevel)world;
                ChangeChunkProcedure.changeChunks(serverLevel, player);
                ChangeChunkProcedure.setNight(serverLevel);
            }
            countdowns.remove(playerId);
            return;
        }
        if (tick % 30 == 0) {
            int seconds = tick / 30;
            if (!world.isClientSide() && seconds > 0) {
                player.sendSystemMessage((Component)Component.literal((String)String.valueOf(seconds)));
            }
        }
        if (tick % 5 == 0 && tick > 0) {
            ChangeChunkProcedure.changeBlocksAroundPlayer(world, player, 5);
        }
        countdowns.put(playerId, tick - 1);
        InsideTheSystemMod.queueServerWork(1, () -> ChangeChunkProcedure.scheduleCountdown(world, player, playerId));
    }

    private static void setNight(ServerLevel world) {
        world.setDayTime(13000L);
    }

    private static void changeBlocksAroundPlayer(LevelAccessor world, Player player, int radius) {
        BlockState targetBlockState = ChangeChunkProcedure.getTargetBlockState();
        BlockPos playerPos = player.blockPosition();
        for (int i = 0; i < 5; ++i) {
            int dz;
            int dy;
            int dx = random.nextInt(2 * radius) - radius;
            BlockPos targetPos = playerPos.offset(dx, dy = random.nextInt(2 * radius) - radius, dz = random.nextInt(2 * radius) - radius);
            BlockState currentState = world.getBlockState(targetPos);
            if (currentState.isAir() || currentState.getBlock() == Blocks.BEDROCK || !(targetPos.distSqr((Vec3i)playerPos) > 1.0)) continue;
            world.setBlock(targetPos, targetBlockState, 3);
        }
    }

    private static void changeChunks(ServerLevel world, Player player) {
        BlockState targetBlockState = ChangeChunkProcedure.getTargetBlockState();
        int chunkX = player.chunkPosition().x;
        int chunkZ = player.chunkPosition().z;
        int xStart = chunkX * 16;
        int zStart = chunkZ * 16;
        for (int x = xStart; x < xStart + 16; ++x) {
            for (int z = zStart; z < zStart + 16; ++z) {
                for (int y = world.getMinBuildHeight(); y <= world.getMaxBuildHeight() - 1; ++y) {
                    BlockPos pos = new BlockPos(x, y, z);
                    BlockState state = world.getBlockState(pos);
                    if (state.isAir() || state.getBlock() == Blocks.BEDROCK) continue;
                    world.setBlock(pos, targetBlockState, 3);
                }
            }
        }
    }
}

