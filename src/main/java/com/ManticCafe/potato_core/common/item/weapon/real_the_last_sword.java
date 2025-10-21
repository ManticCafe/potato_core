package com.ManticCafe.potato_core.common.item.weapon;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import com.ManticCafe.potato_core.common.tier.infinite_tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import com.ManticCafe.potato_core.common.entity.entities.PotatoProjectileEntity;
import com.ManticCafe.potato_core.common.entity.entityhandler;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import com.ManticCafe.potato_core.common.config.configReader;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

import javax.annotation.Nullable;
import java.util.List;

public class real_the_last_sword extends SwordItem {

    private static final int PROJECTILE_DAMAGE = configReader.getr_projectile_damage();

    private static final int MIN_LEVEL = 0;
    private static final int MAX_LEVEL = 4;
    private static final float[] SPEED_LEVELS = {0.0F, 1.0F, 10.0F, 100.0F, 10000.0F};
    private static final String[] LEVEL_NAMES = {"关闭", "慢速", "普通", "快速", "极快"};

    public real_the_last_sword() {
        super(infinite_tier.INFINITE_TIER, 2024, 12, new Item.Properties().durability(-1));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        // 显示挖掘功能状态
        if (configReader.getr_c()) {
            int miningLevel = getMiningSpeedLevel(stack);
            String miningName = LEVEL_NAMES[miningLevel];
            float miningSpeed = SPEED_LEVELS[miningLevel];

            tooltip.add(Component.literal("§6挖掘模式: §e" + miningName));
            if (miningLevel > 0) {
                tooltip.add(Component.literal("§7挖掘速度: §a" + miningSpeed));
            }

            // 显示操作提示
            tooltip.add(Component.literal("§8潜行右键切换挖掘档位"));
        } else {
            tooltip.add(Component.literal("§6挖掘模式: §c未启用"));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!configReader.getr_c()) {
            if (!level.isClientSide) {
                PotatoProjectileEntity potatoProjectile = new PotatoProjectileEntity(
                        entityhandler.POTATO_PROJECTILE.get(),
                        player,
                        level,
                        PROJECTILE_DAMAGE
                );

                Vec3 eyePosition = player.getEyePosition();
                potatoProjectile.setPos(eyePosition.x, eyePosition.y, eyePosition.z);

                potatoProjectile.shootFromRotation(
                        player,
                        player.getXRot(),
                        player.getYRot(),
                        0.0F,     // 倾斜角度
                        3.0F,              // 速度
                        1.0F               // 精准度
                );

                level.addFreshEntity(potatoProjectile);

                // 技能CD
                player.getCooldowns().addCooldown(this, configReader.getr_cd());
            }

            return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        }

        if (player.isCrouching()) {

            if (!level.isClientSide) {
                int currentLevel = getMiningSpeedLevel(itemstack);
                int newLevel = (currentLevel + 1) % (MAX_LEVEL + 1);

                setMiningSpeedLevel(itemstack, newLevel);

                String levelName = LEVEL_NAMES[newLevel];
                float speedValue = SPEED_LEVELS[newLevel];

                player.displayClientMessage(Component.literal("§6当前档位: §e" + levelName + " §7(速度: " + speedValue + ")"), true);
                player.displayClientMessage(Component.literal("§a当前档位: §e" + levelName), false);
            }

            return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        } else {
            if (!level.isClientSide) {
                PotatoProjectileEntity potatoProjectile = new PotatoProjectileEntity(
                        entityhandler.POTATO_PROJECTILE.get(),
                        player,
                        level,
                        PROJECTILE_DAMAGE
                );

                Vec3 eyePosition = player.getEyePosition();
                potatoProjectile.setPos(eyePosition.x, eyePosition.y, eyePosition.z);

                potatoProjectile.shootFromRotation(
                        player,
                        player.getXRot(),
                        player.getYRot(),
                        0.0F,     // 倾斜角度
                        3.0F,              // 速度
                        1.0F               // 精准度
                );

                level.addFreshEntity(potatoProjectile);

                // 技能CD
                player.getCooldowns().addCooldown(this, configReader.getr_cd());
            }

            return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        }
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
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
        Level level = player.level();
        if (player.isCreative()) {
            return false;
        }

        if (configReader.getr_c() && !level.isClientSide) {
            BlockState blockState = level.getBlockState(pos);

            if (getMiningSpeedLevel(itemstack) > 0) {

                if (shouldInstantMine(blockState, level, pos)) {

                    if (level instanceof ServerLevel) {

                        ServerLevel serverLevel = (ServerLevel) level;
                        BlockEntity blockEntity = level.getBlockEntity(pos);

                        LootParams.Builder lootParamsBuilder = new LootParams.Builder(serverLevel)
                                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                                .withParameter(LootContextParams.TOOL, itemstack)
                                .withOptionalParameter(LootContextParams.BLOCK_ENTITY, blockEntity)
                                .withOptionalParameter(LootContextParams.THIS_ENTITY, player);

                        java.util.List<ItemStack> drops = blockState.getDrops(lootParamsBuilder);

                        for (ItemStack drop : drops) {
                            if (!drop.isEmpty()) {
                                net.minecraft.world.entity.item.ItemEntity itemEntity =
                                        new net.minecraft.world.entity.item.ItemEntity(
                                                level,
                                                pos.getX() + 0.5,
                                                pos.getY() + 0.5,
                                                pos.getZ() + 0.5,
                                                drop
                                        );
                                level.addFreshEntity(itemEntity);
                            }
                        }

                        level.levelEvent(2001, pos, net.minecraft.world.level.block.Block.getId(blockState));

                        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                    }

                    return true;
                }
            }
        }
        return false;
    }

