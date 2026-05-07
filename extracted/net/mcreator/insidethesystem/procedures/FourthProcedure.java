/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.LevelTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import java.util.Random;
import javax.annotation.Nullable;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber
public class FourthProcedure {
    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        if (++tickCounter >= 2000) {
            tickCounter = 0;
            FourthProcedure.execute((LevelAccessor)event.getLevel());
        }
    }

    public static void execute(LevelAccessor world) {
        FourthProcedure.execute(null, world);
    }

    private static void execute(@Nullable Event event, LevelAccessor world) {
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Fourth) {
            int centerX = (int)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX;
            int centerY = (int)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY;
            int centerZ = (int)InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ;
            Random random = new Random();
            int chunkRadius = 5;
            int randomChunkX = centerX + (random.nextInt(chunkRadius * 2 + 1) - chunkRadius) * 16;
            int randomChunkZ = centerZ + (random.nextInt(chunkRadius * 2 + 1) - chunkRadius) * 16;
            for (int x = 0; x < 16; ++x) {
                for (int z = 0; z < 16; ++z) {
                    for (int y = 0; y < 300; ++y) {
                        world.setBlock(new BlockPos(randomChunkX + x, y, randomChunkZ + z), Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }
        }
    }
}

