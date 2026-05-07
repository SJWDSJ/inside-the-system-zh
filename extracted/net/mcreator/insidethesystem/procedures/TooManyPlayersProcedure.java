/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.common.util.FakePlayer
 *  net.neoforged.neoforge.event.entity.player.PlayerEvent$PlayerLoggedInEvent
 *  net.neoforged.neoforge.event.tick.ServerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.mcreator.insidethesystem.entity.CoolPlayer303Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber
public class TooManyPlayersProcedure {
    private static final Map<UUID, Integer> playerTimers = new HashMap<UUID, Integer>();
    private static final int DELAY_TICKS = 100;

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer && !(event.getEntity() instanceof FakePlayer)) {
            ServerPlayer player = (ServerPlayer)event.getEntity();
            playerTimers.put(player.getUUID(), 100);
        }
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        HashMap<UUID, Integer> copy = new HashMap<UUID, Integer>(playerTimers);
        for (Map.Entry entry : copy.entrySet()) {
            int remaining = (Integer)entry.getValue() - 1;
            if (remaining <= 0) {
                playerTimers.remove(entry.getKey());
                ServerPlayer player = event.getServer().getPlayerList().getPlayer((UUID)entry.getKey());
                if (player == null) continue;
                TooManyPlayersProcedure.checkAndRemoveExtraEntities(player);
                continue;
            }
            playerTimers.put((UUID)entry.getKey(), remaining);
        }
    }

    private static void checkAndRemoveExtraEntities(ServerPlayer player) {
        ServerLevel serverLevel;
        List entities;
        Level level = player.level();
        if (level instanceof ServerLevel && (entities = (serverLevel = (ServerLevel)level).getEntitiesOfClass(CoolPlayer303Entity.class, new AABB(player.getX() - 100.0, player.getY() - 100.0, player.getZ() - 100.0, player.getX() + 100.0, player.getY() + 100.0, player.getZ() + 100.0))).size() > 1) {
            int removedCount = 0;
            for (int i = 1; i < entities.size(); ++i) {
                ((CoolPlayer303Entity)((Object)entities.get(i))).discard();
                ++removedCount;
            }
            player.sendSystemMessage((Component)Component.literal((String)("\u6ce8\u610f " + removedCount + " \u51b2\u7a81\u7684Aiko\uff0c\u6b63\u5728\u79fb\u9664\u4e2d")));
        }
    }
}

