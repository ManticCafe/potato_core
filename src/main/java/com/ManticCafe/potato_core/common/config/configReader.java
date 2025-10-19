package com.ManticCafe.potato_core.common.config;

public class configReader {

    public static int getl1_damage() {
        ensureCacheInitialized();
        return ConfigCache.L1_DAMAGE;
    }

    public static int getl1_projectile_damage() {
        ensureCacheInitialized();
        return ConfigCache.L1_PROJECTILE_DAMAGE;
    }

    public static int getl1_cd() {
        ensureCacheInitialized();
        return ConfigCache.L1_CD;
    }

    public static int getl2_damage() {
        ensureCacheInitialized();
        return ConfigCache.L2_DAMAGE;
    }

    public static int getl2_projectile_damage() {
        ensureCacheInitialized();
        return ConfigCache.L2_PROJECTILE_DAMAGE;
    }

    public static int getl2_cd() {
        ensureCacheInitialized();
        return ConfigCache.L2_CD;
    }

    public static int getl3_damage() {
        ensureCacheInitialized();
        return ConfigCache.L3_DAMAGE;
    }

    public static int getl3_projectile_damage() {
        ensureCacheInitialized();
        return ConfigCache.L3_PROJECTILE_DAMAGE;
    }

    public static int getl3_cd() {
        ensureCacheInitialized();
        return ConfigCache.L3_CD;
    }

    public static int getl4_damage() {
        ensureCacheInitialized();
        return ConfigCache.L4_DAMAGE;
    }

    public static int getl4_projectile_damage() {
        ensureCacheInitialized();
        return ConfigCache.L4_PROJECTILE_DAMAGE;
    }

    public static int getl4_cd() {
        ensureCacheInitialized();
        return ConfigCache.L4_CD;
    }

    public static int getl5_damage() {
        ensureCacheInitialized();
        return ConfigCache.L5_DAMAGE;
    }

    public static int getl5_projectile_damage() {
        ensureCacheInitialized();
        return ConfigCache.L5_PROJECTILE_DAMAGE;
    }

    public static int getl5_cd() {
        ensureCacheInitialized();
        return ConfigCache.L5_CD;
    }

    public static int getl6_damage() {
        ensureCacheInitialized();
        return ConfigCache.L6_DAMAGE;
    }

    public static int getl6_projectile_damage() {
        ensureCacheInitialized();
        return ConfigCache.L6_PROJECTILE_DAMAGE;
    }

    public static int getl6_cd() {
        ensureCacheInitialized();
        return ConfigCache.L6_CD;
    }

    public static int getl7_damage() {
        ensureCacheInitialized();
        return ConfigCache.L7_DAMAGE;
    }

    public static int getl7_projectile_damage() {
        ensureCacheInitialized();
        return ConfigCache.L7_PROJECTILE_DAMAGE;
    }

    public static int getl7_cd() {
        ensureCacheInitialized();
        return ConfigCache.L7_CD;
    }

    public static int getl8_damage() {
        ensureCacheInitialized();
        return ConfigCache.L8_DAMAGE;
    }

    public static int getl8_projectile_damage() {
        ensureCacheInitialized();
        return ConfigCache.L8_PROJECTILE_DAMAGE;
    }

    public static int getl8_cd() {
        ensureCacheInitialized();
        return ConfigCache.L8_CD;
    }

    public static int getl9_damage() {
        ensureCacheInitialized();
        return ConfigCache.L9_DAMAGE;
    }

    public static int getl9_projectile_damage() {
        ensureCacheInitialized();
        return ConfigCache.L9_PROJECTILE_DAMAGE;
    }

    public static int getl9_cd() {
        ensureCacheInitialized();
        return ConfigCache.L9_CD;
    }

    public static int getr_damage() {
        ensureCacheInitialized();
        return ConfigCache.R_DAMAGE;
    }

    public static int getr_projectile_damage() {
        ensureCacheInitialized();
        return ConfigCache.R_PROJECTILE_DAMAGE;
    }

    public static int getr_cd() {
        ensureCacheInitialized();
        return ConfigCache.R_CD;
    }

    private static void ensureCacheInitialized() {
        if (!ConfigCache.isInitialized()) {
            ConfigCache.initialize();
        }
    }
}