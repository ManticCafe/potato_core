package com.ManticCafe.potato_core.common.crafting;

import com.ManticCafe.potato_core.common.entity.entities.itemBaseBlockEntity;
import com.ManticCafe.potato_core.common.structure.StructureChecker;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.*;

public class StructureCraftingProcessor {

    public static CraftingResult processCrafting(Level level, BlockPos centerPos) {
        StructureChecker.StructureCheckResult checkResult = StructureChecker.checkStructure(level, centerPos);
        if (!checkResult.isValid()) {
            return CraftingResult.failure();
        }

        ItemStack centerItem = ItemStack.EMPTY;
        if (level.getBlockEntity(centerPos) instanceof itemBaseBlockEntity centerEntity) {
            centerItem = centerEntity.getDisplayedItem();
            if (!centerItem.isEmpty() && centerItem.getCount() != 1) {
                return CraftingResult.failure();
            }
        }

        if (centerItem.isEmpty()) {
            return CraftingResult.failure();
        }

        List<ItemStack> surroundingItems = new ArrayList<>();
        for (BlockPos blockPos : checkResult.getStructureBlocks()) {
            if (blockPos.equals(centerPos)) {
                continue;
            }

            if (level.getBlockEntity(blockPos) instanceof itemBaseBlockEntity blockEntity) {
                ItemStack item = blockEntity.getDisplayedItem();
                if (!item.isEmpty()) {
                    if (item.getCount() != 1) {
                        return CraftingResult.failure();
                    }
                    surroundingItems.add(item.copy());
                }
            }
        }

        lightningCrafting.RecipeMatchResult matchResult =
                lightningCrafting.findMatchingRecipe(centerItem, surroundingItems);

        if (!matchResult.isSuccess()) {
            return CraftingResult.failure();
        }

        lightningCrafting.StructureRecipe matchedRecipe = null;
        for (lightningCrafting.StructureRecipe recipe : lightningCrafting.getAllRecipes()) {
            if (recipe.matches(centerItem, surroundingItems)) {
                matchedRecipe = recipe;
                break;
            }
        }

        if (matchedRecipe == null) {
            return CraftingResult.failure();
        }

        consumeItemsForCrafting(level, centerPos, checkResult.getStructureBlocks(), matchedRecipe);

        if (level.getBlockEntity(centerPos) instanceof itemBaseBlockEntity centerEntity) {
            centerEntity.setDisplayedItem(matchResult.getResult());
        }

        return CraftingResult.success(matchResult.getResult());
    }

    private static void consumeItemsForCrafting(Level level, BlockPos centerPos,
                                                List<BlockPos> structureBlocks,
                                                lightningCrafting.StructureRecipe recipe) {
        if (level.getBlockEntity(centerPos) instanceof itemBaseBlockEntity centerEntity) {
            centerEntity.clearDisplayedItem();
        }

        Map<ItemStack, Integer> requiredItems = recipe.getRequiredItemCounts();

        for (Map.Entry<ItemStack, Integer> entry : requiredItems.entrySet()) {
            ItemStack requiredItem = entry.getKey();
            int requiredCount = entry.getValue();

            for (BlockPos blockPos : structureBlocks) {
                if (blockPos.equals(centerPos)) {
                    continue;
                }

                if (level.getBlockEntity(blockPos) instanceof itemBaseBlockEntity blockEntity) {
                    ItemStack currentItem = blockEntity.getDisplayedItem();
                    if (!currentItem.isEmpty() &&
                            currentItem.getItem() == requiredItem.getItem() &&
                            currentItem.getCount() == 1) {

                        blockEntity.clearDisplayedItem();
                        requiredCount--;

                        if (requiredCount <= 0) {
                            break;
                        }
                    }
                }
            }
        }
    }

    public static class CraftingResult {
        private final boolean success;
        private final ItemStack result;

        private CraftingResult(boolean success, ItemStack result) {
            this.success = success;
            this.result = result;
        }

        public static CraftingResult success(ItemStack result) {
            return new CraftingResult(true, result);
        }

        public static CraftingResult failure() {
            return new CraftingResult(false, ItemStack.EMPTY);
        }

        public boolean isSuccess() { return success; }
        public ItemStack getResult() { return result; }
    }
}