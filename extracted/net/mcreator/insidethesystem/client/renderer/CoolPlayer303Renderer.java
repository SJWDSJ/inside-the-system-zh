/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.NativeImage
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.model.geom.ModelLayers
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.HumanoidMobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.AbstractTexture
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.resources.ResourceLocation
 */
package net.mcreator.insidethesystem.client.renderer;

import com.mojang.blaze3d.platform.NativeImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.mcreator.insidethesystem.entity.CoolPlayer303Entity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;

public class CoolPlayer303Renderer
extends HumanoidMobRenderer<CoolPlayer303Entity, HumanoidModel<CoolPlayer303Entity>> {
    private static final Path SKINS_DIR = Paths.get("saves", "InsideTheSystemSkins").toAbsolutePath();
    private static final ResourceLocation DEFAULT_SKIN = ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"textures/entities/default.png");

    public CoolPlayer303Renderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
        this.addLayer((RenderLayer)new HumanoidArmorLayer((RenderLayerParent)this, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
    }

    public ResourceLocation getTextureLocation(CoolPlayer303Entity entity) {
        String skinName = entity.getSkinName();
        if (skinName == null || skinName.isEmpty() || skinName.equals("default")) {
            return DEFAULT_SKIN;
        }
        ResourceLocation skinLocation = this.loadSkinTexture(skinName);
        return skinLocation != null ? skinLocation : DEFAULT_SKIN;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private ResourceLocation loadSkinTexture(String skinName) {
        File skinFile = SKINS_DIR.resolve(skinName + ".png").toFile();
        if (!skinFile.exists()) return null;
        if (!skinFile.isFile()) {
            return null;
        }
        try (FileInputStream stream = new FileInputStream(skinFile);){
            TextureManager textureManager = Minecraft.getInstance().getTextureManager();
            ResourceLocation dynamicLoc = ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)("dynamic_skin_" + skinName));
            if (textureManager.getTexture(dynamicLoc, null) != null) {
                ResourceLocation resourceLocation = dynamicLoc;
                return resourceLocation;
            }
            NativeImage image = NativeImage.read((InputStream)stream);
            DynamicTexture dynamicTexture = new DynamicTexture(image);
            textureManager.register(dynamicLoc, (AbstractTexture)dynamicTexture);
            ResourceLocation resourceLocation = dynamicLoc;
            return resourceLocation;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

