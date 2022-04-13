package com.kattalist.kattsornithology.client.renderer.model;

import com.kattalist.kattsornithology.common.entity.HummingbirdEntity;
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

//Made with Blockbench 4.0.5
//Exported for Minecraft version 1.17 with Mojang mappings
//Paste this class into your mod and generate all required imports


public class HummingbirdEntityModel<T extends HummingbirdEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "hummingbird"), "main");
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart head;
	private final ModelPart leftWing;
	private final ModelPart rightWing;

	public HummingbirdEntityModel(ModelPart root) {
		this.body = root.getChild("body");
		this.tail = body.getChild("tail");
		this.head = body.getChild("head");
		this.leftWing = body.getChild("left_wing");
		this.rightWing = body.getChild("right_wing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

		body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(11, 7).addBox(-2.0F, 0.0803F, 1.6355F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.829F, 0.0F, 0.0F));

		body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 14).addBox(-2.0F, -4.0711F, -3.3507F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(12, 0).addBox(-0.5F, -2.0711F, -6.3507F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

		body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(21, 21).addBox(0.0F, -3.5F, -3.0F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 1.0F));

		body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(16, 14).addBox(-1.0F, -3.5F, -3.0F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(HummingbirdEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		if (entity.isFlying()) {
			float secs = ageInTicks / 20;
			this.leftWing.yRot = -(float) (Mth.sin((float) (12*Math.PI*secs)) + 1F);
			this.rightWing.yRot = (float) (Mth.sin((float) (12*Math.PI*secs)) + 1F);
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, buffer, packedLight, packedOverlay);
	}
}