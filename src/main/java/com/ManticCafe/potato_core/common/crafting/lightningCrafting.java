package com.ManticCafe.potato_core.common.crafting;

import com.ManticCafe.potato_core.common.item.itemhandler;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class lightningCrafting {

    private static final List<StructureRecipe> RECIPES = new CopyOnWriteArrayList<>();

    // 周围输入的最大数量 8个周围方块
    private static final int MAX_SURROUNDING_INPUTS = 8;

    static {

        //配方
        registerRecipeArray(
                new ItemStack(itemhandler.mysterious_potato.get(), 1),//输出
                new ItemStack(Items.POTATO, 1),//主要输入
                new ItemStack(Items.POTATO, 1),//次要输入
                new ItemStack(Items.POTATO, 1),//次要输入
                new ItemStack(Items.POTATO, 1),//次要输入
                new ItemStack(Items.POTATO, 1)//次要输入
        );

        registerRecipeArray(
                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(Items.SNIFFER_EGG),

                new ItemStack(Items.DRAGON_HEAD),
                new ItemStack(Items.DRAGON_BREATH),
                new ItemStack(Items.DRAGON_BREATH),
                new ItemStack(Items.DRAGON_BREATH),
                new ItemStack(Items.NETHER_STAR),
                new ItemStack(Items.NETHERITE_INGOT),
                new ItemStack(Items.NETHERITE_INGOT),
                new ItemStack(Items.NETHERITE_INGOT)
        );

        registerRecipeArray(
                new ItemStack(itemhandler.potato_crystal.get(),4),
                new ItemStack(Items.DRAGON_EGG),

                new ItemStack(itemhandler.mysterious_potato.get()),
                new ItemStack(Items.ECHO_SHARD),
                new ItemStack(itemhandler.mysterious_potato.get()),
                new ItemStack(Items.AMETHYST_SHARD),
                new ItemStack(itemhandler.mysterious_potato.get()),
                new ItemStack(Items.NETHERITE_SCRAP),
                new ItemStack(itemhandler.mysterious_potato.get()),
                new ItemStack(Items.PRISMARINE_SHARD)
        );

        registerRecipeArray(
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(Items.NETHER_STAR),

                new ItemStack(itemhandler.mysterious_potato.get()),
                new ItemStack(Items.DRAGON_BREATH),
                new ItemStack(itemhandler.mysterious_potato.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.mysterious_potato.get()),
                new ItemStack(Items.DRAGON_BREATH),
                new ItemStack(itemhandler.mysterious_potato.get()),
                new ItemStack(itemhandler.potato_crystal.get())
        );

        registerRecipeArray(
                new ItemStack(itemhandler.the_last_sword.get()),
                new ItemStack(Items.NETHERITE_SWORD),

                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get())
        );

        registerRecipeArray(
                new ItemStack(itemhandler.the_last_ingot.get(),3),
                new ItemStack(itemhandler.potato_crystal.get()),

                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(Items.GOLD_INGOT),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(Items.IRON_INGOT),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(Items.COPPER_INGOT),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(Items.NETHERITE_INGOT)
        );

        registerRecipeArray(
                new ItemStack(itemhandler.the_last_sword_level2.get()),
                new ItemStack(itemhandler.the_last_sword.get()),

                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.the_last_ingot.get())
        );

        registerRecipeArray(
                new ItemStack(itemhandler.the_last_sword_level3.get()),
                new ItemStack(itemhandler.the_last_sword_level2.get()),

                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.the_last_ingot.get())
        );

        registerRecipeArray(
                new ItemStack(itemhandler.the_last_sword_level4.get()),
                new ItemStack(itemhandler.the_last_sword_level3.get()),

                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.the_last_ingot.get())
        );

        registerRecipeArray(
                new ItemStack(itemhandler.the_last_sword_level5.get()),
                new ItemStack(itemhandler.the_last_sword_level4.get()),

                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get())
        );

        registerRecipeArray(
                new ItemStack(itemhandler.the_last_sword_level6.get()),
                new ItemStack(itemhandler.the_last_sword_level5.get()),

                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get())
        );

        registerRecipeArray(
                new ItemStack(itemhandler.the_last_sword_level7.get()),
                new ItemStack(itemhandler.the_last_sword_level6.get()),

                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get())
        );

        registerRecipeArray(
                new ItemStack(itemhandler.the_last_sword_level8.get()),
                new ItemStack(itemhandler.the_last_sword_level7.get()),

                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get())
        );

        registerRecipeArray(
                new ItemStack(itemhandler.the_last_sword_final.get()),
                new ItemStack(itemhandler.the_last_sword_level8.get()),

                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get()),
                new ItemStack(Items.DRAGON_EGG),
                new ItemStack(itemhandler.the_last_ingot.get())
        );

        registerRecipeArray(
                new ItemStack(itemhandler.real_the_last_sword.get()),
                new ItemStack(itemhandler.the_last_sword_final.get()),

                new ItemStack(itemhandler.the_last_axe.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.the_last_pickaxe.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.the_last_hoe.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.the_last_shovel.get()),
                new ItemStack(itemhandler.potato_star.get())
        );

        registerRecipeArray(
                new ItemStack(itemhandler.the_last_pickaxe.get()),
                new ItemStack(Items.NETHERITE_PICKAXE),

                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get())
        );

        registerRecipeArray(
                new ItemStack(itemhandler.the_last_axe.get()),
                new ItemStack(Items.NETHERITE_AXE),

                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get())
        );

        registerRecipeArray(
                new ItemStack(itemhandler.the_last_hoe.get()),
                new ItemStack(Items.NETHERITE_HOE),

                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get())
        );

        registerRecipeArray(
                new ItemStack(itemhandler.the_last_shovel.get()),
                new ItemStack(Items.NETHERITE_SHOVEL),

                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get()),
                new ItemStack(itemhandler.potato_star.get()),
                new ItemStack(itemhandler.potato_crystal.get())
        );
    }
    //为KubeJS预留的方法
    public static void registerRecipeArray(ItemStack output, ItemStack mainInput, ItemStack... otherInputs) {
        registerRecipe(output, mainInput, otherInputs);
    }
    //为KubeJS预留的方法
    public static void registerRecipe(ItemStack output, ItemStack mainInput, ItemStack... otherInputs) {
        if (output.getCount() <= 0 || output.getCount() > 64) {
            throw new IllegalArgumentException("输出物品数量必须在1-64之间，但得到: " + output.getCount());
        }

        if (otherInputs.length < 0 || otherInputs.length > MAX_SURROUNDING_INPUTS) {
            throw new IllegalArgumentException("周围输入数量必须在0-" + MAX_SURROUNDING_INPUTS + "之间，但得到: " + otherInputs.length);
        }

        validateSingleItem(mainInput, "主方块物品");
        for (ItemStack stack : otherInputs) {
            validateSingleItem(stack, "周围物品");
        }

        List<ItemStack> otherInputsList = Arrays.asList(otherInputs);

        RECIPES.add(new StructureRecipe(mainInput, otherInputsList, output));
    }

    public static int getMaxSurroundingInputs() {
        return MAX_SURROUNDING_INPUTS;
    }

    //为KubeJS预留的方法
    public static void removeAllRecipes() {
        RECIPES.clear();
    }
    //为KubeJS预留的方法
    public static void removeRecipesByOutput(ItemStack output) {
        RECIPES.removeIf(recipe ->
                recipe.getOutput().getItem() == output.getItem() &&
                        recipe.getOutput().getCount() == output.getCount()
        );
    }
    //为KubeJS预留的方法
    public static void removeRecipesByCenterInput(ItemStack centerInput) {
        RECIPES.removeIf(recipe ->
                recipe.getRequiredCenterItem().getItem() == centerInput.getItem()
        );
    }


