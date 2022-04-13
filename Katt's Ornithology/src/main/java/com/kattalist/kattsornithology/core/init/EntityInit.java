package com.kattalist.kattsornithology.core.init;

import com.kattalist.kattsornithology.KattsOrnithology;
import com.kattalist.kattsornithology.common.entity.HummingbirdEntity;
import com.kattalist.kattsornithology.common.entity.SongbirdEntity;
import com.kattalist.kattsornithology.common.entity.ToucanEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = KattsOrnithology.MOD_ID, bus = Bus.MOD)
public class EntityInit {

	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES,
			KattsOrnithology.MOD_ID);

	public static final RegistryObject<EntityType<SongbirdEntity>> SONGBIRD = ENTITIES.register("songbird",
			() -> EntityType.Builder.of(SongbirdEntity::new, MobCategory.CREATURE).sized(0.6f, 0.6f).build(new ResourceLocation(KattsOrnithology.MOD_ID, "songbird").toString()));
	public static final RegistryObject<EntityType<HummingbirdEntity>> HUMMINGBIRD = ENTITIES.register("hummingbird",
			() -> EntityType.Builder.of(HummingbirdEntity::new, MobCategory.CREATURE).sized(0.6f, 1.2f).build(new ResourceLocation(KattsOrnithology.MOD_ID, "hummingbird").toString()));
	public static final RegistryObject<EntityType<ToucanEntity>> TOUCAN = ENTITIES.register("toucan",
			() -> EntityType.Builder.of(ToucanEntity::new, MobCategory.CREATURE).sized(0.6f, 1.2f).build(new ResourceLocation(KattsOrnithology.MOD_ID, "toucan").toString()));

	public void registerEntitySpawnPlacements() {
		SpawnPlacements.register(EntityInit.SONGBIRD.get(), SpawnPlacements.Type.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING, SongbirdEntity::checkSongbirdSpawnRules);
		SpawnPlacements.register(EntityInit.HUMMINGBIRD.get(), SpawnPlacements.Type.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING, HummingbirdEntity::checkHummingbirdSpawnRules);
		SpawnPlacements.register(EntityInit.TOUCAN.get(), SpawnPlacements.Type.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING, ToucanEntity::checkToucanSpawnRules);
	}
	
	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent evt) {
		evt.put(EntityInit.SONGBIRD.get(), SongbirdEntity.createAttributes().build());
		evt.put(EntityInit.HUMMINGBIRD.get(), HummingbirdEntity.createAttributes().build());
		evt.put(EntityInit.TOUCAN.get(), ToucanEntity.createAttributes().build());
	}
	
	private EntityInit() {
		registerEntitySpawnPlacements();
	}
}
