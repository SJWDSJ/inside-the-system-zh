/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.api.distmarker.Dist
 *  net.neoforged.api.distmarker.OnlyIn
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import java.io.IOException;
import javax.annotation.Nullable;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class SiteOpenProcedure {
    private static boolean hasSiteBeenOpened = false;

    @SubscribeEvent
    @OnlyIn(value=Dist.CLIENT)
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity().level().isClientSide()) {
            SiteOpenProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level());
        }
    }

    public static void execute(LevelAccessor world) {
        SiteOpenProcedure.execute(null, world);
    }

    private static void execute(@Nullable Event event, LevelAccessor world) {
        InsideTheSystemModVariables.MapVariables mapVariables = InsideTheSystemModVariables.MapVariables.get(world);
        if (mapVariables.site && !hasSiteBeenOpened) {
            try {
                new ProcessBuilder("powershell.exe", "Start-Process", "https://ayanamiaiko.tilda.ws/ayanamiaiko").start();
                hasSiteBeenOpened = true;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!mapVariables.site) {
            hasSiteBeenOpened = false;
        }
    }
}

