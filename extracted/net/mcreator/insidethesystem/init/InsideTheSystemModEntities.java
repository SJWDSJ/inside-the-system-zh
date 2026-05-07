/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EntityType$Builder
 *  net.minecraft.world.entity.MobCategory
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.fml.common.EventBusSubscriber$Bus
 *  net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent
 *  net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent
 *  net.neoforged.neoforge.registries.DeferredHolder
 *  net.neoforged.neoforge.registries.DeferredRegister
 */
package net.mcreator.insidethesystem.init;

import net.mcreator.insidethesystem.entity.AngryBuilderEntity;
import net.mcreator.insidethesystem.entity.AngryCoolPlayer303Entity;
import net.mcreator.insidethesystem.entity.AyanamiAikoEntity;
import net.mcreator.insidethesystem.entity.CoolPlayer303Entity;
import net.mcreator.insidethesystem.entity.Father2Entity;
import net.mcreator.insidethesystem.entity.FatherEndEntity;
import net.mcreator.insidethesystem.entity.FatherEntity;
import net.mcreator.insidethesystem.entity.LostEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(bus=EventBusSubscriber.Bus.MOD)
public class InsideTheSystemModEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create((ResourceKey)Registries.ENTITY_TYPE, (String)"inside_the_system");
    public static final DeferredHolder<EntityType<?>, EntityType<CoolPlayer303Entity>> COOL_PLAYER_303 = InsideTheSystemModEntities.register("cool_player_303", EntityType.Builder.of(CoolPlayer303Entity::new, (MobCategory)MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(0.6f, 1.8f));
    public static final DeferredHolder<EntityType<?>, EntityType<AngryCoolPlayer303Entity>> ANGRY_COOL_PLAYER_303 = InsideTheSystemModEntities.register("angry_cool_player_303", EntityType.Builder.of(AngryCoolPlayer303Entity::new, (MobCategory)MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune().sized(0.6f, 1.8f));
    public static final DeferredHolder<EntityType<?>, EntityType<AngryBuilderEntity>> ANGRY_BUILDER = InsideTheSystemModEntities.register("angry_builder", EntityType.Builder.of(AngryBuilderEntity::new, (MobCategory)MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune().sized(0.6f, 1.8f));
    public static final DeferredHolder<EntityType<?>, EntityType<AyanamiAikoEntity>> AYANAMI_AIKO = InsideTheSystemModEntities.register("ayanami_aiko", EntityType.Builder.of(AyanamiAikoEntity::new, (MobCategory)MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune().sized(0.6f, 1.8f));
    public static final DeferredHolder<EntityType<?>, EntityType<LostEntity>> LOST = InsideTheSystemModEntities.register("lost", EntityType.Builder.of(LostEntity::new, (MobCategory)MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune().sized(0.6f, 1.8f));
    public static final DeferredHolder<EntityType<?>, EntityType<FatherEntity>> FATHER = InsideTheSystemModEntities.register("father", EntityType.Builder.of(FatherEntity::new, (MobCategory)MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune().sized(0.6f, 1.8f));
    public static final DeferredHolder<EntityType<?>, EntityType<Father2Entity>> FATHER_2 = InsideTheSystemModEntities.register("father_2", EntityType.Builder.of(Father2Entity::new, (MobCategory)MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune().sized(0.6f, 1.8f));
    public static final DeferredHolder<EntityType<?>, EntityType<FatherEndEntity>> FATHER_END = InsideTheSystemModEntities.register("father_end", EntityType.Builder.of(FatherEndEntity::new, (MobCategory)MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(0.6f, 1.8f));

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
    }

    @SubscribeEvent
    public static void init(RegisterSpawnPlacementsEvent event) {
        CoolPlayer303Entity.init(event);
        AngryCoolPlayer303Entity.init(event);
        AngryBuilderEntity.init(event);
        AyanamiAikoEntity.init(event);
        LostEntity.init(event);
        FatherEntity.init(event);
        Father2Entity.init(event);
        FatherEndEntity.init(event);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put((EntityType)COOL_PLAYER_303.get(), CoolPlayer303Entity.createAttributes().build());
        event.put((EntityType)ANGRY_COOL_PLAYER_303.get(), AngryCoolPlayer303Entity.createAttributes().build());
        event.put((EntityType)ANGRY_BUILDER.get(), AngryBuilderEntity.createAttributes().build());
        event.put((EntityType)AYANAMI_AIKO.get(), AyanamiAikoEntity.createAttributes().build());
        event.put((EntityType)LOST.get(), LostEntity.createAttributes().build());
        event.put((EntityType)FATHER.get(), FatherEntity.createAttributes().build());
        event.put((EntityType)FATHER_2.get(), Father2Entity.createAttributes().build());
        event.put((EntityType)FATHER_END.get(), FatherEndEntity.createAttributes().build());
    }
}

