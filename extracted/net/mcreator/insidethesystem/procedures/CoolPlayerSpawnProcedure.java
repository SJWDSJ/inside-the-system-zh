/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
 *  net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket$Action
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.neoforge.common.util.FakePlayer
 */
package net.mcreator.insidethesystem.procedures;

import com.mojang.authlib.GameProfile;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.neoforge.common.util.FakePlayer;

public class CoolPlayerSpawnProcedure {
    public static void execute(LevelAccessor world) {
        if (!InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).FirstJoin) {
            MinecraftServer server = world.getServer();
            if (server == null) {
                return;
            }
            server.getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u00a7eCoolPlayer303 \u52a0\u5165\u4e86\u6e38\u620f"), false);
            ServerLevel overworld = server.getLevel(Level.OVERWORLD);
            if (overworld == null) {
                return;
            }
            GameProfile profile = new GameProfile(UUID.nameUUIDFromBytes("CoolPlayer303".getBytes()), "CoolPlayer303");
            FakePlayer fakePlayer = new FakePlayer(overworld, profile);
            ClientboundPlayerInfoUpdatePacket addPacket = new ClientboundPlayerInfoUpdatePacket(EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER), List.of(fakePlayer));
            server.getPlayerList().broadcastAll((Packet)addPacket);
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).FirstJoin = true;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
    }
}

