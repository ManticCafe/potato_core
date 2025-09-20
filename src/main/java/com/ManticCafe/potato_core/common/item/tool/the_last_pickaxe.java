package com.ManticCafe.potato_core.common.item.tool;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class the_last_pickaxe extends PickaxeItem {
    public the_last_pickaxe(Tier tier, int attackDamage, float attackSpeed, Item.Properties properties) {
        super(tier,attackDamage,attackSpeed,properties);
    }
}
