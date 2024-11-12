package io.github.razordevs.deep_aether.fluids;

import io.github.razordevs.deep_aether.datagen.tags.DATags;
import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.init.DAFluids;
import io.github.razordevs.deep_aether.init.DAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.fluids.FluidType;

public abstract class PoisonFluid extends FlowingFluid {

    @Override
    public FluidType getFluidType() {
        return DAFluidTypes.POISON_FLUID_TYPE.value();
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        this.fizz(pLevel, pPos);
    }
    @Override
    public Fluid getFlowing() {
        return DAFluids.POISON_FLOWING.get();
    }

    @Override
    public Fluid getSource() {
        return DAFluids.POISON_FLUID.get();
    }

    @Override
    protected boolean canConvertToSource(Level pLevel) {
        return false;
    }

    @Override
    public boolean isSame(Fluid pFluid) {
        return pFluid == DAFluids.POISON_FLUID.get() || pFluid == DAFluids.POISON_FLOWING.get();
    }

    private void fizz(LevelAccessor levelAccessor, BlockPos blockPos) {
        levelAccessor.levelEvent(1501, blockPos, 0);
    }

    protected void spreadTo(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, Direction direction, FluidState fluidState) {
        if (direction == Direction.DOWN) {
            FluidState fluidstate = levelAccessor.getFluidState(blockPos);
            if (this.is(DATags.Fluids.POISON) && fluidstate.is(FluidTags.LAVA)) {
                if (blockState.getBlock() instanceof LiquidBlock) {
                    levelAccessor.setBlock(blockPos, EventHooks.fireFluidPlaceBlockEvent(levelAccessor, blockPos, blockPos, Blocks.CRYING_OBSIDIAN.defaultBlockState()), 3);
                }

                this.fizz(levelAccessor, blockPos);
                return;
            }

            if (this.is(DATags.Fluids.POISON) && fluidstate.is(FluidTags.WATER)) {
                if (blockState.getBlock() instanceof LiquidBlock) {
                    levelAccessor.setBlock(blockPos, EventHooks.fireFluidPlaceBlockEvent(levelAccessor, blockPos, blockPos, DABlocks.AERSMOG.get().defaultBlockState()), 3);
                }

                this.fizz(levelAccessor, blockPos);
                return;
            }
        }

        super.spreadTo(levelAccessor, blockPos, blockState, direction, fluidState);
    }

    @Override
    public int getAmount(FluidState fluidState) {
        return fluidState.getValue(LEVEL);
    }

    @Override
    public boolean isSource(FluidState fluidState) {
        return false;
    }

    @Override
    protected int getSlopeFindDistance(LevelReader pLevel) {
        return 4;
    }

    @Override
    public Item getBucket() {
        return DAItems.PLACEABLE_POISON_BUCKET.get();
    }

    @Override
    public int getDropOff(LevelReader pLevel) {
        return 1;
    }

    @Override
    public int getTickDelay(LevelReader pLevel) {
        return 5;
    }


    @Override
    public boolean canBeReplacedWith(FluidState pFluidState, BlockGetter pBlockReader, BlockPos pPos, Fluid pFluid, Direction pDirection) {
        return pFluidState.getHeight(pBlockReader, pPos) >= 0.44444445F && pFluid.is(FluidTags.WATER);
    }
    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    public BlockState createLegacyBlock(FluidState pState) {
        return DABlocks.POISON_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(pState));
    }

    public static class Flowing extends PoisonFluid {

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> fluidStateBuilder) {
            super.createFluidStateDefinition(fluidStateBuilder);
            fluidStateBuilder.add(LEVEL);
        }
    }

    public static class Source extends PoisonFluid {

        @Override
        public int getAmount(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState fluidState) {
            return true;
        }
    }
}
