package com.ManticCafe.potato_core.common.block.functionBlocks;

import com.ManticCafe.potato_core.common.entity.entities.itemBaseBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class itemBaseBlock extends BaseEntityBlock {

    public itemBaseBlock() {
        super(Properties.of()
                .strength(2.0f)
                .noOcclusion()
                .mapColor(MapColor.METAL)
                .requiresCorrectToolForDrops()
                .sound(SoundType.METAL)
        );
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof itemBaseBlockEntity) {
                return ((itemBaseBlockEntity) blockEntity).interact(player, hand);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new itemBaseBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {

        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (!level.isClientSide && blockEntity instanceof itemBaseBlockEntity) {
                itemBaseBlockEntity itemBase = (itemBaseBlockEntity) blockEntity;
                ItemStack displayedItem = itemBase.getDisplayedItem();

                if (!displayedItem.isEmpty()) {
                    itemBase.clearDisplayedItem();
                    popResource(level, pos, displayedItem);
                }
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, net.minecraft.world.level.storage.loot.LootParams.Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder);

        if (builder.getOptionalParameter(net.minecraft.world.level.storage.loot.parameters.LootContextParams.BLOCK_ENTITY) instanceof itemBaseBlockEntity itemBase) {
            ItemStack displayedItem = itemBase.getDisplayedItem();
            if (!displayedItem.isEmpty()) {
                drops.add(displayedItem.copy());
            }
        }

        return drops;
    }
}