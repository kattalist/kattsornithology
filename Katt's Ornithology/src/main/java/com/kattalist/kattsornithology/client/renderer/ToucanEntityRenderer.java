package com.kattalist.kattsornithology.client.renderer;

import com.kattalist.kattsornithology.KattsOrnithology;
import com.kattalist.kattsornithology.client.renderer.model.ToucanEntityModel;
import com.kattalist.kattsornithology.common.entity.ToucanEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ToucanEntityRenderer<T extends ToucanEntity> extends MobRenderer<T, ToucanEntityModel<T>> {

	private static final ResourceLocation TOUCAN_TEXTURE = new ResourceLocation(KattsOrnithology.MOD_ID,
			"textures/entities/toucan.png");
	
	public ToucanEntityRenderer(Context context) {
		super(context, new ToucanEntityModel<>(context.bakeLayer(ToucanEntityModel.LAYER_LOCATION)), 0.35f);
	}

	@Override
	public ResourceLocation getTextureLocation(T p_114482_) {
		return TOUCAN_TEXTURE;
	}
}
