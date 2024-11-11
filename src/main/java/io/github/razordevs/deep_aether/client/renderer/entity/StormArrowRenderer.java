package io.github.razordevs.deep_aether.client.renderer.entity;

import io.github.razordevs.deep_aether.entity.StormArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StormArrowRenderer extends ArrowRenderer<StormArrow> {
    public static final ResourceLocation SPECTRAL_ARROW_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/projectiles/arrow.png");

    public StormArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    public ResourceLocation getTextureLocation(StormArrow pEntity) {
        return SPECTRAL_ARROW_LOCATION;
    }
}
