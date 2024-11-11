package io.github.razordevs.deep_aether.client.renderer.accessory;

import com.aetherteam.aether.attachment.AetherDataAttachments;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import io.github.razordevs.deep_aether.client.renderer.DAModelLayers;
import io.github.razordevs.deep_aether.item.dungeon.brass.WindShieldItem;
import io.github.razordevs.deep_aether.networking.attachment.DAAttachments;
import io.wispforest.accessories.api.client.AccessoryRenderer;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class WindShieldRenderer implements AccessoryRenderer {

    private final HumanoidModel<LivingEntity> shieldModel;
    public final HumanoidModel<LivingEntity> shieldModelArm;

    public WindShieldRenderer() {
        this.shieldModel = new HumanoidModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(DAModelLayers.WIND_SHIELD));
        this.shieldModelArm = new HumanoidModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(DAModelLayers.WIND_SHIELD_ARM));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <M extends LivingEntity> void render(ItemStack stack, SlotReference reference, PoseStack poseStack, EntityModel<M> entityModel, MultiBufferSource buffer, int packedLight, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity livingEntity = reference.entity();
        if(livingEntity instanceof Player player) {
            if(player.getData(DAAttachments.PLAYER).getWindShieldCooldown() > 0)
                return;
        }

        WindShieldItem shield = (WindShieldItem) stack.getItem();
        ResourceLocation texture = shield.getWindShieldTexture();
        HumanoidModel<LivingEntity> model = this.shieldModel;

        entityModel.copyPropertiesTo((EntityModel<M>) model);

        AccessoryRenderer.followBodyRotations(reference.entity(), model);
        float f = (float)livingEntity.tickCount + partialTicks;
        VertexConsumer consumer = ItemRenderer.getArmorFoilBuffer(buffer, windShieldRenderType(texture, (f * 0.02F) % 1.0F, 0.0F), false);

        model.renderToBuffer(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY);
    }

    @Override
    public boolean shouldRenderInFirstPerson(HumanoidArm arm, ItemStack stack, SlotReference reference) {
        return !(reference.entity() instanceof Player player) || !player.getData(AetherDataAttachments.AETHER_PLAYER).isWearingInvisibilityCloak();
    }

    @Override
    public <M extends LivingEntity> void renderOnFirstPerson(HumanoidArm arm, ItemStack stack, SlotReference reference, PoseStack matrices, EntityModel<M> model, MultiBufferSource multiBufferSource, int light) {
        LivingEntity livingEntity = reference.entity();
        if (livingEntity instanceof AbstractClientPlayer player) {
            this.renderFirstPerson(stack, matrices, multiBufferSource, light, player, arm);
        }
    }

    public void renderFirstPerson(ItemStack stack, PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, HumanoidArm arm) {
        if(player.getData(DAAttachments.PLAYER).getWindShieldCooldown() > 0)
            return;
        boolean isSlim = player.getSkin().model() == PlayerSkin.Model.SLIM;
        this.setupShieldOnHand(stack, this.shieldModelArm, poseStack, buffer, packedLight, player, arm, isSlim);
    }

    private void setupShieldOnHand(ItemStack stack, HumanoidModel<LivingEntity> model, PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, HumanoidArm arm, boolean isSlim) {
        this.setupModel(model, player);
        WindShieldItem shield = (WindShieldItem) stack.getItem();

        VertexConsumer consumer = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.breezeWind(shield.getWindShieldTexture(), (player.tickCount * 0.02F) % 1.0F, 0.0F), false);

        boolean flag = arm != HumanoidArm.LEFT;
        float f = flag ? 1.0F : -1.0F;
        float offset = 0.0375F;
        if (isSlim) {
            offset = 0.0425F;
        }
        poseStack.translate((f * offset) - 0.0025, 0.0025, -0.0025);

        if (arm == HumanoidArm.RIGHT) {
            this.renderShieldOnHand(model.rightArm, poseStack, packedLight, consumer);
        } else if (arm == HumanoidArm.LEFT) {
            this.renderShieldOnHand(model.leftArm, poseStack, packedLight, consumer);
        }
    }

    private void setupModel(HumanoidModel<LivingEntity> model, AbstractClientPlayer player) {
        model.setAllVisible(false);
        model.attackTime = 0.0F;
        model.crouching = false;
        model.swimAmount = 0.0F;
        model.setupAnim(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    }

    private void renderShieldOnHand(ModelPart shieldArm, PoseStack poseStack, int packedLight, VertexConsumer consumer) {
        shieldArm.visible = true;
        shieldArm.xRot = 0.0F;
        shieldArm.render(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY);
    }

    public static RenderType windShieldRenderType(ResourceLocation location, float x, float y) {
        return RenderType.create("deep_aether:wind_shield", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, false, true,
                RenderType.CompositeState.builder()
                        .setShaderState(RenderType.RENDERTYPE_BREEZE_WIND_SHADER)
                        .setTextureState(new RenderStateShard.TextureStateShard(location, false, false))
                        .setTexturingState(new RenderStateShard.OffsetTexturingStateShard(x, y))
                        .setTransparencyState(RenderType.TRANSLUCENT_TRANSPARENCY)
                        .setCullState(RenderType.NO_CULL)
                        .setDepthTestState(RenderType.LEQUAL_DEPTH_TEST)
                        .setLightmapState(RenderType.LIGHTMAP)
                        .setOverlayState(RenderType.OVERLAY)
                        .createCompositeState(false));
    }
}