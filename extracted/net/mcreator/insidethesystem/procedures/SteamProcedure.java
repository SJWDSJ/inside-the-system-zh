/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.network.chat.Style
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LightningBolt
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.SignBlock
 *  net.minecraft.world.level.block.StandingSignBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.SignBlockEntity
 *  net.minecraft.world.level.block.entity.SignText
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class SteamProcedure {
    private static final String[] INITIAL_MESSAGES = new String[]{"\u4f60\u597d\u670b\u53cb", "I am \u00a7k:#\u2116;%;?\u00a7r", "\u4f60\u4e0d\u60f3\u73a9\u4e2a\u6e38\u620f\u5417\uff1f", "\u522b\u5b8c\u6210\u4efb\u52a1 :)", "\u6211\u77e5\u9053\u4f60\u559c\u6b22\u73a9\u6e38\u620f", "\u5c24\u5176\u662f\u5728Steam\u4e0a"};
    private static Map<Player, SteamEventData> activeEvents = new HashMap<Player, SteamEventData>();

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        SteamProcedure.execute((LevelAccessor)event.getEntity().level(), (Entity)event.getEntity());
    }

    public static void execute(LevelAccessor world, Entity entity) {
        if (entity == null || !(entity instanceof Player)) {
            return;
        }
        Player player = (Player)entity;
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Steam) {
            SteamProcedure.startSteamEvent(world, player);
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Steam = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
        if (activeEvents.containsKey(player)) {
            SteamProcedure.processSteamEvent(world, player);
        }
    }

    private static void startSteamEvent(LevelAccessor world, Player player) {
        if (player instanceof LivingEntity && !player.level().isClientSide()) {
            player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 1200, 3));
        }
        SteamEventData eventData = new SteamEventData();
        eventData.steamGames = SteamProcedure.getSteamGames();
        activeEvents.put(player, eventData);
    }

    private static void processSteamEvent(LevelAccessor world, Player player) {
        SteamEventData eventData = activeEvents.get(player);
        ++eventData.tickCounter;
        switch (eventData.stage) {
            case 0: {
                SteamProcedure.processInitialMessages(world, player, eventData);
                break;
            }
            case 1: {
                SteamProcedure.processChaosPhase(world, player, eventData);
                break;
            }
            case 2: {
                SteamProcedure.processWaitingPhase(world, player, eventData);
                break;
            }
            case 3: {
                SteamProcedure.processGoodLuckPhase(world, player, eventData);
                break;
            }
            case 4: {
                SteamProcedure.processFinalPhase(world, player, eventData);
                break;
            }
            case 5: {
                SteamProcedure.finishEvent(world, player);
            }
        }
    }

    private static void processInitialMessages(LevelAccessor world, Player player, SteamEventData eventData) {
        if (eventData.tickCounter % 100 == 0) {
            if (eventData.messageIndex < INITIAL_MESSAGES.length) {
                SteamProcedure.spawnMessageSign(world, player, INITIAL_MESSAGES[eventData.messageIndex]);
                if (eventData.messageIndex == INITIAL_MESSAGES.length - 1 && world instanceof Level) {
                    Level level = (Level)world;
                    level.playSound(null, player.blockPosition(), (SoundEvent)SoundEvents.AMBIENT_CAVE.value(), SoundSource.AMBIENT, 2.5f, 0.6f);
                }
                ++eventData.messageIndex;
            } else {
                eventData.stage = 1;
                eventData.tickCounter = 0;
            }
        }
    }

    private static void processChaosPhase(LevelAccessor world, Player player, SteamEventData eventData) {
        if (!eventData.chaosStarted) {
            eventData.chaosStarted = true;
            if (!eventData.soundPlayed && world instanceof Level) {
                Level level = (Level)world;
                level.playSound(null, player.blockPosition(), (SoundEvent)SoundEvents.AMBIENT_CAVE.value(), SoundSource.MASTER, 2.0f, 0.5f);
                eventData.soundPlayed = true;
            }
        }
        if (eventData.tickCounter % 10 == 0 && eventData.currentGameIndex < eventData.steamGames.size()) {
            int signsToSpawn = Math.min(1 + (int)(Math.random() * 2.0), eventData.steamGames.size() - eventData.currentGameIndex);
            for (int i = 0; i < signsToSpawn; ++i) {
                if (eventData.currentGameIndex >= eventData.steamGames.size()) continue;
                SteamProcedure.spawnSingleGameSign(world, player, eventData, eventData.steamGames.get(eventData.currentGameIndex));
                ++eventData.currentGameIndex;
            }
            if (world instanceof Level) {
                Level level = (Level)world;
                if (Math.random() < 0.3) {
                    level.playSound(null, player.blockPosition(), SoundEvents.ZOMBIE_AMBIENT, SoundSource.MASTER, 1.0f, 0.8f + (float)Math.random() * 0.4f);
                }
            }
        }
        if (eventData.currentGameIndex >= eventData.steamGames.size()) {
            eventData.stage = 2;
            eventData.tickCounter = 0;
        }
    }

    private static void processWaitingPhase(LevelAccessor world, Player player, SteamEventData eventData) {
        if (eventData.tickCounter >= 200) {
            eventData.stage = 3;
            eventData.tickCounter = 0;
        }
    }

    private static void processGoodLuckPhase(LevelAccessor world, Player player, SteamEventData eventData) {
        if (!eventData.goodLuckSent) {
            eventData.goodLuckSent = true;
            eventData.tickCounter = 0;
        }
        if (eventData.tickCounter >= 150) {
            eventData.stage = 4;
            eventData.tickCounter = 0;
        }
    }

    private static void processFinalPhase(LevelAccessor world, Player player, SteamEventData eventData) {
        SteamProcedure.breakAllSigns(world, player, eventData);
        if (player instanceof LivingEntity) {
            player.removeEffect(MobEffects.DARKNESS);
        }
        if (world instanceof Level) {
            Level level = (Level)world;
            level.playSound(null, player.blockPosition(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.MASTER, 2.0f, 0.7f);
        }
        InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Steam = false;
        InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        eventData.stage = 5;
    }

    private static void finishEvent(LevelAccessor world, Player player) {
        activeEvents.remove(player);
    }

    private static void spawnMessageSign(LevelAccessor world, Player player, String message) {
        if (!(world instanceof Level)) {
            return;
        }
        Level level = (Level)world;
        BlockPos playerPos = player.blockPosition();
        float yaw = player.getYHeadRot();
        float pitch = player.getXRot();
        double distance = 3.0;
        double x = -Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)) * distance;
        double z = Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)) * distance;
        double y = 1.0;
        BlockPos signPos = playerPos.offset((int)x, (int)y, (int)z);
        if (!level.getBlockState(signPos).isAir()) {
            signPos = signPos.offset(0, 1, 0);
        }
        if (!level.getBlockState(signPos).isAir()) {
            signPos = signPos.offset(1, 0, 0);
        }
        if (level.getBlockState(signPos).isAir()) {
            BlockState signState = SteamProcedure.getSignStateForDirection(yaw);
            level.setBlock(signPos, signState, 3);
            BlockEntity blockEntity = level.getBlockEntity(signPos);
            if (blockEntity instanceof SignBlockEntity) {
                SignBlockEntity signEntity = (SignBlockEntity)blockEntity;
                String[] lines = SteamProcedure.splitDialogMessage(message);
                SignText newSignText = new SignText();
                for (int i = 0; i < Math.min(lines.length, 4); ++i) {
                    newSignText = newSignText.setMessage(i, (Component)Component.literal((String)lines[i]));
                }
                SignText finalSignText = newSignText;
                signEntity.updateText(signTextObj -> finalSignText, true);
                signEntity.setChanged();
                level.sendBlockUpdated(signPos, level.getBlockState(signPos), level.getBlockState(signPos), 3);
            }
            if (activeEvents.containsKey(player)) {
                SteamProcedure.activeEvents.get((Object)player).spawnedSigns.add(signPos);
            }
        }
    }

    private static String[] splitDialogMessage(String message) {
        if (message.length() <= 12) {
            return new String[]{message, "", "", ""};
        }
        ArrayList<Object> lines = new ArrayList<Object>();
        String[] words = message.split(" ");
        Object currentLine = "";
        for (String word : words) {
            String testLine = (String)currentLine + (((String)currentLine).isEmpty() ? "" : " ") + word;
            if (testLine.length() <= 12) {
                currentLine = testLine;
            } else if (!((String)currentLine).isEmpty()) {
                lines.add(currentLine);
                currentLine = word;
            } else if (word.length() > 12) {
                lines.add(word.substring(0, 12));
                if (lines.size() < 4) {
                    String remainder = word.substring(12);
                    if (remainder.length() <= 12) {
                        lines.add(remainder);
                    } else {
                        lines.add(remainder.substring(0, 12));
                    }
                }
                currentLine = "";
            } else {
                currentLine = word;
            }
            if (lines.size() >= 4) break;
        }
        if (!((String)currentLine).isEmpty() && lines.size() < 4) {
            lines.add(currentLine);
        }
        while (lines.size() < 4) {
            lines.add("");
        }
        return lines.toArray(new String[4]);
    }

    private static BlockState getSignStateForDirection(float yaw) {
        if ((yaw = (yaw + 180.0f) % 360.0f) < 0.0f) {
            yaw += 360.0f;
        }
        int rotation = Math.round(yaw / 22.5f) % 16;
        return (BlockState)Blocks.OAK_SIGN.defaultBlockState().setValue((Property)StandingSignBlock.ROTATION, (Comparable)Integer.valueOf(rotation));
    }

    private static void spawnSingleGameSign(LevelAccessor world, Player player, SteamEventData eventData, String game) {
        if (!(world instanceof Level)) {
            return;
        }
        Level level = (Level)world;
        BlockPos playerPos = player.blockPosition();
        float yaw = player.getYHeadRot();
        double baseDistance = 1.5 + Math.random() * 3.0;
        double randomAngle = (Math.random() - 0.5) * 100.0;
        double finalYaw = Math.toRadians((double)yaw + randomAngle);
        double x = -Math.sin(finalYaw) * baseDistance;
        double z = Math.cos(finalYaw) * baseDistance;
        double y = (Math.random() - 0.5) * 4.0;
        BlockPos signPos = playerPos.offset((int)x, (int)y, (int)z);
        try {
            double angleToPlayer = Math.atan2(playerPos.getZ() - signPos.getZ(), playerPos.getX() - signPos.getX());
            float signYaw = (float)(Math.toDegrees(angleToPlayer) + 90.0);
            signYaw = (signYaw + 180.0f) % 360.0f;
            if (signYaw < 0.0f) {
                signYaw += 360.0f;
            }
            int rotation = Math.round(signYaw / 22.5f) % 16;
            BlockState signState = (BlockState)Blocks.OAK_SIGN.defaultBlockState().setValue((Property)StandingSignBlock.ROTATION, (Comparable)Integer.valueOf(rotation));
            level.setBlock(signPos, signState, 3);
            level.sendBlockUpdated(signPos, level.getBlockState(signPos), signState, 3);
            BlockEntity blockEntity = level.getBlockEntity(signPos);
            if (blockEntity instanceof SignBlockEntity) {
                SignBlockEntity signEntity = (SignBlockEntity)blockEntity;
                String[] lines = SteamProcedure.splitGameName(game);
                SignText newSignText = new SignText();
                for (int j = 0; j < Math.min(lines.length, 4); ++j) {
                    newSignText = newSignText.setMessage(j, (Component)Component.literal((String)lines[j]));
                }
                SignText finalSignText = newSignText;
                signEntity.updateText(signTextObj -> finalSignText, true);
                signEntity.setChanged();
                level.sendBlockUpdated(signPos, signState, signState, 3);
            }
            eventData.spawnedSigns.add(signPos);
            level.playSound(null, signPos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 0.5f, 1.5f + (float)Math.random() * 0.5f);
        }
        catch (Exception e) {
            System.err.println("Error spawning sign for game " + game + ": " + e.getMessage());
        }
    }

    private static void breakAllSigns(LevelAccessor world, Player player, SteamEventData eventData) {
        if (!(world instanceof ServerLevel)) {
            return;
        }
        ServerLevel serverLevel = (ServerLevel)world;
        BlockPos playerPos = player.blockPosition();
        if (world instanceof Level) {
            Level level = (Level)world;
            level.playSound(null, playerPos, SoundEvents.WITHER_SPAWN, SoundSource.MASTER, 1.5f, 0.8f);
        }
        try {
            LightningBolt lightning = (LightningBolt)EntityType.LIGHTNING_BOLT.create((Level)serverLevel);
            if (lightning != null) {
                lightning.moveTo((double)playerPos.getX(), (double)playerPos.getY(), (double)playerPos.getZ());
                serverLevel.addFreshEntity((Entity)lightning);
                if (player instanceof ServerPlayer) {
                    ServerPlayer serverPlayer = (ServerPlayer)player;
                    MutableComponent message = Component.literal((String)"\u795d\u4f60\u597d\u8fd0 :)").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_RED).withBold(Boolean.valueOf(true)));
                    serverPlayer.sendSystemMessage((Component)message);
                }
            }
        }
        catch (Exception e) {
            System.err.println("Failed to spawn lightning: " + e.getMessage());
        }
        for (BlockPos signPos : eventData.spawnedSigns) {
            Level level;
            BlockState state;
            if (!(world instanceof Level) || !((state = (level = (Level)world).getBlockState(signPos)).getBlock() instanceof SignBlock)) continue;
            level.playSound(null, signPos, SoundEvents.WOOD_BREAK, SoundSource.BLOCKS, 0.8f, 1.0f + (float)Math.random() * 0.5f);
            level.destroyBlock(signPos, false);
        }
        eventData.spawnedSigns.clear();
    }

    private static String[] splitGameName(String gameName) {
        if (gameName.length() <= 12) {
            return new String[]{gameName, "", "", ""};
        }
        ArrayList<Object> lines = new ArrayList<Object>();
        String[] words = gameName.split(" ");
        Object currentLine = "";
        for (String word : words) {
            if (((String)currentLine + (((String)currentLine).isEmpty() ? "" : " ") + word).length() <= 12) {
                currentLine = (String)currentLine + (((String)currentLine).isEmpty() ? "" : " ") + word;
            } else if (!((String)currentLine).isEmpty()) {
                lines.add(currentLine);
                currentLine = word;
            } else if (word.length() > 12) {
                lines.add(word.substring(0, 12));
                currentLine = "";
            } else {
                currentLine = word;
            }
            if (lines.size() < 3) continue;
            if (((String)currentLine).isEmpty()) break;
            lines.add(currentLine);
            break;
        }
        if (!((String)currentLine).isEmpty() && lines.size() < 4) {
            lines.add(currentLine);
        }
        while (lines.size() < 4) {
            lines.add("");
        }
        return lines.toArray(new String[4]);
    }

    private static List<String> getSteamGames() {
        ArrayList<String> games;
        block6: {
            games = new ArrayList<String>();
            try {
                File[] gameDirectories;
                File steamDir = SteamProcedure.findSteamDirectory();
                if (steamDir == null || (gameDirectories = steamDir.listFiles(File::isDirectory)) == null) break block6;
                for (File gameDir : gameDirectories) {
                    String gameName = gameDir.getName();
                    if (!(gameName = gameName.trim()).isEmpty()) {
                        games.add(gameName);
                    }
                    if (games.size() < 100) {
                        continue;
                    }
                    break;
                }
            }
            catch (Exception e) {
                System.err.println("Steam directory search error: " + e.getMessage());
            }
        }
        if (games.isEmpty()) {
            games.addAll(Arrays.asList("Counter-Strike 2", "Dota 2", "Among Us", "Fall Guys", "Team Fortress 2", "Left 4 Dead 2", "Portal 2", "Half-Life 2", "Garry's Mod", "Rust", "PUBG", "Apex Legends", "Dead by Daylight", "Terraria", "Stardew Valley", "The Witcher 3", "Cyberpunk 2077", "Grand Theft Auto V", "Red Dead Redemption 2", "Minecraft"));
        }
        return games;
    }

    private static File findSteamDirectory() {
        String[] standardPaths;
        for (String path : standardPaths = new String[]{"C:\\Program Files (x86)\\Steam\\steamapps\\common", "C:\\Program Files\\Steam\\steamapps\\common", System.getProperty("user.home") + "\\.steam\\steam\\steamapps\\common", System.getProperty("user.home") + "\\Library\\Application Support\\Steam\\steamapps\\common", "/usr/share/steam/steamapps/common", "/opt/steam/steamapps/common"}) {
            File dir = new File(path);
            if (!dir.exists() || !dir.isDirectory()) continue;
            return dir;
        }
        File steamFromRegistry = SteamProcedure.findSteamFromRegistry();
        if (steamFromRegistry != null) {
            return steamFromRegistry;
        }
        File steamFromEnv = SteamProcedure.findSteamFromEnvironment();
        if (steamFromEnv != null) {
            return steamFromEnv;
        }
        File steamFromDiskSearch = SteamProcedure.searchAllDisks();
        if (steamFromDiskSearch != null) {
            return steamFromDiskSearch;
        }
        return null;
    }

    private static File findSteamFromRegistry() {
        try {
            String line;
            Process process = Runtime.getRuntime().exec("reg query \"HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Valve\\Steam\" /v InstallPath");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = reader.readLine()) != null) {
                String steamPath;
                File steamDir;
                String[] parts;
                if (!line.contains("InstallPath") || (parts = line.split("REG_SZ")).length <= 1 || !(steamDir = new File(steamPath = parts[1].trim() + "\\steamapps\\common")).exists() || !steamDir.isDirectory()) continue;
                return steamDir;
            }
            process.waitFor();
            reader.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
        return null;
    }

    private static File findSteamFromEnvironment() {
        String[] envVars;
        for (String envVar : envVars = new String[]{"STEAM_ROOT", "STEAMROOT", "STEAM_PATH"}) {
            String steamPath = System.getenv(envVar);
            if (steamPath == null) continue;
            File steamDir = new File(steamPath + "\\steamapps\\common");
            if (steamDir.exists() && steamDir.isDirectory()) {
                return steamDir;
            }
            steamDir = new File(steamPath + "/steamapps/common");
            if (!steamDir.exists() || !steamDir.isDirectory()) continue;
            return steamDir;
        }
        return null;
    }

    private static File searchAllDisks() {
        try {
            File[] roots;
            for (File root : roots = File.listRoots()) {
                File steamDir;
                if (!root.canRead() || root.getTotalSpace() <= 0L || (steamDir = SteamProcedure.searchForSteamInDirectory(root, 0, 3)) == null) continue;
                return steamDir;
            }
        }
        catch (Exception e) {
            System.err.println("Disk search error: " + e.getMessage());
        }
        return null;
    }

    private static File searchForSteamInDirectory(File directory, int currentDepth, int maxDepth) {
        if (currentDepth > maxDepth || directory == null || !directory.exists() || !directory.canRead()) {
            return null;
        }
        try {
            File[] subdirs;
            File result;
            String[] steamFolderNames;
            File[] gameDirectories;
            File steamAppsCommon = new File(directory, "steamapps/common");
            if (!steamAppsCommon.exists()) {
                steamAppsCommon = new File(directory, "steamapps\\common");
            }
            if (steamAppsCommon.exists() && steamAppsCommon.isDirectory() && (gameDirectories = steamAppsCommon.listFiles(File::isDirectory)) != null && gameDirectories.length > 0) {
                return steamAppsCommon;
            }
            for (String steamFolderName : steamFolderNames = new String[]{"Steam", "steam", "SteamLibrary", "steamlibrary"}) {
                File steamFolder = new File(directory, steamFolderName);
                if (!steamFolder.exists() || !steamFolder.isDirectory() || (result = SteamProcedure.searchForSteamInDirectory(steamFolder, currentDepth + 1, maxDepth)) == null) continue;
                return result;
            }
            if (currentDepth == 0 && (subdirs = directory.listFiles(file -> file.isDirectory() && !file.getName().startsWith(".") && !file.getName().equals("System32") && !file.getName().equals("Windows") && !file.getName().equals("ProgramData"))) != null) {
                for (File subdir : subdirs) {
                    result = SteamProcedure.searchForSteamInDirectory(subdir, currentDepth + 1, maxDepth);
                    if (result == null) continue;
                    return result;
                }
            }
        }
        catch (SecurityException securityException) {
            // empty catch block
        }
        return null;
    }

    private static class SteamEventData {
        int stage = 0;
        int messageIndex = 0;
        int tickCounter = 0;
        List<BlockPos> spawnedSigns = new ArrayList<BlockPos>();
        List<String> steamGames = new ArrayList<String>();
        boolean chaosStarted = false;
        int chaosTickCounter = 0;
        int currentGameIndex = 0;
        boolean soundPlayed = false;
        boolean goodLuckSent = false;

        private SteamEventData() {
        }
    }
}

