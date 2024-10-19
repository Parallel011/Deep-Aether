package io.github.razordevs.deep_aether.fluids;


import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.joml.Vector3f;

public class DAFluidTypes {
    public static final ResourceLocation POISON_STILL_RL = ResourceLocation.fromNamespaceAndPath("block/water_still");
    public static final ResourceLocation POISON_FLOWING_RL = ResourceLocation.fromNamespaceAndPath("block/water_flow");
    public static final ResourceLocation POISON_OVERLAY_RL = ResourceLocation.fromNamespaceAndPath("block/water_overlay");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, DeepAether.MODID);

    public static final Holder<FluidType> POISON_FLUID_TYPE = register("poison_fluid", FluidType.Properties.create()
            .descriptionId("block.deep_aether.poison")
            .canSwim(false));
    
    private static Holder<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(POISON_STILL_RL, POISON_FLOWING_RL, POISON_OVERLAY_RL,
                0xffAB5AFD, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f), properties));
    }
}