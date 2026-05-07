/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.components.ImageButton
 *  net.minecraft.client.gui.components.WidgetSprites
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
import net.mcreator.insidethesystem.network.TipsOpen3ButtonMessage;
import net.mcreator.insidethesystem.world.inventory.TipsOpen3Menu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
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

public class TipsOpen3Screen
extends AbstractContainerScreen<TipsOpen3Menu> {
    private static final HashMap<String, Object> guistate = TipsOpen3Menu.guistate;
    private final Level world;
    private final int x;
    private final int y;
    private final int z;
    private final Player entity;
    ImageButton imagebutton_exit;
    private static final ResourceLocation texture = ResourceLocation.parse((String)"inside_the_system:textures/screens/tips_open_3.png");

    public TipsOpen3Screen(TipsOpen3Menu container, Inventory inventory, Component text) {
        super((AbstractContainerMenu)container, inventory, text);
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 149;
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
        guiGraphics.blit(ResourceLocation.parse((String)"inside_the_system:textures/screens/final_tios.png"), this.leftPos + -7, this.topPos + -29, 0.0f, 0.0f, 177, 219, 177, 219);
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
    }

    public void init() {
        super.init();
        this.imagebutton_exit = new ImageButton(this, this.leftPos + 130, this.topPos + 172, 15, 8, new WidgetSprites(ResourceLocation.parse((String)"inside_the_system:textures/screens/exit.png"), ResourceLocation.parse((String)"inside_the_system:textures/screens/exit2.png")), e -> {
            PacketDistributor.sendToServer((CustomPacketPayload)new TipsOpen3ButtonMessage(0, this.x, this.y, this.z), (CustomPacketPayload[])new CustomPacketPayload[0]);
            TipsOpen3ButtonMessage.handleButtonAction(this.entity, 0, this.x, this.y, this.z);
        }){

            public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
                guiGraphics.blit(this.sprites.get(this.isActive(), this.isHoveredOrFocused()), this.getX(), this.getY(), 0.0f, 0.0f, this.width, this.height, this.width, this.height);
            }
        };
        guistate.put("button:imagebutton_exit", this.imagebutton_exit);
        this.addRenderableWidget((GuiEventListener)this.imagebutton_exit);
    }
}

