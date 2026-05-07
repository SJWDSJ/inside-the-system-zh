/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.inventory.MenuType
 *  net.neoforged.neoforge.common.extensions.IMenuTypeExtension
 *  net.neoforged.neoforge.registries.DeferredHolder
 *  net.neoforged.neoforge.registries.DeferredRegister
 */
package net.mcreator.insidethesystem.init;

import net.mcreator.insidethesystem.world.inventory.PasswordWriteMenu;
import net.mcreator.insidethesystem.world.inventory.Survey1Menu;
import net.mcreator.insidethesystem.world.inventory.Survey2Menu;
import net.mcreator.insidethesystem.world.inventory.Survey3Menu;
import net.mcreator.insidethesystem.world.inventory.Survey4Menu;
import net.mcreator.insidethesystem.world.inventory.Survey5Menu;
import net.mcreator.insidethesystem.world.inventory.TipsOpen1Menu;
import net.mcreator.insidethesystem.world.inventory.TipsOpen2Menu;
import net.mcreator.insidethesystem.world.inventory.TipsOpen3Menu;
import net.mcreator.insidethesystem.world.inventory.TipsOpenMenu;
import net.mcreator.insidethesystem.world.inventory.WarningMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InsideTheSystemModMenus {
    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create((ResourceKey)Registries.MENU, (String)"inside_the_system");
    public static final DeferredHolder<MenuType<?>, MenuType<WarningMenu>> WARNING = REGISTRY.register("warning", () -> IMenuTypeExtension.create(WarningMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<PasswordWriteMenu>> PASSWORD_WRITE = REGISTRY.register("password_write", () -> IMenuTypeExtension.create(PasswordWriteMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<TipsOpenMenu>> TIPS_OPEN = REGISTRY.register("tips_open", () -> IMenuTypeExtension.create(TipsOpenMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<TipsOpen2Menu>> TIPS_OPEN_2 = REGISTRY.register("tips_open_2", () -> IMenuTypeExtension.create(TipsOpen2Menu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<TipsOpen3Menu>> TIPS_OPEN_3 = REGISTRY.register("tips_open_3", () -> IMenuTypeExtension.create(TipsOpen3Menu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<TipsOpen1Menu>> TIPS_OPEN_1 = REGISTRY.register("tips_open_1", () -> IMenuTypeExtension.create(TipsOpen1Menu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<Survey1Menu>> SURVEY_1 = REGISTRY.register("survey_1", () -> IMenuTypeExtension.create(Survey1Menu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<Survey2Menu>> SURVEY_2 = REGISTRY.register("survey_2", () -> IMenuTypeExtension.create(Survey2Menu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<Survey3Menu>> SURVEY_3 = REGISTRY.register("survey_3", () -> IMenuTypeExtension.create(Survey3Menu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<Survey4Menu>> SURVEY_4 = REGISTRY.register("survey_4", () -> IMenuTypeExtension.create(Survey4Menu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<Survey5Menu>> SURVEY_5 = REGISTRY.register("survey_5", () -> IMenuTypeExtension.create(Survey5Menu::new));
}

