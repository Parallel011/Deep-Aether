package io.github.razordevs.deep_aether.client.renderer.entity;

import com.aetherteam.aether.client.renderer.entity.IceCrystalRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.entity.living.projectile.WindCrystal;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class WindCrystalRenderer extends IceCrystalRenderer<WindCrystal> {
    private static final ResourceLocation WIND_CRYSTAL_TEXTURE = ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "textures/entity/projectile/wind_ball.png");
    public WindCrystalRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(WindCrystal crystal) {
        return WIND_CRYSTAL_TEXTURE;
    }

    @Override
    public void render(WindCrystal crystal, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(crystal.isFriendly())
            poseStack.scale(0.4F, 0.4F, 0.4F);
        super.render(crystal, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
