package io.github.razordevs.deep_aether.world.feature.tree.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import io.github.razordevs.deep_aether.block.natural.SunrootHangerBlock;
import io.github.razordevs.deep_aether.init.DABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;


public class SunrootHangerDecorator extends TreeDecorator {
    public static final MapCodec<SunrootHangerDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(SunrootHangerDecorator::new, (vineDecorator) -> vineDecorator.probability).stable();

    private final float probability;

    @Override
    protected TreeDecoratorType<?> type() {
        return DADecoratorType.SUNROOT_HANGER.get();
    }

    public SunrootHangerDecorator(float probability) {
        this.probability = probability;
    }

    public void place(Context context) {
        RandomSource randomsource = context.random();
        context.leaves().forEach((blockPos) -> {
            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos = blockPos.below();
                if (context.isAir(blockpos)) {
                    addHangingVine(blockpos, context);
                }
            }
        });
    }

    private void addHangingVine(BlockPos blockPos, Context context) {
        placeVine(blockPos, context, DABlocks.SUNROOT_HANGER.get().defaultBlockState().setValue(SunrootHangerBlock.BOTTOM, false));
        int i = 4;

        for(BlockPos blockpos = blockPos.below(); context.isAir(blockpos) && i > 0; --i) {
            if(i != 1 && context.isAir(blockpos.below())) {
                placeVine(blockPos.below(i), context, DABlocks.SUNROOT_HANGER.get().defaultBlockState().setValue(SunrootHangerBlock.BOTTOM, false));
            }
            else placeVine(blockPos.below(i), context, DABlocks.SUNROOT_HANGER.get().defaultBlockState().setValue(SunrootHangerBlock.BOTTOM, true));
        }

    }
    public void placeVine(BlockPos blockPos, Context context, BlockState state) {
        context.setBlock(blockPos, state);
    }
}

