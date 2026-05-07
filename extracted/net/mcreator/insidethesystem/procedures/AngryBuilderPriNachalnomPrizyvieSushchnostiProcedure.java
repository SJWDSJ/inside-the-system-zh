/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.SignBlockEntity
 *  net.minecraft.world.level.block.entity.SignText
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 */
package net.mcreator.insidethesystem.procedures;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure {
    private static final int MAX_LINE_LENGTH = 15;

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        InsideTheSystemModVariables.MapVariables vars = InsideTheSystemModVariables.MapVariables.get(world);
        if (vars.TimerBuild > 0.0) {
            vars.TimerBuild -= 1.0;
            vars.syncData(world);
        }
        if (vars.build && vars.TimerBuild == 50.0) {
            BlockPos signPos = AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure.calculateSignPosition(entity, x, y, z);
            AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure.placeAndConfigureSign(world, vars, signPos, entity);
        }
        if (vars.TimerBuild <= 0.0) {
            AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure.resetProcedure(world, entity, vars);
        }
    }

    private static BlockPos calculateSignPosition(Entity entity, double x, double y, double z) {
        if (entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)entity;
            living.swing(InteractionHand.MAIN_HAND, true);
            Vec3 look = living.getLookAngle();
            Vec3 horizLook = new Vec3(look.x, 0.0, look.z).normalize();
            return new BlockPos((int)Math.floor(living.getX() + horizLook.x), (int)Math.floor(living.getY()), (int)Math.floor(living.getZ() + horizLook.z));
        }
        return new BlockPos((int)Math.floor(x), (int)Math.floor(y), (int)Math.floor(z));
    }

    private static void placeAndConfigureSign(LevelAccessor world, InsideTheSystemModVariables.MapVariables vars, BlockPos pos, Entity entity) {
        world.setBlock(pos, Blocks.OAK_SIGN.defaultBlockState(), 3);
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof SignBlockEntity) {
            SignBlockEntity sign = (SignBlockEntity)be;
            String geoInfo = AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure.getGeoInfo(world, entity, (int)vars.Angrybuild);
            String signText = AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure.generateSignText(vars, geoInfo);
            Component[] lines = AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure.wrapTextToSign(signText);
            AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure.applyTextToSign(sign, lines);
        }
        vars.build = true;
        vars.syncData(world);
    }

    private static String generateSignText(InsideTheSystemModVariables.MapVariables vars, String geoInfo) {
        int stage = (int)vars.Angrybuild;
        Object text = switch (stage) {
            case 0 -> "\u4f60\u8ba4\u8bc6\u8fd9\u4e2a\u56fd\u5bb6\u5417\uff1f" + geoInfo;
            case 1 -> "\u6211\u89c9\u5f97\u4f60\u5728" + geoInfo + "\u7684\u67d0\u4e2a\u5730\u65b9";
            case 2 -> "\u6211\u5728" + geoInfo;
            case 3 -> "\u8fd9\u4e9b\u6570\u5b57\u6709\u4ec0\u4e48\u542b\u4e49\u5417\uff1f" + geoInfo;
            case 4 -> "\u5728" + geoInfo + "\u89c1\u9762";
            default -> geoInfo;
        };
        vars.Angrybuild = (stage + 1) % 5;
        return ((String)text).isEmpty() ? "\u65ad\u7f51\u4e86\uff1f" : text;
    }

    private static void applyTextToSign(SignBlockEntity sign, Component[] lines) {
        SignText signText = sign.getFrontText();
        for (int i = 0; i < 4; ++i) {
            MutableComponent line = i < lines.length ? lines[i] : Component.empty();
            signText = signText.setMessage(i, (Component)line);
        }
        sign.setText(signText, true);
        sign.setChanged();
    }

    private static void updateSignBlock(LevelAccessor world, BlockPos pos) {
        if (!world.isClientSide() && world instanceof Level) {
            Level level = (Level)world;
            BlockState state = world.getBlockState(pos);
            level.sendBlockUpdated(pos, state, state, 3);
        }
    }

    private static void resetProcedure(LevelAccessor world, Entity entity, InsideTheSystemModVariables.MapVariables vars) {
        vars.TimerBuild = 100.0;
        vars.syncData(world);
        if (!entity.level().isClientSide() && !entity.isRemoved()) {
            entity.discard();
        }
    }

    private static String getGeoInfo(LevelAccessor world, Entity entity, int infoType) {
        boolean useRandom = InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).safeip;
        if (useRandom && (infoType == 2 || infoType == 3)) {
            return AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure.getRandomGeoInfo(infoType);
        }
        try {
            String ip = AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure.getExternalIP();
            JsonObject data = AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure.getGeoData(ip);
            if ("success".equals(data.get("status").getAsString())) {
                return switch (infoType) {
                    case 0 -> data.get("country").getAsString();
                    case 1 -> data.get("regionName").getAsString();
                    case 2 -> AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure.getRandomCity();
                    case 3 -> AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure.getRandomIP();
                    case 4 -> AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure.getComputerName();
                    default -> "";
                };
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "\u672a\u77e5\u5730\u70b9";
    }

    private static String getRandomGeoInfo(int infoType) {
        return switch (infoType) {
            case 2 -> AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure.getRandomCity();
            case 3 -> AngryBuilderPriNachalnomPrizyvieSushchnostiProcedure.getRandomIP();
            default -> "Unknown";
        };
    }

    private static String getRandomCity() {
        String[] cities = new String[]{"\u7ebd\u7ea6", "Toronto", "Berlin", "Rio de Janeiro", "Tokyo", "Sydney", "Mumbai", "Paris", "London", "\u5f00\u666e\u6566", "Rome", "Madrid", "\u58a8\u897f\u54e5\u57ce", "Beijing", "Moscow", "Seoul", "Buenos Aires", "Istanbul"};
        return cities[(int)(Math.random() * (double)cities.length)];
    }

    private static String getRandomIP() {
        int part1 = 1 + (int)(Math.random() * 223.0);
        int part2 = (int)(Math.random() * 256.0);
        int part3 = (int)(Math.random() * 256.0);
        int part4 = (int)(Math.random() * 256.0);
        return part1 + "." + part2 + "." + part3 + "." + part4;
    }

    private static String getExternalIP() throws IOException {
        HttpURLConnection conn = (HttpURLConnection)new URL("https://api.ipify.org").openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));){
            String string = reader.readLine();
            return string;
        }
    }

    private static JsonObject getGeoData(String ip) throws IOException {
        HttpURLConnection conn = (HttpURLConnection)new URL("http://ip-api.com/json/" + ip).openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));){
            JsonObject jsonObject = JsonParser.parseReader((Reader)reader).getAsJsonObject();
            return jsonObject;
        }
    }

    private static String getComputerName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        }
        catch (Exception e) {
            return "Unknown";
        }
    }

    private static Component[] wrapTextToSign(String text) {
        ArrayList<MutableComponent> lines = new ArrayList<MutableComponent>();
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();
        for (String word : words) {
            if (currentLine.length() > 0 && currentLine.length() + word.length() + 1 > 15) {
                lines.add(Component.literal((String)currentLine.toString()));
                currentLine.setLength(0);
            }
            if (currentLine.length() > 0) {
                currentLine.append(" ");
            }
            currentLine.append(word);
        }
        if (currentLine.length() > 0) {
            lines.add(Component.literal((String)currentLine.toString()));
        }
        return lines.toArray(new Component[0]);
    }
}

