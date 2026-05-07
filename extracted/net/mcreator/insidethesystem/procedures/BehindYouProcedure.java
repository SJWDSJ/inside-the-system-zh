/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Position
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.Mth
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.mcreator.insidethesystem.InsideTheSystemMod;
import net.mcreator.insidethesystem.entity.FatherEntity;
import net.mcreator.insidethesystem.init.InsideTheSystemModEntities;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class BehindYouProcedure {
    private static long teleportTimer = 0L;
    private static boolean isPlayerLookingAtFather = false;
    private static long approachTimer = 0L;
    private static final int APPROACH_INTERVAL = 40;
    private static Map<UUID, Integer> kickCountdowns = new HashMap<UUID, Integer>();
    private static Map<UUID, Boolean> entityRemoved = new HashMap<UUID, Boolean>();
    private static Map<UUID, Boolean> teleportationDisabled = new HashMap<UUID, Boolean>();
    private static Map<UUID, Boolean> kickSequenceStarted = new HashMap<UUID, Boolean>();
    private static Map<UUID, Integer> scaryMessageCounter = new HashMap<UUID, Integer>();

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        BehindYouProcedure.execute((LevelAccessor)event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), (Entity)event.getEntity());
    }

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        BehindYouProcedure.execute(null, world, x, y, z, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null || !(entity instanceof Player)) {
            return;
        }
        Player player = (Player)entity;
        UUID playerId = player.getUUID();
        Integer countdown = kickCountdowns.get(playerId);
        if (countdown != null && countdown >= 0) {
            Integer n = countdown;
            countdown = countdown - 1;
            kickCountdowns.put(playerId, countdown);
            if (countdown > 0 && countdown % 5 == 0) {
                BehindYouProcedure.sendScaryMessage(world, player);
            }
            if (countdown <= 0) {
                BehindYouProcedure.kickPlayerGuaranteed(player);
                BehindYouProcedure.cleanupPlayerData(playerId);
                return;
            }
        }
        BehindYouProcedure.handleFatherBehavior(world, player);
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task1) {
            Level _level;
            LivingEntity _entity;
            if (entity instanceof LivingEntity && !(_entity = (LivingEntity)entity).level().isClientSide()) {
                _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 5, false, false));
                _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 5, false, false));
            }
            player.displayClientMessage((Component)Component.literal((String)"\u00a74\u00a7l\u4ed6\u5df2\u7ecf\u5728\u4f60\u8eab\u540e\u4e86"), true);
            boolean fatherExists = false;
            if (world instanceof Level) {
                _level = (Level)world;
                AABB searchArea = new AABB(player.getX() - 50.0, player.getY() - 50.0, player.getZ() - 50.0, player.getX() + 50.0, player.getY() + 50.0, player.getZ() + 50.0);
                List existingFathers = _level.getEntitiesOfClass(FatherEntity.class, searchArea);
                boolean bl = fatherExists = !existingFathers.isEmpty();
            }
            if (!fatherExists && world instanceof ServerLevel) {
                _level = (ServerLevel)world;
                Vec3 lookVector = player.getLookAngle();
                Vec3 behindVector = lookVector.scale(-30.0);
                double spawnX = x + behindVector.x;
                double spawnY = y;
                double spawnZ = z + behindVector.z;
                BehindYouProcedure.createTunnel((ServerLevel)_level, new BlockPos((int)x, (int)y, (int)z), new BlockPos((int)spawnX, (int)spawnY, (int)spawnZ));
                BlockPos spawnPos = new BlockPos((int)spawnX, (int)spawnY, (int)spawnZ);
                while (!_level.getBlockState(spawnPos).isAir() && spawnPos.getY() < _level.getMaxBuildHeight()) {
                    spawnPos = spawnPos.above();
                    spawnY += 1.0;
                }
                FatherEntity entityToSpawn = new FatherEntity((EntityType<FatherEntity>)((EntityType)InsideTheSystemModEntities.FATHER.get()), _level);
                entityToSpawn.moveTo(spawnX, spawnY, spawnZ, 0.0f, 0.0f);
                if (entityToSpawn instanceof Mob) {
                    Mob _mobToSpawn = (Mob)entityToSpawn;
                    _mobToSpawn.finalizeSpawn((ServerLevelAccessor)_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null);
                }
                world.addFreshEntity((Entity)entityToSpawn);
            }
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task1 = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
    }

    private static void sendScaryMessage(LevelAccessor world, Player player) {
        Integer messageCount;
        if (world.isClientSide() || world.getServer() == null) {
            return;
        }
        UUID playerId = player.getUUID();
        Integer n = messageCount = scaryMessageCounter.getOrDefault(playerId, 0);
        messageCount = messageCount + 1;
        scaryMessageCounter.put(playerId, messageCount);
        String[] scaryMessages = new String[]{"\u00a74\u00a7lHAHAHAHAHA", "\u00a7c\u00a7l\u4f60\u56de\u5934\u770b\u4e86\u2026\u2026", "\u00a74\u00a7k\u00a7l\u4ed6\u77e5\u9053\u00a7r \u00a74\u00a7l\u54c8\u54c8\u54c8\u54c8", "\u00a7c\u00a7l\u73b0\u5728\u65e0\u8def\u53ef\u9003\u4e86\u2026\u2026", "\u00a74\u00a7lHA\u00a7c\u00a7lHA\u00a74\u00a7lHA\u00a7c\u00a7lHA\u00a74\u00a7lHA", "\u00a7c\u00a7l\u4f60\u4e0d\u8be5\u56de\u5934\u7684\u2026\u2026", "\u00a74\u00a7k\u00a7l\u2588\u2588\u2588\u00a7r \u00a74\u00a7l\u4ed6\u6765\u4e86\u00a7r \u00a74\u00a7k\u00a7l\u2588\u2588\u2588", "\u00a7c\u00a7l\u9ed1\u6697\u541e\u566c\u4e00\u5207\u2026\u2026", "\u00a74\u00a7lHAHAHAHAHAHAHA", "\u00a74\u00a7k\u00a7l\u5feb\u8dd1\u00a7r \u00a7c\u00a7l\u4f46\u4f60\u65e0\u5904\u53ef\u85cf", "\u00a74\u00a7l\u4ed6\u770b\u5230\u4e00\u5207", "\u00a7c\u00a7l\u4f60\u7684\u547d\u8fd0\u5df2\u5b9a"};
        String message = scaryMessages[messageCount % scaryMessages.length];
        if (player instanceof ServerPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer)player;
            serverPlayer.sendSystemMessage((Component)Component.literal((String)message));
        }
    }

    private static void removeAllFatherEntities(LevelAccessor world, Player player) {
        if (!(world instanceof Level)) {
            return;
        }
        Level _level = (Level)world;
        AABB searchArea = new AABB(player.getX() - 100.0, player.getY() - 100.0, player.getZ() - 100.0, player.getX() + 100.0, player.getY() + 100.0, player.getZ() + 100.0);
        List fatherEntities = _level.getEntitiesOfClass(FatherEntity.class, searchArea);
        for (FatherEntity father : fatherEntities) {
            father.discard();
        }
    }

    private static void cleanupPlayerData(UUID playerId) {
        kickCountdowns.remove(playerId);
        entityRemoved.remove(playerId);
        kickSequenceStarted.remove(playerId);
        scaryMessageCounter.remove(playerId);
    }

    private static void kickPlayerGuaranteed(Player player) {
        if (!(player instanceof ServerPlayer)) {
            return;
        }
        ServerPlayer serverPlayer = (ServerPlayer)player;
        MinecraftServer server = serverPlayer.getServer();
        if (server == null) {
            return;
        }
        MutableComponent kickMessage = Component.literal((String)"An unknown entity has been detected in your world\nPlease report it to https://help.minecraft.net/hc/en-us\n\u00a78\u00a7oError Code: ENTITY_UNKNOWN_0x4F");
        try {
            serverPlayer.connection.disconnect((Component)kickMessage);
            InsideTheSystemMod.LOGGER.info("\u6210\u529f\u8e22\u51fa\u73a9\u5bb6 " + serverPlayer.getName().getString() + "\uff08Father\u906d\u9047\u540e\uff09\u3002");
        }
        catch (Exception e) {
            InsideTheSystemMod.LOGGER.error("CRITICAL: Failed to kick player with primary method: " + e.getMessage());
            try {
                serverPlayer.connection.disconnect((Component)kickMessage);
            }
            catch (Exception e2) {
                InsideTheSystemMod.LOGGER.error("CRITICAL: Failed to kick player even with fallback method: " + e2.getMessage());
            }
        }
        server.execute(() -> BehindYouProcedure.lambda$kickPlayerGuaranteed$0(serverPlayer, (Component)kickMessage));
    }

    private static void handleFatherBehavior(LevelAccessor world, Player player) {
        if (!(world instanceof Level)) {
            return;
        }
        Level _level = (Level)world;
        UUID playerId = player.getUUID();
        if (kickSequenceStarted.getOrDefault(playerId, false).booleanValue()) {
            return;
        }
        AABB searchArea = new AABB(player.getX() - 50.0, player.getY() - 50.0, player.getZ() - 50.0, player.getX() + 50.0, player.getY() + 50.0, player.getZ() + 50.0);
        List fatherEntities = _level.getEntitiesOfClass(FatherEntity.class, searchArea);
        for (FatherEntity father : fatherEntities) {
            if ((double)father.distanceTo((Entity)player) <= 2.0 && !kickSequenceStarted.getOrDefault(playerId, false).booleanValue()) {
                kickSequenceStarted.put(playerId, true);
                teleportationDisabled.put(playerId, true);
                try {
                    _level.playSound(null, new BlockPos((int)player.getX(), (int)player.getY(), (int)player.getZ()), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:behindscreamer")), SoundSource.NEUTRAL, 1.0f, 1.0f);
                }
                catch (Exception e) {
                    InsideTheSystemMod.LOGGER.warn("Could not play sound: " + e.getMessage());
                }
                BehindYouProcedure.removeAllFatherEntities(world, player);
                entityRemoved.put(playerId, true);
                scaryMessageCounter.put(playerId, 0);
                kickCountdowns.put(playerId, 40);
                return;
            }
            boolean lookingAtFather = BehindYouProcedure.isPlayerLookingAtEntity(player, (Entity)father);
            if (lookingAtFather) {
                if (!player.level().isClientSide()) {
                    BehindYouProcedure.lockPlayerLookAtEntity(player, (Entity)father);
                }
                if (!isPlayerLookingAtFather) {
                    isPlayerLookingAtFather = true;
                    approachTimer = 0L;
                }
                if (++approachTimer < 40L) continue;
                approachTimer = 0L;
                BehindYouProcedure.moveEntityCloserToPlayer((Entity)father, player, 5.0);
                continue;
            }
            isPlayerLookingAtFather = false;
            approachTimer = 0L;
            if (teleportationDisabled.getOrDefault(playerId, false).booleanValue() || ++teleportTimer < 20L) continue;
            teleportTimer = 0L;
            BehindYouProcedure.teleportBehindPlayer((Entity)father, player, 30.0);
        }
    }

    private static boolean isPlayerLookingAtEntity(Player player, Entity target) {
        Vec3 playerPos = player.getEyePosition();
        Vec3 targetPos = target.position().add(0.0, (double)(target.getBbHeight() / 2.0f), 0.0);
        Vec3 toTarget = targetPos.subtract(playerPos).normalize();
        Vec3 lookVector = player.getLookAngle();
        double dot = lookVector.dot(toTarget);
        return dot > 0.7;
    }

    private static void lockPlayerLookAtEntity(Player player, Entity target) {
        Vec3 playerPos = player.getEyePosition();
        Vec3 targetPos = target.position().add(0.0, (double)(target.getBbHeight() / 2.0f), 0.0);
        Vec3 direction = targetPos.subtract(playerPos).normalize();
        float yaw = (float)(Mth.atan2((double)(-direction.x), (double)direction.z) * 57.29577951308232);
        float pitch = (float)(-Mth.atan2((double)direction.y, (double)Math.sqrt(direction.x * direction.x + direction.z * direction.z)) * 57.29577951308232);
        player.setYRot(yaw);
        player.setXRot(pitch);
        player.yRotO = yaw;
        player.xRotO = pitch;
    }

    private static void teleportBehindPlayer(Entity entity, Player player, double distance) {
        Vec3 lookVector = player.getLookAngle();
        Vec3 behindVector = lookVector.scale(-distance);
        double newX = player.getX() + behindVector.x;
        double newY = player.getY();
        double newZ = player.getZ() + behindVector.z;
        Level level = entity.level();
        BlockPos targetPos = new BlockPos((int)newX, (int)newY, (int)newZ);
        while (!level.getBlockState(targetPos).isAir() && targetPos.getY() < level.getMaxBuildHeight()) {
            targetPos = targetPos.above();
            newY += 1.0;
        }
        entity.teleportTo(newX, newY, newZ);
    }

    private static void moveEntityCloserToPlayer(Entity entity, Player player, double stepDistance) {
        Vec3 entityPos = entity.position();
        Vec3 playerPos = player.position();
        Vec3 direction = playerPos.subtract(entityPos).normalize();
        double currentDistance = entityPos.distanceTo(playerPos);
        if (currentDistance > 1.5) {
            int a;
            BlockPos targetPos;
            BlockPos groundPos;
            double actualStep = Math.min(stepDistance, currentDistance - 1.4);
            if (actualStep <= 0.0) {
                actualStep = 0.1;
            }
            Vec3 newPos = entityPos.add(direction.scale(actualStep));
            Level level = entity.level();
            if (level.getBlockState(groundPos = (targetPos = BlockPos.containing((Position)newPos)).below()).isAir()) {
                for (a = 0; level.getBlockState(groundPos).isAir() && groundPos.getY() > level.getMinBuildHeight() && a < 10; ++a) {
                    groundPos = groundPos.below();
                }
                if (!level.getBlockState(groundPos).isAir()) {
                    targetPos = groundPos.above();
                }
            }
            if (!level.getBlockState(targetPos).isAir()) {
                for (a = 0; !level.getBlockState(targetPos).isAir() && targetPos.getY() < level.getMaxBuildHeight() && a < 5; ++a) {
                    targetPos = targetPos.above();
                }
            }
            newPos = new Vec3(newPos.x, (double)targetPos.getY(), newPos.z);
            entity.teleportTo(newPos.x, newPos.y, newPos.z);
        }
    }

    private static void createTunnel(ServerLevel level, BlockPos start, BlockPos end) {
        double dist = Math.sqrt(start.distSqr((Vec3i)end));
        if (dist > 1.0) {
            Vec3 direction = new Vec3((double)(end.getX() - start.getX()), (double)(end.getY() - start.getY()), (double)(end.getZ() - start.getZ())).normalize();
            double step = 0.5;
            double currentX = (double)start.getX() + 0.5;
            double currentY = start.getY();
            double currentZ = (double)start.getZ() + 0.5;
            for (double d = 0.0; d < dist; d += step) {
                BlockPos pos1 = BlockPos.containing((double)(currentX += direction.x * step), (double)(currentY += direction.y * step), (double)(currentZ += direction.z * step));
                BlockPos pos2 = pos1.above();
                BlockPos pos3 = pos2.above();
                BehindYouProcedure.clearBlock(level, pos1);
                BehindYouProcedure.clearBlock(level, pos2);
            }
        }
    }

    private static void clearBlock(ServerLevel level, BlockPos pos) {
        if (!level.getBlockState(pos).isAir() && !level.getBlockState(pos).is(Blocks.BEDROCK)) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        }
    }

    private static /* synthetic */ void lambda$kickPlayerGuaranteed$0(ServerPlayer serverPlayer, Component kickMessage) {
        if (serverPlayer.connection != null) {
            try {
                serverPlayer.connection.disconnect(kickMessage);
                InsideTheSystemMod.LOGGER.info("Force-kicked player " + serverPlayer.getName().getString() + " via delayed execution");
            }
            catch (Exception e) {
                InsideTheSystemMod.LOGGER.error("Final kick attempt failed: " + e.getMessage());
            }
        }
    }
}

