/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.Vec3
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.entity.living.LivingDeathEvent
 */
package net.mcreator.insidethesystem.procedures;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import net.mcreator.insidethesystem.entity.CoolPlayer303Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class PlayerDiedProcedure {
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event != null && event.getEntity() != null) {
            PlayerDiedProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level(), (Entity)event.getEntity());
        }
    }

    public static void execute(LevelAccessor world, Entity entity) {
        PlayerDiedProcedure.execute(null, world, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
        if (entity == null) {
            return;
        }
        if (entity instanceof CoolPlayer303Entity) {
            PlayerDiedProcedure.dropAllEquipment((CoolPlayer303Entity)entity);
        }
    }

    private static void dropAllEquipment(CoolPlayer303Entity entity) {
        if (entity.level() instanceof ServerLevel) {
            List<EquipmentSlot> slots = Arrays.asList(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND);
            for (EquipmentSlot slot : slots) {
                ItemStack stack = entity.getItemBySlot(slot);
                if (stack.isEmpty()) continue;
                Vec3 pos = entity.position();
                ItemEntity itemEntity = new ItemEntity(entity.level(), pos.x(), pos.y() + 0.5, pos.z(), stack.copy());
                itemEntity.setPickUpDelay(10);
                entity.level().addFreshEntity((Entity)itemEntity);
                entity.setItemSlot(slot, ItemStack.EMPTY);
            }
        }
    }
}

