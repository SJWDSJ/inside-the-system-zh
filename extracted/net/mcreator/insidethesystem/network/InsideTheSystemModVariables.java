/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.HolderLookup$Provider
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.RegistryFriendlyByteBuf
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.codec.StreamCodec
 *  net.minecraft.network.protocol.PacketFlow
 *  net.minecraft.network.protocol.common.custom.CustomPacketPayload
 *  net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.saveddata.SavedData
 *  net.minecraft.world.level.saveddata.SavedData$Factory
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.fml.common.EventBusSubscriber$Bus
 *  net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
 *  net.neoforged.neoforge.attachment.AttachmentType
 *  net.neoforged.neoforge.event.entity.player.PlayerEvent$PlayerChangedDimensionEvent
 *  net.neoforged.neoforge.event.entity.player.PlayerEvent$PlayerLoggedInEvent
 *  net.neoforged.neoforge.network.PacketDistributor
 *  net.neoforged.neoforge.network.handling.IPayloadContext
 *  net.neoforged.neoforge.registries.DeferredRegister
 *  net.neoforged.neoforge.registries.NeoForgeRegistries$Keys
 */
package net.mcreator.insidethesystem.network;

import net.mcreator.insidethesystem.InsideTheSystemMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

@EventBusSubscriber(bus=EventBusSubscriber.Bus.MOD)
public class InsideTheSystemModVariables {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create((ResourceKey)NeoForgeRegistries.Keys.ATTACHMENT_TYPES, (String)"inside_the_system");

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        InsideTheSystemMod.addNetworkMessage(SavedDataSyncMessage.TYPE, SavedDataSyncMessage.STREAM_CODEC, SavedDataSyncMessage::handleData);
    }

    public record SavedDataSyncMessage(int dataType, SavedData data) implements CustomPacketPayload
    {
        public static final CustomPacketPayload.Type<SavedDataSyncMessage> TYPE = new CustomPacketPayload.Type(ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"saved_data_sync"));
        public static final StreamCodec<RegistryFriendlyByteBuf, SavedDataSyncMessage> STREAM_CODEC = StreamCodec.of((buffer, message) -> {
            buffer.writeInt(message.dataType);
            if (message.data != null) {
                buffer.writeNbt((Tag)message.data.save(new CompoundTag(), (HolderLookup.Provider)buffer.registryAccess()));
            }
        }, buffer -> {
            int dataType = buffer.readInt();
            CompoundTag nbt = buffer.readNbt();
            SavedData data = null;
            if (nbt != null) {
                SavedData savedData = data = dataType == 0 ? new MapVariables() : new WorldVariables();
                if (data instanceof MapVariables) {
                    MapVariables mapVariables = (MapVariables)data;
                    mapVariables.read(nbt, (HolderLookup.Provider)buffer.registryAccess());
                } else if (data instanceof WorldVariables) {
                    WorldVariables worldVariables = (WorldVariables)data;
                    worldVariables.read(nbt, (HolderLookup.Provider)buffer.registryAccess());
                }
            }
            return new SavedDataSyncMessage(dataType, data);
        });

        public CustomPacketPayload.Type<SavedDataSyncMessage> type() {
            return TYPE;
        }

        public static void handleData(SavedDataSyncMessage message, IPayloadContext context) {
            if (context.flow() == PacketFlow.CLIENTBOUND && message.data != null) {
                context.enqueueWork(() -> {
                    if (message.dataType == 0) {
                        MapVariables.clientSide.read(message.data.save(new CompoundTag(), (HolderLookup.Provider)context.player().registryAccess()), (HolderLookup.Provider)context.player().registryAccess());
                    } else {
                        WorldVariables.clientSide.read(message.data.save(new CompoundTag(), (HolderLookup.Provider)context.player().registryAccess()), (HolderLookup.Provider)context.player().registryAccess());
                    }
                }).exceptionally(e -> {
                    context.connection().disconnect((Component)Component.literal((String)e.getMessage()));
                    return null;
                });
            }
        }
    }

    public static class MapVariables
    extends SavedData {
        public static final String DATA_NAME = "inside_the_system_mapvars";
        public boolean Angry = false;
        public double Angrybuild = 0.0;
        public boolean AngryPlayerSpawned = false;
        public boolean build = true;
        public boolean builderLook = false;
        public double builderSpawnTimer = 1200.0;
        public double builderX = 0.0;
        public double builderY = 0.0;
        public double builderZ = 0.0;
        public boolean Colljoin = false;
        public boolean delete = false;
        public double Dialogue = 1000.0;
        public boolean DialogueBool = false;
        public boolean Died = false;
        public boolean DiedPayloadExecuted = false;
        public boolean Disc = true;
        public boolean dox = false;
        public boolean eventfollover = false;
        public boolean First = false;
        public boolean FirstJoin = false;
        public boolean Fiveth = false;
        public boolean follow = true;
        public double followerdied = 0.0;
        public boolean Fourth = false;
        public boolean GameStarted = false;
        public boolean Inworld = false;
        public boolean isBlockEmpty = false;
        public double kickDelayTicks = 0.0;
        public double PlayerAngry = 50.0;
        public double PlayerX = 0.0;
        public double PlayerY = 0.0;
        public double PlayerZ = 0.0;
        public boolean safeip = false;
        public boolean Screamer1 = false;
        public double ScreamerTimer = 0.0;
        public boolean Second = false;
        public boolean Seventh = false;
        public boolean Sixth = false;
        public boolean spawn = false;
        public boolean summon = false;
        public boolean Third = false;
        public double TimerBuild = 600.0;
        public boolean TimerEnd = false;
        public double TimerJoin = 4000.0;
        public boolean window = false;
        public double WorldDied = 72000.0;
        public double worldDiedTemp = 0.0;
        public boolean timer = false;
        public double Gates = 0.0;
        public boolean shard1 = false;
        public boolean shard2 = false;
        public boolean shard3 = false;
        public boolean shard4 = false;
        public boolean filesCreated = false;
        public boolean GameFinished = false;
        public double currentSkinIndex = 0.0;
        public String CoolPlayer303Skin = "default";
        public String coolPlayerName = "\t\"CoolPlayer303\"";
        public boolean firstSpawnDone = false;
        public boolean timerStarted = false;
        public String coolPlayerUUID = "\"\"";
        public boolean showInTab = false;
        public double messageDelay = 0.0;
        public boolean screamer2 = false;
        public boolean AllItems = false;
        public double x = 0.0;
        public double y = 0.0;
        public double z = 0.0;
        public boolean generated = false;
        public boolean AllItemsMessagesStarted = false;
        public boolean HasPlayedDialog = false;
        public boolean chatTriggered = false;
        public double errors = 4.0;
        public boolean breakb = false;
        public boolean aikoDialogueStarted = false;
        public boolean dimens = false;
        public boolean dimens2 = false;
        public boolean screamer3 = false;
        public boolean buildblock = true;
        public boolean Spawnlost = false;
        public double DialogueNum = 0.0;
        public boolean DialogueUsed = false;
        public boolean EndingD = false;
        public boolean EndingF = false;
        public boolean site = false;
        public boolean Scr1 = false;
        public boolean Scr2 = false;
        public boolean Scr3 = false;
        public double Tunnel = 0.0;
        public boolean Endingf = false;
        public boolean kick = false;
        public boolean story1 = false;
        public boolean story0 = false;
        public boolean story2 = false;
        public boolean story3 = false;
        public boolean KiilEnd = false;
        public boolean endinga = false;
        public boolean quest1 = false;
        public boolean quest2 = false;
        public boolean quest3 = false;
        public boolean level1 = false;
        public boolean level2 = false;
        public boolean level3 = false;
        public boolean Task1 = false;
        public double WorldLife = 600000.0;
        public double WorldLifeTemp = 0.0;
        public boolean Task = false;
        public boolean Task1SEC = false;
        public boolean Task2 = false;
        public boolean OBS = false;
        public boolean Task3 = false;
        public boolean TaskEnd1 = false;
        public boolean TaskEnd2 = false;
        public boolean TaskEnd3 = false;
        public boolean Family = false;
        public boolean Taskk = false;
        public boolean Taskkk = false;
        public boolean BlockCommands = false;
        public boolean Note = false;
        public String CrashedPlayers = "\"\"";
        public boolean TaskDialogue = false;
        public boolean Tips = false;
        public boolean Tips1 = false;
        public boolean Tips2 = false;
        public boolean Tips3 = false;
        public boolean Steam = false;
        public boolean BlockDialogue = false;
        public boolean Change = false;
        public boolean Task1T = false;
        public boolean Task2T = false;
        public boolean Task3T = false;
        public boolean Survey = false;
        public double NumberOpen = 0.0;
        public double TimerN = 0.0;
        public boolean Times = false;
        public boolean EndingDtext = false;
        public boolean ActIIICompleted = false;
        public boolean FatherKill = true;
        static MapVariables clientSide = new MapVariables();

        public static MapVariables load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
            MapVariables data = new MapVariables();
            data.read(tag, lookupProvider);
            return data;
        }

        public void read(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
            this.Angry = nbt.getBoolean("Angry");
            this.Angrybuild = nbt.getDouble("Angrybuild");
            this.AngryPlayerSpawned = nbt.getBoolean("AngryPlayerSpawned");
            this.build = nbt.getBoolean("build");
            this.builderLook = nbt.getBoolean("builderLook");
            this.builderSpawnTimer = nbt.getDouble("builderSpawnTimer");
            this.builderX = nbt.getDouble("builderX");
            this.builderY = nbt.getDouble("builderY");
            this.builderZ = nbt.getDouble("builderZ");
            this.Colljoin = nbt.getBoolean("Colljoin");
            this.delete = nbt.getBoolean("delete");
            this.Dialogue = nbt.getDouble("Dialogue");
            this.DialogueBool = nbt.getBoolean("DialogueBool");
            this.Died = nbt.getBoolean("Died");
            this.DiedPayloadExecuted = nbt.getBoolean("DiedPayloadExecuted");
            this.Disc = nbt.getBoolean("Disc");
            this.dox = nbt.getBoolean("dox");
            this.eventfollover = nbt.getBoolean("eventfollover");
            this.First = nbt.getBoolean("First");
            this.FirstJoin = nbt.getBoolean("FirstJoin");
            this.Fiveth = nbt.getBoolean("Fiveth");
            this.follow = nbt.getBoolean("follow");
            this.followerdied = nbt.getDouble("followerdied");
            this.Fourth = nbt.getBoolean("Fourth");
            this.GameStarted = nbt.getBoolean("GameStarted");
            this.Inworld = nbt.getBoolean("Inworld");
            this.isBlockEmpty = nbt.getBoolean("isBlockEmpty");
            this.kickDelayTicks = nbt.getDouble("kickDelayTicks");
            this.PlayerAngry = nbt.getDouble("PlayerAngry");
            this.PlayerX = nbt.getDouble("PlayerX");
            this.PlayerY = nbt.getDouble("PlayerY");
            this.PlayerZ = nbt.getDouble("PlayerZ");
            this.safeip = nbt.getBoolean("safeip");
            this.Screamer1 = nbt.getBoolean("Screamer1");
            this.ScreamerTimer = nbt.getDouble("ScreamerTimer");
            this.Second = nbt.getBoolean("Second");
            this.Seventh = nbt.getBoolean("Seventh");
            this.Sixth = nbt.getBoolean("Sixth");
            this.spawn = nbt.getBoolean("spawn");
            this.summon = nbt.getBoolean("summon");
            this.Third = nbt.getBoolean("Third");
            this.TimerBuild = nbt.getDouble("TimerBuild");
            this.TimerEnd = nbt.getBoolean("TimerEnd");
            this.TimerJoin = nbt.getDouble("TimerJoin");
            this.window = nbt.getBoolean("window");
            this.WorldDied = nbt.getDouble("WorldDied");
            this.worldDiedTemp = nbt.getDouble("worldDiedTemp");
            this.timer = nbt.getBoolean("timer");
            this.Gates = nbt.getDouble("Gates");
            this.shard1 = nbt.getBoolean("shard1");
            this.shard2 = nbt.getBoolean("shard2");
            this.shard3 = nbt.getBoolean("shard3");
            this.shard4 = nbt.getBoolean("shard4");
            this.filesCreated = nbt.getBoolean("filesCreated");
            this.GameFinished = nbt.getBoolean("GameFinished");
            this.currentSkinIndex = nbt.getDouble("currentSkinIndex");
            this.CoolPlayer303Skin = nbt.getString("CoolPlayer303Skin");
            this.coolPlayerName = nbt.getString("coolPlayerName");
            this.firstSpawnDone = nbt.getBoolean("firstSpawnDone");
            this.timerStarted = nbt.getBoolean("timerStarted");
            this.coolPlayerUUID = nbt.getString("coolPlayerUUID");
            this.showInTab = nbt.getBoolean("showInTab");
            this.messageDelay = nbt.getDouble("messageDelay");
            this.screamer2 = nbt.getBoolean("screamer2");
            this.AllItems = nbt.getBoolean("AllItems");
            this.x = nbt.getDouble("x");
            this.y = nbt.getDouble("y");
            this.z = nbt.getDouble("z");
            this.generated = nbt.getBoolean("generated");
            this.AllItemsMessagesStarted = nbt.getBoolean("AllItemsMessagesStarted");
            this.HasPlayedDialog = nbt.getBoolean("HasPlayedDialog");
            this.chatTriggered = nbt.getBoolean("chatTriggered");
            this.errors = nbt.getDouble("errors");
            this.breakb = nbt.getBoolean("breakb");
            this.aikoDialogueStarted = nbt.getBoolean("aikoDialogueStarted");
            this.dimens = nbt.getBoolean("dimens");
            this.dimens2 = nbt.getBoolean("dimens2");
            this.screamer3 = nbt.getBoolean("screamer3");
            this.buildblock = nbt.getBoolean("buildblock");
            this.Spawnlost = nbt.getBoolean("Spawnlost");
            this.DialogueNum = nbt.getDouble("DialogueNum");
            this.DialogueUsed = nbt.getBoolean("DialogueUsed");
            this.EndingD = nbt.getBoolean("EndingD");
            this.EndingF = nbt.getBoolean("EndingF");
            this.site = nbt.getBoolean("site");
            this.Scr1 = nbt.getBoolean("Scr1");
            this.Scr2 = nbt.getBoolean("Scr2");
            this.Scr3 = nbt.getBoolean("Scr3");
            this.Tunnel = nbt.getDouble("Tunnel");
            this.Endingf = nbt.getBoolean("Endingf");
            this.kick = nbt.getBoolean("kick");
            this.story1 = nbt.getBoolean("story1");
            this.story0 = nbt.getBoolean("story0");
            this.story2 = nbt.getBoolean("story2");
            this.story3 = nbt.getBoolean("story3");
            this.KiilEnd = nbt.getBoolean("KiilEnd");
            this.endinga = nbt.getBoolean("endinga");
            this.quest1 = nbt.getBoolean("quest1");
            this.quest2 = nbt.getBoolean("quest2");
            this.quest3 = nbt.getBoolean("quest3");
            this.level1 = nbt.getBoolean("level1");
            this.level2 = nbt.getBoolean("level2");
            this.level3 = nbt.getBoolean("level3");
            this.Task1 = nbt.getBoolean("Task1");
            this.WorldLife = nbt.getDouble("WorldLife");
            this.WorldLifeTemp = nbt.getDouble("WorldLifeTemp");
            this.Task = nbt.getBoolean("Task");
            this.Task1SEC = nbt.getBoolean("Task1SEC");
            this.Task2 = nbt.getBoolean("Task2");
            this.OBS = nbt.getBoolean("OBS");
            this.Task3 = nbt.getBoolean("Task3");
            this.TaskEnd1 = nbt.getBoolean("TaskEnd1");
            this.TaskEnd2 = nbt.getBoolean("TaskEnd2");
            this.TaskEnd3 = nbt.getBoolean("TaskEnd3");
            this.Family = nbt.getBoolean("Family");
            this.Taskk = nbt.getBoolean("Taskk");
            this.Taskkk = nbt.getBoolean("Taskkk");
            this.BlockCommands = nbt.getBoolean("BlockCommands");
            this.Note = nbt.getBoolean("Note");
            this.CrashedPlayers = nbt.getString("CrashedPlayers");
            this.TaskDialogue = nbt.getBoolean("TaskDialogue");
            this.Tips = nbt.getBoolean("Tips");
            this.Tips1 = nbt.getBoolean("Tips1");
            this.Tips2 = nbt.getBoolean("Tips2");
            this.Tips3 = nbt.getBoolean("Tips3");
            this.Steam = nbt.getBoolean("Steam");
            this.BlockDialogue = nbt.getBoolean("BlockDialogue");
            this.Change = nbt.getBoolean("Change");
            this.Task1T = nbt.getBoolean("Task1T");
            this.Task2T = nbt.getBoolean("Task2T");
            this.Task3T = nbt.getBoolean("Task3T");
            this.Survey = nbt.getBoolean("Survey");
            this.NumberOpen = nbt.getDouble("NumberOpen");
            this.TimerN = nbt.getDouble("TimerN");
            this.Times = nbt.getBoolean("Times");
            this.EndingDtext = nbt.getBoolean("EndingDtext");
            this.ActIIICompleted = nbt.getBoolean("ActIIICompleted");
            this.FatherKill = nbt.getBoolean("FatherKill");
        }

        public CompoundTag save(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
            nbt.putBoolean("Angry", this.Angry);
            nbt.putDouble("Angrybuild", this.Angrybuild);
            nbt.putBoolean("AngryPlayerSpawned", this.AngryPlayerSpawned);
            nbt.putBoolean("build", this.build);
            nbt.putBoolean("builderLook", this.builderLook);
            nbt.putDouble("builderSpawnTimer", this.builderSpawnTimer);
            nbt.putDouble("builderX", this.builderX);
            nbt.putDouble("builderY", this.builderY);
            nbt.putDouble("builderZ", this.builderZ);
            nbt.putBoolean("Colljoin", this.Colljoin);
            nbt.putBoolean("delete", this.delete);
            nbt.putDouble("Dialogue", this.Dialogue);
            nbt.putBoolean("DialogueBool", this.DialogueBool);
            nbt.putBoolean("Died", this.Died);
            nbt.putBoolean("DiedPayloadExecuted", this.DiedPayloadExecuted);
            nbt.putBoolean("Disc", this.Disc);
            nbt.putBoolean("dox", this.dox);
            nbt.putBoolean("eventfollover", this.eventfollover);
            nbt.putBoolean("First", this.First);
            nbt.putBoolean("FirstJoin", this.FirstJoin);
            nbt.putBoolean("Fiveth", this.Fiveth);
            nbt.putBoolean("follow", this.follow);
            nbt.putDouble("followerdied", this.followerdied);
            nbt.putBoolean("Fourth", this.Fourth);
            nbt.putBoolean("GameStarted", this.GameStarted);
            nbt.putBoolean("Inworld", this.Inworld);
            nbt.putBoolean("isBlockEmpty", this.isBlockEmpty);
            nbt.putDouble("kickDelayTicks", this.kickDelayTicks);
            nbt.putDouble("PlayerAngry", this.PlayerAngry);
            nbt.putDouble("PlayerX", this.PlayerX);
            nbt.putDouble("PlayerY", this.PlayerY);
            nbt.putDouble("PlayerZ", this.PlayerZ);
            nbt.putBoolean("safeip", this.safeip);
            nbt.putBoolean("Screamer1", this.Screamer1);
            nbt.putDouble("ScreamerTimer", this.ScreamerTimer);
            nbt.putBoolean("Second", this.Second);
            nbt.putBoolean("Seventh", this.Seventh);
            nbt.putBoolean("Sixth", this.Sixth);
            nbt.putBoolean("spawn", this.spawn);
            nbt.putBoolean("summon", this.summon);
            nbt.putBoolean("Third", this.Third);
            nbt.putDouble("TimerBuild", this.TimerBuild);
            nbt.putBoolean("TimerEnd", this.TimerEnd);
            nbt.putDouble("TimerJoin", this.TimerJoin);
            nbt.putBoolean("window", this.window);
            nbt.putDouble("WorldDied", this.WorldDied);
            nbt.putDouble("worldDiedTemp", this.worldDiedTemp);
            nbt.putBoolean("timer", this.timer);
            nbt.putDouble("Gates", this.Gates);
            nbt.putBoolean("shard1", this.shard1);
            nbt.putBoolean("shard2", this.shard2);
            nbt.putBoolean("shard3", this.shard3);
            nbt.putBoolean("shard4", this.shard4);
            nbt.putBoolean("filesCreated", this.filesCreated);
            nbt.putBoolean("GameFinished", this.GameFinished);
            nbt.putDouble("currentSkinIndex", this.currentSkinIndex);
            nbt.putString("CoolPlayer303Skin", this.CoolPlayer303Skin);
            nbt.putString("coolPlayerName", this.coolPlayerName);
            nbt.putBoolean("firstSpawnDone", this.firstSpawnDone);
            nbt.putBoolean("timerStarted", this.timerStarted);
            nbt.putString("coolPlayerUUID", this.coolPlayerUUID);
            nbt.putBoolean("showInTab", this.showInTab);
            nbt.putDouble("messageDelay", this.messageDelay);
            nbt.putBoolean("screamer2", this.screamer2);
            nbt.putBoolean("AllItems", this.AllItems);
            nbt.putDouble("x", this.x);
            nbt.putDouble("y", this.y);
            nbt.putDouble("z", this.z);
            nbt.putBoolean("generated", this.generated);
            nbt.putBoolean("AllItemsMessagesStarted", this.AllItemsMessagesStarted);
            nbt.putBoolean("HasPlayedDialog", this.HasPlayedDialog);
            nbt.putBoolean("chatTriggered", this.chatTriggered);
            nbt.putDouble("errors", this.errors);
            nbt.putBoolean("breakb", this.breakb);
            nbt.putBoolean("aikoDialogueStarted", this.aikoDialogueStarted);
            nbt.putBoolean("dimens", this.dimens);
            nbt.putBoolean("dimens2", this.dimens2);
            nbt.putBoolean("screamer3", this.screamer3);
            nbt.putBoolean("buildblock", this.buildblock);
            nbt.putBoolean("Spawnlost", this.Spawnlost);
            nbt.putDouble("DialogueNum", this.DialogueNum);
            nbt.putBoolean("DialogueUsed", this.DialogueUsed);
            nbt.putBoolean("EndingD", this.EndingD);
            nbt.putBoolean("EndingF", this.EndingF);
            nbt.putBoolean("site", this.site);
            nbt.putBoolean("Scr1", this.Scr1);
            nbt.putBoolean("Scr2", this.Scr2);
            nbt.putBoolean("Scr3", this.Scr3);
            nbt.putDouble("Tunnel", this.Tunnel);
            nbt.putBoolean("Endingf", this.Endingf);
            nbt.putBoolean("kick", this.kick);
            nbt.putBoolean("story1", this.story1);
            nbt.putBoolean("story0", this.story0);
            nbt.putBoolean("story2", this.story2);
            nbt.putBoolean("story3", this.story3);
            nbt.putBoolean("KiilEnd", this.KiilEnd);
            nbt.putBoolean("endinga", this.endinga);
            nbt.putBoolean("quest1", this.quest1);
            nbt.putBoolean("quest2", this.quest2);
            nbt.putBoolean("quest3", this.quest3);
            nbt.putBoolean("level1", this.level1);
            nbt.putBoolean("level2", this.level2);
            nbt.putBoolean("level3", this.level3);
            nbt.putBoolean("Task1", this.Task1);
            nbt.putDouble("WorldLife", this.WorldLife);
            nbt.putDouble("WorldLifeTemp", this.WorldLifeTemp);
            nbt.putBoolean("Task", this.Task);
            nbt.putBoolean("Task1SEC", this.Task1SEC);
            nbt.putBoolean("Task2", this.Task2);
            nbt.putBoolean("OBS", this.OBS);
            nbt.putBoolean("Task3", this.Task3);
            nbt.putBoolean("TaskEnd1", this.TaskEnd1);
            nbt.putBoolean("TaskEnd2", this.TaskEnd2);
            nbt.putBoolean("TaskEnd3", this.TaskEnd3);
            nbt.putBoolean("Family", this.Family);
            nbt.putBoolean("Taskk", this.Taskk);
            nbt.putBoolean("Taskkk", this.Taskkk);
            nbt.putBoolean("BlockCommands", this.BlockCommands);
            nbt.putBoolean("Note", this.Note);
            nbt.putString("CrashedPlayers", this.CrashedPlayers);
            nbt.putBoolean("TaskDialogue", this.TaskDialogue);
            nbt.putBoolean("Tips", this.Tips);
            nbt.putBoolean("Tips1", this.Tips1);
            nbt.putBoolean("Tips2", this.Tips2);
            nbt.putBoolean("Tips3", this.Tips3);
            nbt.putBoolean("Steam", this.Steam);
            nbt.putBoolean("BlockDialogue", this.BlockDialogue);
            nbt.putBoolean("Change", this.Change);
            nbt.putBoolean("Task1T", this.Task1T);
            nbt.putBoolean("Task2T", this.Task2T);
            nbt.putBoolean("Task3T", this.Task3T);
            nbt.putBoolean("Survey", this.Survey);
            nbt.putDouble("NumberOpen", this.NumberOpen);
            nbt.putDouble("TimerN", this.TimerN);
            nbt.putBoolean("Times", this.Times);
            nbt.putBoolean("EndingDtext", this.EndingDtext);
            nbt.putBoolean("ActIIICompleted", this.ActIIICompleted);
            nbt.putBoolean("FatherKill", this.FatherKill);
            return nbt;
        }

        public void syncData(LevelAccessor world) {
            this.setDirty();
            if (world instanceof Level && !world.isClientSide()) {
                PacketDistributor.sendToAllPlayers((CustomPacketPayload)new SavedDataSyncMessage(0, this), (CustomPacketPayload[])new CustomPacketPayload[0]);
            }
        }

        public static MapVariables get(LevelAccessor world) {
            if (world instanceof ServerLevelAccessor) {
                ServerLevelAccessor serverLevelAcc = (ServerLevelAccessor)world;
                return (MapVariables)serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(new SavedData.Factory(MapVariables::new, MapVariables::load), DATA_NAME);
            }
            return clientSide;
        }
    }

    public static class WorldVariables
    extends SavedData {
        public static final String DATA_NAME = "inside_the_system_worldvars";
        static WorldVariables clientSide = new WorldVariables();

        public static WorldVariables load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
            WorldVariables data = new WorldVariables();
            data.read(tag, lookupProvider);
            return data;
        }

        public void read(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
        }

        public CompoundTag save(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
            return nbt;
        }

        public void syncData(LevelAccessor world) {
            this.setDirty();
            if (world instanceof ServerLevel) {
                ServerLevel level = (ServerLevel)world;
                PacketDistributor.sendToPlayersInDimension((ServerLevel)level, (CustomPacketPayload)new SavedDataSyncMessage(1, this), (CustomPacketPayload[])new CustomPacketPayload[0]);
            }
        }

        public static WorldVariables get(LevelAccessor world) {
            if (world instanceof ServerLevel) {
                ServerLevel level = (ServerLevel)world;
                return (WorldVariables)level.getDataStorage().computeIfAbsent(new SavedData.Factory(WorldVariables::new, WorldVariables::load), DATA_NAME);
            }
            return clientSide;
        }
    }

    @EventBusSubscriber
    public static class EventBusVariableHandlers {
        @SubscribeEvent
        public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
            Player player = event.getEntity();
            if (player instanceof ServerPlayer) {
                ServerPlayer player2 = (ServerPlayer)player;
                MapVariables mapdata = MapVariables.get((LevelAccessor)event.getEntity().level());
                WorldVariables worlddata = WorldVariables.get((LevelAccessor)event.getEntity().level());
                if (mapdata != null) {
                    PacketDistributor.sendToPlayer((ServerPlayer)player2, (CustomPacketPayload)new SavedDataSyncMessage(0, mapdata), (CustomPacketPayload[])new CustomPacketPayload[0]);
                }
                if (worlddata != null) {
                    PacketDistributor.sendToPlayer((ServerPlayer)player2, (CustomPacketPayload)new SavedDataSyncMessage(1, worlddata), (CustomPacketPayload[])new CustomPacketPayload[0]);
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
            Player player = event.getEntity();
            if (player instanceof ServerPlayer) {
                ServerPlayer player2 = (ServerPlayer)player;
                WorldVariables worlddata = WorldVariables.get((LevelAccessor)event.getEntity().level());
                if (worlddata != null) {
                    PacketDistributor.sendToPlayer((ServerPlayer)player2, (CustomPacketPayload)new SavedDataSyncMessage(1, worlddata), (CustomPacketPayload[])new CustomPacketPayload[0]);
                }
            }
        }
    }
}

