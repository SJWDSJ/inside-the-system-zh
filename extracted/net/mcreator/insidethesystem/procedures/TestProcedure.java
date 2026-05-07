/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.ServerChatEvent
 */
package net.mcreator.insidethesystem.procedures;

import javax.annotation.Nullable;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ServerChatEvent;

@EventBusSubscriber
public class TestProcedure {
    @SubscribeEvent
    public static void onChat(ServerChatEvent event) {
        TestProcedure.execute((Event)event);
    }

    public static void execute() {
        TestProcedure.execute(null);
    }

    private static void execute(@Nullable Event event) {
    }
}

