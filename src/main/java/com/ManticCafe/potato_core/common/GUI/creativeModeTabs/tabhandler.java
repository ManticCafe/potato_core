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
                    .icon(() -> new ItemStack(itemhandler.mysterious_potato.get()))
                    .title(Component.translatable("itemGroup.potato_core"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(itemhandler.the_last_sword.get());
                        pOutput.accept(itemhandler.the_last_sword_level2.get());
                        pOutput.accept(itemhandler.the_last_sword_level3.get());
                        pOutput.accept(itemhandler.the_last_sword_level4.get());
                        pOutput.accept(itemhandler.the_last_sword_level5.get());
                        pOutput.accept(itemhandler.the_last_sword_level6.get());
                        pOutput.accept(itemhandler.the_last_sword_level7.get());
                        pOutput.accept(itemhandler.the_last_sword_level8.get());
                        pOutput.accept(itemhandler.the_last_sword_final.get());
                        pOutput.accept(itemhandler.real_the_last_sword.get());
                        pOutput.accept(itemhandler.the_last_pickaxe.get());
                        pOutput.accept(itemhandler.the_last_axe.get());
                        pOutput.accept(itemhandler.the_last_shovel.get());
                        pOutput.accept(itemhandler.the_last_hoe.get());
                        pOutput.accept(itemhandler.mysterious_potato.get());
                        pOutput.accept(itemhandler.the_last_ingot.get());
                        pOutput.accept(itemhandler.potato_star.get());
                        pOutput.accept(itemhandler.potato_crystal.get());
                        pOutput.accept(itemhandler.ITEM_BASE_BLOCK_ITEM.get());
                    }).build());

    //注册事件
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
