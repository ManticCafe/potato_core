package com.ManticCafe.potato_core.common.config;

import java.util.concurrent.atomic.AtomicBoolean;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class ConfigCache {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final AtomicBoolean initialized = new AtomicBoolean(false);

    public static int L1_DAMAGE = 24;
    public static int L1_PROJECTILE_DAMAGE = 12;
    public static int L1_CD = 80;

    public static int L2_DAMAGE = 48;
    public static int L2_PROJECTILE_DAMAGE = 24;
    public static int L2_CD = 70;

    public static int L3_DAMAGE = 72;
    public static int L3_PROJECTILE_DAMAGE = 36;
    public static int L3_CD = 60;

    public static int L4_DAMAGE = 84;
    public static int L4_PROJECTILE_DAMAGE = 42;
    public static int L4_CD = 50;

    public static int L5_DAMAGE = 90;
    public static int L5_PROJECTILE_DAMAGE = 45;
    public static int L5_CD = 45;

    public static int L6_DAMAGE = 96;
    public static int L6_PROJECTILE_DAMAGE = 49;
    public static int L6_CD = 40;

    public static int L7_DAMAGE = 104;
    public static int L7_PROJECTILE_DAMAGE = 57;
    public static int L7_CD = 30;

    public static int L8_DAMAGE = 128;
    public static int L8_PROJECTILE_DAMAGE = 64;
    public static int L8_CD = 20;

    public static int L9_DAMAGE = 256;
    public static int L9_PROJECTILE_DAMAGE = 128;
    public static int L9_CD = 5;

    public static int R_DAMAGE = 2048;
    public static int R_PROJECTILE_DAMAGE = 1024;
    public static int R_CD = 1;

    public static void initialize() {
        if (initialized.get()) {
            return;
        }

        try {
            L1_DAMAGE = configManager.COMMON.l1_damage.get();
            L1_PROJECTILE_DAMAGE = configManager.COMMON.l1_projectile_damage.get();
            L1_CD = configManager.COMMON.l1_cd.get();

            L2_DAMAGE = configManager.COMMON.l2_damage.get();
            L2_PROJECTILE_DAMAGE = configManager.COMMON.l2_projectile_damage.get();
            L2_CD = configManager.COMMON.l2_cd.get();

            L3_DAMAGE = configManager.COMMON.l3_damage.get();
            L3_PROJECTILE_DAMAGE = configManager.COMMON.l3_projectile_damage.get();
            L3_CD = configManager.COMMON.l3_cd.get();

            L4_DAMAGE = configManager.COMMON.l4_damage.get();
            L4_PROJECTILE_DAMAGE = configManager.COMMON.l4_projectile_damage.get();
            L4_CD = configManager.COMMON.l4_cd.get();

            L5_DAMAGE = configManager.COMMON.l5_damage.get();
            L5_PROJECTILE_DAMAGE = configManager.COMMON.l5_projectile_damage.get();
            L5_CD = configManager.COMMON.l5_cd.get();

            L6_DAMAGE = configManager.COMMON.l6_damage.get();
            L6_PROJECTILE_DAMAGE = configManager.COMMON.l6_projectile_damage.get();
            L6_CD = configManager.COMMON.l6_cd.get();

            L7_DAMAGE = configManager.COMMON.l7_damage.get();
            L7_PROJECTILE_DAMAGE = configManager.COMMON.l7_projectile_damage.get();
            L7_CD = configManager.COMMON.l7_cd.get();

            L8_DAMAGE = configManager.COMMON.l8_damage.get();
            L8_PROJECTILE_DAMAGE = configManager.COMMON.l8_projectile_damage.get();
            L8_CD = configManager.COMMON.l8_cd.get();

            L9_DAMAGE = configManager.COMMON.l9_damage.get();
            L9_PROJECTILE_DAMAGE = configManager.COMMON.l9_projectile_damage.get();
            L9_CD = configManager.COMMON.l9_cd.get();

            R_DAMAGE = configManager.COMMON.r_damage.get();
            R_PROJECTILE_DAMAGE = configManager.COMMON.r_projectile_damage.get();
            R_CD = configManager.COMMON.r_cd.get();

            initialized.set(true);
            LOGGER.info("Config cache initialized successfully with all values");
        } catch (Exception e) {
            LOGGER.warn("Config not available yet, using defaults. Error: {}", e.getMessage());
        }
    }

    public static boolean isInitialized() {
        return initialized.get();
    }

    public static void reload() {
        LOGGER.info("Reloading config cache...");
        initialized.set(false);
        initialize();
    }

    public static void initializeOrThrow() {
        if (!initialized.get()) {
            initialize();
            if (!initialized.get()) {
                throw new IllegalStateException("Config cache failed to initialize");
            }
        }
    }
}