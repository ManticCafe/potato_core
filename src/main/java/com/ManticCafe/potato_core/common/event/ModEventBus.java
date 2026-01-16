package com.ManticCafe.potato_core.common.event;

import com.ManticCafe.potato_core.common.entity.entityhandler;
import com.ManticCafe.potato_core.main;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.ManticCafe.potato_core.common.entity.lootBox.potato_loot_box;

@Mod.EventBusSubscriber(modid = main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBus {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        // 将之前实体类中定义的createAttributes()方法关联到实体类型上
        event.put(entityhandler.POTATO_LOOT_BOX.get(), potato_loot_box.createAttributes().build());
    }
}