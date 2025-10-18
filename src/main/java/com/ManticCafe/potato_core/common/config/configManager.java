package com.ManticCafe.potato_core.common.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;


public class configManager {

    public static final weaponsConfigs COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        final Pair<weaponsConfigs, ForgeConfigSpec> commonPair = new ForgeConfigSpec.Builder().configure(weaponsConfigs::new);
        COMMON = commonPair.getLeft();
        COMMON_SPEC = commonPair.getRight();
    }

    public static void register() {

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_SPEC,"potato_core/weaponsConfigs.toml");
    }
}