//    public static boolean recipeExists(ItemStack output, ItemStack centerInput, List<ItemStack> surroundingInputs) {
//        for (StructureRecipe recipe : RECIPES) {
//            if (recipe.getOutput().getItem() == output.getItem() &&
//                    recipe.getOutput().getCount() == output.getCount() &&
//                    recipe.getRequiredCenterItem().getItem() == centerInput.getItem()) {
//
//                if (areIngredientListsEqual(recipe.getRequiredSurroundingItems(), surroundingInputs)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    private static boolean areIngredientListsEqual(List<ItemStack> list1, List<ItemStack> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }

        Map<String, Integer> countMap1 = new HashMap<>();
        Map<String, Integer> countMap2 = new HashMap<>();

        for (ItemStack stack : list1) {
            String key = stack.getItem().toString();
            countMap1.put(key, countMap1.getOrDefault(key, 0) + 1);
        }

        for (ItemStack stack : list2) {
            String key = stack.getItem().toString();
            countMap2.put(key, countMap2.getOrDefault(key, 0) + 1);
        }

        return countMap1.equals(countMap2);
    }

    private static void validateSingleItem(ItemStack stack, String context) {
        if (stack.getCount() != 1) {
            throw new IllegalArgumentException(context + "的数量必须为1，但得到: " + stack.getCount());
        }
    }

    public static RecipeMatchResult findMatchingRecipe(ItemStack centerItem, List<ItemStack> surroundingItems) {
        // 验证输入物品数量都为1
        if (centerItem.getCount() != 1) {
            return RecipeMatchResult.failure();
        }

        for (ItemStack stack : surroundingItems) {
            if (stack.getCount() != 1) {
                return RecipeMatchResult.failure();
            }
        }

        for (StructureRecipe recipe : RECIPES) {
            if (recipe.matches(centerItem, surroundingItems)) {
                return RecipeMatchResult.success(recipe.getOutput(), recipe.getRequiredCenterItem());
            }
        }
        return RecipeMatchResult.failure();
    }

    public static class StructureRecipe {
        private final ItemStack centerItem;
        private final List<ItemStack> requiredSurroundingItems;
        private final ItemStack output;

        public StructureRecipe(ItemStack centerItem, List<ItemStack> surroundingItems, ItemStack output) {
            this.centerItem = centerItem.copy();


            this.requiredSurroundingItems = new ArrayList<>();
            for (ItemStack stack : surroundingItems) {
                if (stack.getCount() != 1) {
                    throw new IllegalArgumentException("周围物品数量必须为1，但得到: " + stack.getCount());
                }
                this.requiredSurroundingItems.add(stack.copy());
            }

            this.output = output.copy();
        }

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
                    ItemStack available = availableItems.get(i);
                    if (areItemsEqual(available, required)) {
                        availableItems.remove(i);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
            }

            return true;
        }

        private boolean areItemsEqual(ItemStack stack1, ItemStack stack2) {
            return stack1.getItem() == stack2.getItem();
        }

        public ItemStack getOutput() {
            return output;
        }

        public ItemStack getRequiredCenterItem() {
            return centerItem;
        }

        public List<ItemStack> getRequiredSurroundingItems() {
            return requiredSurroundingItems;
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

    public static class RecipeMatchResult {
        private final boolean success;
        private final ItemStack result;
        private final ItemStack requiredCenterItem;

        private RecipeMatchResult(boolean success, ItemStack result, ItemStack requiredCenterItem) {
            this.success = success;
            this.result = result;
            this.requiredCenterItem = requiredCenterItem;
        }

        public static RecipeMatchResult success(ItemStack result, ItemStack requiredCenterItem) {
            return new RecipeMatchResult(true, result, requiredCenterItem);
        }

        public static RecipeMatchResult failure() {
            return new RecipeMatchResult(false, ItemStack.EMPTY, ItemStack.EMPTY);
        }

        public boolean isSuccess() { return success; }
        public ItemStack getResult() { return result; }
        public ItemStack getRequiredCenterItem() { return requiredCenterItem; }
    }

    // 获取所有配方（用于调试或显示）
    public static List<StructureRecipe> getAllRecipes() {
        return new ArrayList<>(RECIPES);
    }
}