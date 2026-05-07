/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerPlayer
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.entity.player.PlayerEvent$PlayerLoggedInEvent
 */
package net.mcreator.insidethesystem.procedures;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber
public class SkinFolderProcedure {
    private static boolean initialized = false;
    private static final String[] SKIN_FILES = new String[]{"default.png", "vivilly.png", "angry.png", "bratishkinoff.png", "notvixios.png", "aiko.png", "lost.png", "venom.png", "mrhals.png", "hangg.png", "hurmabom2.png", "mrha0s.png", "d3nter.png", "andeku.png", "danilapodpivas.png", "mslan.png"};

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer)event.getEntity();
            SkinFolderProcedure.execute(player);
        }
    }

    private static void execute(ServerPlayer player) {
        if (initialized) {
            return;
        }
        initialized = true;
        MinecraftServer server = player.getServer();
        if (server == null) {
            return;
        }
        Path skinsDir = Paths.get("saves", new String[0]).resolve("InsideTheSystemSkins");
        try {
            if (!Files.exists(skinsDir, new LinkOption[0])) {
                Files.createDirectories(skinsDir, new FileAttribute[0]);
            }
            SkinFolderProcedure.copySkinsFromResources(skinsDir);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copySkinsFromResources(Path targetDir) throws IOException {
        for (String skinFile : SKIN_FILES) {
            try (InputStream in = SkinFolderProcedure.class.getClassLoader().getResourceAsStream("assets/inside_the_system/textures/entities/" + skinFile);){
                Path targetFile;
                if (in == null || Files.exists(targetFile = targetDir.resolve(skinFile), new LinkOption[0])) continue;
                try (OutputStream out = Files.newOutputStream(targetFile, StandardOpenOption.CREATE);){
                    int bytesRead;
                    byte[] buffer = new byte[8192];
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

