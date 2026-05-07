/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.DoubleHighBlockItem
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.level.block.Block
 *  net.neoforged.neoforge.common.DeferredSpawnEggItem
 *  net.neoforged.neoforge.registries.DeferredHolder
 *  net.neoforged.neoforge.registries.DeferredItem
 *  net.neoforged.neoforge.registries.DeferredRegister
 *  net.neoforged.neoforge.registries.DeferredRegister$Items
 */
package net.mcreator.insidethesystem.init;

import net.mcreator.insidethesystem.init.InsideTheSystemModBlocks;
import net.mcreator.insidethesystem.init.InsideTheSystemModEntities;
import net.mcreator.insidethesystem.item.AcceptanceItem;
import net.mcreator.insidethesystem.item.AikoItem;
import net.mcreator.insidethesystem.item.BetrayalItem;
import net.mcreator.insidethesystem.item.BloodyknifeItem;
import net.mcreator.insidethesystem.item.ConfusionItem;
import net.mcreator.insidethesystem.item.GerdItem;
import net.mcreator.insidethesystem.item.InsideTheSystemItem;
import net.mcreator.insidethesystem.item.KnifeItem;
import net.mcreator.insidethesystem.item.PictureItem;
import net.mcreator.insidethesystem.item.ShellnecklaceItem;
import net.mcreator.insidethesystem.item.StarofMemoriesItem;
import net.mcreator.insidethesystem.item.TipsItem;
import net.mcreator.insidethesystem.item.ViolenceItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InsideTheSystemModItems {
    public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems((String)"inside_the_system");
    public static final DeferredItem<Item> COOL_PLAYER_303_SPAWN_EGG = REGISTRY.register("cool_player_303_spawn_egg", () -> new DeferredSpawnEggItem(InsideTheSystemModEntities.COOL_PLAYER_303, -1, -1, new Item.Properties()));
    public static final DeferredItem<Item> ANGRY_COOL_PLAYER_303_SPAWN_EGG = REGISTRY.register("angry_cool_player_303_spawn_egg", () -> new DeferredSpawnEggItem(InsideTheSystemModEntities.ANGRY_COOL_PLAYER_303, -1, -1, new Item.Properties()));
    public static final DeferredItem<Item> ANGRY_BUILDER_SPAWN_EGG = REGISTRY.register("angry_builder_spawn_egg", () -> new DeferredSpawnEggItem(InsideTheSystemModEntities.ANGRY_BUILDER, -1, -1, new Item.Properties()));
    public static final DeferredItem<Item> GATE = InsideTheSystemModItems.block(InsideTheSystemModBlocks.GATE);
    public static final DeferredItem<Item> STAROF_MEMORIES = REGISTRY.register("starof_memories", StarofMemoriesItem::new);
    public static final DeferredItem<Item> BETRAYAL = REGISTRY.register("betrayal", BetrayalItem::new);
    public static final DeferredItem<Item> VIOLENCE = REGISTRY.register("violence", ViolenceItem::new);
    public static final DeferredItem<Item> CONFUSION = REGISTRY.register("confusion", ConfusionItem::new);
    public static final DeferredItem<Item> ACCEPTANCE = REGISTRY.register("acceptance", AcceptanceItem::new);
    public static final DeferredItem<Item> BASE = InsideTheSystemModItems.block(InsideTheSystemModBlocks.BASE);
    public static final DeferredItem<Item> ACTIVATE_GATE = InsideTheSystemModItems.block(InsideTheSystemModBlocks.ACTIVATE_GATE);
    public static final DeferredItem<Item> KNIFE = REGISTRY.register("knife", KnifeItem::new);
    public static final DeferredItem<Item> BLOODYKNIFE = REGISTRY.register("bloodyknife", BloodyknifeItem::new);
    public static final DeferredItem<Item> SHELLNECKLACE_CHESTPLATE = REGISTRY.register("shellnecklace_chestplate", ShellnecklaceItem.Chestplate::new);
    public static final DeferredItem<Item> GERD = REGISTRY.register("gerd", GerdItem::new);
    public static final DeferredItem<Item> AIKO = REGISTRY.register("aiko", AikoItem::new);
    public static final DeferredItem<Item> BLOODY_JUKEBOX = InsideTheSystemModItems.block(InsideTheSystemModBlocks.BLOODY_JUKEBOX);
    public static final DeferredItem<Item> PASSWORD_BLOCK = InsideTheSystemModItems.block(InsideTheSystemModBlocks.PASSWORD_BLOCK);
    public static final DeferredItem<Item> MEMORY_DOORS = InsideTheSystemModItems.doubleBlock(InsideTheSystemModBlocks.MEMORY_DOORS);
    public static final DeferredItem<Item> PICTURE = REGISTRY.register("picture", PictureItem::new);
    public static final DeferredItem<Item> TIPS = REGISTRY.register("tips", TipsItem::new);
    public static final DeferredItem<Item> INSIDE_THE_SYSTEM = REGISTRY.register("inside_the_system", InsideTheSystemItem::new);

    private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem((Block)block.get(), new Item.Properties()));
    }

    private static DeferredItem<Item> doubleBlock(DeferredHolder<Block, Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem((Block)block.get(), new Item.Properties()));
    }
}

