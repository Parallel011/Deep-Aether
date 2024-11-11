package io.github.razordevs.deep_aether.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.client.model.EOTSSegmentModel;
import io.github.razordevs.deep_aether.client.renderer.DAModelLayers;
import io.github.razordevs.deep_aether.entity.living.boss.eots.EOTSSegment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class EOTSSegmentRenderer extends MobRenderer<EOTSSegment, EOTSSegmentModel> {
	private static final ResourceLocation EOTS_SEGMENT_LOCATION = ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "textures/entity/eots/eots_segment.png");
	private static final ResourceLocation EOTS_SEGMENT_CONTROLLING_LOCATION = ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "textures/entity/eots/eots_segment_controlling.png");

	public EOTSSegmentRenderer(EntityRendererProvider.Context renderer) {
		super(renderer, new EOTSSegmentModel(renderer.bakeLayer(DAModelLayers.EOTS_SEGMENT)), 0F);
	}

	@Override
	public ResourceLocation getTextureLocation(EOTSSegment segment) {
		return segment.isControllingSegment() ? EOTS_SEGMENT_CONTROLLING_LOCATION : EOTS_SEGMENT_LOCATION;
	}

	@Override
	protected float getFlipDegrees(EOTSSegment eotsSegment) {
		return 0F;
	}

	@Override
	public void render(EOTSSegment eots, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
		/*pPoseStack.pushPose();
		if(!eots.isControllingSegment()) {
			int sub = 0;
			sub = subTreeSearch(eots, sub) / 10;
			pPoseStack.scale(1.2f - sub, 1.2f - sub, 1.2f - sub);
		}else {
			pPoseStack.scale(1.2f, 1.2f, 1.2f);
		}
		pPoseStack.popPose();*/

		if(eots.isDeadOrDying()) {
			pPoseStack.scale(eots.getScale() - (float) eots.deathTime/20 , eots.getScale() - (float) eots.deathTime/20, eots.getScale() - (float) eots.deathTime/20);
		}

		pPoseStack.scale(1.2f, 1.2f, 1.2f);
		super.render(eots, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
	}

	@Override
	protected void scale(EOTSSegment eotsSegment, PoseStack poseStack, float scale) {
		super.scale(eotsSegment, poseStack, scale);
	}

	@Override
	protected float getBob(EOTSSegment pLivingBase, float pPartialTick) {
		return pPartialTick;
	}

	/*
	private static int subTreeSearch(EOTSSegment eots, int sub){
		if(!eots.isControllingSegment() && eots.getParent() != null){
			sub++;
			sub = subTreeSearch((EOTSSegment) eots.getServer().getLevel(eots.level().dimension()).getEntity(UUID.fromString(eots.getEntityData().get(eots.PARENT_DATA))), sub);
		}
		return sub;
	}
*/

	@Override
	protected void setupRotations(EOTSSegment pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks, float scale) {
		super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks, scale);
		pPoseStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(pAgeInTicks, pEntityLiving.xRotO, pEntityLiving.getXRot())));
	}
}
