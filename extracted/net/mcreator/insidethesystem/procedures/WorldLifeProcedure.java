/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.level.GameRules$IntegerValue
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.LevelTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.mcreator.insidethesystem.init.InsideTheSystemModGameRules;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber
public class WorldLifeProcedure {
    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        WorldLifeProcedure.execute((Event)event, (LevelAccessor)event.getLevel());
    }

    public static String execute(LevelAccessor world) {
        return WorldLifeProcedure.execute(null, world);
    }

    private static String execute(@Nullable Event event, LevelAccessor world) {
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife <= 600000.0 && !InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Angry && InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife != 0.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife -= 1.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife == 575000.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Tips = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife == 550000.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task1T = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife == 500000.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Steam = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife == 450000.0 && !InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest1) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Times = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife == 400000.0) {
            if (!InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest1) {
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task1 = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task1SEC = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Taskkk = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task2T = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            } else {
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Taskkk = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task2T = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            }
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife == 350000.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).BlockDialogue = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife == 300000.0 && !InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest2) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Times = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife == 250000.0) {
            if (!InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest2) {
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task2 = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Taskk = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task3T = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            } else {
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Taskk = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task3T = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            }
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife == 200000.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Change = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife == 150000.0 && !InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest3) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Times = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife == 100000.0 && !InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest3) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task3 = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife == 50000.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Survey = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife == 0.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife = -200.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            if (!(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest3 || InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest2 || InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest1)) {
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).TaskEnd3 = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).TaskDialogue = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                ((GameRules.IntegerValue)world.getLevelData().getGameRules().getRule(InsideTheSystemModGameRules.PLAYER_ANGRY)).set(26, world.getServer());
            } else if (!InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest3 && !InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest2 || !InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest3 && !InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest1 || !InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest1 && !InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest2) {
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).TaskEnd2 = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                ((GameRules.IntegerValue)world.getLevelData().getGameRules().getRule(InsideTheSystemModGameRules.PLAYER_ANGRY)).set(26, world.getServer());
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).TaskDialogue = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            } else if (!(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest3 && InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest2 && InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).quest1)) {
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).TaskEnd1 = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            }
        }
        return "" + InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).WorldLife;
    }
}

