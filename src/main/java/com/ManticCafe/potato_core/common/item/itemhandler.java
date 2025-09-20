package com.ManticCafe.potato_core.common.item;

import com.ManticCafe.potato_core.common.item.weapon.the_last_sword;
import com.ManticCafe.potato_core.main;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class itemhandler {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, main.MODID);

    //register
    public static final RegistryObject<Item> the_last_sword = ITEMS.register("the_last_sword", () -> new the_last_sword());

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
