package com.ManticCafe.potato_core.common.item.entityGenerator;

import com.ManticCafe.potato_core.common.entity.lootBox.potato_loot_box;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;

public class generator_potato_loot_box extends Item {

    private final RegistryObject<EntityType<potato_loot_box>> entityTypeSupplier;

    public generator_potato_loot_box(Properties properties,
                                     RegistryObject<EntityType<potato_loot_box>> entityTypeSupplier) {
        super(properties);
        this.entityTypeSupplier = entityTypeSupplier;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        ItemStack itemStack = context.getItemInHand();
        Player player = context.getPlayer();
        BlockPos blockPos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        BlockPos spawnPos = blockPos.relative(direction);
        potato_loot_box lootBox = entityTypeSupplier.get().create(level);
        if (lootBox == null) {
            return InteractionResult.FAIL;
        }
        lootBox.moveTo(
                spawnPos.getX() + 0.5,
                spawnPos.getY(),
                spawnPos.getZ() + 0.5,
                0.0F,
                0.0F
        );
        lootBox.setYRot(0.0F);
        lootBox.setXRot(0.0F);
        lootBox.yHeadRot = 0.0F;
        lootBox.yBodyRot = 0.0F;
        level.addFreshEntity(lootBox);
        if (player != null && !player.isCreative()) {
            itemStack.shrink(1);
        }
        return InteractionResult.CONSUME;
    }
}
