package com.kattalist.kattsornithology.client.renderer;

import com.kattalist.kattsornithology.KattsOrnithology;
import com.kattalist.kattsornithology.client.renderer.model.HummingbirdEntityModel;
import com.kattalist.kattsornithology.client.renderer.model.SongbirdEntityModel;
import com.kattalist.kattsornithology.common.entity.HummingbirdEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HummingbirdEntityRenderer<T extends HummingbirdEntity> extends MobRenderer<T, HummingbirdEntityModel<T>> {

	private static final ResourceLocation HUMMINGBIRD_TEXTURE = new ResourceLocation(KattsOrnithology.MOD_ID,
			"textures/entities/hummingbird.png");
	
	public HummingbirdEntityRenderer(Context context) {
		super(context, new HummingbirdEntityModel<>(context.bakeLayer(HummingbirdEntityModel.LAYER_LOCATION)), 0.35f);
	}

	@Override
	public ResourceLocation getTextureLocation(T p_114482_) {
		return HUMMINGBIRD_TEXTURE;
	}

}
