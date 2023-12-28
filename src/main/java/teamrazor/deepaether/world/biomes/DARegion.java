package teamrazor.deepaether.world.biomes;

import com.aetherteam.aether.data.resources.registries.AetherBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraftforge.fml.ModList;
import teamrazor.aeroblender.aether.AetherRegionType;
import teamrazor.deepaether.DeepAetherConfig;
import terrablender.api.Region;

import java.util.function.Consumer;


public class DARegion extends Region {

    public DARegion(ResourceLocation name, int weight)
    {
        super(name, AetherRegionType.THE_AETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        ResourceKey<Biome> YagrootSwamp = DeepAetherConfig.COMMON.disable_yagroot_swap_biomes.get() ? AetherBiomes.SKYROOT_WOODLAND : DABiomes.YAGROOT_SWAMP;
        ResourceKey<Biome> AerglowForest = DeepAetherConfig.COMMON.disable_roseroot_forest_biomes.get() ? AetherBiomes.SKYROOT_FOREST : DABiomes.AERGLOW_FOREST;
        ResourceKey<Biome> MysticAerglowForest = DeepAetherConfig.COMMON.disable_roseroot_forest_biomes.get() ? AetherBiomes.SKYROOT_FOREST : DABiomes.MYSTIC_AERGLOW_FOREST;
        ResourceKey<Biome> BlueAerglowForest = DeepAetherConfig.COMMON.disable_roseroot_forest_biomes.get() ? AetherBiomes.SKYROOT_FOREST : DABiomes.BLUE_AERGLOW_FOREST;
        ResourceKey<Biome> GoldenHeights = DeepAetherConfig.COMMON.disable_golden_heights_biomes.get() ? AetherBiomes.SKYROOT_GROVE : DABiomes.GOLDEN_HEIGHTS;
        ResourceKey<Biome> GoldenGrove = DeepAetherConfig.COMMON.disable_golden_heights_biomes.get() ? AetherBiomes.SKYROOT_GROVE : DABiomes.GOLDEN_GROVE;
        ResourceKey<Biome> AerlavenderFields = DeepAetherConfig.COMMON.disable_aerlavenender_field_biomes.get() ? AetherBiomes.SKYROOT_MEADOW : DABiomes.AERLAVENDER_FIELDS;

        Climate.Parameter fullRange = Climate.Parameter.span(-1.0F, 1.0F);

        Climate.Parameter tempDefault1 = Climate.Parameter.span(-1.0F, -0.8F);
        Climate.Parameter tempGold = Climate.Parameter.span(-0.8F, -0.4F);
        Climate.Parameter tempDefault2 = Climate.Parameter.span(-0.4F, 0.0F);
        Climate.Parameter tempDefault3 = Climate.Parameter.span(0.0F, 0.4F);
        Climate.Parameter tempDefault4 = Climate.Parameter.span(0.4F, 0.8F);
        Climate.Parameter tempDefault5 = Climate.Parameter.span(0.8F, 1.0F);

        addBiome(mapper, new Climate.ParameterPoint(tempDefault1, fullRange, fullRange, fullRange, fullRange, fullRange, 0),
                    AetherBiomes.SKYROOT_WOODLAND);
        // Row 2
        addBiome(mapper, new Climate.ParameterPoint(tempDefault2, Climate.Parameter.span(-1.0F, -0.6F), fullRange, fullRange, fullRange, fullRange, 0),
                    BlueAerglowForest);
        addBiome(mapper, new Climate.ParameterPoint(tempDefault2, Climate.Parameter.span(-0.6F, 0.0F), fullRange, fullRange, fullRange, fullRange, 0),
                    AerglowForest);
        addBiome(mapper, new Climate.ParameterPoint(tempDefault2, Climate.Parameter.span(0.0F, 0.7F), fullRange, fullRange, fullRange, fullRange, 0),
                    MysticAerglowForest);
        addBiome(mapper, new Climate.ParameterPoint(tempDefault2, Climate.Parameter.span(0.7F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                    AerlavenderFields);
        // Row 3
        addBiome(mapper, new Climate.ParameterPoint(tempDefault3, Climate.Parameter.span(-1.0F, -0.5F), fullRange, fullRange, fullRange, fullRange, 0),
                    AetherBiomes.SKYROOT_FOREST);
        addBiome(mapper, new Climate.ParameterPoint(tempDefault3, Climate.Parameter.span(-0.5F, 0.5F), fullRange, fullRange, fullRange, fullRange, 0),
                    AerlavenderFields);
        addBiome(mapper, new Climate.ParameterPoint(tempDefault3, Climate.Parameter.span(0.5F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                    AetherBiomes.SKYROOT_GROVE);

        // Row 4
        addBiome(mapper, new Climate.ParameterPoint(tempDefault4, Climate.Parameter.span(-1.0F, -0.3F), fullRange, fullRange, fullRange, fullRange, 0),
                    AerglowForest);
        addBiome(mapper, new Climate.ParameterPoint(tempDefault4, Climate.Parameter.span(-0.3F, 0.2F), fullRange, fullRange, fullRange, fullRange, 0),
                    GoldenGrove);
        addBiome(mapper, new Climate.ParameterPoint(tempDefault4, Climate.Parameter.span(0.2F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                    GoldenHeights);
        // Row 5
        addBiome(mapper, new Climate.ParameterPoint(tempDefault5, Climate.Parameter.span(-1.0F, 0.7F), fullRange, fullRange, fullRange, fullRange, 0),
                    BlueAerglowForest);
        addBiome(mapper, new Climate.ParameterPoint(tempDefault5, Climate.Parameter.span(0.7F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                    AetherBiomes.SKYROOT_FOREST);

        addBiome(mapper, new Climate.ParameterPoint(tempGold, Climate.Parameter.span(-1.0F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                    YagrootSwamp);

        /*

        if (ModList.get().isLoaded("ancient_aether")) {
            fullRange = Climate.Parameter.span(-1.5F, 1.5F);
            Climate.Parameter tempWyndcaps = Climate.Parameter.span(-1.5F, -0.5F);
            Climate.Parameter tempSkyroot1 = Climate.Parameter.span(-0.5F, -0.25F);
            Climate.Parameter tempSkyroot2 = Climate.Parameter.span(-0.25F, 0.25F);
            Climate.Parameter tempSkyroot3 = Climate.Parameter.span(0.25F, 0.5F);
            Climate.Parameter tempSakura = Climate.Parameter.span(0.5F, 1.5F);
            Climate.Parameter erosionDefault = Climate.Parameter.span(-1.5F, 0.5F);
            Climate.Parameter erosionElevated = Climate.Parameter.span(0.5F, 1.5F);

            addBiome(mapper, new Climate.ParameterPoint(tempWyndcaps, fullRange, fullRange, erosionDefault, fullRange, fullRange, 0),
                    DABiomes.BLUE_AERGLOW_FOREST);

            addBiome(mapper, new Climate.ParameterPoint(tempSkyroot1, Climate.Parameter.span(-1.5F, -0.25F), fullRange, erosionDefault, fullRange, fullRange, 0),
                    AetherBiomes.SKYROOT_MEADOW);
            addBiome(mapper, new Climate.ParameterPoint(tempSkyroot1, Climate.Parameter.span(-0.25F, 0.25F), fullRange, erosionDefault, fullRange, fullRange, 0),
                    AetherBiomes.SKYROOT_WOODLAND);
            addBiome(mapper, new Climate.ParameterPoint(tempSkyroot1, Climate.Parameter.span(0.25F, 1.5F), fullRange, erosionDefault, fullRange, fullRange, 0),
                    DABiomes.AERLAVENDER_FIELDS);

            addBiome(mapper, new Climate.ParameterPoint(tempSkyroot2, Climate.Parameter.span(-1.5F, -0.2F), fullRange, erosionDefault, fullRange, fullRange, 0),
                    AetherBiomes.SKYROOT_FOREST);
            addBiome(mapper, new Climate.ParameterPoint(tempSkyroot2, Climate.Parameter.span(-0.2F, 0F), fullRange, erosionDefault, fullRange, fullRange, 0),
                    AetherBiomes.SKYROOT_MEADOW);
            addBiome(mapper, new Climate.ParameterPoint(tempSkyroot2, Climate.Parameter.span(0F, 0.2F), fullRange, erosionDefault, fullRange, fullRange, 0),
                    DABiomes.AERGLOW_FOREST);
            addBiome(mapper, new Climate.ParameterPoint(tempSkyroot2, Climate.Parameter.span(0.2F, 1.5F), fullRange, erosionDefault, fullRange, fullRange, 0),
                    DABiomes.AERLAVENDER_FIELDS);

            addBiome(mapper, new Climate.ParameterPoint(tempSkyroot3, Climate.Parameter.span(-1.5F, -0.1F), fullRange, erosionDefault, fullRange, fullRange, 0),
                    AetherBiomes.SKYROOT_WOODLAND);
            addBiome(mapper, new Climate.ParameterPoint(tempSkyroot3, Climate.Parameter.span(-0.1F, 0.1F), fullRange, erosionDefault, fullRange, fullRange, 0),
                    AetherBiomes.SKYROOT_GROVE);
            addBiome(mapper, new Climate.ParameterPoint(tempSkyroot3, Climate.Parameter.span(0.1F, 0.2F), fullRange, erosionDefault, fullRange, fullRange, 0),
                    DABiomes.AERGLOW_FOREST);
            addBiome(mapper, new Climate.ParameterPoint(tempSkyroot3, Climate.Parameter.span(0.2F, 1.5F), fullRange, erosionDefault, fullRange, fullRange, 0),
                    DABiomes.AERLAVENDER_FIELDS);

            addBiome(mapper, new Climate.ParameterPoint(tempSakura, fullRange, fullRange, erosionDefault, fullRange, fullRange, 0),
                    DABiomes.YAGROOT_SWAMP);

            addBiome(mapper, new Climate.ParameterPoint(fullRange, Climate.Parameter.span(-1.5F, -0.25F), fullRange, erosionElevated, fullRange, fullRange, 0),
                    DABiomes.GOLDEN_GROVE);
            addBiome(mapper, new Climate.ParameterPoint(fullRange, Climate.Parameter.span(-0.25F, 0), fullRange, erosionElevated, fullRange, fullRange, 0),
                    DABiomes.GOLDEN_HEIGHTS);
            addBiome(mapper, new Climate.ParameterPoint(fullRange, Climate.Parameter.span(0, 1.5F), fullRange, erosionElevated, fullRange, fullRange, 0),
                    DABiomes.MYSTIC_AERGLOW_FOREST);
        }
        
         */
    }
}
