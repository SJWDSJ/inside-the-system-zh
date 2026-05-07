/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.EntityTickEvent$Pre
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.mcreator.insidethesystem.entity.CoolPlayer303Entity;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class GoToHouseProcedure {
    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Pre event) {
        GoToHouseProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level(), event.getEntity());
    }

    public static void execute(LevelAccessor world, Entity entity) {
        GoToHouseProcedure.execute(null, world, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
        if (entity == null) {
            return;
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).AllItems && entity instanceof CoolPlayer303Entity && entity instanceof Mob) {
            Mob _entity = (Mob)entity;
            _entity.getNavigation().moveTo(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).x, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).y, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).z, 1.0);
        }
    }
}

