package teamrazor.deepaether.world.feature;

import com.gildedgames.aether.data.resources.builders.AetherPlacedFeatureBuilders;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamrazor.deepaether.DeepAetherMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.world.feature.tree.decorators.FlowerDecorator;

import java.util.List;

import static teamrazor.deepaether.world.feature.DeepAetherModConfiguredFeatures.AERGLOW_FOREST_TREES_PLACEMENT;

public class DeepAetherModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> AERGLOW_FOREST_TREES_PLACEMENT = createKey("aerglow_forest_trees_placement");


    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(DeepAetherMod.MODID, name));
    }


    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, AERGLOW_FOREST_TREES_PLACEMENT, configuredFeatures.getOrThrow(DeepAetherModConfiguredFeatures.AERGLOW_FOREST_TREES_PLACEMENT),
                AetherPlacedFeatureBuilders.treePlacement(PlacementUtils.countExtra(2, 0.1F, 1)));


    }

    private static void register
            (BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>>
                    configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register
            (BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>>
                    configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
        //TREES
        /*public static final RegistryObject<PlacedFeature> ROSEWOOD_PLACED = PLACED_FEATURES.register("rosewood_placed",
                () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                DeepAetherModConfiguredFeatures.ROSEWOOD_SPAWN, VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(0, 0.05F, 1))));
        public static final RegistryObject<PlacedFeature> ROSEWOOD_PLACED = PLACED_FEATURES.register("yagroot_placed",
                () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                        DeepAetherModConfiguredFeatures.YAGROOT_SPAWN, VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(0, 0.05F, 1))));
        public static final RegistryObject<PlacedFeature> CRUDEROOT_PLACED = PLACED_FEATURES.register("cruderoot_placed",
                () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                        DeepAetherModConfiguredFeatures.CRUDEROOT_SPAWN, VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(0, 0.05F, 1))));



/*
        //ORES
        public static final RegistryObject<PlacedFeature> ASETERITE_PLACED = PLACED_FEATURES.register("aseterite_placed",  () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                DeepAetherModConfiguredFeatures.ASETERITE, DeepAetherModOrePlacement.commonOrePlacement(1, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-50), VerticalAnchor.aboveBottom(200)))));
        public static final RegistryObject<PlacedFeature> YALLESITE_PLACED = PLACED_FEATURES.register("yallesite_placed",  () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                DeepAetherModConfiguredFeatures.YALLESITE, DeepAetherModOrePlacement.commonOrePlacement(1, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-50), VerticalAnchor.aboveBottom(200)))));
        public static final RegistryObject<PlacedFeature> DARKERITE_PLACED = PLACED_FEATURES.register("darkerite_placed",  () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                DeepAetherModConfiguredFeatures.DARKERITE, DeepAetherModOrePlacement.commonOrePlacement(1, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-50), VerticalAnchor.aboveBottom(200)))));

        public static final RegistryObject<PlacedFeature> CLORITE_PLACED = PLACED_FEATURES.register("clorite_placed",  () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                DeepAetherModConfiguredFeatures.CLORITE, DeepAetherModOrePlacement.commonOrePlacement(1, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-50), VerticalAnchor.aboveBottom(200)))));

        public static final RegistryObject<PlacedFeature> JARINITE_PLACED = PLACED_FEATURES.register("jarinite_placed",  () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                DeepAetherModConfiguredFeatures.JARINITE, DeepAetherModOrePlacement.commonOrePlacement(1, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-50), VerticalAnchor.aboveBottom(200)))));
        public static final RegistryObject<PlacedFeature> GREOTITE_PLACED = PLACED_FEATURES.register("greotite_placed",  () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                DeepAetherModConfiguredFeatures.GREOTITE, DeepAetherModOrePlacement.commonOrePlacement(1, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-50), VerticalAnchor.aboveBottom(200)))));
        public static final RegistryObject<PlacedFeature> SKYJADE_ORE_PLACED = PLACED_FEATURES.register("skyjade_ore_placed",  () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                DeepAetherModConfiguredFeatures.SKYJADE_ORE, DeepAetherModOrePlacement.commonOrePlacement(7, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-50), VerticalAnchor.aboveBottom(200)))));
        public static final RegistryObject<PlacedFeature> SKYJADE_ORE_SMALL_PLACED = PLACED_FEATURES.register("skyjade_ore_small_placed",  () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                DeepAetherModConfiguredFeatures.SKYJADE_ORE, DeepAetherModOrePlacement.commonOrePlacement(2, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-50), VerticalAnchor.aboveBottom(200)))));
        //GRASS ORES

        public static final RegistryObject<PlacedFeature> AETHER_MOSS_PLACED = PLACED_FEATURES.register("aether_moss_placed",  () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                DeepAetherModConfiguredFeatures.AETHER_MOSS, DeepAetherModOrePlacement.commonOrePlacement(2, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-50), VerticalAnchor.aboveBottom(200)))));
        public static final RegistryObject<PlacedFeature> VIRULENT_QUICKSAND_PLACED = PLACED_FEATURES.register("virulent_quicksand_placed",  () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                DeepAetherModConfiguredFeatures.VIRULENT_QUICKSAND, DeepAetherModOrePlacement.commonOrePlacement(5, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-50), VerticalAnchor.aboveBottom(200)))));
*/



