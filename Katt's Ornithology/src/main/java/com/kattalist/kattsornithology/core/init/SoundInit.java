package com.kattalist.kattsornithology.core.init;

import com.kattalist.kattsornithology.KattsOrnithology;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
			KattsOrnithology.MOD_ID);

	public static final RegistryObject<SoundEvent> SONGBIRD_BLUEBIRD_AMBIENT = SOUNDS.register(
			"entity.songbird.bluebird.ambient",
			() -> new SoundEvent(new ResourceLocation(KattsOrnithology.MOD_ID, "entity.songbird.bluebird.ambient")));
	public static final RegistryObject<SoundEvent> SONGBIRD_CHICKADEE_AMBIENT = SOUNDS.register(
			"entity.songbird.chickadee.ambient",
			() -> new SoundEvent(new ResourceLocation(KattsOrnithology.MOD_ID, "entity.songbird.chickadee.ambient")));
	public static final RegistryObject<SoundEvent> SONGBIRD_GOLDFINCH_AMBIENT = SOUNDS.register(
			"entity.songbird.goldfinch.ambient",
			() -> new SoundEvent(new ResourceLocation(KattsOrnithology.MOD_ID, "entity.songbird.goldfinch.ambient")));
	public static final RegistryObject<SoundEvent> SONGBIRD_ORIOLE_AMBIENT = SOUNDS.register(
			"entity.songbird.oriole.ambient",
			() -> new SoundEvent(new ResourceLocation(KattsOrnithology.MOD_ID, "entity.songbird.oriole.ambient")));
	
	
	/*public static final RegistryObject<SoundEvent> SONGBIRD_HURT = SOUNDS.register(
			"entity.songbird.hurt",
			() -> new SoundEvent(new ResourceLocation(KattsOrnithology.MOD_ID, "entity.songbird.hurt")));
	
	public static final RegistryObject<SoundEvent> SONGBIRD_DEATH = SOUNDS.register(
			"entity.songbird.death",
			() -> new SoundEvent(new ResourceLocation(KattsOrnithology.MOD_ID, "entity.songbird.death")));
	 */
	private SoundInit() {

	}
}
