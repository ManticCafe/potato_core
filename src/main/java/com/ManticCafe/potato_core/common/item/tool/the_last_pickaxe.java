// the_last_pickaxe.java
package com.ManticCafe.potato_core.common.item.tool;

import com.ManticCafe.potato_core.common.tier.infinite_tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class the_last_pickaxe extends PickaxeItem {

    private static final int MIN_LEVEL = 0;
    private static final int MAX_LEVEL = 4;
    private static final float[] SPEED_LEVELS = {0.0F, 1.0F, 10.0F, 100.0F, 10000.0F};
    private static final String[] LEVEL_NAMES = {"关闭", "慢速", "普通", "快速", "极快"};

    public the_last_pickaxe() {
        super(infinite_tier.INFINITE_TIER, 0, 12, new Item.Properties().durability(-1));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        int miningLevel = getMiningSpeedLevel(stack);
        String miningName = LEVEL_NAMES[miningLevel];
        float miningSpeed = SPEED_LEVELS[miningLevel];

        tooltip.add(Component.literal("§6挖掘模式: §e" + miningName));
        if (miningLevel > 0) {
            tooltip.add(Component.literal("§7挖掘速度: §a" + miningSpeed));
        }

        tooltip.add(Component.literal("§8潜行右键切换挖掘模式"));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (player.isCrouching()) {
            if (!level.isClientSide) {
                int currentLevel = getMiningSpeedLevel(itemstack);
                int newLevel = (currentLevel + 1) % (MAX_LEVEL + 1);

                setMiningSpeedLevel(itemstack, newLevel);

                String levelName = LEVEL_NAMES[newLevel];
                float speedValue = SPEED_LEVELS[newLevel];

                player.displayClientMessage(Component.literal("§6当前档位: §e" + levelName + " §7(速度: " + speedValue + ")"), true);
            }

            return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        }

        return InteractionResultHolder.pass(itemstack);
    }

    private int getMiningSpeedLevel(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("MiningSpeedLevel")) {
            return Mth.clamp(tag.getInt("MiningSpeedLevel"), MIN_LEVEL, MAX_LEVEL);
        }
        return 0;
    }

    private void setMiningSpeedLevel(ItemStack stack, int level) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt("MiningSpeedLevel", Mth.clamp(level, MIN_LEVEL, MAX_LEVEL));
    }

    private float getCurrentMiningSpeed(ItemStack stack) {
        int level = getMiningSpeedLevel(stack);
        return SPEED_LEVELS[level];
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return getCurrentMiningSpeed(stack);
    }
}