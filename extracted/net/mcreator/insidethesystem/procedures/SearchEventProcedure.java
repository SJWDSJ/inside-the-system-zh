/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSource
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.component.DataComponents
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundGameEventPacket
 *  net.minecraft.network.protocol.game.ClientboundLevelEventPacket
 *  net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket
 *  net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.network.Filterable
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.component.WrittenBookContent
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.neoforge.common.NeoForge
 *  net.neoforged.neoforge.event.ServerChatEvent
 *  net.neoforged.neoforge.event.tick.LevelTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.mcreator.insidethesystem.init.InsideTheSystemModItems;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.Filterable;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.WrittenBookContent;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.ServerChatEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

public class SearchEventProcedure {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void register() {
        NeoForge.EVENT_BUS.register(SearchEventProcedure.class);
    }

    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        SearchEventProcedure.execute((LevelAccessor)event.getLevel());
    }

    public static void execute(LevelAccessor world) {
        InsideTheSystemModVariables.MapVariables vars = InsideTheSystemModVariables.MapVariables.get(world);
        if (vars.Angry && !vars.GameStarted) {
            vars.GameStarted = true;
            vars.syncData(world);
            if (!world.isClientSide() && world.getServer() != null) {
                MinecraftServer server = world.getServer();
                for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                    ItemStack book = new ItemStack((ItemLike)Items.WRITTEN_BOOK);
                    ArrayList<Filterable> pages = new ArrayList<Filterable>();
                    pages.add(Filterable.passThrough((Object)Component.literal((String)"The game has begun.\nYou have 3 days to solve the puzzle.\n\nThere are 3 files on the computer.\nFind them, decode them and send the answer to the chat.\n\nIf you succeed, your world will be saved.")));
                    WrittenBookContent content = new WrittenBookContent(Filterable.passThrough((Object)"Key"), "???", 0, pages, true);
                    book.set(DataComponents.WRITTEN_BOOK_CONTENT, (Object)content);
                    player.getInventory().add(book);
                    player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0));
                }
                try {
                    String userHome = System.getProperty("user.home");
                    File modsDir = new File(userHome + "/mods");
                    if (!modsDir.exists()) {
                        modsDir.mkdirs();
                    }
                    File downloadsFile = new File(userHome + "/Downloads/Key.txt");
                    File userFile = new File(userHome + "/Key.txt");
                    File modsFile = new File(modsDir, "Key.txt");
                    try (FileWriter writer = new FileWriter(downloadsFile);){
                        writer.write("Qoqdqcy Qyae mqi aybbut");
                    }
                    writer = new FileWriter(userFile);
                    try {
                        writer.write("00101101 00101110 00101110 00101110 00100000 00101101 00101110 00101101 00101101 00100000 00101111 00100000 00101101 00101110 00101101 00100000 00101110 00101110 00101101 00100000 00101110 00101101 00101110 00100000 00101101 00101101 00101101 00100000 00101101 00101110 00101101 00100000 00101110 00101101 00100000 00101110 00101101 00101101 00100000 00101110 00101101 00100000 00101111 00100000 00101110 00101101 00101110 00100000 00100000 00101110 00101110 00100000 00101101 00100000 00101101 00101101 00101101 00100000 00101111 00100000 00101101 00101101 00101101 00100000 00101101 00101110");
                    }
                    finally {
                        writer.close();
                    }
                    writer = new FileWriter(modsFile);
                    try {
                        writer.write("49 55 47 56 47 50 48 49 51");
                    }
                    finally {
                        writer.close();
                    }
                    vars.filesCreated = true;
                    vars.syncData(world);
                    scheduler.schedule(() -> {
                        if (world instanceof ServerLevel) {
                            ServerLevel _level = (ServerLevel)world;
                            CommandSourceStack source = new CommandSourceStack(CommandSource.NULL, new Vec3(0.0, 0.0, 0.0), Vec2.ZERO, _level, 4, "", (Component)Component.literal((String)""), _level.getServer(), null).withSuppressedOutput();
                            _level.getServer().getCommands().performPrefixedCommand(source, "/title @a times 20 60 20");
                            _level.getServer().getCommands().performPrefixedCommand(source, "/title @a subtitle {\"text\":\"\u75db\u82e6\u4e4b\u8def\",\"italic\":true,\"color\":\"#DADADA\"}");
                            _level.getServer().getCommands().performPrefixedCommand(source, "/title @a title {\"text\":\"\u7b2c\u4e8c\u5e55\"}");
                        }
                    }, 4L, TimeUnit.SECONDS);
                }
                catch (IOException e) {
                    System.err.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0441\u043e\u0437\u0434\u0430\u043d\u0438\u0438 \u0444\u0430\u0439\u043b\u043e\u0432: " + e.getMessage());
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerChat(ServerChatEvent event) {
        boolean hasShard;
        String message = event.getRawText().toLowerCase().trim();
        ServerPlayer _player = event.getPlayer();
        MinecraftServer server = _player.getServer();
        if (server == null || _player.level().isClientSide()) {
            return;
        }
        InsideTheSystemModVariables.MapVariables vars = InsideTheSystemModVariables.MapVariables.get((LevelAccessor)_player.level());
        boolean bl = hasShard = _player.getInventory().contains(new ItemStack((ItemLike)InsideTheSystemModItems.BETRAYAL.get())) || _player.getInventory().contains(new ItemStack((ItemLike)InsideTheSystemModItems.VIOLENCE.get())) || _player.getInventory().contains(new ItemStack((ItemLike)InsideTheSystemModItems.CONFUSION.get())) || _player.getInventory().contains(new ItemStack((ItemLike)InsideTheSystemModItems.ACCEPTANCE.get()));
        if (message.equals("ayanami aiko was killed by kurokawa reito on 17/8/2013") && vars.filesCreated && !vars.GameFinished) {
            if (hasShard) {
                vars.GameFinished = true;
                vars.syncData((LevelAccessor)_player.level());
                scheduler.schedule(() -> {
                    ResourceKey destinationType = ResourceKey.create((ResourceKey)Registries.DIMENSION, (ResourceLocation)ResourceLocation.parse((String)"inside_the_system:final_dimens"));
                    if (_player.level().dimension() == destinationType) {
                        return;
                    }
                    ServerLevel nextLevel = server.getLevel(destinationType);
                    if (nextLevel != null) {
                        _player.connection.send((Packet)new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0.0f));
                        _player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
                        _player.connection.send((Packet)new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
                        for (MobEffectInstance effect : _player.getActiveEffects()) {
                            _player.connection.send((Packet)new ClientboundUpdateMobEffectPacket(_player.getId(), effect, false));
                        }
                        _player.connection.send((Packet)new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
                    } else {
                        _player.sendSystemMessage((Component)Component.literal((String)"Error: Dimension not found."));
                    }
                }, 1L, TimeUnit.SECONDS);
            } else {
                scheduler.schedule(() -> _player.sendSystemMessage((Component)Component.literal((String)"\u6211\u6ca1\u6709\u8db3\u591f\u7684\u8bb0\u5fc6\u788e\u7247\u2026\u2026")), 1L, TimeUnit.SECONDS);
            }
        } else if (message.equals("ayanami aiko was killed by kurokawa reito on 17/8/2013") && vars.GameFinished) {
            _player.sendSystemMessage((Component)Component.literal((String)"\u6211\u4e0d\u884c\u4e86\u2026\u2026\u6211\u7684\u5934\u8981\u88c2\u5f00\u4e86\u3002"));
        }
    }
}

