/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.components.Button
 *  net.minecraft.client.gui.components.EditBox
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
import net.mcreator.insidethesystem.network.PasswordWriteButtonMessage;
import net.mcreator.insidethesystem.world.inventory.PasswordWriteMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
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

public class PasswordWriteScreen
extends AbstractContainerScreen<PasswordWriteMenu> {
    private static final HashMap<String, Object> guistate = PasswordWriteMenu.guistate;
    private final Level world;
    private final int x;
    private final int y;
    private final int z;
    private final Player entity;
    EditBox Password;
    Button button_empty;
    private static final ResourceLocation texture = ResourceLocation.parse((String)"inside_the_system:textures/screens/password_write.png");

    public PasswordWriteScreen(PasswordWriteMenu container, Inventory inventory, Component text) {
        super((AbstractContainerMenu)container, inventory, text);
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 150;
        this.imageHeight = 166;
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.Password.render(guiGraphics, mouseX, mouseY, partialTicks);
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
        if (this.Password.isFocused()) {
            return this.Password.keyPressed(key, b, c);
        }
        return super.keyPressed(key, b, c);
    }

    public void resize(Minecraft minecraft, int width, int height) {
        String PasswordValue = this.Password.getValue();
        super.resize(minecraft, width, height);
        this.Password.setValue(PasswordValue);
    }

    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    }

    public void init() {
        super.init();
        this.Password = new EditBox(this, this.font, this.leftPos + 16, this.topPos + 61, 118, 18, (Component)Component.translatable((String)"gui.inside_the_system.password_write.Password")){

            public void insertText(String text) {
                super.insertText(text);
                if (this.getValue().isEmpty()) {
                    this.setSuggestion(Component.translatable((String)"gui.inside_the_system.password_write.Password").getString());
                } else {
                    this.setSuggestion(null);
                }
            }

            public void moveCursorTo(int pos, boolean flag) {
                super.moveCursorTo(pos, flag);
                if (this.getValue().isEmpty()) {
                    this.setSuggestion(Component.translatable((String)"gui.inside_the_system.password_write.Password").getString());
                } else {
                    this.setSuggestion(null);
                }
            }
        };
        this.Password.setMaxLength(Short.MAX_VALUE);
        this.Password.setSuggestion(Component.translatable((String)"gui.inside_the_system.password_write.Password").getString());
        guistate.put("text:Password", this.Password);
        this.addWidget((GuiEventListener)this.Password);
        this.button_empty = Button.builder((Component)Component.translatable((String)"gui.inside_the_system.password_write.button_empty"), e -> {
            PacketDistributor.sendToServer((CustomPacketPayload)new PasswordWriteButtonMessage(0, this.x, this.y, this.z), (CustomPacketPayload[])new CustomPacketPayload[0]);
            PasswordWriteButtonMessage.handleButtonAction(this.entity, 0, this.x, this.y, this.z);
        }).bounds(this.leftPos + 60, this.topPos + 95, 25, 20).build();
        guistate.put("button:button_empty", this.button_empty);
        this.addRenderableWidget((GuiEventListener)this.button_empty);
    }
}

