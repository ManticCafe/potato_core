package com.ManticCafe.potato_core.common.crafting;

import com.ManticCafe.potato_core.common.entity.entities.itemBaseBlockEntity;
import com.ManticCafe.potato_core.common.structure.StructureChecker;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.*;

public class EntityCraftingProcessor {

    public static EntityCraftingResult processEntityCrafting(Level level, BlockPos centerPos) {
        StructureChecker.StructureCheckResult checkResult = StructureChecker.checkStructure(level, centerPos);
        if (!checkResult.isValid()) {
            return EntityCraftingResult.failure();
        }

        ItemStack centerItem = getCenterItem(level, centerPos);
        if (centerItem.isEmpty()) {
            return EntityCraftingResult.failure();
        }

        List<ItemStack> surroundingItems = collectSurroundingItems(level, checkResult.getStructureBlocks(), centerPos);
        if (surroundingItems == null) {
            return EntityCraftingResult.failure();
        }

        lightningCrafting.RecipeMatchResult matchResult =
                lightningCrafting.findMatchingRecipe(centerItem, surroundingItems);

        if (!matchResult.isSuccess() || !matchResult.isEntityRecipe()) {
            return EntityCraftingResult.failure();
        }

        lightningCrafting.EntityRecipe entityRecipe = matchResult.getEntityResult();
        EntityType<?> entityType = entityRecipe.getEntityType();

        // 消耗物品
        consumeItemsForCrafting(level, centerPos, checkResult.getStructureBlocks(), entityRecipe);

        // 在中心方块上方召唤实体
        BlockPos spawnPos = centerPos.above(entityRecipe.getYOffset());
        Entity entity = entityType.create(level);
        if (entity != null) {
            entity.setPos(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5);
            level.addFreshEntity(entity);
            return EntityCraftingResult.success(entityType);
        }

        return EntityCraftingResult.failure();
    }

    private static ItemStack getCenterItem(Level level, BlockPos centerPos) {
        if (level.getBlockEntity(centerPos) instanceof itemBaseBlockEntity centerEntity) {
            ItemStack centerItem = centerEntity.getDisplayedItem();
            if (!centerItem.isEmpty() && centerItem.getCount() == 1) {
                return centerItem;
            }
        }
        return ItemStack.EMPTY;
    }

    private static List<ItemStack> collectSurroundingItems(Level level, List<BlockPos> structureBlocks, BlockPos centerPos) {
        List<ItemStack> surroundingItems = new ArrayList<>();

        for (BlockPos blockPos : structureBlocks) {
            if (blockPos.equals(centerPos)) continue;

            if (level.getBlockEntity(blockPos) instanceof itemBaseBlockEntity blockEntity) {
                ItemStack item = blockEntity.getDisplayedItem();
                if (!item.isEmpty()) {
                    if (item.getCount() != 1) return null;
                    surroundingItems.add(item.copy());
                }
            }
        }

        return surroundingItems;
    }

    private static void consumeItemsForCrafting(Level level, BlockPos centerPos,
                                                List<BlockPos> structureBlocks,
                                                lightningCrafting.EntityRecipe recipe) {
        // 消耗中心物品
        if (level.getBlockEntity(centerPos) instanceof itemBaseBlockEntity centerEntity) {
            centerEntity.clearDisplayedItem();
        }

        // 消耗周围物品
        Map<ItemStack, Integer> requiredItems = recipe.getRequiredItemCounts();

        for (Map.Entry<ItemStack, Integer> entry : requiredItems.entrySet()) {
            ItemStack requiredItem = entry.getKey();
            int requiredCount = entry.getValue();

            for (BlockPos blockPos : structureBlocks) {
                if (blockPos.equals(centerPos)) continue;

                if (level.getBlockEntity(blockPos) instanceof itemBaseBlockEntity blockEntity) {
                    ItemStack currentItem = blockEntity.getDisplayedItem();
                    if (!currentItem.isEmpty() &&
                            currentItem.getItem() == requiredItem.getItem() &&
                            currentItem.getCount() == 1) {

                        blockEntity.clearDisplayedItem();
                        requiredCount--;

                        if (requiredCount <= 0) break;
                    }
                }
            }
        }
    }

    public static class EntityCraftingResult {
        private final boolean success;
        private final EntityType<?> entityType;

        private EntityCraftingResult(boolean success, EntityType<?> entityType) {
            this.success = success;
            this.entityType = entityType;
        }

        public static EntityCraftingResult success(EntityType<?> entityType) {
            return new EntityCraftingResult(true, entityType);
        }

        public static EntityCraftingResult failure() {
            return new EntityCraftingResult(false, null);
        }

        public boolean isSuccess() { return success; }
        public EntityType<?> getEntityType() { return entityType; }
    }
}