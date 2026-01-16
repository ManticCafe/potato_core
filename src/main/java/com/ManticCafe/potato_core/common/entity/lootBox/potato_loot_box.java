package com.ManticCafe.potato_core.common.entity.lootBox;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.List;



public class potato_loot_box extends Mob implements GeoEntity{

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final int STATE_SPAWNING = 0;      // 生成动画
    private static final int STATE_IDLE = 1;          // 空闲状态
    private static final int STATE_OPENING = 2;       // 被点击打开动画
    private static final int STATE_SPITTING = 3;      // 吐物品动画
    private static final int STATE_DESPAWNING = 4;    // 消失状态

    private static final EntityDataAccessor<Integer> DATA_STATE = SynchedEntityData.defineId(potato_loot_box.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ANIMATION_TICK = SynchedEntityData.defineId(potato_loot_box.class, EntityDataSerializers.INT);

    private int clientAnimationTimer = 0;
    private int spitItemCount = 0;
    private int spitCooldown = 0;

    private final List<ItemStack> itemsToSpit = new ArrayList<>();

    public potato_loot_box(EntityType<? extends Mob> type, Level level) {
        super(type, level);
        this.setNoAi(true);
        initializeItemsToSpit();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_STATE, STATE_SPAWNING);
        this.entityData.define(DATA_ANIMATION_TICK, 0);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (DATA_STATE.equals(key) || DATA_ANIMATION_TICK.equals(key)) {
            clientAnimationTimer = 0;
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.0)
                .add(Attributes.ATTACK_DAMAGE, 0.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 0.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0);
    }

