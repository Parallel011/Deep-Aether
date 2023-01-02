package teamrazor.deepaether.datagen.tags;

import com.gildedgames.aether.Aether;
import net.minecraft.core.HolderLookup;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamrazor.deepaether.DeepAetherMod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DeepAetherModBlockTagData extends BlockTagsProvider {

    public DeepAetherModBlockTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, DeepAetherMod.MODID, helper);
    }
    @Nonnull
    @Override
    public String getName() {
        return "Deep Aether Block Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {

    }
}