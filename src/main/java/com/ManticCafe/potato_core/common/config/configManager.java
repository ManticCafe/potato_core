package com.ManticCafe.potato_core.common.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class configManager {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final weaponsConfigs COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        LOGGER.info("Building configuration spec...");
        final Pair<weaponsConfigs, ForgeConfigSpec> commonPair = new ForgeConfigSpec.Builder().configure(weaponsConfigs::new);
        COMMON = commonPair.getLeft();
        COMMON_SPEC = commonPair.getRight();
    }


    public static void register() {
        LOGGER.info("Registering configuration with Forge...");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_SPEC, "potato_core/weaponsConfigs.toml");

        LOGGER.info("Configuration registered, waiting for load event...");
    }
}