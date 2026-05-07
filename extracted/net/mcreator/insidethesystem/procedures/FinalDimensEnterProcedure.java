/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LightningBolt
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.DoorBlock
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.ChestBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.DoorHingeSide
 *  net.minecraft.world.level.block.state.properties.DoubleBlockHalf
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 */
package net.mcreator.insidethesystem.procedures;

import java.util.concurrent.atomic.AtomicInteger;
import net.mcreator.insidethesystem.InsideTheSystemMod;
import net.mcreator.insidethesystem.init.InsideTheSystemModBlocks;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class FinalDimensEnterProcedure {
    private static boolean isChecking = false;
    private static final int NO_NEIGHBOR_UPDATE_FLAG = 2;

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        StructureTemplate template;
        if (entity == null || !(world instanceof ServerLevel)) {
            return;
        }
        ServerLevel serverWorld = (ServerLevel)world;
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)entity;
            livingEntity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 99999, 3, false, false));
        }
        if ((template = serverWorld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"memory"))) != null) {
            template.placeInWorld((ServerLevelAccessor)serverWorld, new BlockPos(0, 100, 0), new BlockPos(0, 100, 0), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), serverWorld.random, 2);
        }
        entity.teleportTo(9.0, 103.0, 6.0);
        if (entity instanceof ServerPlayer) {
            ServerPlayer sp = (ServerPlayer)entity;
            sp.connection.teleport(9.0, 103.0, 6.0, entity.getYRot(), entity.getXRot());
        }
        double entX = entity.getX();
        double entY = entity.getY();
        double entZ = entity.getZ();
        FinalDimensEnterProcedure.runCommand(serverWorld, entX, entY, entZ, "/title @a times 20 60 20");
        FinalDimensEnterProcedure.runCommand(serverWorld, entX, entY, entZ, "/title @a subtitle {\"text\":\"\u56de\u5fc6\u4e00\u5207\",\"italic\":true,\"color\":\"#DADADA\"}");
        InsideTheSystemMod.queueServerWork(80, () -> FinalDimensEnterProcedure.runCommand(serverWorld, entX, entY, entZ, "/title @a title {\"text\":\"\u7b2c\u4e09\u5e55\"}"));
        if (!isChecking) {
            isChecking = true;
            FinalDimensEnterProcedure.checkForBlocksAndStrikeRepeatedly(serverWorld, 3600);
        }
    }

    private static void checkForBlocksAndStrikeRepeatedly(final ServerLevel world, final int durationTicks) {
        int checkInterval = 40;
        final Block targetBlock = (Block)InsideTheSystemModBlocks.ACTIVATE_GATE.get();
        int requiredCount = 4;
        int radius = 30;
        final BlockPos center = new BlockPos(10, 101, 6);
        final BlockPos doorPos = new BlockPos(10, 101, 16);
        final AtomicInteger ticksPassed = new AtomicInteger(0);
        Runnable repeatingTask = new Runnable(){

            @Override
            public void run() {
                if (world.getServer() == null || world.getServer().isStopped()) {
                    isChecking = false;
                    return;
                }
                if (ticksPassed.get() >= durationTicks) {
                    FinalDimensEnterProcedure.placeModDoor(world, doorPos);
                    isChecking = false;
                    return;
                }
                int count = 0;
                for (int dx = -30; dx <= 30; ++dx) {
                    for (int dy = -30; dy <= 30; ++dy) {
                        for (int dz = -30; dz <= 30; ++dz) {
                            BlockPos pos = center.offset(dx, dy, dz);
                            if (!world.getBlockState(pos).is(targetBlock)) continue;
                            ++count;
                        }
                    }
                }
                if (count >= 4) {
                    FinalDimensEnterProcedure.strike(world, center);
                    FinalDimensEnterProcedure.placeModDoor(world, doorPos);
                    isChecking = false;
                } else {
                    ticksPassed.addAndGet(40);
                    InsideTheSystemMod.queueServerWork(40, this);
                }
            }
        };
        InsideTheSystemMod.queueServerWork(40, repeatingTask);
    }

    private static void strike(ServerLevel world, BlockPos center) {
        LightningBolt lightning = (LightningBolt)EntityType.LIGHTNING_BOLT.create((Level)world);
        if (lightning != null) {
            lightning.moveTo(Vec3.atBottomCenterOf((Vec3i)center));
            lightning.setVisualOnly(false);
            world.addFreshEntity((Entity)lightning);
        }
        BlockState chestState = Blocks.CHEST.defaultBlockState();
        world.setBlockAndUpdate(center, chestState);
        BlockEntity be = world.getBlockEntity(center);
        if (be instanceof ChestBlockEntity) {
            ChestBlockEntity chest = (ChestBlockEntity)be;
            for (int i = 0; i < chest.getContainerSize(); ++i) {
                chest.setItem(i, ItemStack.EMPTY);
            }
            ResourceLocation starLocation = ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"starof_memories");
            ItemStack star = new ItemStack((ItemLike)BuiltInRegistries.ITEM.get(starLocation));
            star.setCount(1);
            chest.setItem(13, star);
            chest.setChanged();
        }
    }

    private static void placeModDoor(ServerLevel world, BlockPos pos) {
        BlockState baseState = ((Block)InsideTheSystemModBlocks.MEMORY_DOORS.get()).defaultBlockState();
        BlockState lowerDoorState = (BlockState)((BlockState)((BlockState)baseState.setValue((Property)DoorBlock.HALF, (Comparable)DoubleBlockHalf.LOWER)).setValue((Property)DoorBlock.FACING, (Comparable)Direction.SOUTH)).setValue((Property)DoorBlock.HINGE, (Comparable)DoorHingeSide.LEFT);
        world.setBlockAndUpdate(pos, lowerDoorState);
        BlockState upperDoorState = (BlockState)((BlockState)((BlockState)baseState.setValue((Property)DoorBlock.HALF, (Comparable)DoubleBlockHalf.UPPER)).setValue((Property)DoorBlock.FACING, (Comparable)Direction.SOUTH)).setValue((Property)DoorBlock.HINGE, (Comparable)DoorHingeSide.LEFT);
        world.setBlockAndUpdate(pos.above(), upperDoorState);
        world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"accept")), SoundSource.HOSTILE, 1.0f, 1.0f);
    }

    private static void runCommand(ServerLevel world, double x, double y, double z, String command) {
        world.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, world, 4, "", (Component)Component.empty(), world.getServer(), null).withSuppressedOutput(), command);
    }
}

