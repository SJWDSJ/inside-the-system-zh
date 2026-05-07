/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.arguments.EntityAnchorArgument$Anchor
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.Vec3
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class UpdateProcedure {
    public static void execute(LevelAccessor world, Entity entity) {
        if (entity == null) {
            return;
        }
        Entity _ent = entity;
        _ent.teleportTo(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX + 3.0, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ);
        if (_ent instanceof ServerPlayer) {
            ServerPlayer _serverPlayer = (ServerPlayer)_ent;
            _serverPlayer.connection.teleport(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX + 3.0, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ, _ent.getYRot(), _ent.getXRot());
        }
        entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerX, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerY + 1.0, InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).PlayerZ));
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).followerdied <= 2000.0 && InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).followerdied != 0.0) {
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).followerdied -= 1.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).eventfollover = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).followerdied == 0.0) {
            if (!entity.level().isClientSide()) {
                entity.discard();
            }
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).eventfollover = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
    }
}

