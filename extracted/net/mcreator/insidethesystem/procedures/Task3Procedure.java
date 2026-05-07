/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.level.LevelAccessor
 *  net.neoforged.bus.api.Event
 *  net.neoforged.bus.api.SubscribeEvent
 *  net.neoforged.fml.common.EventBusSubscriber
 *  net.neoforged.neoforge.event.tick.PlayerTickEvent$Post
 */
package net.mcreator.insidethesystem.procedures;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import net.mcreator.insidethesystem.network.InsideTheSystemModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class Task3Procedure {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Task3Procedure.execute((Event)event, (LevelAccessor)event.getEntity().level());
    }

    public static void execute(LevelAccessor world) {
        Task3Procedure.execute(null, world);
    }

    private static void execute(@Nullable Event event, LevelAccessor world) {
        if (InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task3) {
            if (!world.isClientSide() && world.getServer() != null) {
                world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)"RUN"), false);
                Task3Procedure.executeLoadingSequence(world);
            }
            InsideTheSystemModVariables.MapVariables.get((LevelAccessor)world).Task3 = false;
            InsideTheSystemModVariables.MapVariables.get(world).syncData(world);
        }
    }

    private static void executeLoadingSequence(LevelAccessor world) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        ArrayList<Runnable> sequence = new ArrayList<Runnable>();
        sequence.add(() -> Task3Procedure.sendMessage(world, "\u7cfb\u7edf\u4ecb\u7ecd"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Python 3.11.4 (main, Jul 5 2023, 13:45:01) [MSC v.1916 64 bit (AMD64)]"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> import sys"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> import numpy as np"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Collecting numpy==1.24.3"));
        sequence.add(() -> Task3Procedure.sendMessage(world, " Downloading numpy-1.24.3-cp311-cp311-win_amd64.whl (14.8 MB)"));
        sequence.add(() -> Task3Procedure.sendMessage(world, " \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588 14.8/14.8 MB 2.1 MB/s eta 0:00:00"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Successfully installed numpy-1.24.3"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> import pandas as pd"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Collecting pandas==2.0.3"));
        sequence.add(() -> Task3Procedure.sendMessage(world, " Downloading pandas-2.0.3-cp311-cp311-win_amd64.whl (11.6 MB)"));
        sequence.add(() -> Task3Procedure.sendMessage(world, " \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588 11.6/11.6 MB 1.8 MB/s eta 0:00:00"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Successfully installed pandas-2.0.3"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> import tensorflow as tf"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Collecting tensorflow==2.13.0"));
        sequence.add(() -> Task3Procedure.sendMessage(world, " Downloading tensorflow-2.13.0-cp311-cp311-win_amd64.whl (2.1 MB)"));
        sequence.add(() -> Task3Procedure.sendMessage(world, " \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588 2.1/2.1 MB 900.0 kB/s eta 0:00:00"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Installing collected packages: tensorflow"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Successfully installed tensorflow-2.13.0"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "2023-07-15 14:32:10.432156: I tensorflow/core/platform/cpu_feature_guard.cc:182]"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> import torch"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Collecting torch==2.0.1"));
        sequence.add(() -> Task3Procedure.sendMessage(world, " Downloading torch-2.0.1-cp311-cp311-win_amd64.whl (172.3 MB)"));
        sequence.add(() -> Task3Procedure.sendMessage(world, " \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588 172.3/172.3 MB 1.2 MB/s eta 0:00:00"));
        sequence.add(() -> Task3Procedure.continueSequence1(world));
        long delay = 0L;
        long[] delays = new long[]{60L, 4L, 1L, 1L, 2L, 3L, 4L, 2L, 1L, 3L, 3L, 4L, 2L, 1L, 4L, 5L, 6L, 3L, 4L, 2L, 1L, 3L, 4L, 8L};
        for (int i = 0; i < sequence.size() - 1; ++i) {
            int index = i;
            executor.schedule((Runnable)sequence.get(index), (delay += delays[i]) * 50L, TimeUnit.MILLISECONDS);
        }
        executor.schedule((Runnable)sequence.get(sequence.size() - 1), (delay + delays[delays.length - 1]) * 50L, TimeUnit.MILLISECONDS);
    }

    private static void continueSequence1(LevelAccessor world) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        ArrayList<Runnable> sequence = new ArrayList<Runnable>();
        sequence.add(() -> Task3Procedure.sendMessage(world, "Successfully installed torch-2.0.1"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> import cv2"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Collecting opencv-python==4.8.0.74"));
        sequence.add(() -> Task3Procedure.sendMessage(world, " Downloading opencv_python-4.8.0.74-cp37-abi3-win_amd64.whl (38.1 MB)"));
        sequence.add(() -> Task3Procedure.sendMessage(world, " \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588 38.1/38.1 MB 1.5 MB/s eta 0:00:00"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Successfully installed opencv-python-4.8.0.74"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> from sklearn import model_selection"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Collecting scikit-learn==1.3.0"));
        sequence.add(() -> Task3Procedure.sendMessage(world, " Downloading scikit_learn-1.3.0-cp311-cp311-win_amd64.whl (9.2 MB)"));
        sequence.add(() -> Task3Procedure.sendMessage(world, " \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588 9.2/9.2 MB 1.3 MB/s eta 0:00:00"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Successfully installed scikit-learn-1.3.0"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> import matplotlib.pyplot as plt"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Collecting matplotlib==3.7.1"));
        sequence.add(() -> Task3Procedure.sendMessage(world, " Downloading matplotlib-3.7.1-cp311-cp311-win_amd64.whl (7.6 MB)"));
        sequence.add(() -> Task3Procedure.sendMessage(world, " \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588 7.6/7.6 MB 1.4 MB/s eta 0:00:00"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Successfully installed matplotlib-3.7.1"));
        sequence.add(() -> Task3Procedure.continueSequence2(world));
        long delay = 0L;
        long[] delays = new long[]{4L, 1L, 3L, 4L, 5L, 2L, 1L, 2L, 3L, 3L, 2L, 1L, 2L, 2L, 3L, 1L};
        for (int i = 0; i < sequence.size() - 1; ++i) {
            int index = i;
            executor.schedule((Runnable)sequence.get(index), (delay += delays[i]) * 50L, TimeUnit.MILLISECONDS);
        }
        executor.schedule((Runnable)sequence.get(sequence.size() - 1), (delay + delays[delays.length - 1]) * 50L, TimeUnit.MILLISECONDS);
    }

    private static void continueSequence2(LevelAccessor world) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        ArrayList<Runnable> sequence = new ArrayList<Runnable>();
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> import requests"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> import seaborn as sns"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> from datetime import datetime"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> import json"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> import os"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> print('Initializing neural network model...')"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Initializing neural network model..."));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> model = tf.keras.Sequential()"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> model.add(tf.keras.layers.Dense(128, activation='relu'))"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> model.compile(optimizer='adam', loss='sparse_categorical_crossentropy')"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> X_train, y_train = load_data()"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Loading training dataset... [\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588] 100%"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> model.fit(X_train, y_train, epochs=100, batch_size=32)"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Epoch 1/100"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "1563/1563 [\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588] - 12s 8ms/step - loss: 0.4521 - accuracy: 0.8385"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Epoch 50/100"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "1563/1563 [\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588] - 10s 6ms/step - loss: 0.1456 - accuracy: 0.9456"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Epoch 100/100"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "1563/1563 [\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588] - 8s 5ms/step - loss: 0.0789 - accuracy: 0.9734"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> model.save('neural_network_model.h5')"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Model saved successfully."));
        sequence.add(() -> Task3Procedure.finalSequence(world));
        long delay = 0L;
        long[] delays = new long[]{1L, 1L, 1L, 1L, 1L, 2L, 1L, 2L, 3L, 2L, 4L, 6L, 3L, 2L, 3L, 2L, 3L, 4L, 3L, 2L, 3L};
        for (int i = 0; i < sequence.size() - 1; ++i) {
            int index = i;
            executor.schedule((Runnable)sequence.get(index), (delay += delays[i]) * 50L, TimeUnit.MILLISECONDS);
        }
        executor.schedule((Runnable)sequence.get(sequence.size() - 1), (delay + delays[delays.length - 1]) * 50L, TimeUnit.MILLISECONDS);
    }

    private static void finalSequence(LevelAccessor world) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        ArrayList<Runnable> sequence = new ArrayList<Runnable>();
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> from cryptography import fernet"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> import hashlib"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> accessing_user_data = True"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> for file in os.listdir('/Users/'):"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "... encrypt_file(file)"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Encrypting personal files... [\u2588\u2588\u2588\u2588\u2588\u2588\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591] 25%"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Encrypting personal files... [\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591] 50%"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Encrypting personal files... [\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591] 75%"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Encrypting personal files... [\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588] 100%"));
        sequence.add(() -> Task3Procedure.sendMessage(world, ">>> send_to_server(encrypted_data)"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Traceback (most recent call last):"));
        sequence.add(() -> Task3Procedure.sendMessage(world, " File \"<stdin>\", line 1, in <module>"));
        sequence.add(() -> Task3Procedure.sendMessage(world, " File \"malware.py\", line 247, in send_to_server"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "MemoryError: Unable to allocate 8.00 GiB for an array with shape (1073741824,) and data type float64"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "FATAL ERROR: MEMORY corruption detected at address 0x7FF8A2B4C000"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "\u4e25\u91cd\u9519\u8bef\uff1a\u68c0\u6d4b\u5230\u7cfb\u7edf\u635f\u574f"));
        sequence.add(() -> Task3Procedure.sendMessage(world, "Kernel panic - not syncing: Fatal exception in interrupt"));
        sequence.add(() -> Task3Procedure.showBSODAndScheduleShutdown());
        long delay = 0L;
        long[] delays = new long[]{2L, 1L, 2L, 2L, 3L, 1L, 2L, 3L, 3L, 2L, 4L, 1L, 1L, 2L, 3L, 2L, 4L};
        for (int i = 0; i < sequence.size() - 1; ++i) {
            int index = i;
            executor.schedule((Runnable)sequence.get(index), (delay += delays[i]) * 50L, TimeUnit.MILLISECONDS);
        }
        executor.schedule((Runnable)sequence.get(sequence.size() - 1), (delay + delays[delays.length - 1]) * 50L, TimeUnit.MILLISECONDS);
    }

    private static void sendMessage(LevelAccessor world, String message) {
        if (!world.isClientSide() && world.getServer() != null) {
            world.getServer().getPlayerList().broadcastSystemMessage((Component)Component.literal((String)message), false);
        }
    }

    private static void showBSODAndScheduleShutdown() {
        ScheduledExecutorService bsodExecutor = Executors.newSingleThreadScheduledExecutor();
        try {
            String bsodCommand = "Add-Type -AssemblyName PresentationFramework; $bsodWindow = New-Object Windows.Window; $bsodWindow.WindowStyle = 'None'; $bsodWindow.WindowState = 'Maximized'; $bsodWindow.Background = 'Blue'; $bsodWindow.Topmost = $true; $bsodWindow.Title = 'BSOD'; $bsodText = New-Object Windows.Controls.TextBlock; $bsodText.Text = ':( ' + [Environment]::NewLine + 'Your PC ran into a problem and needs to restart.' + [Environment]::NewLine + 'We are just collecting some error info, and then we will restart for you.' + [Environment]::NewLine + '0% complete' + [Environment]::NewLine + 'For more information about this issue, visit https://www.windows.com/stopcode'; $bsodText.Foreground = 'White'; $bsodText.FontSize = 36; $bsodText.FontFamily = 'Consolas'; $bsodText.Margin = '50'; $bsodWindow.Content = $bsodText; $bsodWindow.ShowDialog()";
            Process bsodProcess = Runtime.getRuntime().exec("powershell -command \"" + bsodCommand + "\"");
            bsodExecutor.schedule(() -> {
                try {
                    Runtime.getRuntime().exec("powershell -command \"Get-Process | Where-Object {$_.MainWindowTitle -eq 'BSOD'} | Stop-Process -Force\"");
                    bsodProcess.destroyForcibly();
                    String restoreCommand = "Add-Type -AssemblyName System.Windows.Forms; [System.Windows.Forms.MessageBox]::Show('System has been restored after a critical breach attempt. Unauthorized control signal detected and neutralized', 'System notification!', [System.Windows.Forms.MessageBoxButtons]::OK, [System.Windows.Forms.MessageBoxIcon]::Information)";
                    Runtime.getRuntime().exec("powershell -command \"" + restoreCommand + "\"");
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    bsodExecutor.shutdown();
                }
            }, 25L, TimeUnit.SECONDS);
        }
        catch (IOException e) {
            e.printStackTrace();
            bsodExecutor.shutdown();
        }
    }
}

