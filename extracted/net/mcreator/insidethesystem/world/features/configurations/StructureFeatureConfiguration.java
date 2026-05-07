/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  net.minecraft.core.HolderSet
 *  net.minecraft.core.RegistryCodecs
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
 */
package net.mcreator.insidethesystem.world.features.configurations;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record StructureFeatureConfiguration(ResourceLocation structure, boolean randomRotation, boolean randomMirror, HolderSet<Block> ignoredBlocks, Vec3i offset) implements FeatureConfiguration
{
    public static final Codec<StructureFeatureConfiguration> CODEC = RecordCodecBuilder.create(builder -> builder.group((App)ResourceLocation.CODEC.fieldOf("structure").forGetter(StructureFeatureConfiguration::structure), (App)Codec.BOOL.fieldOf("random_rotation").orElse((Object)false).forGetter(StructureFeatureConfiguration::randomRotation), (App)Codec.BOOL.fieldOf("random_mirror").orElse((Object)false).forGetter(StructureFeatureConfiguration::randomMirror), (App)RegistryCodecs.homogeneousList((ResourceKey)Registries.BLOCK).fieldOf("ignored_blocks").forGetter(StructureFeatureConfiguration::ignoredBlocks), (App)Vec3i.offsetCodec((int)48).optionalFieldOf("offset", (Object)Vec3i.ZERO).forGetter(StructureFeatureConfiguration::offset)).apply((Applicative)builder, StructureFeatureConfiguration::new));
}

