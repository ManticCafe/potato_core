package com.ManticCafe.potato_core.common.item.tool;

import com.ManticCafe.potato_core.common.tier.infinite_tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class the_last_pickaxe extends PickaxeItem {
    public the_last_pickaxe() {
        super(infinite_tier.INFINITE_TIER,0,12,new Item.Properties().durability(-1));
    }
}
