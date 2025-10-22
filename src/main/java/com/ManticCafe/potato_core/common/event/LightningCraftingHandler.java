package com.ManticCafe.potato_core.common.event;

import com.ManticCafe.potato_core.common.block.functionBlocks.itemBaseBlock;
import com.ManticCafe.potato_core.common.crafting.StructureCraftingProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LightningCraftingHandler {

    @SubscribeEvent
    public static void onLightningStrike(EntityStruckByLightningEvent event) {
        LightningBolt lightning = event.getLightning();
        Level level = lightning.getCommandSenderWorld();

        if (level.isClientSide()) {
            return;
        }

        BlockPos lightningPos = lightning.blockPosition();

        checkAndCraftInVolume(level, lightningPos);
    }

    private static void checkAndCraftInVolume(Level level, BlockPos centerPos) {
        boolean foundStructure = false;

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    BlockPos checkPos = centerPos.offset(x, y, z);

                    BlockState state = level.getBlockState(checkPos);
                    if (state.getBlock() instanceof itemBaseBlock) {
                        StructureCraftingProcessor.CraftingResult result =
                                StructureCraftingProcessor.processCrafting(level, checkPos);

                        if (result.isSuccess()) {
                            level.levelEvent(3001, checkPos, 0); // 末影龙生长效果
                            level.playSound(null, checkPos,
                                    net.minecraft.sounds.SoundEvents.LIGHTNING_BOLT_IMPACT,
                                    net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);

                            foundStructure = true;
                            break;
                        }
                    }
                }
                if (foundStructure) {
                    break;
                }
            }
            if (foundStructure) {
                break;
            }
        }
    }
}