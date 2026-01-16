package com.ManticCafe.potato_core.common.item;

import com.ManticCafe.potato_core.common.block.blockhandler;
import com.ManticCafe.potato_core.common.entity.entityhandler;
import com.ManticCafe.potato_core.common.item.entityGenerator.generator_potato_loot_box;
import com.ManticCafe.potato_core.common.item.item.mysterious_potato;
import com.ManticCafe.potato_core.common.item.tool.the_last_axe;
import com.ManticCafe.potato_core.common.item.tool.the_last_hoe;
import com.ManticCafe.potato_core.common.item.tool.the_last_pickaxe;
import com.ManticCafe.potato_core.common.item.tool.the_last_shovel;
import com.ManticCafe.potato_core.common.item.weapon.*;
import com.ManticCafe.potato_core.main;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class itemhandler {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, main.MODID);

    public static final DeferredRegister<Item> BLOCKITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, main.MODID);

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
    public static final RegistryObject<Item> the_last_shovel = ITEMS.register("the_last_shovel", () -> new the_last_shovel());
    public static final RegistryObject<Item> the_last_hoe = ITEMS.register("the_last_hoe", () -> new the_last_hoe());
    public static final RegistryObject<Item> mysterious_potato = ITEMS.register("mysterious_potato", () -> new mysterious_potato());
    public static final RegistryObject<Item> the_last_ingot = ITEMS.register("the_last_ingot", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> potato_crystal = ITEMS.register("potato_crystal", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> potato_star = ITEMS.register("potato_star", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> lightning_ring = ITEMS.register("lightning_ring",() -> new lightningRing());
    public static final RegistryObject<Item> lightning_ingot = ITEMS.register("lightning_ingot", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> generator_potato_loot_box = ITEMS.register("generator_potato_loot_box", () -> new generator_potato_loot_box(new Item.Properties()
            .rarity(Rarity.COMMON),
            entityhandler.POTATO_LOOT_BOX
    ));

    public static final RegistryObject<Item> APOCALYPTIUM_BLOCK_item = BLOCKITEMS.register("apocalyptium_block", () -> new BlockItem(blockhandler.APOCALYPTIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.EPIC).food(new FoodProperties.Builder()
            .nutrition(6)
            .saturationMod(7)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 15400, 0), 1.0f) // 发光1
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 15400, 4), 1.0f)  // 伤害吸收5
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 15400, 6), 1.0f)  // 生命恢复7
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1540, 3), 1.0f) // 抗性提升4
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1540, 0), 1.0f) // 火焰抗性1
            .build()
    )));

    public static final RegistryObject<Item> ITEM_BASE_BLOCK_ITEM = ITEMS.register("item_base_block", () -> new BlockItem(blockhandler.ITEM_BASE_BLOCK.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        BLOCKITEMS.register(eventBus);
    }
}
