package com.ManticCafe.potato_core.common.item.weapon;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import com.ManticCafe.potato_core.common.tier.infinite_tier;
import net.minecraft.world.level.Level;
import com.ManticCafe.potato_core.common.entity.entities.DiamondProjectileEntity;
import com.ManticCafe.potato_core.common.entity.entityhandler;
import net.minecraft.world.phys.Vec3;

public class the_last_sword_level2 extends SwordItem {

    private static final float PROJECTILE_DAMAGE = 24.0F;

    public the_last_sword_level2() {
        super(infinite_tier.INFINITE_TIER,24,1,new Item.Properties().durability(-1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            DiamondProjectileEntity diamondProjectile = new DiamondProjectileEntity(
                    entityhandler.DIAMOND_PROJECTILE.get(),
                    player,
                    level,
                    PROJECTILE_DAMAGE // 传递伤害值
            );

            Vec3 eyePosition = player.getEyePosition();
            diamondProjectile.setPos(eyePosition.x, eyePosition.y, eyePosition.z);

            diamondProjectile.shootFromRotation(
                    player,
                    player.getXRot(),
                    player.getYRot(),
                    0.0F,     // 倾斜角度
                    3.0F,              // 速度
                    1.0F               // 精准度
            );

            level.addFreshEntity(diamondProjectile);

            //技能CD
            player.getCooldowns().addCooldown(this, 70);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}
