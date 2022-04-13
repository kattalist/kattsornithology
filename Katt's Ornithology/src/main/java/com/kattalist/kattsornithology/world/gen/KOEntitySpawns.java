package com.kattalist.kattsornithology.world.gen;

import com.kattalist.kattsornithology.KattsOrnithology;
import com.kattalist.kattsornithology.common.entity.SongbirdEntity;
import com.kattalist.kattsornithology.core.init.EntityInit;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = KattsOrnithology.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KOEntitySpawns {

	public static void onBiomesLoad(BiomeLoadingEvent evt) {
		Biome biome = ForgeRegistries.BIOMES.getValue(evt.getName());

		if (biome.getBiomeCategory() == BiomeCategory.FOREST) {
			evt.getSpawns().getSpawner(MobCategory.CREATURE)
					.add(new MobSpawnSettings.SpawnerData(EntityInit.SONGBIRD.get(), 10, 4, 6));

		}
		if (biome.getRegistryName().equals(Biomes.FLOWER_FOREST.location())) {
			evt.getSpawns().getSpawner(MobCategory.CREATURE)
					.add(new MobSpawnSettings.SpawnerData(EntityInit.HUMMINGBIRD.get(), 20, 4, 6));
		}
		if (biome.getBiomeCategory() == BiomeCategory.JUNGLE) {
			evt.getSpawns().getSpawner(MobCategory.CREATURE)
					.add(new MobSpawnSettings.SpawnerData(EntityInit.TOUCAN.get(), 5, 2, 6));
		}
	}
}
