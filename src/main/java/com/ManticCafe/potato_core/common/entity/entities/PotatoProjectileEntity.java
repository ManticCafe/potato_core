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
import net.minecraft.world.phys.AABB;

public class PotatoProjectileEntity extends ThrowableItemProjectile {
    private float damage;

    public PotatoProjectileEntity(EntityType<? extends ThrowableItemProjectile> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
        this.damage = 0.0F; // 默认伤害值
    }

    public PotatoProjectileEntity(EntityType<? extends ThrowableItemProjectile> type, LivingEntity shooter, Level level, float damage) {
        super(type, shooter, level);
        this.setNoGravity(true);
        this.damage = damage;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.POTATO;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        if (!this.level().isClientSide) {
            createLightningAt(result.getLocation(), damage);
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        if (!this.level().isClientSide) {
            if (result.getEntity() instanceof LivingEntity livingEntity) {
                livingEntity.hurt(this.damageSources().lightningBolt(), damage);
            }

            createLightningAt(result.getLocation(), damage);
            this.discard();
        }
    }

    private void createLightningAt(Vec3 position, float damage) {
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