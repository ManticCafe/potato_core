package com.ManticCafe.potato_core.Jei;

import com.ManticCafe.potato_core.common.crafting.lightningCrafting;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import com.ManticCafe.potato_core.Jei.*;
import com.ManticCafe.potato_core.common.item.itemhandler;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class PotatoCoreJEIPlugin implements IModPlugin {
    private static final ResourceLocation ID = new ResourceLocation("potato_core", "jei_plugin");

    @Override
    @NotNull
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {

        ItemStack categoryIcon = new ItemStack(Items.LIGHTNING_ROD);
        registration.addRecipeCategories(new LightningCraftingCategory(registration.getJeiHelpers().getGuiHelper(), categoryIcon));
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {

        if (Minecraft.getInstance().level == null) return;


        List<LightningCraftingJEIRecipe> jeiRecipes = new ArrayList<>();

        for (lightningCrafting.StructureRecipe recipe : lightningCrafting.getAllRecipes()) {
            jeiRecipes.add(new LightningCraftingJEIRecipe(
                    recipe.getRequiredCenterItem(),
                    recipe.getRequiredSurroundingItems(),
                    recipe.getOutput()
            ));
        }


        registration.addRecipes(LightningCraftingCategory.RECIPE_TYPE, jeiRecipes);
    }

    @Override
    public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration registration) {

        ItemStack catalyst = new ItemStack(itemhandler.ITEM_BASE_BLOCK_ITEM.get());
        registration.addRecipeCatalyst(catalyst, LightningCraftingCategory.RECIPE_TYPE);
    }
}