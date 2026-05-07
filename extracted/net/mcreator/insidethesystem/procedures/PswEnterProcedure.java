/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate
 */
package net.mcreator.insidethesystem.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class PswEnterProcedure {
    public static void execute(LevelAccessor world, Entity entity) {
        ServerLevel _serverworld;
        StructureTemplate template;
        if (entity == null) {
            return;
        }
        if (world instanceof ServerLevel && (template = (_serverworld = (ServerLevel)world).getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"tunnel"))) != null) {
            template.placeInWorld((ServerLevelAccessor)_serverworld, new BlockPos(0, 0, 0), new BlockPos(0, 0, 0), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random, 3);
        }
        Entity _ent = entity;
        _ent.teleportTo(4.0, 1.0, 1.0);
        if (_ent instanceof ServerPlayer) {
            ServerPlayer _serverPlayer = (ServerPlayer)_ent;
            _serverPlayer.connection.teleport(4.0, 1.0, 1.0, _ent.getYRot(), _ent.getXRot());
        }
    }
}

