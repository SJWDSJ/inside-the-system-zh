/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.neoforged.neoforge.registries.DeferredHolder
 *  net.neoforged.neoforge.registries.DeferredRegister
 */
package net.mcreator.insidethesystem.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InsideTheSystemModSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create((ResourceKey)Registries.SOUND_EVENT, (String)"inside_the_system");
    public static final DeferredHolder<SoundEvent, SoundEvent> SCREAMER1 = REGISTRY.register("screamer1", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"screamer1")));
    public static final DeferredHolder<SoundEvent, SoundEvent> BUILDERSPAWN = REGISTRY.register("builderspawn", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"builderspawn")));
    public static final DeferredHolder<SoundEvent, SoundEvent> MUSICBOX = REGISTRY.register("musicbox", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"musicbox")));
    public static final DeferredHolder<SoundEvent, SoundEvent> END = REGISTRY.register("end", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"end")));
    public static final DeferredHolder<SoundEvent, SoundEvent> BEGINING = REGISTRY.register("begining", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"begining")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ACTIVATE = REGISTRY.register("activate", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"activate")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SHARDS = REGISTRY.register("shards", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"shards")));
    public static final DeferredHolder<SoundEvent, SoundEvent> BOOD = REGISTRY.register("bood", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"bood")));
    public static final DeferredHolder<SoundEvent, SoundEvent> MEMORIES = REGISTRY.register("memories", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"memories")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ERROR = REGISTRY.register("error", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"error")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ACCEPT = REGISTRY.register("accept", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"accept")));
    public static final DeferredHolder<SoundEvent, SoundEvent> MAINOST = REGISTRY.register("mainost", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"mainost")));
    public static final DeferredHolder<SoundEvent, SoundEvent> PSWSCREAMER = REGISTRY.register("pswscreamer", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"pswscreamer")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SPAWNFOLLOWER = REGISTRY.register("spawnfollower", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"spawnfollower")));
    public static final DeferredHolder<SoundEvent, SoundEvent> AIKO = REGISTRY.register("aiko", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"aiko")));
    public static final DeferredHolder<SoundEvent, SoundEvent> AMBIENTGOODDIMENS = REGISTRY.register("ambientgooddimens", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"ambientgooddimens")));
    public static final DeferredHolder<SoundEvent, SoundEvent> AMBIENT2 = REGISTRY.register("ambient2", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"ambient2")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ENDINGD = REGISTRY.register("endingd", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"endingd")));
    public static final DeferredHolder<SoundEvent, SoundEvent> BROKENBOX = REGISTRY.register("brokenbox", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"brokenbox")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ENDINGFSCREAMERS = REGISTRY.register("endingfscreamers", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"endingfscreamers")));
    public static final DeferredHolder<SoundEvent, SoundEvent> TUNNELSOUND = REGISTRY.register("tunnelsound", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"tunnelsound")));
    public static final DeferredHolder<SoundEvent, SoundEvent> OPENMEMORY = REGISTRY.register("openmemory", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"openmemory")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RAVEN = REGISTRY.register("raven", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"raven")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ENDINGAAMBIENT = REGISTRY.register("endingaambient", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"endingaambient")));
    public static final DeferredHolder<SoundEvent, SoundEvent> LIGHT = REGISTRY.register("light", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"light")));
    public static final DeferredHolder<SoundEvent, SoundEvent> FATHERSPAWN = REGISTRY.register("fatherspawn", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"fatherspawn")));
    public static final DeferredHolder<SoundEvent, SoundEvent> RISESOUND = REGISTRY.register("risesound", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"risesound")));
    public static final DeferredHolder<SoundEvent, SoundEvent> WOOSH = REGISTRY.register("woosh", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"woosh")));
    public static final DeferredHolder<SoundEvent, SoundEvent> MUSICBOXATTAC = REGISTRY.register("musicboxattac", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"musicboxattac")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ENDINGC = REGISTRY.register("endingc", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"endingc")));
    public static final DeferredHolder<SoundEvent, SoundEvent> TITLES = REGISTRY.register("titles", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"titles")));
    public static final DeferredHolder<SoundEvent, SoundEvent> BEHIND = REGISTRY.register("behind", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"behind")));
    public static final DeferredHolder<SoundEvent, SoundEvent> BEHINDSCREAMER = REGISTRY.register("behindscreamer", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"behindscreamer")));
    public static final DeferredHolder<SoundEvent, SoundEvent> PHOTO = REGISTRY.register("photo", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"photo")));
    public static final DeferredHolder<SoundEvent, SoundEvent> THESUN = REGISTRY.register("thesun", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"thesun")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SPAWNPLAYER = REGISTRY.register("spawnplayer", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"spawnplayer")));
    public static final DeferredHolder<SoundEvent, SoundEvent> CHANGE = REGISTRY.register("change", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"change")));
    public static final DeferredHolder<SoundEvent, SoundEvent> WRITE = REGISTRY.register("write", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"write")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SMILE = REGISTRY.register("smile", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"smile")));
    public static final DeferredHolder<SoundEvent, SoundEvent> GLITCH = REGISTRY.register("glitch", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"glitch")));
    public static final DeferredHolder<SoundEvent, SoundEvent> SHAGI = REGISTRY.register("shagi", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath((String)"inside_the_system", (String)"shagi")));
}

