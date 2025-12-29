package com.ManticCafe.potato_core.common.crafting;

import com.ManticCafe.potato_core.common.entity.entities.itemBaseBlockEntity;
import com.ManticCafe.potato_core.common.structure.StructureChecker;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        // 如果是物品配方
        if (matchResult.isItemRecipe()) {
            // 查找匹配的配方来消耗物品
            lightningCrafting.ItemRecipe matchedRecipe = null;
            for (lightningCrafting.ItemRecipe recipe : lightningCrafting.getAllItemRecipes()) {
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
                centerEntity.setDisplayedItem(matchResult.getItemResult());
            }

            return CraftingResult.success(matchResult.getItemResult(), lightningCrafting.RecipeType.ITEM);
        }

        // 如果是实体配方，返回特定结果，由EntityCraftingProcessor处理
        if (matchResult.isEntityRecipe()) {
            return CraftingResult.successEntity(matchResult.getEntityResult());
        }

        return CraftingResult.failure();
    }

    private static void consumeItemsForCrafting(Level level, BlockPos centerPos,
                                                List<BlockPos> structureBlocks,
                                                lightningCrafting.BaseRecipe recipe) {
        // 消耗中心物品
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
        private final ItemStack itemResult;
        private final lightningCrafting.EntityRecipe entityResult;
        private final lightningCrafting.RecipeType type;

        private CraftingResult(boolean success, ItemStack itemResult,
                               lightningCrafting.EntityRecipe entityResult,
                               lightningCrafting.RecipeType type) {
            this.success = success;
            this.itemResult = itemResult;
            this.entityResult = entityResult;
            this.type = type;
        }

        public static CraftingResult success(ItemStack result, lightningCrafting.RecipeType type) {
            return new CraftingResult(true, result, null, type);
        }

        public static CraftingResult successEntity(lightningCrafting.EntityRecipe entityResult) {
            return new CraftingResult(true, ItemStack.EMPTY, entityResult, lightningCrafting.RecipeType.ENTITY);
        }

        public static CraftingResult failure() {
            return new CraftingResult(false, ItemStack.EMPTY, null, null);
        }

        public boolean isSuccess() { return success; }
        public ItemStack getItemResult() { return itemResult; }
        public lightningCrafting.EntityRecipe getEntityResult() { return entityResult; }
        public lightningCrafting.RecipeType getType() { return type; }
        public boolean isItemRecipe() { return type == lightningCrafting.RecipeType.ITEM; }
        public boolean isEntityRecipe() { return type == lightningCrafting.RecipeType.ENTITY; }
    }
}