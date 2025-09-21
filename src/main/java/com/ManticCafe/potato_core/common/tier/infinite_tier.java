package com.ManticCafe.potato_core.common.tier;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeTier;
import org.jetbrains.annotations.NotNull;

public class infinite_tier implements Tier{

    public static final infinite_tier INFINITE_TIER = new infinite_tier();

    private infinite_tier() {}

        @Override
        public int getUses () {
            return -1;
        }

        @Override
        public float getSpeed () {
            return 12.0F;
        }

        @Override
        public float getAttackDamageBonus () {
            return 23.0F;
        }

        @Override
        public int getLevel () {
            return 5;
        }

        @Override
        public int getEnchantmentValue () {
            return 25;
        }

        @Override
        public @NotNull Ingredient getRepairIngredient () {
            return Ingredient.EMPTY;
        }
}
