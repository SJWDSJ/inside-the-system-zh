/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.Block
 *  net.neoforged.neoforge.registries.DeferredBlock
 *  net.neoforged.neoforge.registries.DeferredRegister
 *  net.neoforged.neoforge.registries.DeferredRegister$Blocks
 */
package net.mcreator.insidethesystem.init;

import net.mcreator.insidethesystem.block.ActivateGateBlock;
import net.mcreator.insidethesystem.block.BaseBlock;
import net.mcreator.insidethesystem.block.BloodyJukeboxBlock;
import net.mcreator.insidethesystem.block.GateBlock;
import net.mcreator.insidethesystem.block.MemoryDoorsBlock;
import net.mcreator.insidethesystem.block.PasswordBlockBlock;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InsideTheSystemModBlocks {
    public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks((String)"inside_the_system");
    public static final DeferredBlock<Block> GATE = REGISTRY.register("gate", GateBlock::new);
    public static final DeferredBlock<Block> BASE = REGISTRY.register("base", BaseBlock::new);
    public static final DeferredBlock<Block> ACTIVATE_GATE = REGISTRY.register("activate_gate", ActivateGateBlock::new);
    public static final DeferredBlock<Block> BLOODY_JUKEBOX = REGISTRY.register("bloody_jukebox", BloodyJukeboxBlock::new);
    public static final DeferredBlock<Block> PASSWORD_BLOCK = REGISTRY.register("password_block", PasswordBlockBlock::new);
    public static final DeferredBlock<Block> MEMORY_DOORS = REGISTRY.register("memory_doors", MemoryDoorsBlock::new);
}

