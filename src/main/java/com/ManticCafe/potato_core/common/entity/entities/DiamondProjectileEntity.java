package com.ManticCafe.potato_core.common.entity.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class DiamondProjectileEntity extends ThrowableItemProjectile {
    public DiamondProjectileEntity(EntityType<? extends ThrowableItemProjectile> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
    }

    public DiamondProjectileEntity(EntityType<? extends ThrowableItemProjectile> type, LivingEntity shooter, Level level) {
        super(type, shooter, level);
        this.setNoGravity(true);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.DIAMOND;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        if (!this.level().isClientSide) {
            createLightningAt(result.getLocation());

            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        if (!this.level().isClientSide) {
            createLightningAt(result.getLocation());

            this.discard();
        }
    }

    private void createLightningAt(Vec3 position) {
        LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(this.level());
        if (lightningBolt != null) {
            lightningBolt.moveTo(position.x, position.y, position.z);
            lightningBolt.setVisualOnly(false);
            this.level().addFreshEntity(lightningBolt);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.tickCount > 200) {
            this.discard();
        }
    }
}