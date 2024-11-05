package io.github.razordevs.deep_aether.world.feature;

import com.aetherteam.aether.world.configuration.AercloudConfiguration;
import com.aetherteam.aether.world.configuration.AetherLakeConfiguration;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.world.feature.features.*;
import io.github.razordevs.deep_aether.world.feature.features.configuration.AercloudCloudConfiguration;
import io.github.razordevs.deep_aether.world.feature.features.configuration.DAHugeMushroomFeatureConfiguration;
import io.github.razordevs.deep_aether.world.feature.features.configuration.FallenTreeConfiguration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DAFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, DeepAether.MODID);
    public static DeferredHolder<Feature<?>, Feature<FallenTreeConfiguration>> FALLEN_TREE = FEATURES.register("fallen_tree", () -> new FallenTreeFeature(FallenTreeConfiguration.CODEC));
    public static DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> TOTEM = FEATURES.register("totem", () -> new TotemFeature(NoneFeatureConfiguration.CODEC));
    public static DeferredHolder<Feature<?>, Feature<AercloudCloudConfiguration>> AERCLOUD_CLOUD = FEATURES.register("aercloud_cloud", () -> new AercloudCloudFeature(AercloudCloudConfiguration.CODEC));
    public static DeferredHolder<Feature<?>, Feature<AercloudCloudConfiguration>> RAIN_AERCLOUD_CLOUD = FEATURES.register("rain_aercloud_cloud", () -> new RainAercloudCloudFeature(AercloudCloudConfiguration.CODEC));
    public static DeferredHolder<Feature<?>, RootFeature> AERCLOUD_ROOTS = FEATURES.register("aercloud_roots", () -> new RootFeature(AercloudConfiguration.CODEC));
    public static DeferredHolder<Feature<?>, Feature<ConfiguredBoulder.Config>> CONFIGURED_BOULDER = FEATURES.register("configured_boulder", () -> new ConfiguredBoulder(ConfiguredBoulder.Config.CODEC));
    public static DeferredHolder<Feature<?>, Feature<ColumnFeatureConfiguration>> CLORITE_COLUMNS = FEATURES.register("clorite_columns", () -> new CloriteColumnsFeature(ColumnFeatureConfiguration.CODEC));
    public static DeferredHolder<Feature<?>, Feature<DAHugeMushroomFeatureConfiguration>> IMPROVED_MUSHROOM_FEATURE = FEATURES.register("improved_mushroom_feature", () -> new DAHugeMushroomFeature(DAHugeMushroomFeatureConfiguration.CODEC));
    public static DeferredHolder<Feature<?>, Feature<AetherLakeConfiguration>> POISON_LAKE = FEATURES.register("poison_lake", () -> new PoisonLakeFeature(AetherLakeConfiguration.CODEC));
}

