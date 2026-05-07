/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.components.Button
 *  net.minecraft.client.gui.components.events.GuiEventListener
 *  net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.common.custom.CustomPacketPayload
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.level.Level
 *  net.neoforged.neoforge.network.PacketDistributor
 */
package net.mcreator.insidethesystem.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import net.mcreator.insidethesystem.network.Survey5ButtonMessage;
import net.mcreator.insidethesystem.world.inventory.Survey5Menu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

public class Survey5Screen
extends AbstractContainerScreen<Survey5Menu> {
    private static final HashMap<String, Object> guistate = Survey5Menu.guistate;
    private final Level world;
    private final int x;
    private final int y;
    private final int z;
    private final Player entity;
    Button button_yes;
    Button button_yes1;
    private static final ResourceLocation texture = ResourceLocation.parse((String)"inside_the_system:textures/screens/survey_5.png");

    public Survey5Screen(Survey5Menu container, Inventory inventory, Component text) {
        super((AbstractContainerMenu)container, inventory, text);
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        guiGraphics.blit(texture, this.leftPos, this.topPos, 0.0f, 0.0f, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        RenderSystem.disableBlend();
    }

    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, (Component)Component.translatable((String)"gui.inside_the_system.survey_5.label_are_you_alone_in_the_room_right"), 27, 53, -12829636, false);
    }

    public void init() {
        super.init();
        this.button_yes = Button.builder((Component)Component.translatable((String)"gui.inside_the_system.survey_5.button_yes"), e -> {
            PacketDistributor.sendToServer((CustomPacketPayload)new Survey5ButtonMessage(0, this.x, this.y, this.z), (CustomPacketPayload[])new CustomPacketPayload[0]);
            Survey5ButtonMessage.handleButtonAction(this.entity, 0, this.x, this.y, this.z);
        }).bounds(this.leftPos + 24, this.topPos + 119, 40, 20).build();
        guistate.put("button:button_yes", this.button_yes);
        this.addRenderableWidget((GuiEventListener)this.button_yes);
        this.button_yes1 = Button.builder((Component)Component.translatable((String)"gui.inside_the_system.survey_5.button_yes1"), e -> {
            PacketDistributor.sendToServer((CustomPacketPayload)new Survey5ButtonMessage(1, this.x, this.y, this.z), (CustomPacketPayload[])new CustomPacketPayload[0]);
            Survey5ButtonMessage.handleButtonAction(this.entity, 1, this.x, this.y, this.z);
        }).bounds(this.leftPos + 108, this.topPos + 119, 40, 20).build();
        guistate.put("button:button_yes1", this.button_yes1);
        this.addRenderableWidget((GuiEventListener)this.button_yes1);
    }
}

