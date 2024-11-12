package io.github.razordevs.deep_aether.world.feature.features.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record AercloudCloudConfiguration(BlockStateProvider block, Boolean hasGrass) implements FeatureConfiguration {
    public static final Codec<AercloudCloudConfiguration> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BlockStateProvider.CODEC.fieldOf("block").forGetter(AercloudCloudConfiguration::block),
            Codec.BOOL.fieldOf("hasGrass").forGetter(AercloudCloudConfiguration::hasGrass))

            .apply(instance, AercloudCloudConfiguration::new));

    public BlockStateProvider block() {
        return this.block;
    }
}

