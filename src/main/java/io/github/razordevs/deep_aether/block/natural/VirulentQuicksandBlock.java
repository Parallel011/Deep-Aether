package io.github.razordevs.deep_aether.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PowderSnowBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import io.github.razordevs.deep_aether.init.DAItems;

import java.util.Optional;

public class VirulentQuicksandBlock extends PowderSnowBlock {
    public VirulentQuicksandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState state, boolean b) {
        level.scheduleTick(blockPos, this, this.getDelayAfterPlace());
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos1) {
        levelAccessor.scheduleTick(blockPos, this, this.getDelayAfterPlace());
        return super.updateShape(blockState, direction, blockState1, levelAccessor, blockPos, blockPos1);
    }

    protected int getDelayAfterPlace() {
        return 2;
    }

    public static boolean isFree(BlockState blockState) {
        return blockState.isAir() || blockState.is(BlockTags.FIRE) || !blockState.getFluidState().isEmpty() || blockState.canBeReplaced();
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(16) == 0) {
            BlockPos blockpos = blockPos.below();
            if (isFree(level.getBlockState(blockpos))) {
                double d0 = (double)blockPos.getX() + randomSource.nextDouble();
                double d1 = (double)blockPos.getY() - 0.05D;
                double d2 = (double)blockPos.getZ() + randomSource.nextDouble();
                level.addParticle(ParticleTypes.WHITE_ASH, d0, d1, d2, 0.1, 0, 0.2);
                level.addParticle(ParticleTypes.WHITE_ASH, d0, d1, d2, 0.2, 0,  0.1);
            }
        }

    }


    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getInBlockState().is(this)) {
            entity.makeStuckInBlock(blockState, new Vec3(0.9F, 1.5D, 0.9F));
            if (level.isClientSide) {
                RandomSource randomsource = level.getRandom();
                boolean flag = entity.xOld != entity.getX() || entity.zOld != entity.getZ();
                if (flag && randomsource.nextBoolean()) {
                    level.addParticle(ParticleTypes.ASH, entity.getX(), pos.getY() + 1, entity.getZ(), Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F, 0.05F, Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F);
                }
            }
        }
        if (((int)entity.getEyeY()) == pos.getY())
            if (entity instanceof LivingEntity living)
                living.hurt(level.damageSources().inWall(), 1f);
    }


    @Override
    public ItemStack pickupBlock(Player player, LevelAccessor accessor, BlockPos pos, BlockState blockState) {
        accessor.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
        if (!accessor.isClientSide()) {
            accessor.levelEvent(2001, pos, Block.getId(blockState));
        }

        return new ItemStack(DAItems.VIRULENT_QUICKSAND_BUCKET.get());
    }

    @Override
    public boolean skipRendering(BlockState blockState, BlockState blockState1, Direction direction) {
        return blockState1.is(this) || super.skipRendering(blockState, blockState1, direction);
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getVisualShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.SAND_BREAK);
    }

}
