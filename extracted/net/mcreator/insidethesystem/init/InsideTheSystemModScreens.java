/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.inventory.MenuType
 *  net.neoforged.api.distmarker.Dist
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.fml.common.EventBusSubscriber$Bus
 *  net.neoforged.neoforge.client.event.RegisterMenuScreensEvent
 */
package net.mcreator.insidethesystem.init;

import net.mcreator.insidethesystem.client.gui.PasswordWriteScreen;
import net.mcreator.insidethesystem.client.gui.Survey1Screen;
import net.mcreator.insidethesystem.client.gui.Survey2Screen;
import net.mcreator.insidethesystem.client.gui.Survey3Screen;
import net.mcreator.insidethesystem.client.gui.Survey4Screen;
import net.mcreator.insidethesystem.client.gui.Survey5Screen;
import net.mcreator.insidethesystem.client.gui.TipsOpen1Screen;
import net.mcreator.insidethesystem.client.gui.TipsOpen2Screen;
import net.mcreator.insidethesystem.client.gui.TipsOpen3Screen;
import net.mcreator.insidethesystem.client.gui.TipsOpenScreen;
import net.mcreator.insidethesystem.client.gui.WarningScreen;
import net.mcreator.insidethesystem.init.InsideTheSystemModMenus;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(bus=EventBusSubscriber.Bus.MOD, value={Dist.CLIENT})
public class InsideTheSystemModScreens {
    @SubscribeEvent
    public static void clientLoad(RegisterMenuScreensEvent event) {
        event.register((MenuType)InsideTheSystemModMenus.WARNING.get(), WarningScreen::new);
        event.register((MenuType)InsideTheSystemModMenus.PASSWORD_WRITE.get(), PasswordWriteScreen::new);
        event.register((MenuType)InsideTheSystemModMenus.TIPS_OPEN.get(), TipsOpenScreen::new);
        event.register((MenuType)InsideTheSystemModMenus.TIPS_OPEN_2.get(), TipsOpen2Screen::new);
        event.register((MenuType)InsideTheSystemModMenus.TIPS_OPEN_3.get(), TipsOpen3Screen::new);
        event.register((MenuType)InsideTheSystemModMenus.TIPS_OPEN_1.get(), TipsOpen1Screen::new);
        event.register((MenuType)InsideTheSystemModMenus.SURVEY_1.get(), Survey1Screen::new);
        event.register((MenuType)InsideTheSystemModMenus.SURVEY_2.get(), Survey2Screen::new);
        event.register((MenuType)InsideTheSystemModMenus.SURVEY_3.get(), Survey3Screen::new);
        event.register((MenuType)InsideTheSystemModMenus.SURVEY_4.get(), Survey4Screen::new);
        event.register((MenuType)InsideTheSystemModMenus.SURVEY_5.get(), Survey5Screen::new);
    }
}

