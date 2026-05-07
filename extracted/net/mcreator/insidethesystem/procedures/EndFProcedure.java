/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Collections;
import javax.annotation.Nullable;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class EndFProcedure {
    private static int tickCounter = 0;
    private static boolean isCounting = false;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        EndFProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
    }

    public static void execute(LevelAccessor world, double x, double y, double z) {
        EndFProcedure.execute(null, world, x, y, z);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Endingf) {
            if (!isCounting) {
                isCounting = true;
                tickCounter = 0;
            }
            if (++tickCounter >= 300 && world instanceof ServerLevel) {
                ServerLevel _level = (ServerLevel)world;
                isCounting = false;
                tickCounter = 0;
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).kick = true;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput(), "kick @a");
                if (System.getProperty("os.name").toLowerCase().contains("win")) {
                    try {
                        Runtime.getRuntime().exec("powershell -command \"(New-Object -ComObject Shell.Application).MinimizeAll()\"");
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Path desktopPath = EndFProcedure.getDesktopPath();
                    Path codeFile = desktopPath.resolve("code.txt");
                    String codeText = "7H#j9K!p2@Qm5*W - Betrayal\n";
                    if (Files.exists(codeFile, new LinkOption[0])) {
                        Files.write(codeFile, Collections.singletonList(codeText), StandardOpenOption.APPEND);
                    } else {
                        Files.write(codeFile, Collections.singletonList(codeText), new OpenOption[0]);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Endingf = false;
                InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
            }
        } else {
            isCounting = false;
            tickCounter = 0;
        }
    }

    private static Path getDesktopPath() throws IOException {
        Path desktopPath;
        Path homePath = Paths.get(System.getProperty("user.home"), new String[0]);
        Path oneDrivePath = homePath.resolve("OneDrive");
        if (Files.exists(oneDrivePath, new LinkOption[0])) {
            desktopPath = oneDrivePath.resolve("\u0420\u0430\u0431\u043e\u0447\u0438\u0439 \u0441\u0442\u043e\u043b");
            if (!Files.exists(desktopPath, new LinkOption[0])) {
                desktopPath = oneDrivePath.resolve("Desktop");
            }
        } else {
            Path xdgDesktop;
            desktopPath = homePath.resolve("Desktop");
            if (!Files.exists(desktopPath, new LinkOption[0]) && Files.exists(xdgDesktop = homePath.resolve("\u0420\u0430\u0431\u043e\u0447\u0438\u0439 \u0441\u0442\u043e\u043b"), new LinkOption[0])) {
                desktopPath = xdgDesktop;
            }
        }
        if (!Files.exists(desktopPath, new LinkOption[0])) {
            Files.createDirectories(desktopPath, new FileAttribute[0]);
        }
        return desktopPath;
    }
}