    @Override
    protected AABB makeBoundingBox() {

        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        float offsetX = 0.0F;  // 向X轴正方向偏移
        float offsetZ = -0.3725F; // 向Z轴负方向偏移
        float offsetY = 0.0F;  // Y轴偏移（通常为0）
        float collisionLength = 1.65F;   // X轴（长度）
        float collisionWidth = 1.8725F;    // Z轴（宽度）
        float collisionHeight = 1.5F;   // Y轴（高度）

        double halfLength = collisionLength / 2.0;
        double halfWidth = collisionWidth / 2.0;

        return new AABB(
                x - halfLength + offsetX,
                y + offsetY,
                z - halfWidth + offsetZ,
                x + halfLength + offsetX,
                y + collisionHeight + offsetY,
                z + halfWidth + offsetZ
        );
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 0.7F;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isAffectedByPotions() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public void push(Entity entity) {

    }

    @Override
    public void playerTouch(Player player) {

    }

    @Override
    public void aiStep() {
        if (this.level().isClientSide) {
            this.yBodyRot = this.getYRot();
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public void tickLeash() {
        if (this.isLeashed()) {
            this.dropLeash(true, true);
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(Vec3.ZERO);
        if (!this.level().isClientSide && this.isLeashed()) {
            this.dropLeash(true, true);
        }
        if (this.level().isClientSide) {
            clientAnimationTimer++;
        } else {
            int currentState = getEntityState();
            int animationTick = getAnimationTick();
            setAnimationTick(animationTick + 1);

            switch (currentState) {
                case STATE_SPAWNING:
                    if (animationTick >= 20) {
                        setEntityState(STATE_IDLE);
                        setAnimationTick(0);
                    }
                    break;

                case STATE_OPENING:
                    if (animationTick >= 20) {
                        setEntityState(STATE_SPITTING);
                        setAnimationTick(0);
                        spitItemCount = 0;
                        spitCooldown = 0;
                    }
                    break;

                case STATE_SPITTING:
                    spitCooldown++;
                    if (animationTick < 20) {

                    } else {
                        if (spitCooldown >= 5 && spitItemCount < itemsToSpit.size()) { // 每5tick吐一个
                            spitItem(itemsToSpit.get(spitItemCount));
                            spitItemCount++;
                            spitCooldown = 0;
                            this.level().playSound(null, this.blockPosition(),
                                    SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.5f, 1.0f);
                            for (int i = 0; i < 5; i++) {
                                double dx = (this.random.nextDouble() - 0.5) * 0.5;
                                double dy = this.random.nextDouble() * 0.5;
                                double dz = (this.random.nextDouble() - 0.5) * 0.5;
                                this.level().addParticle(ParticleTypes.HAPPY_VILLAGER,
                                        this.getX(), this.getY() + 1.0, this.getZ(), dx, dy, dz);
                            }
                        }
                        if (spitItemCount >= itemsToSpit.size()) {
                            setEntityState(STATE_DESPAWNING);
                            setAnimationTick(0);
                        }
                    }
                    break;

                case STATE_DESPAWNING:
                    if (animationTick >= 20) {
                        this.remove(RemovalReason.KILLED);
                    }
                    break;
            }
        }
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi()) {
            super.travel(travelVector);
            return;
        }
        this.setDeltaMovement(Vec3.ZERO);
    }

    @Override
    protected void customServerAiStep() {
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() instanceof Player player) {
            if (player.isCreative()) {
                this.remove(RemovalReason.KILLED);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAffectedByFluids() {
        return false;
    }

    @Override
    public boolean isNoAi() {
        return true;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level().isClientSide && hand == InteractionHand.MAIN_HAND) {
            int currentState = getEntityState();
            if (currentState == STATE_IDLE) {
                setEntityState(STATE_OPENING);
                setAnimationTick(0);
                this.level().playSound(null, this.blockPosition(),
                        SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 1.0f, 1.0f);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "mainController", 0, this::predicate));
    }

    protected <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        int currentState = getEntityState();

        switch (currentState) {
            case STATE_SPAWNING:
                event.getController().setAnimation(RawAnimation.begin()
                        .then("animation2", Animation.LoopType.PLAY_ONCE));
                return PlayState.CONTINUE;

            case STATE_OPENING:
                event.getController().setAnimation(RawAnimation.begin()
                        .then("animation3", Animation.LoopType.PLAY_ONCE));
                return PlayState.CONTINUE;

            case STATE_SPITTING:
                if (getAnimationTick() < 20) {
                    event.getController().setAnimation(RawAnimation.begin()
                            .then("animation", Animation.LoopType.PLAY_ONCE));
                } else {
                    event.getController().setAnimation(RawAnimation.begin()
                            .then("animation", Animation.LoopType.HOLD_ON_LAST_FRAME));
                }
                return PlayState.CONTINUE;

            case STATE_IDLE:
            case STATE_DESPAWNING:
            default:
                return PlayState.STOP;
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

   // 初始化物品列表
    private void initializeItemsToSpit() {
        itemsToSpit.clear();
        itemsToSpit.add(new ItemStack(Items.GOLD_INGOT, 1));
        itemsToSpit.add(new ItemStack(Items.DIAMOND, 1));
        itemsToSpit.add(new ItemStack(Items.EMERALD, 1));
        itemsToSpit.add(new ItemStack(Items.IRON_INGOT, 1));
    }

    // 设置物品列表
    public void setItemsToSpit(ItemStack... items) {
        itemsToSpit.clear();
        if (items != null) {
            for (ItemStack item : items) {
                if (item != null && !item.isEmpty()) {
                    itemsToSpit.add(item);
                }
            }
        }
    }

    // 吐出一个物品
    private void spitItem(ItemStack itemStack) {
        if (!this.level().isClientSide) {
            ItemEntity itemEntity = new ItemEntity(
                    this.level(),
                    this.getX(),
                    this.getY() + 1.5,
                    this.getZ(),
                    itemStack
            );
            itemEntity.setDeltaMovement(
                    (this.random.nextDouble() - 0.5) * 0.2,
                    0.5 + this.random.nextDouble() * 0.3,
                    (this.random.nextDouble() - 0.5) * 0.2
            );
            itemEntity.setPickUpDelay(10);
            itemEntity.lifespan = 6000;
            this.level().addFreshEntity(itemEntity);
        }
    }

    // 获取实体状态
    private int getEntityState() {
        return this.entityData.get(DATA_STATE);
    }

    // 设置实体状态
    private void setEntityState(int state) {
        this.entityData.set(DATA_STATE, state);
    }

    // 获取动画tick
    private int getAnimationTick() {
        return this.entityData.get(DATA_ANIMATION_TICK);
    }

    // 设置动画tick
    private void setAnimationTick(int tick) {
        this.entityData.set(DATA_ANIMATION_TICK, tick);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("EntityState", getEntityState());
        compound.putInt("AnimationTick", getAnimationTick());
        compound.putInt("SpitItemCount", spitItemCount);

        CompoundTag itemsTag = new CompoundTag();
        for (int i = 0; i < itemsToSpit.size(); i++) {
            itemsToSpit.get(i).save(itemsTag);
        }
        compound.put("ItemsToSpit", itemsTag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("EntityState")) {
            setEntityState(compound.getInt("EntityState"));
        }
        if (compound.contains("AnimationTick")) {
            setAnimationTick(compound.getInt("AnimationTick"));
        }
        if (compound.contains("SpitItemCount")) {
            spitItemCount = compound.getInt("SpitItemCount");
        }

        if (compound.contains("ItemsToSpit")) {
            itemsToSpit.clear();
            initializeItemsToSpit();
        }
    }
}
