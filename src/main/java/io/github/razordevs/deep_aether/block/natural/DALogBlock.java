package io.github.razordevs.deep_aether.block.natural;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.natural.AetherLogBlock;
import io.github.razordevs.deep_aether.init.DABlocks;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;

public class DALogBlock extends AetherLogBlock {
    public DALogBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, false));
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            if(state.is(DABlocks.ROSEROOT_LOG.get())) {
                return DABlocks.STRIPPED_ROSEROOT_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DABlocks.ROSEROOT_WOOD.get())) {
                return DABlocks.STRIPPED_ROSEROOT_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DABlocks.YAGROOT_LOG.get())) {
                return DABlocks.STRIPPED_YAGROOT_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DABlocks.YAGROOT_WOOD.get())) {
                return DABlocks.STRIPPED_YAGROOT_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DABlocks.CRUDEROOT_LOG.get())) {
                return DABlocks.STRIPPED_CRUDEROOT_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DABlocks.CRUDEROOT_WOOD.get())) {
                return DABlocks.CRUDEROOT_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DABlocks.CONBERRY_LOG.get())) {
                return DABlocks.STRIPPED_CONBERRY_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DABlocks.CONBERRY_WOOD.get())) {
                return DABlocks.STRIPPED_CONBERRY_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DABlocks.SUNROOT_LOG.get())) {
                return DABlocks.STRIPPED_SUNROOT_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DABlocks.SUNROOT_WOOD.get())) {
                return DABlocks.STRIPPED_SUNROOT_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}