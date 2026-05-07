/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.init.InsideTheSystemModEntities;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class EnterProcedure {
    public static void execute(LevelAccessor world, Entity entity) {
        ServerLevel _level;
        ServerLevel _serverworld;
        StructureTemplate template;
        if (entity == null) {
            return;
        }
        if (world instanceof ServerLevel && (template = (_serverworld = (ServerLevel)world).getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"parkour"))) != null) {
            template.placeInWorld((ServerLevelAccessor)_serverworld, new BlockPos(10, 10, 10), new BlockPos(10, 10, 10), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random, 3);
        }
        Entity _ent = entity;
        _ent.teleportTo(44.0, 12.0, 13.0);
        if (_ent instanceof ServerPlayer) {
            ServerPlayer _serverPlayer = (ServerPlayer)_ent;
            _serverPlayer.connection.teleport(44.0, 12.0, 13.0, _ent.getYRot(), _ent.getXRot());
        }
        if (world instanceof ServerLevel) {
            _level = (ServerLevel)world;
            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(44.0, 12.0, 13.0), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "spawnpoint @a");
        }
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).buildblock = false;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        if (!InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Spawnlost) {
            if (world instanceof ServerLevel) {
                _level = (ServerLevel)world;
                Entity entityToSpawn = ((EntityType)InsideTheSystemModEntities.LOST.get()).spawn(_level, new BlockPos(25, 37, 13), MobSpawnType.MOB_SUMMONED);
                if (entityToSpawn != null) {
                    entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0f);
                }
            }
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Spawnlost = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
    }
}

