/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.LevelTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber
public class CoolPlayerSpawnTimerProcedure {
    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        CoolPlayerSpawnTimerProcedure.execute((LevelAccessor)event.getLevel());
    }

    private static void execute(LevelAccessor world) {
        InsideTheSystemModVariables.MapVariables vars = InsideTheSystemModVariables.MapVariables.get(world);
        if (vars.TimerJoin <= 0.0 && vars.Colljoin && !vars.TimerEnd) {
            CoolPlayerSpawnTimerProcedure.playSpawnSoundWithSilentCommand(world, vars.PlayerX, vars.PlayerY, vars.PlayerZ);
            vars.showInTab = true;
            vars.Colljoin = false;
            vars.spawn = true;
            vars.TimerEnd = true;
            vars.firstSpawnDone = true;
            vars.messageDelay = 20.0;
            vars.syncData(world);
        }
        if (vars.messageDelay > 0.0) {
            vars.messageDelay -= 1.0;
            if (vars.messageDelay <= 0.0) {
                CoolPlayerSpawnTimerProcedure.sendDelayedMessage(world);
            }
            vars.syncData(world);
        }
    }

    private static void playSpawnSoundWithSilentCommand(LevelAccessor world, double x, double y, double z) {
        ServerLevel serverLevel;
        MinecraftServer server;
        if (world instanceof ServerLevel && (server = (serverLevel = (ServerLevel)world).getServer()) != null) {
            CommandSourceStack silentSource = new CommandSourceStack((CommandSource)server, new Vec3(x, y, z), Vec2.ZERO, serverLevel, 2, "", (Component)Component.empty(), server, null).withSuppressedOutput();
            String command = String.format("playsound inside_the_system:spawnplayer ambient @a %s %s %s 1 1", (int)x, (int)y, (int)z);
            server.getCommands().performPrefixedCommand(silentSource, command);
        }
    }

    private static void sendDelayedMessage(LevelAccessor world) {
        if (!world.isClientSide() && world.getServer() != null) {
            world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"<CoolPlayer303> \u5443\uff0c\u4f60\u597d\u554a\uff01\uff01\uff01\uff01"), false);
        }
    }
}

