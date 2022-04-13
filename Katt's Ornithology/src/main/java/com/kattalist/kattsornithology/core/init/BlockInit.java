package com.kattalist.kattsornithology.core.init;

import com.kattalist.kattsornithology.KattsOrnithology;
import com.kattalist.kattsornithology.world.level.block.AeronautTableBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = KattsOrnithology.MOD_ID, bus = Bus.MOD)
public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			KattsOrnithology.MOD_ID);

	public static final RegistryObject<Block> AERONAUTS_TABLE = BLOCKS.register("aeronauts_table",
			() -> new AeronautTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
}
