package com.ManticCafe.potato_core.common.block;

import com.ManticCafe.potato_core.common.block.functionBlocks.itemBaseBlock;
import com.ManticCafe.potato_core.main;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class blockhandler {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, main.MODID);

    public static final RegistryObject<Block> ITEM_BASE_BLOCK = BLOCKS.register("item_base_block",
            itemBaseBlock::new);

    public static final RegistryObject<Block> APOCALYPTIUM_BLOCK = BLOCKS.register("apocalyptium_block", () -> new Block(BlockBehaviour.Properties.of()
            .strength(50.0F,1200.0F)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()
            .lightLevel(state -> 14)
    ));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}