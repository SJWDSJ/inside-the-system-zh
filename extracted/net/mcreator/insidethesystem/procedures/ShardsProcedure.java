/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.ServerChatEvent
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.init.InsideTheSystemModItems;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ServerChatEvent;

@EventBusSubscriber
public class ShardsProcedure {
    @SubscribeEvent
    public static void onChat(ServerChatEvent event) {
        ShardsProcedure.execute((LevelAccessor)event.getPlayer().level(), event.getPlayer().getX(), event.getPlayer().getY(), event.getPlayer().getZ(), event.getRawText(), (Entity)event.getPlayer());
    }

    public static void execute(LevelAccessor world, double x, double y, double z, String text, Entity entity) {
        if (text == null) {
            return;
        }
        InsideTheSystemModVariables.MapVariables vars = InsideTheSystemModVariables.MapVariables.get(world);
        if (text.equals("7H#j9K!p2@Qm5*W") && !vars.shard1) {
            vars.shard1 = true;
            vars.syncData(world);
            ShardsProcedure.spawnItemAndSound(world, x, y, z, new ItemStack((ItemLike)InsideTheSystemModItems.BETRAYAL.get()), entity);
        } else if (text.equals("L3f3R8%tN6vX&zY4") && !vars.shard2) {
            vars.shard2 = true;
            vars.syncData(world);
            ShardsProcedure.spawnItemAndSound(world, x, y, z, new ItemStack((ItemLike)InsideTheSystemModItems.VIOLENCE.get()), entity);
        } else if (text.equals("9Bq2!kP8*#mD3$wF") && !vars.shard3) {
            vars.shard3 = true;
            vars.syncData(world);
            ShardsProcedure.spawnItemAndSound(world, x, y, z, new ItemStack((ItemLike)InsideTheSystemModItems.CONFUSION.get()), entity);
        } else if (text.equals("T5@yH7*gK4!nJ8$d") && !vars.shard4) {
            vars.shard4 = true;
            vars.syncData(world);
            ShardsProcedure.spawnItemAndSound(world, x, y, z, new ItemStack((ItemLike)InsideTheSystemModItems.ACCEPTANCE.get()), entity);
        }
    }

    private static void spawnItemAndSound(LevelAccessor world, double x, double y, double z, ItemStack item, Entity entity) {
        Level _level;
        if (world instanceof Level && !(_level = (Level)world).isClientSide()) {
            ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, item);
            entityToSpawn.setPickUpDelay(10);
            _level.addFreshEntity((Entity)entityToSpawn);
        }
        if (world instanceof Level) {
            _level = (Level)world;
            if (!_level.isClientSide()) {
                _level.playSound(null, new BlockPos((int)x, (int)y, (int)z), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"shards")), SoundSource.NEUTRAL, 1.0f, 1.0f);
            } else {
                _level.playLocalSound(x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"shards")), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
            }
        }
        if (entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)entity;
            living.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 0));
        }
    }
}

