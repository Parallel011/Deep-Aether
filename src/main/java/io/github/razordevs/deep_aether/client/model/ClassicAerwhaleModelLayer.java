package io.github.razordevs.deep_aether.client.model;

import com.aetherteam.aether.entity.passive.Aerwhale;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.entity.AerwhaleSaddleable;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class ClassicAerwhaleModelLayer extends RenderLayer<Aerwhale, EntityModel<Aerwhale>> {
    private static final RenderType SADDLED_AERWHALE_CLASSIC = RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "textures/entity/aerwhale_saddled_classic.png"));

    public ClassicAerwhaleModelLayer(RenderLayerParent<Aerwhale, EntityModel<Aerwhale>> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Aerwhale aerwhale, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (((AerwhaleSaddleable) aerwhale).isSaddled() && !aerwhale.isInvisible()) {
            VertexConsumer consumer = buffer.getBuffer(SADDLED_AERWHALE_CLASSIC);
            this.getParentModel().renderToBuffer(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY);
        }
    }
}