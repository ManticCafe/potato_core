package com.ManticCafe.potato_core.common.entity;

import com.ManticCafe.potato_core.common.block.blockhandler;
import com.ManticCafe.potato_core.common.entity.entities.DiamondProjectileEntity;
import com.ManticCafe.potato_core.common.entity.entities.PotatoProjectileEntity;
import com.ManticCafe.potato_core.common.entity.entities.itemBaseBlockEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.ManticCafe.potato_core.main;

public class entityhandler {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, main.MODID);

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, main.MODID);
    //register

    public static final RegistryObject<EntityType<DiamondProjectileEntity>> DIAMOND_PROJECTILE =
            ENTITY_TYPES.register("diamond_projectile",
                    () -> EntityType.Builder.<DiamondProjectileEntity>of(DiamondProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .build("diamond_projectile"));

    public static final RegistryObject<EntityType<PotatoProjectileEntity>> POTATO_PROJECTILE =
            ENTITY_TYPES.register("potato_projectile",
                    () -> EntityType.Builder.<PotatoProjectileEntity>of(PotatoProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .build("potato_projectile"));

    public static final RegistryObject<BlockEntityType<itemBaseBlockEntity>> ITEM_BASE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("item_base_block_entity",
                    () -> BlockEntityType.Builder.of(itemBaseBlockEntity::new,
                            blockhandler.ITEM_BASE_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
        BLOCK_ENTITIES.register(eventBus);
    }
}
