/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.neoforge.common.NeoForge
 *  net.neoforged.neoforge.event.tick.LevelTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import java.util.List;
import java.util.Random;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

public class DialogueAngryProcedure {
    private static long lastTick = 0L;
    private static final List<String> glitchedMessages = List.of("\u306a\u306b\uff71#@!?\u3042\u308c\u3001do you see the star\u223d\u223d", "\u30a4\u30c6\u2026\u97f3\u304c\u30a6\u30eb\u30b5\uff72", "\u4ed6\u8bf4\u4e86'\u8ddf\u4e0a'\uff0c\u4f46\u6ca1\u6709\u56de\u5934\u8def\u3002", "\u30a8\u30e9\u30fc#@\u6587\u5b57\u5316\u3051\u2026", "\u6551\u6551\u6211\u3002 \uff90\uff90\uff90\uff90\uff90", "\u3069\u3053\u306b\u884c\u3051\u3070\u3044\u3044\uff1f", "\u4ec0\u4e48\u90fd\u4e0d\u771f\u5b9e\uff01\uff01\uff01@#%", "ERROR: ENTITY_\u2588\u2588\u2588", "\u5f71\u2026\u898b\u3048\u305f\uff1f", "\u5922\u3060\u3063\u305f\u6c17\u304c\uff7d\u308b", "\u76ee\u304c\u899a\u3081\u306a\uff72", "\u79c1\u3001\u8a18\u61b6\u304c\u5909\u306b\u306a\u3063\u3066\u308b", "ShadowEnder..\u4f55\u3092\u3057\u305f\u306e", "\u7b11\u3063\u3066\u308b...\u3042\u308c\u306f\u4eba\u3058\u3083\u306a\u3044", "\u2588\u031b\u030d\u0362You \u1d43\u02b3\u1d49 \u207f\u1d52\u1d57 \u02e2\u1d43\u1da0\u1d49\u2588", "\u4f60\u2026\u2026\u4e5f\u574f\u6389\u4e86\u5417\uff1f", "\u30a6\u30ef\u30a1~ glitchglitchglitch", "msg@##lost##user::", "\u4f60\u770b\u5230\u4e86\u5417\uff1f", "Z\u0338\u0322\u0356\u0355\u0359\u0317\u0359\u0359\u0330\u0356\u0329\u0313\u0307\u034b\u030b\u030f\u0310\u0309\u0342\u035b\u0306\u035b\u031a\u035c\u0360\u035d\u0229\u0336\u0328\u031c\u031f\u034d\u032c\u0356\u0333\u031f\u0353\u0326\u032a\u033b\u0329\u032c\u0332\u0359\u031e\u034a\u030e\u0350\u034a\u0303\u034c\u0301\u0301\u030d\u034a\u0302\u035d\u0345n\u0334\u0321\u0324\u0326\u0359\u0359\u0316\u0331\u0349\u031e\u0331\u0353\u033b\u032f\u0320\u0329\u0353\u033e\u030f\u0313\u0300\u0310\u0346\u0313\u035b\u0310\u030d\u0300\u031a\u0345", "\u76ee\u3092\u5408\u308f\u305b\u306a\u3044\u3067", "\u8ab0\u304b\u2026\uff1f", "\u3090\u3091\u3091\u3091\u2026", "\u2588\u0337\u0322\u031b\u0329\u033b\u0319\u0329\u031f\u032c\u034d\u033c\u0329\u0339\u032c\u0304\u0312\u0308\u0301\u034a\u0312\u0313\u0306\u0300\u0350\u0350\u034a\u030c\u0310\u0306\u0315\u031a\u031a\u035d\u035d\u0345", "\u54c8\u54c8\u2026\u2026\u6709\u8da3\u7684\u9519\u8bef", "\u4ed6\u5728\u65b9\u5757\u91cc\u3002\u5728\u5939\u7f1d\u4e2d\u3002", "\u3042\u306a\u305f\u306f\u307e\u3060\u751f\u304d\u3066\u308b\uff1f", "\u0489W\u0489H\u0489E\u0489R\u0489E\u0489 \u0335\u0359\u0325\u034e\u0319\u0318\u032f\u033a\u0332\u034e\u0318\u0332\u0339\u032c\u0317\u031c\u034a\u0313\u034a\u034b\u0342\u030b\u030d\u035dI\u0335\u0354\u0317\u0316\u0359\u0348\u032f\u0356\u0332\u0324\u0353\u0347\u0348\u0320\u031e\u0350\u0351\u0308\u0301\u0300\u0342\u0346\u0306\u0308\u035d\u0345\u0218\u0336\u0320\u0354\u031e\u0332\u031c\u0313\u0351\u0342\u0308\u033f\u0308\u0315\u0360\u0360", "\u30b3\u30b3\u30cb\u30a4\u30bf\u30cf\u30ba\u2026", "\u770b\u770b\u4f60\u8eab\u540e", "W\u0335\u0356\u0333\u032c\u034e\u033b\u031d\u034c\u0305\u0309\u034b\u0350\u0313\u0313\u0350\u0300\u030b\u0308\u0301\u0315\u035d\u035dH\u0334\u034e\u034e\u034e\u032a\u0319\u0356\u0316\u0356\u0349\u0349\u032e\u032c\u033a\u033f\u030b\u034c\u0313\u030b\u030e\u0352\u0306\u0358\u035c\u035c\u0345A\u0337\u031b\u034e\u0326\u034d\u033a\u0349\u0353\u0310\u0352\u030e\u030b\u0301\u033f\u0310\u0358\u031a\u031a\u0360T\u0335\u0321\u031b\u031c\u0333\u032e\u0339\u0330\u031e\u033e\u0305\u0312\u0313\u0310\u033f\u031a\u035d", "\u4ed6\u8ba9\u6211\u51fa\u6545\u969c\u4e86 :)", "\u3053\u3093\u306a\u306f\u305a\u3058\u3083\u306a\u304b\u3063\u305f", "\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588");

    public static void register() {
        NeoForge.EVENT_BUS.register(DialogueAngryProcedure.class);
    }

    @SubscribeEvent
    public static void onWorldTick(LevelTickEvent.Post event) {
        DialogueAngryProcedure.execute((LevelAccessor)event.getLevel());
    }

    public static void execute(LevelAccessor world) {
        if (!InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).eventfollover) {
            return;
        }
        long currentTick = world.dayTime();
        if (currentTick - lastTick < 500L) {
            return;
        }
        lastTick = currentTick;
        Random random = new Random();
        String message = glitchedMessages.get(random.nextInt(glitchedMessages.size()));
        if (!world.isClientSide() && world.getServer() != null) {
            world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)("<???\u00a7k303\u00a7r> " + message)), false);
        }
    }
}

