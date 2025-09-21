package com.ManticCafe.potato_core.common.item;

import com.ManticCafe.potato_core.common.item.item.mysterious_potato;
import com.ManticCafe.potato_core.common.item.tool.the_last_axe;
import com.ManticCafe.potato_core.common.item.tool.the_last_pickaxe;
import com.ManticCafe.potato_core.common.item.weapon.the_last_sword;
import com.ManticCafe.potato_core.main;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.ManticCafe.potato_core.common.item.weapon.*;

public class itemhandler {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, main.MODID);

    //register
    public static final RegistryObject<Item> the_last_sword = ITEMS.register("the_last_sword", () -> new the_last_sword());
    public static final RegistryObject<Item> the_last_sword_level2 = ITEMS.register("the_last_sword_level2", () -> new the_last_sword_level2());
    public static final RegistryObject<Item> the_last_sword_level3 = ITEMS.register("the_last_sword_level3", () -> new the_last_sword_level3());
    public static final RegistryObject<Item> the_last_sword_level4 = ITEMS.register("the_last_sword_level4", () -> new the_last_sword_level4());
    public static final RegistryObject<Item> the_last_sword_level5 = ITEMS.register("the_last_sword_level5", () -> new the_last_sword_level5());
    public static final RegistryObject<Item> the_last_sword_level6 = ITEMS.register("the_last_sword_level6", () -> new the_last_sword_level6());
    public static final RegistryObject<Item> the_last_sword_level7 = ITEMS.register("the_last_sword_level7", () -> new the_last_sword_level7());
    public static final RegistryObject<Item> the_last_sword_level8 = ITEMS.register("the_last_sword_level8", () -> new the_last_sword_level8());
    public static final RegistryObject<Item> the_last_sword_final = ITEMS.register("the_last_sword_final", () -> new the_last_sword_final());
    public static final RegistryObject<Item> real_the_last_sword = ITEMS.register("real_the_last_sword", () -> new real_the_last_sword());
    public static final RegistryObject<Item> the_last_axe = ITEMS.register("the_last_axe", () -> new the_last_axe());
    public static final RegistryObject<Item> the_last_pickaxe = ITEMS.register("the_last_pickaxe", () -> new the_last_pickaxe());
    public static final RegistryObject<Item> mysterious_potato = ITEMS.register("mysterious_potato", () -> new mysterious_potato());
    public static final RegistryObject<Item> the_last_ingot = ITEMS.register("the_last_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> potato_crystal = ITEMS.register("potato_crystal", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> potato_star = ITEMS.register("potato_star", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
