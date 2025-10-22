package com.ManticCafe.potato_core.Jei;

import net.minecraft.world.item.ItemStack;

import java.util.List;

public class LightningCraftingJEIRecipe {
    private final ItemStack centerInput;
    private final List<ItemStack> surroundingInputs;
    private final ItemStack output;

    public LightningCraftingJEIRecipe(ItemStack centerInput, List<ItemStack> surroundingInputs, ItemStack output) {
        this.centerInput = centerInput;
        this.surroundingInputs = surroundingInputs;
        this.output = output;
    }

    public ItemStack getCenterInput() {
        return centerInput;
    }

    public List<ItemStack> getSurroundingInputs() {
        return surroundingInputs;
    }

    public ItemStack getOutput() {
        return output;
    }
}