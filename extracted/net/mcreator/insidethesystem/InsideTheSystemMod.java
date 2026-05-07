/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.codec.StreamCodec
 *  net.minecraft.network.protocol.common.custom.CustomPacketPayload
 *  net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type
 *  net.minecraft.util.Tuple
 *  net.neoforged.bus.api.IEventBus
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.Mod
 *  net.neoforged.fml.util.thread.SidedThreadGroups
 *  net.neoforged.neoforge.common.NeoForge
 *  net.neoforged.neoforge.event.tick.ServerTickEvent$Post
 *  net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent
 *  net.neoforged.neoforge.network.handling.IPayloadHandler
 *  net.neoforged.neoforge.network.registration.PayloadRegistrar
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.mcreator.insidethesystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.mcreator.insidethesystem.init.InsideTheSystemModBlocks;
import net.mcreator.insidethesystem.init.InsideTheSystemModEntities;
import net.mcreator.insidethesystem.init.InsideTheSystemModItems;
import net.mcreator.insidethesystem.init.InsideTheSystemModMenus;
import net.mcreator.insidethesystem.init.InsideTheSystemModSounds;
import net.mcreator.insidethesystem.init.InsideTheSystemModTabs;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.mcreator.insidethesystem.world.features.StructureFeature;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.util.Tuple;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.util.thread.SidedThreadGroups;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(value="inside_the_system")
public class InsideTheSystemMod {
    public static final Logger LOGGER = LogManager.getLogger(InsideTheSystemMod.class);
    public static final String MODID = "inside_the_system";
    private static boolean networkingRegistered = false;
    private static final Map<CustomPacketPayload.Type<?>, NetworkMessage<?>> MESSAGES = new HashMap();
    private static final Collection<Tuple<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<Tuple<Runnable, Integer>>();

    public InsideTheSystemMod(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.register((Object)this);
        modEventBus.addListener(this::registerNetworking);
        InsideTheSystemModSounds.REGISTRY.register(modEventBus);
        InsideTheSystemModBlocks.REGISTRY.register(modEventBus);
        InsideTheSystemModItems.REGISTRY.register(modEventBus);
        InsideTheSystemModEntities.REGISTRY.register(modEventBus);
        InsideTheSystemModTabs.REGISTRY.register(modEventBus);
        InsideTheSystemModVariables.ATTACHMENT_TYPES.register(modEventBus);
        StructureFeature.REGISTRY.register(modEventBus);
        InsideTheSystemModMenus.REGISTRY.register(modEventBus);
    }

    public static <T extends CustomPacketPayload> void addNetworkMessage(CustomPacketPayload.Type<T> id, StreamCodec<? extends FriendlyByteBuf, T> reader, IPayloadHandler<T> handler) {
        if (networkingRegistered) {
            throw new IllegalStateException("Cannot register new network messages after networking has been registered");
        }
        MESSAGES.put(id, new NetworkMessage<T>(reader, handler));
    }

    private void registerNetworking(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(MODID);
        MESSAGES.forEach((id, networkMessage) -> registrar.playBidirectional(id, networkMessage.reader(), networkMessage.handler()));
        networkingRegistered = true;
    }

    public static void queueServerWork(int tick, Runnable action) {
        if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
            workQueue.add((Tuple<Runnable, Integer>)new Tuple((Object)action, (Object)tick));
        }
    }

    @SubscribeEvent
    public void tick(ServerTickEvent.Post event) {
        ArrayList actions = new ArrayList();
        workQueue.forEach(work -> {
            work.setB((Object)((Integer)work.getB() - 1));
            if ((Integer)work.getB() == 0) {
                actions.add(work);
            }
        });
        actions.forEach(e -> ((Runnable)e.getA()).run());
        workQueue.removeAll(actions);
    }

    private record NetworkMessage<T extends CustomPacketPayload>(StreamCodec<? extends FriendlyByteBuf, T> reader, IPayloadHandler<T> handler) {
    }
}

