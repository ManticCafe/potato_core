package com.ManticCafe.potato_core.common.crafting;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class lightningCrafting {

    // 配方类型枚举
    public enum RecipeType {
        ITEM,      // 物品输出
        ENTITY     // 实体召唤
    }

    // 通用配方基类
    public static abstract class BaseRecipe {
        protected final ItemStack centerItem;
        protected final List<ItemStack> requiredSurroundingItems;

        protected BaseRecipe(ItemStack centerItem, List<ItemStack> surroundingItems) {
            this.centerItem = centerItem.copy();
            this.requiredSurroundingItems = new ArrayList<>();

            for (ItemStack stack : surroundingItems) {
                validateSingleItem(stack, "周围物品");
                this.requiredSurroundingItems.add(stack.copy());
            }
        }

        public abstract RecipeType getType();

        public boolean matches(ItemStack actualCenterItem, List<ItemStack> actualSurroundingItems) {
            if (!areItemsEqual(actualCenterItem, centerItem)) {
                return false;
            }
            return matchSurroundingItems(actualSurroundingItems);
        }

        private boolean matchSurroundingItems(List<ItemStack> actualItems) {
            List<ItemStack> availableItems = new ArrayList<>();
            for (ItemStack stack : actualItems) {
                if (!stack.isEmpty()) {
                    availableItems.add(stack.copy());
                }
            }

            for (ItemStack required : requiredSurroundingItems) {
                boolean found = false;
                for (int i = 0; i < availableItems.size(); i++) {
                    if (areItemsEqual(availableItems.get(i), required)) {
                        availableItems.remove(i);
                        found = true;
                        break;
                    }
                }
                if (!found) return false;
            }
            return true;
        }

        protected boolean areItemsEqual(ItemStack stack1, ItemStack stack2) {
            return stack1.getItem() == stack2.getItem();
        }

        public ItemStack getRequiredCenterItem() {
            return centerItem.copy();
        }

        public List<ItemStack> getRequiredSurroundingItems() {
            return new ArrayList<>(requiredSurroundingItems);
        }

        public Map<ItemStack, Integer> getRequiredItemCounts() {
            Map<ItemStack, Integer> counts = new HashMap<>();
            for (ItemStack stack : requiredSurroundingItems) {
                ItemStack key = new ItemStack(stack.getItem(), 1);
                counts.put(key, counts.getOrDefault(key, 0) + 1);
            }
            return counts;
        }
    }

    // 物品输出配方
    public static class ItemRecipe extends BaseRecipe {
        private final ItemStack output;

        public ItemRecipe(ItemStack centerItem, List<ItemStack> surroundingItems, ItemStack output) {
            super(centerItem, surroundingItems);
            validateOutput(output);
            this.output = output.copy();
        }

        private void validateOutput(ItemStack output) {
            if (output.getCount() <= 0 || output.getCount() > 64) {
                throw new IllegalArgumentException("输出物品数量必须在1-64之间");
            }
        }

        @Override
        public RecipeType getType() {
            return RecipeType.ITEM;
        }

        public ItemStack getOutput() {
            return output.copy();
        }
    }

    // 实体召唤配方
    public static class EntityRecipe extends BaseRecipe {
        private final EntityType<?> entityType;
        private final int yOffset; // 高度偏移

        public EntityRecipe(ItemStack centerItem, List<ItemStack> surroundingItems, EntityType<?> entityType) {
            this(centerItem, surroundingItems, entityType, 3);
        }

        public EntityRecipe(ItemStack centerItem, List<ItemStack> surroundingItems,
                            EntityType<?> entityType, int yOffset) {
            super(centerItem, surroundingItems);
            this.entityType = entityType;
            this.yOffset = Math.max(0, yOffset);
        }

        @Override
        public RecipeType getType() {
            return RecipeType.ENTITY;
        }

        public EntityType<?> getEntityType() {
            return entityType;
        }

        public int getYOffset() {
            return yOffset;
        }
    }

    // 配方存储
    private static final List<ItemRecipe> ITEM_RECIPES = new CopyOnWriteArrayList<>();
    private static final List<EntityRecipe> ENTITY_RECIPES = new CopyOnWriteArrayList<>();
    private static final int MAX_SURROUNDING_INPUTS = 8;

    // 初始化默认配方
    static {
        initializeDefaultRecipes();
    }

    private static void initializeDefaultRecipes() {
//        // 物品配方
//        registerRecipeArray(
//                new ItemStack(itemhandler.mysterious_potato.get(), 1),
//                new ItemStack(Items.POTATO, 1),
//                new ItemStack(Items.POTATO, 1),
//                new ItemStack(Items.POTATO, 1),
//                new ItemStack(Items.POTATO, 1),
//                new ItemStack(Items.POTATO, 1)
//        );
//
//        registerRecipeArray(
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(Items.SNIFFER_EGG),
//                new ItemStack(Items.DRAGON_HEAD),
//                new ItemStack(Items.DRAGON_BREATH),
//                new ItemStack(Items.DRAGON_BREATH),
//                new ItemStack(Items.DRAGON_BREATH),
//                new ItemStack(Items.NETHER_STAR),
//                new ItemStack(Items.NETHERITE_INGOT),
//                new ItemStack(Items.NETHERITE_INGOT),
//                new ItemStack(Items.NETHERITE_INGOT)
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.potato_crystal.get(),4),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.mysterious_potato.get()),
//                new ItemStack(Items.ECHO_SHARD),
//                new ItemStack(itemhandler.mysterious_potato.get()),
//                new ItemStack(Items.AMETHYST_SHARD),
//                new ItemStack(itemhandler.mysterious_potato.get()),
//                new ItemStack(Items.NETHERITE_SCRAP),
//                new ItemStack(itemhandler.mysterious_potato.get()),
//                new ItemStack(Items.PRISMARINE_SHARD)
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(Items.NETHER_STAR),
//                new ItemStack(itemhandler.mysterious_potato.get()),
//                new ItemStack(Items.DRAGON_BREATH),
//                new ItemStack(itemhandler.mysterious_potato.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.mysterious_potato.get()),
//                new ItemStack(Items.DRAGON_BREATH),
//                new ItemStack(itemhandler.mysterious_potato.get()),
//                new ItemStack(itemhandler.potato_crystal.get())
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.the_last_sword.get()),
//                new ItemStack(Items.NETHERITE_SWORD),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get())
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.the_last_ingot.get(),3),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(Items.GOLD_INGOT),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(Items.IRON_INGOT),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(Items.COPPER_INGOT),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(Items.NETHERITE_INGOT)
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.the_last_sword_level2.get()),
//                new ItemStack(itemhandler.the_last_sword.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.the_last_ingot.get())
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.the_last_sword_level3.get()),
//                new ItemStack(itemhandler.the_last_sword_level2.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.the_last_ingot.get())
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.the_last_sword_level4.get()),
//                new ItemStack(itemhandler.the_last_sword_level3.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.the_last_ingot.get())
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.the_last_sword_level5.get()),
//                new ItemStack(itemhandler.the_last_sword_level4.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get())
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.the_last_sword_level6.get()),
//                new ItemStack(itemhandler.the_last_sword_level5.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get())
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.the_last_sword_level7.get()),
//                new ItemStack(itemhandler.the_last_sword_level6.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get())
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.the_last_sword_level8.get()),
//                new ItemStack(itemhandler.the_last_sword_level7.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get())
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.the_last_sword_final.get()),
//                new ItemStack(itemhandler.the_last_sword_level8.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get()),
//                new ItemStack(Items.DRAGON_EGG),
//                new ItemStack(itemhandler.the_last_ingot.get())
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.real_the_last_sword.get()),
//                new ItemStack(itemhandler.the_last_sword_final.get()),
//                new ItemStack(itemhandler.the_last_axe.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.the_last_pickaxe.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.the_last_hoe.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.the_last_shovel.get()),
//                new ItemStack(itemhandler.potato_star.get())
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.the_last_pickaxe.get()),
//                new ItemStack(Items.NETHERITE_PICKAXE),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get())
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.the_last_axe.get()),
//                new ItemStack(Items.NETHERITE_AXE),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get())
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.the_last_hoe.get()),
//                new ItemStack(Items.NETHERITE_HOE),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get())
//        );
//
//        registerRecipeArray(
//                new ItemStack(itemhandler.the_last_shovel.get()),
//                new ItemStack(Items.NETHERITE_SHOVEL),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get()),
//                new ItemStack(itemhandler.potato_star.get()),
//                new ItemStack(itemhandler.potato_crystal.get())
//        );

    }

    // 物品配方
    public static void registerRecipeArray(ItemStack output, ItemStack mainInput, ItemStack... otherInputs) {
        registerItemRecipe(output, mainInput, otherInputs);
    }

    public static void registerItemRecipe(ItemStack output, ItemStack mainInput, ItemStack... otherInputs) {
        validateInputs(mainInput, otherInputs);
        validateOutput(output);

        ITEM_RECIPES.add(new ItemRecipe(mainInput, Arrays.asList(otherInputs), output));
    }

    // 实体配方
    public static void registerEntityRecipeArray(EntityType<?> entityType, ItemStack mainInput, ItemStack... otherInputs) {
        registerEntityRecipe(entityType, mainInput, 2, otherInputs);
    }

    public static void registerEntityRecipe(EntityType<?> entityType, ItemStack mainInput, int yOffset, ItemStack... otherInputs) {
        validateInputs(mainInput, otherInputs);

        ENTITY_RECIPES.add(new EntityRecipe(mainInput, Arrays.asList(otherInputs), entityType, yOffset));
    }

    // 验证方法
    private static void validateInputs(ItemStack mainInput, ItemStack[] otherInputs) {
        if (otherInputs.length > MAX_SURROUNDING_INPUTS) {
            throw new IllegalArgumentException("周围输入数量不能超过" + MAX_SURROUNDING_INPUTS);
        }
        validateSingleItem(mainInput, "主方块物品");
        for (ItemStack stack : otherInputs) {
            validateSingleItem(stack, "周围物品");
        }
    }

    private static void validateOutput(ItemStack output) {
        if (output.getCount() <= 0 || output.getCount() > 64) {
            throw new IllegalArgumentException("输出物品数量必须在1-64之间");
        }
    }

    private static void validateSingleItem(ItemStack stack, String context) {
        if (stack.getCount() != 1) {
            throw new IllegalArgumentException(context + "的数量必须为1");
        }
    }

    // 配方查找

    public static RecipeMatchResult findMatchingRecipe(ItemStack centerItem, List<ItemStack> surroundingItems) {
        if (!validateItemStacks(centerItem, surroundingItems)) {
            return RecipeMatchResult.failure();
        }

        // 先查找物品配方
        for (ItemRecipe recipe : ITEM_RECIPES) {
            if (recipe.matches(centerItem, surroundingItems)) {
                return RecipeMatchResult.success(recipe.getOutput(), recipe.getRequiredCenterItem(), RecipeType.ITEM);
            }
        }

        // 再查找实体配方
        for (EntityRecipe recipe : ENTITY_RECIPES) {
            if (recipe.matches(centerItem, surroundingItems)) {
                return RecipeMatchResult.success(recipe, RecipeType.ENTITY);
            }
        }

        return RecipeMatchResult.failure();
    }

    private static boolean validateItemStacks(ItemStack centerItem, List<ItemStack> surroundingItems) {
        if (centerItem.getCount() != 1) return false;
        for (ItemStack stack : surroundingItems) {
            if (stack.getCount() != 1) return false;
        }
        return true;
    }

    // === KubeJS API - 移除配方 ===

    public static void removeAllRecipes() {
        ITEM_RECIPES.clear();
        ENTITY_RECIPES.clear();
    }

    public static void removeAllItemRecipes() {
        ITEM_RECIPES.clear();
    }

    public static void removeAllEntityRecipes() {
        ENTITY_RECIPES.clear();
    }

    public static void removeRecipesByOutput(ItemStack output) {
        ITEM_RECIPES.removeIf(recipe ->
                recipe.getOutput().getItem() == output.getItem() &&
                        recipe.getOutput().getCount() == output.getCount()
        );
    }

    public static void removeEntityRecipesByType(EntityType<?> entityType) {
        ENTITY_RECIPES.removeIf(recipe -> recipe.getEntityType() == entityType);
    }

    public static void removeRecipesByCenterInput(ItemStack centerInput) {
        ITEM_RECIPES.removeIf(recipe ->
                recipe.getRequiredCenterItem().getItem() == centerInput.getItem()
        );
        ENTITY_RECIPES.removeIf(recipe ->
                recipe.getRequiredCenterItem().getItem() == centerInput.getItem()
        );
    }

    // === 辅助方法 ===

    public static int getMaxSurroundingInputs() {
        return MAX_SURROUNDING_INPUTS;
    }

    public static List<ItemRecipe> getAllItemRecipes() {
        return new ArrayList<>(ITEM_RECIPES);
    }

    public static List<EntityRecipe> getAllEntityRecipes() {
        return new ArrayList<>(ENTITY_RECIPES);
    }

    // === 匹配结果类 ===

    public static class RecipeMatchResult {
        private final boolean success;
        private final RecipeType type;
        private final ItemStack itemResult;
        private final EntityRecipe entityResult;
        private final ItemStack requiredCenterItem;

        private RecipeMatchResult(boolean success, RecipeType type,
                                  ItemStack itemResult, EntityRecipe entityResult,
                                  ItemStack requiredCenterItem) {
            this.success = success;
            this.type = type;
            this.itemResult = itemResult;
            this.entityResult = entityResult;
            this.requiredCenterItem = requiredCenterItem;
        }

        public static RecipeMatchResult success(ItemStack result, ItemStack requiredCenterItem, RecipeType type) {
            return new RecipeMatchResult(true, type, result, null, requiredCenterItem);
        }

        public static RecipeMatchResult success(EntityRecipe entityResult, RecipeType type) {
            return new RecipeMatchResult(true, type, ItemStack.EMPTY, entityResult,
                    entityResult.getRequiredCenterItem());
        }

        public static RecipeMatchResult failure() {
            return new RecipeMatchResult(false, null, ItemStack.EMPTY, null, ItemStack.EMPTY);
        }

        public boolean isSuccess() { return success; }
        public RecipeType getType() { return type; }
        public boolean isItemRecipe() { return type == RecipeType.ITEM; }
        public boolean isEntityRecipe() { return type == RecipeType.ENTITY; }
        public ItemStack getItemResult() { return itemResult.copy(); }
        public EntityRecipe getEntityResult() { return entityResult; }
        public ItemStack getRequiredCenterItem() { return requiredCenterItem.copy(); }
    }
}