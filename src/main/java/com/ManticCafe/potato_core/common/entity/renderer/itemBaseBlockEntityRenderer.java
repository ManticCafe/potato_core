package com.ManticCafe.potato_core.common.entity.renderer;

import com.ManticCafe.potato_core.common.entity.entities.itemBaseBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class itemBaseBlockEntityRenderer implements BlockEntityRenderer<itemBaseBlockEntity> {

    public itemBaseBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(itemBaseBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        ItemStack itemStack = blockEntity.getDisplayedItem();
        if (itemStack.isEmpty()) return;

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        poseStack.pushPose();
                                                                                                //0.05为物品浮动幅度 0.15为物品悬浮高度
        double offsetY = Math.sin((blockEntity.getLevel().getGameTime() + partialTick) * 0.1) * 0.05 + 0.15;
        poseStack.translate(0.5, 1.2 + offsetY, 0.5);

        float rotation = (blockEntity.getLevel().getGameTime() + partialTick) * 2;
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));

        poseStack.scale(0.8f, 0.8f, 0.8f);

        itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, packedLight,
                packedOverlay, poseStack, bufferSource, blockEntity.getLevel(), 0);

        poseStack.popPose();
    }
}