package com.ManticCafe.potato_core.common.config;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import com.ManticCafe.potato_core.main;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigLoadingHandler {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static boolean configLoaded = false;

    @SubscribeEvent
    public static void onConfigLoading(final ModConfigEvent.Loading event) {
        if (event.getConfig().getSpec() == configManager.COMMON_SPEC) {
            LOGGER.info("Configuration is now loading...");
        }
    }

    @SubscribeEvent
    public static void onConfigLoaded(final ModConfigEvent.Loading event) {
        if (event.getConfig().getSpec() == configManager.COMMON_SPEC) {
            configLoaded = true;
            ConfigCache.initialize(); // 初始化缓存
            LOGGER.info("Configuration loaded and cached successfully!");
        }
    }

    @SubscribeEvent
    public static void onConfigReloading(final ModConfigEvent.Reloading event) {
        if (event.getConfig().getSpec() == configManager.COMMON_SPEC) {
            LOGGER.info("Configuration reloading...");
        }
    }

    @SubscribeEvent
    public static void onConfigReloaded(final ModConfigEvent.Reloading event) {
        if (event.getConfig().getSpec() == configManager.COMMON_SPEC) {
            ConfigCache.reload(); // 重新加载缓存
            LOGGER.info("Configuration reloaded and cache updated!");
        }
    }

    public static boolean isConfigLoaded() {
        return configLoaded;
    }
}