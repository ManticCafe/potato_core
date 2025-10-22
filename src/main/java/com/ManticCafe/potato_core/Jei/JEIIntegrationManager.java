package com.ManticCafe.potato_core.Jei;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JEIIntegrationManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static boolean jeiLoaded = false;

    public static void init() {
        try {
            Class.forName("mezz.jei.api.IModPlugin");
            jeiLoaded = true;
            LOGGER.info("JEI 已加载，启用 JEI 集成");
        } catch (ClassNotFoundException e) {
            jeiLoaded = false;
            LOGGER.debug("JEI 未找到，跳过 JEI 集成");
        }
    }

    public static boolean isJeiLoaded() {
        return jeiLoaded;
    }
}