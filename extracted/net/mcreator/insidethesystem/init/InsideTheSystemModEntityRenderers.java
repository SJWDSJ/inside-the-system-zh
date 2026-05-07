/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.EntityType
 *  net.neoforged.api.distmarker.Dist
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.fml.common.EventBusSubscriber$Bus
 *  net.neoforged.neoforge.client.event.EntityRenderersEvent$RegisterRenderers
 */
package net.mcreator.insidethesystem.init;

import net.mcreator.insidethesystem.client.renderer.AngryBuilderRenderer;
import net.mcreator.insidethesystem.client.renderer.AngryCoolPlayer303Renderer;
import net.mcreator.insidethesystem.client.renderer.AyanamiAikoRenderer;
import net.mcreator.insidethesystem.client.renderer.CoolPlayer303Renderer;
import net.mcreator.insidethesystem.client.renderer.Father2Renderer;
import net.mcreator.insidethesystem.client.renderer.FatherEndRenderer;
import net.mcreator.insidethesystem.client.renderer.FatherRenderer;
import net.mcreator.insidethesystem.client.renderer.LostRenderer;
import net.mcreator.insidethesystem.init.InsideTheSystemModEntities;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(bus=EventBusSubscriber.Bus.MOD, value={Dist.CLIENT})
public class InsideTheSystemModEntityRenderers {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer((EntityType)InsideTheSystemModEntities.COOL_PLAYER_303.get(), CoolPlayer303Renderer::new);
        event.registerEntityRenderer((EntityType)InsideTheSystemModEntities.ANGRY_COOL_PLAYER_303.get(), AngryCoolPlayer303Renderer::new);
        event.registerEntityRenderer((EntityType)InsideTheSystemModEntities.ANGRY_BUILDER.get(), AngryBuilderRenderer::new);
        event.registerEntityRenderer((EntityType)InsideTheSystemModEntities.AYANAMI_AIKO.get(), AyanamiAikoRenderer::new);
        event.registerEntityRenderer((EntityType)InsideTheSystemModEntities.LOST.get(), LostRenderer::new);
        event.registerEntityRenderer((EntityType)InsideTheSystemModEntities.FATHER.get(), FatherRenderer::new);
        event.registerEntityRenderer((EntityType)InsideTheSystemModEntities.FATHER_2.get(), Father2Renderer::new);
        event.registerEntityRenderer((EntityType)InsideTheSystemModEntities.FATHER_END.get(), FatherEndRenderer::new);
    }
}

