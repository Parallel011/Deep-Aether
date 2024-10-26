package io.github.razordevs.deep_aether.item.component;

import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DADataComponentTypes {
    public static final DeferredRegister.DataComponents DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(
            ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "data_components")), DeepAether.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<DungeonTracker>> DUNGEON_TRACKER = DATA_COMPONENT_TYPES.registerComponentType(
            "dungeon_tracker",
            builder -> builder
                    .persistent(DungeonTracker.CODEC)
                    .networkSynchronized(DungeonTracker.STREAM_CODEC)
    );
}
