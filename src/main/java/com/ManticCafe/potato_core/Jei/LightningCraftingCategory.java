package com.ManticCafe.potato_core.Jei;

import com.ManticCafe.potato_core.main;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LightningCraftingCategory implements IRecipeCategory<LightningCraftingJEIRecipe> {
    public static final RecipeType<LightningCraftingJEIRecipe> RECIPE_TYPE =
            RecipeType.create(main.MODID, "lightning_crafting", LightningCraftingJEIRecipe.class);

    private static final ResourceLocation TEXTURE = new ResourceLocation(main.MODID, "textures/gui/jei/lightning_crafting_jei.png");

    private static final ResourceLocation FURNACE_ARROW = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");

    private static final ResourceLocation SLOT_BACKGROUND = new ResourceLocation(main.MODID, "textures/jei/gui/slot_background.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic slotBackground;
    private final IDrawableStatic furnaceArrow;
    private final Component title;

    // 槽位位置定义
    private final int[][] slotPositions = {
            {40, 11},   // 上 (0)
            {60, 21},   // 右上 (1)
            {70, 41},   // 右 (2)
            {60, 61},   // 右下 (3)
            {40, 71},   // 下 (4)
            {20, 61},   // 左下 (5)
            {10, 41},   // 左 (6)
            {20, 21}    // 左上 (7)
    };
    private final int centerSlotX = 40;
    private final int centerSlotY = 41;
    private final int outputSlotX = 125;
    private final int outputSlotY = 41;
    private final int arrowX = 93;   // 箭头X坐标
    private final int arrowY = 41;   // 箭头Y坐标
    private final int arrowWidth = 24;  // 箭头宽度
    private final int arrowHeight = 17; // 箭头高度
    private final int slotSize = 18; // 槽位大小

    public LightningCraftingCategory(IGuiHelper guiHelper, ItemStack iconStack) {
        this.background = guiHelper.createDrawable(TEXTURE, 0, 0, 150, 100);
        this.icon = guiHelper.createDrawableItemStack(iconStack);

        this.slotBackground = guiHelper.createDrawable(
                new ResourceLocation("textures/gui/container/generic_54.png"),
                7, 17, 18, 18
        );

        this.furnaceArrow = guiHelper.createDrawable(FURNACE_ARROW, 176, 14, arrowWidth, arrowHeight);

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
        // 中心
        builder.addSlot(mezz.jei.api.recipe.RecipeIngredientRole.INPUT, centerSlotX, centerSlotY)
                .addItemStack(recipe.getCenterInput());

        // 周围
        List<ItemStack> surroundingInputs = recipe.getSurroundingInputs();

        for (int i = 0; i < surroundingInputs.size() && i < slotPositions.length; i++) {
            builder.addSlot(mezz.jei.api.recipe.RecipeIngredientRole.INPUT,
                            slotPositions[i][0], slotPositions[i][1])
                    .addItemStack(surroundingInputs.get(i));
        }

        // 输出
        builder.addSlot(mezz.jei.api.recipe.RecipeIngredientRole.OUTPUT, outputSlotX, outputSlotY)
                .addItemStack(recipe.getOutput());
    }

    @Override
    public void draw(LightningCraftingJEIRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        // 槽位背景
        slotBackground.draw(guiGraphics, centerSlotX - 1, centerSlotY - 1);

        for (int i = 0; i < slotPositions.length; i++) {
            slotBackground.draw(guiGraphics, slotPositions[i][0] - 1, slotPositions[i][1] - 1);
        }

        slotBackground.draw(guiGraphics, outputSlotX - 1, outputSlotY - 1);

        // 箭头
        furnaceArrow.draw(guiGraphics, arrowX, arrowY);
    }

    @Override
    public List<Component> getTooltipStrings(LightningCraftingJEIRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {

        if (isMouseOverArrow(mouseX, mouseY)) {
            return List.of(Component.translatable("jei.tooltip.potato_core.lightning_strike"));
        }
        return List.of();
    }

    private boolean isMouseOverArrow(double mouseX, double mouseY) {
        return mouseX >= arrowX &&
                mouseX <= arrowX + arrowWidth &&
                mouseY >= arrowY &&
                mouseY <= arrowY + arrowHeight;
    }
}