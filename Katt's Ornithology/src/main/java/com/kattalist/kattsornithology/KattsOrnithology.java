package com.kattalist.kattsornithology;

import com.kattalist.kattsornithology.core.init.BlockInit;
import com.kattalist.kattsornithology.core.init.EntityInit;
import com.kattalist.kattsornithology.core.init.ItemInit;
import com.kattalist.kattsornithology.core.init.MenuTypeInit;
import com.kattalist.kattsornithology.core.init.SoundInit;
import com.kattalist.kattsornithology.world.gen.KOEntitySpawns;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = KattsOrnithology.MOD_ID)
public class KattsOrnithology {
	public static final String MOD_ID = "kattsornithology";
	
	public KattsOrnithology() {
		var bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		BlockInit.BLOCKS.register(bus);
		ItemInit.ITEMS.register(bus);
		EntityInit.ENTITIES.register(bus);
		SoundInit.SOUNDS.register(bus);
		MenuTypeInit.MENU_TYPES.register(bus);
		
		MinecraftForge.EVENT_BUS.addListener(this::onBiomeLoadFromJSON);
	}
	
	@SubscribeEvent
    public void onBiomeLoadFromJSON(BiomeLoadingEvent event) {
        KOEntitySpawns.onBiomesLoad(event);
    }
}
