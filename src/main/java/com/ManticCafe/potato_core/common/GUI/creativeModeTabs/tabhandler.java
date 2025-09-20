package com.ManticCafe.potato_core.common.GUI.creativeModeTabs;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import com.ManticCafe.potato_core.main;
import com.ManticCafe.potato_core.common.item.itemhandler;

public class tabhandler {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,main.MODID);

    public static final RegistryObject<CreativeModeTab> Function_Block_Tab = CREATIVE_MODE_TABS.register("function_block_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(itemhandler.the_last_sword.get()))
                    .title(Component.translatable("itemGroup.potato_core"))
                    .displayItems((pParameters, pOutput) -> {pOutput.accept(itemhandler.the_last_sword.get());
                    }).build());

    //注册事件
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
