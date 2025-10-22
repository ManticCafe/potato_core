package com.ManticCafe.potato_core.Jei;

import com.ManticCafe.potato_core.main;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import com.ManticCafe.potato_core.Jei.*;

import java.util.List;

public class LightningCraftingCategory implements IRecipeCategory<LightningCraftingJEIRecipe> {
    public static final RecipeType<LightningCraftingJEIRecipe> RECIPE_TYPE =
            RecipeType.create(main.MODID, "lightning_crafting", LightningCraftingJEIRecipe.class);


    private static final ResourceLocation TEXTURE = new ResourceLocation(main.MODID, "textures/gui/lightning_crafting_jei.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final Component title;

    public LightningCraftingCategory(IGuiHelper guiHelper, ItemStack iconStack) {
        // 使用更大的背景 - 150x100
        this.background = guiHelper.createDrawable(TEXTURE, 0, 0, 150, 100);
        this.icon = guiHelper.createDrawableItemStack(iconStack);
        this.title = Component.translatable("jei.category.potato_core.lightning_crafting");
    }

    @Override
    @NotNull
    public RecipeType<LightningCraftingJEIRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    @NotNull
    public Component getTitle() {
        return title;
    }

    @Override
    @NotNull
    public IDrawable getBackground() {
        return background;
    }

    @Override
    @NotNull
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull LightningCraftingJEIRecipe recipe, @NotNull IFocusGroup focuses) {

        //中心位置
        builder.addSlot(mezz.jei.api.recipe.RecipeIngredientRole.INPUT, 40, 41)
                .addItemStack(recipe.getCenterInput());

        //周围物品（8个位置）
        List<ItemStack> surroundingInputs = recipe.getSurroundingInputs();

        int[][] slotPositions = {
                {40, 11},   // 上 (0)
                {60, 21},   // 右上 (1)
                {70, 41},  // 右 (2)
                {60, 61},   // 右下 (3)
                {40, 71},   // 下 (4)
                {20, 61},   // 左下 (5)
                {10, 41},   // 左 (6)
                {20, 21}    // 左上 (7)
        };

        //周围输入槽位
        for (int i = 0; i < surroundingInputs.size() && i < slotPositions.length; i++) {
            builder.addSlot(mezz.jei.api.recipe.RecipeIngredientRole.INPUT,
                            slotPositions[i][0], slotPositions[i][1])
                    .addItemStack(surroundingInputs.get(i));
        }

        //输出槽位(120, 41)
        builder.addSlot(mezz.jei.api.recipe.RecipeIngredientRole.OUTPUT, 125, 41)
                .addItemStack(recipe.getOutput());
    }
}