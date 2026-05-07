/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.network.chat.Style
 *  net.minecraft.network.chat.TextColor
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.AABB
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.ServerChatEvent
 *  net.neoforged.neoforge.event.tick.LevelTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.annotation.Nullable;
import net.mcreator.insidethesystem.entity.CoolPlayer303Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ServerChatEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber
public class CoolPlayerSkinsProcedure {
    private static int messageDelay = 0;
    private static String skinFound = "";
    private static Player playerToSend = null;

    @SubscribeEvent
    public static void onChat(ServerChatEvent event) {
        CoolPlayerSkinsProcedure.execute((Event)event, (LevelAccessor)event.getPlayer().level(), event.getRawText(), (Player)event.getPlayer());
    }

    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        CoolPlayerSkinsProcedure.handleMessageDelay();
    }

    private static void execute(@Nullable Event event, LevelAccessor world, String text, Player player) {
        int radius;
        if (text == null) {
            return;
        }
        String skinName = text.trim().toLowerCase();
        if (skinName.contains(" ")) {
            return;
        }
        if (!CoolPlayerSkinsProcedure.skinFileExists(skinName)) {
            return;
        }
        BlockPos playerPos = player.blockPosition();
        List mobs = world.getEntitiesOfClass(CoolPlayer303Entity.class, new AABB(playerPos).inflate((double)(radius = 10)));
        if (mobs.isEmpty()) {
            return;
        }
        for (CoolPlayer303Entity mob : mobs) {
            mob.setSkinName(skinName);
        }
        messageDelay = 20;
        skinFound = skinName;
        playerToSend = player;
    }

    private static void handleMessageDelay() {
        if (messageDelay > 0 && --messageDelay <= 0 && playerToSend != null) {
            CoolPlayerSkinsProcedure.playChangeSound(playerToSend);
            CoolPlayerSkinsProcedure.sendDelayedMessage(playerToSend, skinFound);
            playerToSend = null;
            skinFound = "";
        }
    }

    private static void playChangeSound(Player player) {
        Level _level = player.level();
        if (!_level.isClientSide()) {
            _level.playSound(null, player.blockPosition(), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse((String)"inside_the_system:change")), SoundSource.NEUTRAL, 1.0f, 1.0f);
        }
    }

    private static void sendDelayedMessage(Player player, String skinName) {
        MutableComponent message = Component.literal((String)"\u4f60\u627e\u5230\u4e86\uff1a").append((Component)Component.literal((String)(skinName + "!")).setStyle(Style.EMPTY.withColor(TextColor.fromRgb((int)9055202))));
        player.sendSystemMessage((Component)message);
    }

    private static boolean skinFileExists(String skinName) {
        Path skinsDir = Paths.get("saves", "InsideTheSystemSkins");
        File[] files = skinsDir.toFile().listFiles();
        if (files == null) {
            return false;
        }
        String targetFilename = skinName.toLowerCase() + ".png";
        for (File file : files) {
            if (!file.isFile() || !file.getName().equalsIgnoreCase(targetFilename)) continue;
            return true;
        }
        return false;
    }
}

