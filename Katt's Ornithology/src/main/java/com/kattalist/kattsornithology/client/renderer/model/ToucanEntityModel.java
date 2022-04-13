package com.kattalist.kattsornithology.client.renderer.model;

import com.kattalist.kattsornithology.common.entity.SongbirdEntity;
import com.kattalist.kattsornithology.common.entity.ToucanEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ToucanEntityModel<T extends ToucanEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "toucan"), "main");
	private final ModelPart leftleg;
	private final ModelPart rightleg;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart torso;
	private final ModelPart head;

	public ToucanEntityModel(ModelPart root) {
		this.leftleg = root.getChild("leftleg");
		this.rightleg = root.getChild("rightleg");
		this.leftwing = root.getChild("leftwing");
		this.rightwing = root.getChild("rightwing");
		this.torso = root.getChild("torso");
		this.head = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg",
				CubeListBuilder.create().texOffs(0, 2)
						.addBox(-0.75F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-0.75F, 2.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.0F, 22.0F, 1.0F));

		PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg",
				CubeListBuilder.create().texOffs(2, 2)
						.addBox(-0.25F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 1)
						.addBox(-0.25F, 2.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.0F, 22.0F, 1.0F));

		PartDefinition leftwing = partdefinition.addOrReplaceChild("leftwing", CubeListBuilder.create(),
				PartPose.offset(2.0F, 19.0F, 1.0F));

		PartDefinition left_wing_r1 = leftwing.addOrReplaceChild(
				"left_wing_r1", CubeListBuilder.create().texOffs(11, 12).addBox(0.5F, -0.642F, -2.8372F, 1.0F, 3.0F,
						3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition rightwing = partdefinition.addOrReplaceChild("rightwing", CubeListBuilder.create(),
				PartPose.offset(-2.0F, 19.0F, 1.0F));

		PartDefinition right_wing_r1 = rightwing.addOrReplaceChild(
				"right_wing_r1", CubeListBuilder.create().texOffs(10, 0).addBox(-1.5F, -0.642F, -2.8372F, 1.0F, 3.0F,
						3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition torso = partdefinition.addOrReplaceChild("torso", CubeListBuilder.create(),
				PartPose.offset(0.0F, 20.0F, 0.0F));

		PartDefinition body_r1 = torso
				.addOrReplaceChild("body_r1",
						CubeListBuilder.create().texOffs(0, 9).addBox(-1.5F, -0.8258F, -2.7037F, 3.0F, 2.0F, 4.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 1.0F, 1.0F, -1.1345F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(
				-1.0F, -3.0F, -6.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(ToucanEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		if (entity.isFlying()) {
			float secs = ageInTicks / 20;
			this.leftwing.zRot = -(float) (Mth.sin((float) (8 * Math.PI * secs)) + 1F);
			this.rightwing.zRot = (float) (Mth.sin((float) (8 * Math.PI * secs)) + 1F);
			this.leftleg.xRot = 1F;
			this.rightleg.xRot = 1F;
		} else {
			this.leftwing.zRot = 0F;
			this.rightwing.zRot = 0F;
			this.rightleg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.leftleg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		leftleg.render(poseStack, buffer, packedLight, packedOverlay);
		rightleg.render(poseStack, buffer, packedLight, packedOverlay);
		leftwing.render(poseStack, buffer, packedLight, packedOverlay);
		rightwing.render(poseStack, buffer, packedLight, packedOverlay);
		torso.render(poseStack, buffer, packedLight, packedOverlay);
		head.render(poseStack, buffer, packedLight, packedOverlay);
	}
}
