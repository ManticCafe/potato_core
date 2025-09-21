package com.ManticCafe.potato_core.common.item.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class mysterious_potato extends Item {
    public mysterious_potato() {
        super(new Properties());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        // 添加一个灰色的提示信息
        tooltip.add(Component.translatable("tooltip.potato_core.mysterious_potato").withStyle(ChatFormatting.BLUE));

        // 你可以添加更多的提示信息
        // tooltip.add(Component.translatable("tooltip.potato_core.magic_potato.line2").withStyle(ChatFormatting.RED));

        super.appendHoverText(stack, level, tooltip, flag);
    }
}
