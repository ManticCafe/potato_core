package com.ManticCafe.potato_core;

import com.ManticCafe.potato_core.common.block.blockhandler;
import com.ManticCafe.potato_core.common.config.configManager;
import com.ManticCafe.potato_core.common.config.ConfigCache;
import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import com.ManticCafe.potato_core.common.entity.entityhandler;
import com.ManticCafe.potato_core.common.item.itemhandler;
import com.ManticCafe.potato_core.common.GUI.creativeModeTabs.tabhandler;

@Mod(main.MODID)
public class main {
    public static final String MODID = "potato_core";
    private static final Logger LOGGER = LogUtils.getLogger();

    public main(FMLJavaModLoadingContext context) {

        LOGGER.info("Starting mod initialization...");

        LOGGER.info("Registering configuration manager...");
        configManager.register();

        LOGGER.info("Pre-initializing config cache...");
        ConfigCache.initialize();

        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::onCommonSetup);

        LOGGER.info("Registering entities, items, and tabs...");
        entityhandler.register(modEventBus);
        itemhandler.register(modEventBus);
        tabhandler.register(modEventBus);
        blockhandler.register(modEventBus);

        LOGGER.info("Mod initialization phase 1 complete");
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Common setup - verifying configuration cache...");

        if (ConfigCache.isInitialized()) {
            LOGGER.info("Configuration cache verified - ready to use");
        } else {
            LOGGER.warn("Configuration cache not initialized, attempting to initialize again...");
            ConfigCache.initialize();
            if (ConfigCache.isInitialized()) {
                LOGGER.info("Configuration cache initialized successfully in common setup");
            } else {
                LOGGER.error("Configuration cache failed to initialize in common setup");
            }
        }

        com.ManticCafe.potato_core.Jei.JEIIntegrationManager.init();

        LOGGER.info("Potato Core mod common setup complete");
    }
}