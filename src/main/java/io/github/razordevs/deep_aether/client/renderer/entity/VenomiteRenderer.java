package io.github.razordevs.deep_aether.client.renderer.entity;

import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.client.model.VenomiteModel;
import io.github.razordevs.deep_aether.client.renderer.DAModelLayers;
import io.github.razordevs.deep_aether.entity.living.Venomite;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class VenomiteRenderer extends MobRenderer<Venomite, VenomiteModel> {
    public VenomiteRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VenomiteModel(renderManager.bakeLayer(DAModelLayers.VENOMITE)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Venomite instance) {
        if (instance.isAngry()) return ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "textures/entity/venomite/venomite_angry.png");
        return ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "textures/entity/venomite/venomite.png");
    }
}