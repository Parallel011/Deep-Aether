package io.github.razordevs.deep_aether.world.feature.features;

import com.mojang.serialization.Codec;
import io.github.razordevs.deep_aether.datagen.tags.DATags;
import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.world.feature.features.configuration.AercloudCloudConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;

import java.util.List;

public class AercloudCloudFeature extends Feature<AercloudCloudConfiguration> {

    public static final PerlinSimplexNoise NOISE = new PerlinSimplexNoise(new XoroshiroRandomSource(42), List.of(0,1, 0, 0, 0, 1, 0, 1));

    public static final int lowestY  = 155;
    public AercloudCloudFeature(Codec<AercloudCloudConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<AercloudCloudConfiguration> context) {
        WorldGenLevel reader = context.level();
        BlockPos pos = context.origin();
        AercloudCloudConfiguration config = context.config();
        place(reader, pos, config, context.random(), config.hasGrass());

        return true;
    }

    public void place(WorldGenLevel reader, BlockPos pos, AercloudCloudConfiguration config, RandomSource random, boolean hasGrass) {


        boolean goAgainstX = !reader.getBiomeManager().getNoiseBiomeAtPosition(pos.relative(Direction.Axis.X, 16)).is(DATags.Biomes.IS_CLOUD);
        boolean goAgainstNegativeX = !reader.getBiomeManager().getNoiseBiomeAtPosition(pos.relative(Direction.Axis.X, -16)).is(DATags.Biomes.IS_CLOUD);
        boolean goAgainstZ = !reader.getBiomeManager().getNoiseBiomeAtPosition(pos.relative(Direction.Axis.Z, 16)).is(DATags.Biomes.IS_CLOUD);
        boolean goAgainstNegativeZ = !reader.getBiomeManager().getNoiseBiomeAtPosition(pos.relative(Direction.Axis.Z, -16)).is(DATags.Biomes.IS_CLOUD);

        boolean goAgainstXAndZ = ((!reader.getBiomeManager().getNoiseBiomeAtPosition(pos.relative(Direction.Axis.X, 16).relative(Direction.Axis.Z, 16)).is(DATags.Biomes.IS_CLOUD)) && !goAgainstX && !goAgainstZ);
        boolean goAgainstXAndNegativeZ = (!reader.getBiomeManager().getNoiseBiomeAtPosition(pos.relative(Direction.Axis.X, 16).relative(Direction.Axis.Z, -16)).is(DATags.Biomes.IS_CLOUD) && !goAgainstX && !goAgainstNegativeZ);
        boolean goAgainstNegativeXAndZ = (!reader.getBiomeManager().getNoiseBiomeAtPosition(pos.relative(Direction.Axis.X, -16).relative(Direction.Axis.Z, 16)).is(DATags.Biomes.IS_CLOUD) && !goAgainstNegativeX && !goAgainstZ);
        boolean goAgainstNegativeXAndNegativeZ = (!reader.getBiomeManager().getNoiseBiomeAtPosition(pos.relative(Direction.Axis.X, -16).relative(Direction.Axis.Z, -16)).is(DATags.Biomes.IS_CLOUD) && !goAgainstNegativeX && !goAgainstNegativeZ);

        int chunkX = pos.getX() - (pos.getX() % 16);
        int chunkZ = pos.getZ() - (pos.getZ() % 16);

        //Fills a chunk with blocks with a noise-based terrain
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int xCoord = chunkX + x;
                int zCoord = chunkZ + z;

                double bottomNoiseValue = NOISE.getValue(xCoord * 0.02D, zCoord * 0.02D, false);
                double bottom = Math.abs(Mth.lerp(bottomNoiseValue, 4, 2));
                double originalBottom = bottom;

                double topNoiseValue = NOISE.getValue(xCoord * 0.007D, zCoord * 0.007D, false);
                double top = Mth.lerp(topNoiseValue, -2, 7) + 2;

                if (top < 3) {
                    top -= ((3-top) * 2);
                }

                final double decreaseMultiplier = 1.5D;
                final double decreaseMultiplierCorner = 4D;

                //SOUP
                if (goAgainstX) {
                    if (x > 8) {
                        bottom += (x - 8D) / decreaseMultiplier;
                        top -= (x - 8D) / decreaseMultiplier;
                    }
                }
                if (goAgainstNegativeX) {
                    if (x <= 8) {
                        bottom += (8D - x) / decreaseMultiplier;
                        top -= (8D - x) / decreaseMultiplier;
                    }
                }
                if (goAgainstZ) {
                    if (z > 8) {
                        bottom += (z - 8D) / decreaseMultiplier;
                        top -= (z - 8D) / decreaseMultiplier;
                    }
                }
                if (goAgainstNegativeZ) {
                    if (z < 8) {
                        bottom += (8D - z) / decreaseMultiplier;
                        top -= (8D - z) / decreaseMultiplier;
                    }
                } else if (goAgainstXAndZ) {
                    if (x > 12) {
                        bottom += (x - 12D) / decreaseMultiplierCorner;
                        top -= (x - 12D) / decreaseMultiplierCorner;
                    }
                    if (z > 12) {
                        bottom += (z - 12D) / decreaseMultiplierCorner;
                        top -= (z - 12D) / decreaseMultiplierCorner;
                    }
                } else if (goAgainstXAndNegativeZ) {
                    if (x > 12) {
                        bottom += (x - 12D) / decreaseMultiplierCorner;
                        top -= (x - 12D) / decreaseMultiplierCorner;
                    }
                    if (z < 4) {
                        bottom += (4D - z) / decreaseMultiplierCorner;
                        top -= (4D - z) / decreaseMultiplierCorner;
                    }
                } else if (goAgainstNegativeXAndZ) {
                    if (x < 4) {
                        bottom += (4D - x) / decreaseMultiplierCorner;
                        top -= (4D - x) / decreaseMultiplierCorner;
                    }
                    if (z > 12) {
                        bottom += (z - 12D) / decreaseMultiplierCorner;
                        top -= (z - 12D) / decreaseMultiplierCorner;
                    }
                } else if (goAgainstNegativeXAndNegativeZ) {
                    if (x < 4) {
                        bottom += (4D - x) / decreaseMultiplierCorner;
                        top -= (4D - x) / decreaseMultiplierCorner;
                    }
                    if (z < 4) {
                        bottom += (4D - z) / decreaseMultiplierCorner;
                        top -= (4D - z) / decreaseMultiplierCorner;
                    }
                }

                int y;
                for (y = Math.round(Math.round(lowestY + bottom - (top / 2))); y < lowestY + top + originalBottom; y++) {
                    this.setBlock(reader, pos.relative(Direction.Axis.X, x).relative(Direction.Axis.Z, z).atY(y), config.block().getState(random, pos));
                }
                if(hasGrass && top >= 4) {
                    this.setBlock(reader, pos.relative(Direction.Axis.X, x).relative(Direction.Axis.Z, z).atY(y), DABlocks.AERCLOUD_GRASS_BLOCK.get().defaultBlockState());
                }

                originalBottom = originalBottom * 4;

                int a = 0;
                if(originalBottom < 12) {
                    a = (int) (12 - originalBottom);
                }

                for (y = 12 + a; y > originalBottom; y--) {
                    this.setBlock(reader, pos.relative(Direction.Axis.X, x).relative(Direction.Axis.Z, z).atY(y + lowestY + 20), config.block().getState(random, pos));
                }
            }
        }
    }
}