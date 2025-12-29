package com.ManticCafe.potato_core.common.item.weapon;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class lightningRing extends Item {

    public lightningRing() {
        super(new Item.Properties()
                .stacksTo(1) // 最大堆叠
                .rarity(Rarity.RARE) // 稀有度
                .durability(-1) // 耐久
        );
    }
}
