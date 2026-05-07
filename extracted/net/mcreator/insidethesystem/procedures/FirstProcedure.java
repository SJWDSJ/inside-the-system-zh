/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.AABB
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.neoforge.common.NeoForge
 *  net.neoforged.neoforge.event.tick.LevelTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

public class FirstProcedure {
    private static boolean messageShown = false;

    public static void register() {
        NeoForge.EVENT_BUS.register(FirstProcedure.class);
    }

    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        FirstProcedure.execute((LevelAccessor)event.getLevel());
    }

    public static void execute(LevelAccessor world) {
        if (!(world instanceof ServerLevel)) {
            return;
        }
        ServerLevel level = (ServerLevel)world;
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).First) {
            if (!messageShown) {
                String osName = System.getProperty("os.name").toLowerCase();
                if (osName.contains("win")) {
                    try {
                        File tempVbs = File.createTempFile("WshMessage", ".vbs");
                        String vbsContent = "MsgBox \"\u6211\u6b63\u901a\u8fc7\u4e16\u754c\u4e0a\u7684\u6bcf\u4e00\u4e2a\u751f\u7269\u6ce8\u89c6\u7740\u4f60\", 0, \"\u7cfb\u7edf\u8b66\u544a\"";
                        try (FileWriter fw = new FileWriter(tempVbs);){
                            fw.write(vbsContent);
                        }
                        Runtime.getRuntime().exec("wscript \"" + tempVbs.getAbsolutePath() + "\"");
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                messageShown = true;
            }
            for (ServerPlayer player : level.getServer().getPlayerList().getPlayers()) {
                BlockPos playerPos = player.blockPosition();
                AABB area = new AABB(playerPos).inflate(32.0);
                List mobs = level.getEntitiesOfClass(Mob.class, area);
                for (Mob mob : mobs) {
                    if (!mob.isAlive()) continue;
                    mob.getLookControl().setLookAt((Entity)player, 30.0f, 30.0f);
                }
            }
        } else {
            messageShown = false;
        }
    }
}

