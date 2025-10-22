package com.ManticCafe.potato_core.common.structure;

import com.ManticCafe.potato_core.common.block.functionBlocks.itemBaseBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class StructureChecker {

    private static final boolean[][] STRUCTURE_TEMPLATE = {
            {false, false, false, true,  false, false, false}, // AAAIAAA
            {false, true,  false, false, false, true,  false}, // AIAAAIA
            {false, false, false, false, false, false, false}, // AAAAAAA
            {true,  false, false, true,  false, false, true},  // IAAIAAI
            {false, false, false, false, false, false, false}, // AAAAAAA
            {false, true,  false, false, false, true,  false}, // AIAAAIA
            {false, false, false, true,  false, false, false}  // AAAIAAA
    };

    private static final List<TagKey<Block>> ALLOWED_BLOCK_TAGS = List.of(
            BlockTags.FLOWERS,      // 花
            BlockTags.SAPLINGS,     // 树苗
            BlockTags.SNOW,         // 雪
            BlockTags.REPLACEABLE   // 可替换方块
    );

    // 定义允许的特定方块
    private static final List<Block> ALLOWED_SPECIFIC_BLOCKS = List.of(
            Blocks.AIR,             // 空气
            Blocks.FIRE,            // 火焰
            Blocks.SOUL_FIRE,       // 灵魂火焰
            Blocks.GRASS,           // 草
            Blocks.TALL_GRASS,      // 高草
            Blocks.FERN,            // 蕨
            Blocks.LARGE_FERN       // 大型蕨
    );

    public static StructureCheckResult checkStructure(Level level, BlockPos centerPos) {
        List<BlockPos> structureBlocks = new ArrayList<>();
        int centerX = centerPos.getX();
        int centerZ = centerPos.getZ();
        int y = centerPos.getY();

        for (int xOffset = -3; xOffset <= 3; xOffset++) {
            for (int zOffset = -3; zOffset <= 3; zOffset++) {
                BlockPos checkPos = new BlockPos(centerX + xOffset, y, centerZ + zOffset);
                BlockState state = level.getBlockState(checkPos);
                boolean shouldBeItemBase = STRUCTURE_TEMPLATE[xOffset + 3][zOffset + 3];

                if (shouldBeItemBase) {
                    if (!(state.getBlock() instanceof itemBaseBlock)) {
                        return StructureCheckResult.failure();
                    }
                    structureBlocks.add(checkPos);
                } else {
                    if (!isAllowedBlock(state)) {
                        return StructureCheckResult.failure();
                    }
                }
            }
        }

        return StructureCheckResult.success(structureBlocks);
    }

    private static boolean isAllowedBlock(BlockState state) {
        if (ALLOWED_SPECIFIC_BLOCKS.contains(state.getBlock())) {
            return true;
        }

        for (TagKey<Block> tag : ALLOWED_BLOCK_TAGS) {
            if (state.is(tag)) {
                return true;
            }
        }

        if (state.isAir()) {
            return true;
        }

        return false;
    }

    public static class StructureCheckResult {
        private final boolean valid;
        private final List<BlockPos> structureBlocks;

        private StructureCheckResult(boolean valid, List<BlockPos> structureBlocks) {
            this.valid = valid;
            this.structureBlocks = structureBlocks;
        }

        public static StructureCheckResult success(List<BlockPos> structureBlocks) {
            return new StructureCheckResult(true, structureBlocks);
        }

        public static StructureCheckResult failure() {
            return new StructureCheckResult(false, new ArrayList<>());
        }

        public boolean isValid() { return valid; }
        public List<BlockPos> getStructureBlocks() { return structureBlocks; }
    }
}