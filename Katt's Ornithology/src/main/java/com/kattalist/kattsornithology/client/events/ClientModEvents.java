package com.kattalist.kattsornithology.client.events;

import com.kattalist.kattsornithology.KattsOrnithology;
import com.kattalist.kattsornithology.client.renderer.HummingbirdEntityRenderer;
import com.kattalist.kattsornithology.client.renderer.SongbirdEntityRenderer;
import com.kattalist.kattsornithology.client.renderer.ToucanEntityRenderer;
import com.kattalist.kattsornithology.client.renderer.model.HummingbirdEntityModel;
import com.kattalist.kattsornithology.client.renderer.model.SongbirdEntityModel;
import com.kattalist.kattsornithology.client.renderer.model.ToucanEntityModel;
import com.kattalist.kattsornithology.client.screen.AeronautTableScreen;
import com.kattalist.kattsornithology.core.init.EntityInit;
import com.kattalist.kattsornithology.core.init.MenuTypeInit;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = KattsOrnithology.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

	@SubscribeEvent
	public static void clientSetup(EntityRenderersEvent.RegisterLayerDefinitions evt) {
		evt.registerLayerDefinition(SongbirdEntityModel.LAYER_LOCATION, SongbirdEntityModel::createBodyLayer);
		evt.registerLayerDefinition(HummingbirdEntityModel.LAYER_LOCATION, HummingbirdEntityModel::createBodyLayer);
		evt.registerLayerDefinition(ToucanEntityModel.LAYER_LOCATION, ToucanEntityModel::createBodyLayer);
		
		MenuScreens.register(MenuTypeInit.AERONAUTS_TABLE.get(), AeronautTableScreen::new);
	}
	
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers evt) {
		evt.registerEntityRenderer(EntityInit.SONGBIRD.get(), SongbirdEntityRenderer::new);
		evt.registerEntityRenderer(EntityInit.HUMMINGBIRD.get(), HummingbirdEntityRenderer::new);
		evt.registerEntityRenderer(EntityInit.TOUCAN.get(), ToucanEntityRenderer::new);
	}
}
