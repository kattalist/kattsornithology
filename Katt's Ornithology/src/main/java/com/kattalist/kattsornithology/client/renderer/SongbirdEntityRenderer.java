package com.kattalist.kattsornithology.client.renderer;

import java.util.Random;

import com.kattalist.kattsornithology.KattsOrnithology;
import com.kattalist.kattsornithology.client.renderer.model.SongbirdEntityModel;
import com.kattalist.kattsornithology.common.entity.SongbirdEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SongbirdEntityRenderer<T extends SongbirdEntity> extends MobRenderer<T, SongbirdEntityModel<T>> {

	private static final ResourceLocation BLUEBIRD_TEXTURE = new ResourceLocation(KattsOrnithology.MOD_ID,
			"textures/entities/bluebird.png");
	private static final ResourceLocation CHICKADEE_TEXTURE = new ResourceLocation(KattsOrnithology.MOD_ID,
			"textures/entities/chickadee.png");
	private static final ResourceLocation GOLDFINCH_TEXTURE = new ResourceLocation(KattsOrnithology.MOD_ID,
			"textures/entities/goldfinch.png");
	private static final ResourceLocation ORIOLE_TEXTURE = new ResourceLocation(KattsOrnithology.MOD_ID,
			"textures/entities/oriole.png");

	public SongbirdEntityRenderer(Context context) {
		super(context, new SongbirdEntityModel<>(context.bakeLayer(SongbirdEntityModel.LAYER_LOCATION)), 0.35f);
	}

	@Override
	public ResourceLocation getTextureLocation(SongbirdEntity entity) {
		switch (entity.getBirdType()) {
		case 0:
			return BLUEBIRD_TEXTURE;
		case 1:
			return CHICKADEE_TEXTURE;
		case 2:
			return GOLDFINCH_TEXTURE;
		default:
			return ORIOLE_TEXTURE;
		}
	}

}
