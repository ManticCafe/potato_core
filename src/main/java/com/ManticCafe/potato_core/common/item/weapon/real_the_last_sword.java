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
import com.ManticCafe.potato_core.common.entity.entities.PotatoProjectileEntity;
import com.ManticCafe.potato_core.common.entity.entityhandler;
import net.minecraft.world.phys.Vec3;
import com.ManticCafe.potato_core.common.entity.entities.PotatoProjectileEntity;
import com.ManticCafe.potato_core.common.config.configReader;

public class real_the_last_sword extends SwordItem {

    private static final int PROJECTILE_DAMAGE = configReader.getr_projectile_damage();

    public real_the_last_sword() {
        super(infinite_tier.INFINITE_TIER, 2024, 12, new Item.Properties().durability(-1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

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