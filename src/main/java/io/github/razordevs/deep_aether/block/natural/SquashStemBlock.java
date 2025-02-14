package io.github.razordevs.deep_aether.block.natural;

import io.github.razordevs.deep_aether.init.DABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Optional;
import java.util.Random;

public class SquashStemBlock extends StemBlock {
    private final ResourceKey<Block> attachedStem;

    public SquashStemBlock(ResourceKey<Block> fruit, ResourceKey<Block> stem, ResourceKey<Item> seed, Properties properties) {
        super(fruit, stem, seed, properties);
        this.attachedStem = stem;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource randomSource) {
        if (!level.isAreaLoaded(blockPos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (level.getRawBrightness(blockPos, 0) >= 9) {
            float f = getGrowthSpeed(blockState, level, blockPos);
            if (net.neoforged.neoforge.common.CommonHooks.canCropGrow(level, blockPos, blockState, randomSource.nextInt((int)(25.0F / f) + 1) == 0)) {
                int i = blockState.getValue(AGE);
                if (i < 7) {
                    level.setBlock(blockPos, blockState.setValue(AGE, i + 1), 2);
                } else {
                    Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource);
                    BlockPos blockpos = blockPos.relative(direction);
                    BlockState blockstate = level.getBlockState(blockpos.below());
                    Registry<Block> registry = level.registryAccess().registryOrThrow(Registries.BLOCK);
                    Optional<Block> optional = registry.getOptional(this.randomizedSquash().getKey());
                    Optional<Block> optional1 = registry.getOptional(this.attachedStem);
                    if (optional.isPresent() && optional1.isPresent()) {
                        if (level.isEmptyBlock(blockpos) && (blockstate.canSustainPlant(level, blockpos.below(), Direction.UP, optional.get().defaultBlockState()).isDefault() || blockstate.is(Blocks.FARMLAND) || blockstate.is(BlockTags.DIRT))) {
                                level.setBlockAndUpdate(blockpos, optional.get().defaultBlockState());
                                level.setBlockAndUpdate(blockPos, optional1.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, direction));
                        }
                    }
                }
                net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(level, blockPos, blockState);
            }
        }
    }

    /**
     * [Code Copy] {@link CropBlock#getGrowthSpeed(BlockState, BlockGetter, BlockPos)}
     */
    protected static float getGrowthSpeed(BlockState blockState, BlockGetter getter, BlockPos pos) {
        Block p_52273_ = blockState.getBlock();
        float f = 1.0F;
        BlockPos blockpos = pos.below();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                float f1 = 0.0F;
                BlockState blockstate = getter.getBlockState(blockpos.offset(i, 0, j));
                net.neoforged.neoforge.common.util.TriState soilDecision = blockstate.canSustainPlant(getter, blockpos.offset(i, 0, j), net.minecraft.core.Direction.UP, blockState);
                if (soilDecision.isDefault() ? blockstate.getBlock() instanceof net.minecraft.world.level.block.FarmBlock : soilDecision.isTrue()) {
                    f1 = 1.0F;
                    if (blockstate.isFertile(getter, pos.offset(i, 0, j))) {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = getter.getBlockState(blockpos3).is(p_52273_) || getter.getBlockState(blockpos4).is(p_52273_);
        boolean flag1 = getter.getBlockState(blockpos1).is(p_52273_) || getter.getBlockState(blockpos2).is(p_52273_);
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = getter.getBlockState(blockpos3.north()).is(p_52273_)
                    || getter.getBlockState(blockpos4.north()).is(p_52273_)
                    || getter.getBlockState(blockpos4.south()).is(p_52273_)
                    || getter.getBlockState(blockpos3.south()).is(p_52273_);
            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }

    private DeferredBlock<Block> randomizedSquash() {
        return new Random().nextBoolean() ? DABlocks.BLUE_SQUASH : DABlocks.GREEN_SQUASH;
    }
}
