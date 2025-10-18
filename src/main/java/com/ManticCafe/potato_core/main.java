package com.ManticCafe.potato_core;

import com.ManticCafe.potato_core.common.config.configManager;
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
    // Define mod id in a common place for everything to reference
    public static final String MODID = "potato_core";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // 主函数
    public main(FMLJavaModLoadingContext context) {

        configManager.register();

        IEventBus modEventBus = context.getModEventBus();

        System.out.println("Starting mod initialization...");

        modEventBus.addListener(this::onCommonSetup);


        entityhandler.register(modEventBus);
        itemhandler.register(modEventBus);
        tabhandler.register(modEventBus);
    }


    private void onCommonSetup(final FMLCommonSetupEvent event) {
        // 这里可以放置需要在模组初始化时执行的代码
        LOGGER.info("Territory Cabinet mod common setup complete");
    }
}