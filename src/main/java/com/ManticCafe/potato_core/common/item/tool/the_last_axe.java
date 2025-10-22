// the_last_axe.java
package com.ManticCafe.potato_core.common.item.tool;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import com.ManticCafe.potato_core.common.tier.infinite_tier;
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

public class the_last_axe extends AxeItem {

    private static final int MIN_LEVEL = 0;
    private static final int MAX_LEVEL = 4;
    private static final float[] SPEED_LEVELS = {0.0F, 1.0F, 10.0F, 100.0F, 10000.0F};
    private static final String[] LEVEL_KEYS = {
            "mining_mode.potato_core.off",
            "mining_mode.potato_core.slow",
            "mining_mode.potato_core.normal",
            "mining_mode.potato_core.fast",
            "mining_mode.potato_core.very_fast"
    };

    public the_last_axe() {
        super(infinite_tier.INFINITE_TIER, 0, 12, new Item.Properties().durability(-1));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        int miningLevel = getMiningSpeedLevel(stack);
        Component miningName = Component.translatable(LEVEL_KEYS[miningLevel]);
        float miningSpeed = SPEED_LEVELS[miningLevel];

        tooltip.add(Component.translatable("tooltip.potato_core.mining_mode").append(": ").append(miningName));
        if (miningLevel > 0) {
            tooltip.add(Component.translatable("tooltip.potato_core.mining_speed").append(": §a" + miningSpeed));
        }

        tooltip.add(Component.translatable("tooltip.potato_core.sneak_right_click"));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (player.isCrouching()) {
            if (!level.isClientSide) {
                int currentLevel = getMiningSpeedLevel(itemstack);
                int newLevel = (currentLevel + 1) % (MAX_LEVEL + 1);

                setMiningSpeedLevel(itemstack, newLevel);

                Component levelName = Component.translatable(LEVEL_KEYS[newLevel]);
                float speedValue = SPEED_LEVELS[newLevel];

                Component speedValueText = Component.literal(String.valueOf(speedValue)).withStyle(net.minecraft.ChatFormatting.GREEN);
                Component detailedMessage = Component.translatable("message.potato_core.mode_switch",
                        levelName, speedValueText);
                player.displayClientMessage(detailedMessage, true);
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