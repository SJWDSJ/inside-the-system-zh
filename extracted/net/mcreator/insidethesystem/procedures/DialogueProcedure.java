/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.LevelTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber
public class DialogueProcedure {
    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        DialogueProcedure.execute((Event)event, (LevelAccessor)event.getLevel());
    }

    public static void execute(LevelAccessor world) {
        DialogueProcedure.execute(null, world);
    }

    private static void execute(@Nullable Event event, LevelAccessor world) {
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).DialogueBool) {
            if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Dialogue == 1000.0) {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"..."), false);
                }
            } else if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Dialogue == 800.0) {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u4e0d\u5e78\u7684\u662f\uff0c\u90a3\u4e2a\u4e16\u754c\u88ab\u6467\u6bc1\u4e86"), false);
                }
            } else if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Dialogue == 600.0) {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u4f60\u4e3a\u4ec0\u4e48\u4e0d\u6551\u6211\uff1f"), false);
                }
            } else if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Dialogue == 400.0) {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u4e5f\u8bb8\u6211\u6d3b\u8be5\u3002"), false);
                }
            } else if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Dialogue == 200.0) {
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u53bb\u6b7b\u3002"), false);
                }
            } else if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Dialogue == 0.0) {
                ServerLevel _level;
                if (!world.isClientSide() && world.getServer() != null) {
                    world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"\u3053\u306e\u77ac\u9593\u3092\u4e00\u7dd2\u306b\u904e\u3054\u3057\u3066\u304f\u308c\u3066\u3042\u308a\u304c\u3068\u3046\u3002"), false);
                }
                if (world instanceof ServerLevel) {
                    _level = (ServerLevel)world;
                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0.0, 0.0, 0.0), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a times 20 60 20");
                }
                if (world instanceof ServerLevel) {
                    _level = (ServerLevel)world;
                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0.0, 0.0, 0.0), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a subtitle {\"text\":\"\u672a\u89e3\u4e4b\u8c1c\u2026\u2026\",\"italic\":true,\"color\":\"#DADADA\"}");
                }
                if (world instanceof ServerLevel) {
                    _level = (ServerLevel)world;
                    _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0.0, 0.0, 0.0), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "/title @a title {\"text\":\"\u7ed3\u5c40E\"}");
                }
            }
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Dialogue -= 1.0;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
    }
}

