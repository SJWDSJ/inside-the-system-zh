/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.api.distmarker.Dist
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.fml.loading.FMLEnvironment
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 *  org.lwjgl.glfw.GLFW
 *  org.lwjgl.glfw.GLFWVidMode
 */
package net.mcreator.insidethesystem.procedures;

import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

@EventBusSubscriber(value={Dist.CLIENT})
public class WindownsShakeProcedure {
    private static boolean shaking = false;
    private static int shakeTicks = 0;
    private static int originalX = -1;
    private static int originalY = -1;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!FMLEnvironment.dist.isClient()) {
            return;
        }
        if (event.getEntity().level().isClientSide()) {
            WindownsShakeProcedure.execute(event.getEntity().level());
        }
    }

    private static void execute(Level world) {
        Minecraft mc = Minecraft.getInstance();
        if (mc == null || mc.getWindow() == null) {
            return;
        }
        long window = mc.getWindow().getWindow();
        if (window == 0L) {
            return;
        }
        InsideTheSystemModVariables.MapVariables mapVars = InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world);
        if (mapVars == null) {
            return;
        }
        if (mapVars.eventfollover) {
            if (WindownsShakeProcedure.isFullscreen(mc)) {
                WindownsShakeProcedure.toggleFullscreen(window);
            }
            if (!shaking) {
                int[] pos = WindownsShakeProcedure.getWindowPosition(window);
                originalX = pos[0];
                originalY = pos[1];
            }
            shaking = true;
            shakeTicks = 40;
        }
        if (shaking && shakeTicks > 0) {
            WindownsShakeProcedure.shakeWindow(window);
            if (--shakeTicks <= 0) {
                shaking = false;
                WindownsShakeProcedure.restoreWindowPosition(window);
            }
        }
    }

    private static void shakeWindow(long window) {
        int baseX = originalX != -1 ? originalX : 100;
        int baseY = originalY != -1 ? originalY : 100;
        int xOffset = (int)(Math.random() * 30.0 - 15.0);
        int yOffset = (int)(Math.random() * 30.0 - 15.0);
        GLFW.glfwSetWindowPos((long)window, (int)(baseX + xOffset), (int)(baseY + yOffset));
    }

    private static void restoreWindowPosition(long window) {
        if (originalX != -1 && originalY != -1) {
            GLFW.glfwSetWindowPos((long)window, (int)originalX, (int)originalY);
            originalX = -1;
            originalY = -1;
        }
    }

    private static int[] getWindowPosition(long window) {
        int[] xPos = new int[1];
        int[] yPos = new int[1];
        GLFW.glfwGetWindowPos((long)window, (int[])xPos, (int[])yPos);
        return new int[]{xPos[0], yPos[0]};
    }

    private static boolean isFullscreen(Minecraft mc) {
        long window = mc.getWindow().getWindow();
        if (window == 0L) {
            return false;
        }
        return GLFW.glfwGetWindowMonitor((long)window) != 0L;
    }

    private static void toggleFullscreen(long window) {
        if (window == 0L) {
            return;
        }
        GLFWVidMode vidMode = GLFW.glfwGetVideoMode((long)GLFW.glfwGetPrimaryMonitor());
        if (vidMode == null) {
            return;
        }
        if (GLFW.glfwGetWindowMonitor((long)window) == 0L) {
            GLFW.glfwSetWindowMonitor((long)window, (long)GLFW.glfwGetPrimaryMonitor(), (int)0, (int)0, (int)vidMode.width(), (int)vidMode.height(), (int)-1);
        } else {
            int screenWidth = vidMode.width();
            int screenHeight = vidMode.height();
            int newWidth = (int)((double)screenWidth * 0.8);
            int newHeight = (int)((double)screenHeight * 0.8);
            int centerX = (screenWidth - newWidth) / 2;
            int centerY = (screenHeight - newHeight) / 2;
            GLFW.glfwSetWindowMonitor((long)window, (long)0L, (int)centerX, (int)centerY, (int)newWidth, (int)newHeight, (int)-1);
        }
    }
}

