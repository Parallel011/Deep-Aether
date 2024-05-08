package teamrazor.deepaether.datagen.tags;


import com.aetherteam.aether.AetherTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.world.biomes.DABiomes;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DABiomeTagData extends BiomeTagsProvider {
    public DABiomeTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, DeepAether.MODID, helper);
    }
    @Override
    @SuppressWarnings("unchecked")
    public void addTags(HolderLookup.Provider provider) {
        this.tag(AetherTags.Biomes.IS_AETHER).add(
                DABiomes.AERLAVENDER_FIELDS,
                DABiomes.AERGLOW_FOREST,
                DABiomes.MYSTIC_AERGLOW_FOREST,
                DABiomes.BLUE_AERGLOW_FOREST,
                DABiomes.YAGROOT_SWAMP,
                DABiomes.GOLDEN_HEIGHTS,
                DABiomes.GOLDEN_GROVE,
                DABiomes.SACRED_LANDS
        );

        this.tag(DATags.Biomes.IS_NOT_SWAMP).add(
                DABiomes.AERLAVENDER_FIELDS,
                DABiomes.AERGLOW_FOREST,
                DABiomes.MYSTIC_AERGLOW_FOREST,
                DABiomes.BLUE_AERGLOW_FOREST,
                DABiomes.GOLDEN_HEIGHTS,
                DABiomes.GOLDEN_GROVE,
                DABiomes.SACRED_LANDS
        );
        this.tag(DATags.Biomes.CAN_QUAIL_SPAWN).add(
                DABiomes.AERLAVENDER_FIELDS,
                DABiomes.AERGLOW_FOREST,
                DABiomes.MYSTIC_AERGLOW_FOREST,
                DABiomes.BLUE_AERGLOW_FOREST,
                DABiomes.GOLDEN_HEIGHTS,
                DABiomes.GOLDEN_GROVE
        );
    }
}