/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.GameRules$Category
 *  net.minecraft.world.level.GameRules$IntegerValue
 *  net.minecraft.world.level.GameRules$Key
 *  net.minecraft.world.level.GameRules$Type
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.fml.common.EventBusSubscriber$Bus
 *  net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
 */
package net.mcreator.insidethesystem.init;

import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(bus=EventBusSubscriber.Bus.MOD)
public class InsideTheSystemModGameRules {
    public static GameRules.Key<GameRules.IntegerValue> PLAYER_ANGRY;

    @SubscribeEvent
    public static void registerGameRules(FMLCommonSetupEvent event) {
        PLAYER_ANGRY = GameRules.register((String)"playerAngry", (GameRules.Category)GameRules.Category.PLAYER, (GameRules.Type)GameRules.IntegerValue.create((int)50));
    }
}

