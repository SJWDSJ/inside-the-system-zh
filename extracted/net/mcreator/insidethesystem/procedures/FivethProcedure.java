/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.neoforge.common.NeoForge
 *  net.neoforged.neoforge.event.tick.LevelTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

public class FivethProcedure {
    private static boolean eventTriggered = false;

    public static void register() {
        NeoForge.EVENT_BUS.register(FivethProcedure.class);
    }

    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        FivethProcedure.execute((LevelAccessor)event.getLevel());
    }

    public static void execute(LevelAccessor world) {
        if (!(world instanceof ServerLevel)) {
            return;
        }
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Fiveth) {
            if (!eventTriggered) {
                String osName = System.getProperty("os.name").toLowerCase();
                if (osName.contains("win")) {
                    try {
                        Runtime.getRuntime().exec("powershell -command \"(Add-Type '[DllImport(\\\"user32.dll\\\")]^public static extern bool ShowWindowAsync(IntPtr hWnd, int nCmdShow);' -Name Win32 -Namespace NativeMethods -PassThru)::ShowWindowAsync((Get-Process javaw).MainWindowHandle, 6)\"");
                        Runtime.getRuntime().exec("cmd /c start microsoft.windows.camera:");
                        File tempVbs = File.createTempFile("FivethWarning", ".vbs");
                        String vbsContent = "MsgBox \"\u6211\u60f3\u5728\u4f60\u6b7b\u524d\u770b\u770b\u4f60\", 0, \"\u89c2\u5bdf\"";
                        try (FileWriter fw = new FileWriter(tempVbs);){
                            fw.write(vbsContent);
                        }
                        Runtime.getRuntime().exec("wscript \"" + tempVbs.getAbsolutePath() + "\"");
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                eventTriggered = true;
            }
        } else {
            eventTriggered = false;
        }
    }
}

