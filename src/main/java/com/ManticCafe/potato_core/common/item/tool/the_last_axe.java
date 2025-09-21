package com.ManticCafe.potato_core.common.item.tool;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import com.ManticCafe.potato_core.common.tier.infinite_tier;

public class the_last_axe extends AxeItem {
    public the_last_axe() {
        super(infinite_tier.INFINITE_TIER,0,12,new Item.Properties().durability(-1));
    }
}
