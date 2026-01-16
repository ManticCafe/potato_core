package com.ManticCafe.potato_core.common.GUI.creativeModeTabs;

import com.ManticCafe.potato_core.common.item.itemhandler;
import com.ManticCafe.potato_core.main;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

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
                        pOutput.accept(itemhandler.lightning_ring.get()); // 闪电戒指
                        pOutput.accept(itemhandler.mysterious_potato.get()); // 神秘的土豆
                        pOutput.accept(itemhandler.potato_crystal.get()); // 土豆结晶
                        pOutput.accept(itemhandler.lightning_ingot.get()); // 闪电锭
                        pOutput.accept(itemhandler.the_last_ingot.get()); // 最终锭
                        pOutput.accept(itemhandler.potato_star.get()); // 土豆之星
                        pOutput.accept(itemhandler.ITEM_BASE_BLOCK_ITEM.get()); // 物品基座
                        pOutput.accept(itemhandler.APOCALYPTIUM_BLOCK_item.get()); //神灵金属块
                        pOutput.accept(itemhandler.generator_potato_loot_box.get()); // 生成土豆战利品箱
                    }).build());

    //注册事件
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