    private boolean shouldInstantMine(BlockState blockState, Level level, BlockPos pos) {

        if (blockState.getDestroySpeed(level, pos) < 0) {
            return false;
        }

        FluidState fluidState = blockState.getFluidState();
        if (!fluidState.isEmpty()) {
            return false;
        }

        if (blockState.is(Blocks.FIRE) || blockState.is(Blocks.SOUL_FIRE)) {
            return false;
        }

        if (blockState.isAir()) {
            return false;
        }

        return true;
    }

    private boolean isFireBlock(BlockState blockState) {

        return blockState.is(Blocks.FIRE) || blockState.is(Blocks.SOUL_FIRE);

    }

    @Override
    public InteractionResult useOn(net.minecraft.world.item.context.UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState blockState = level.getBlockState(pos);
        if (isFireBlock(blockState)) {
            if (!level.isClientSide) {
                level.levelEvent(null, 1009, pos, 0);
                level.removeBlock(pos, false);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.useOn(context);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {

        if (stack.getTag() != null && stack.getTag().contains("CreativePlayer")) {
            return super.getDestroySpeed(stack, state);
        }

        if (configReader.getr_c()) {
            return getCurrentMiningSpeed(stack);
        }

        return super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {

        if (stack.getTag() != null && stack.getTag().contains("CreativePlayer")) {
            return super.isCorrectToolForDrops(stack, state);
        }

        if (configReader.getr_c() && getMiningSpeedLevel(stack) > 0) {
            return true;
        }

        return super.isCorrectToolForDrops(stack, state);
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {

        if (player.isCreative()) {
            return super.canAttackBlock(state, level, pos, player);
        }

        if (configReader.getr_c() && getMiningSpeedLevel(player.getItemInHand(InteractionHand.MAIN_HAND)) > 0) {
            return true;
        }

        return super.canAttackBlock(state, level, pos, player);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, net.minecraft.world.entity.LivingEntity entity) {

        if (entity instanceof Player && ((Player)entity).isCreative()) {
            return super.mineBlock(stack, level, state, pos, entity);
        }

        if (configReader.getr_c() && getMiningSpeedLevel(stack) > 0) {
            return true;
        }

        return super.mineBlock(stack, level, state, pos, entity);
    }
}