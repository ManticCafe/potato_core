package com.ManticCafe.potato_core.common.block;

import com.ManticCafe.potato_core.common.block.functionBlocks.itemBaseBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.ManticCafe.potato_core.main;

public class blockhandler {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, main.MODID);

    public static final RegistryObject<Block> ITEM_BASE_BLOCK = BLOCKS.register("item_base_block",
            itemBaseBlock::new);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}