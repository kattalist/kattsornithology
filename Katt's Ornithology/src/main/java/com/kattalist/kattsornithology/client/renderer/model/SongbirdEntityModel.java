package com.kattalist.kattsornithology.client.renderer.model;

import com.kattalist.kattsornithology.KattsOrnithology;
import com.kattalist.kattsornithology.common.entity.SongbirdEntity;
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
import net.minecraft.world.entity.Entity;

// Made with Blockbench 4.0.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports

public class SongbirdEntityModel<T extends SongbirdEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation(KattsOrnithology.MOD_ID, "songbird"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leftWing;
	private final ModelPart rightWing;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public SongbirdEntityModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = body.getChild("head");
		this.leftWing = body.getChild("left_wing");
		this.rightWing = body.getChild("right_wing");
		this.leftLeg = body.getChild("left_leg");
		this.rightLeg = body.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		body.addOrReplaceChild("torso_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -7.0F, -5.0F, 6.0F,
				4.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 12).addBox(-2.0F,
				-4.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, -2.0F));

		head.addOrReplaceChild("beak_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -8.0F, -8.0F, 2.0F, 1.0F,
				2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 2.0F, -0.1309F, 0.0F, 0.0F));

		body.addOrReplaceChild("left_leg",
				CubeListBuilder.create().texOffs(0, 3)
						.addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 3)
						.addBox(0.0F, 3.0F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.0F, -3.0F, 0.0F));

		body.addOrReplaceChild("right_leg",
				CubeListBuilder.create().texOffs(0, 3)
						.addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 3)
						.addBox(-1.0F, 3.0F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.0F, -3.0F, 0.0F));

		body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(11, 15).addBox(0.0F, 0.0F, -2.0F, 1.0F,
				4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -7.0F, -1.0F));

		body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(11, 15).addBox(-1.0F, 0.0F, -2.0F, 1.0F,
				4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -7.0F, -1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(SongbirdEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		if (entity.isFlying()) {
			float secs = ageInTicks / 20;
			this.leftWing.zRot = -(float) (Mth.sin((float) (8*Math.PI*secs)) + 1F);
			this.rightWing.zRot = (float) (Mth.sin((float) (8*Math.PI*secs)) + 1F);
			this.leftLeg.xRot = 1F;
			this.rightLeg.xRot = 1F;
		} else {
			this.leftWing.zRot = 0F;
			this.rightWing.zRot = 0F;
			this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		this.body.render(poseStack, buffer, packedLight, packedOverlay);
	}
}