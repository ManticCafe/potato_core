package com.ManticCafe.potato_core.common.entity.entities;

import com.ManticCafe.potato_core.common.entity.entityhandler;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class itemBaseBlockEntity extends BlockEntity {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public itemBaseBlockEntity(BlockPos pos, BlockState state) {
        super(entityhandler.ITEM_BASE_BLOCK_ENTITY.get(), pos, state);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                if (level != null) {
                    level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
                }
            }
        };
    }

    public InteractionResult interact(Player player, InteractionHand hand) {
        ItemStack handItem = player.getItemInHand(hand);
        ItemStack storedItem = itemHandler.getStackInSlot(0);

        if (!level.isClientSide) {
            if (!storedItem.isEmpty()) {
                ItemStack itemToGive = storedItem.copy();
                boolean success = player.getInventory().add(itemToGive);

                if (success) {
                    itemHandler.setStackInSlot(0, ItemStack.EMPTY);

                    level.playSound(null, worldPosition, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.8F, 1.0F);
                } else {

                    double randomOffsetX = (level.random.nextDouble() - 0.3) * 0.3;
                    double randomOffsetZ = (level.random.nextDouble() - 0.3) * 0.3;

                    ItemEntity itemEntity = new ItemEntity(level,
                            player.getX() + randomOffsetX,
                            player.getY() + 0.7,
                            player.getZ() + randomOffsetZ,
                            itemToGive);
                    itemEntity.setDefaultPickUpDelay();

                    itemEntity.setDeltaMovement(
                            (level.random.nextDouble() - 0.5) * 0.1,
                            level.random.nextDouble() * 0.1 + 0.1,
                            (level.random.nextDouble() - 0.5) * 0.1
                    );

                    level.addFreshEntity(itemEntity);

                    itemHandler.setStackInSlot(0, ItemStack.EMPTY);

                    level.playSound(null, worldPosition, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.8F, 1.0F);
                }

                setChanged();
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
                return InteractionResult.SUCCESS;

            } else if (storedItem.isEmpty() && !handItem.isEmpty()) {
                ItemStack copy = handItem.copy();
                copy.setCount(1);
                itemHandler.setStackInSlot(0, copy);
                if (!player.isCreative()) {
                    handItem.shrink(1);
                }

                level.playSound(null, worldPosition, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 0.8F, 1.0F);

                setChanged();
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    //获取当前存储的物品
    public ItemStack getDisplayedItem() {
        return itemHandler.getStackInSlot(0);
    }

    public void clearDisplayedItem() {
        itemHandler.setStackInSlot(0, ItemStack.EMPTY);
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    public void setDisplayedItem(ItemStack stack) {
        itemHandler.setStackInSlot(0, stack);
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("inv"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("inv", itemHandler.serializeNBT());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return handler.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        handler.invalidate();
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}