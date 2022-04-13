package com.kattalist.kattsornithology.core.init;

import com.kattalist.kattsornithology.KattsOrnithology;
import com.kattalist.kattsornithology.world.inventory.AeronautTableMenu;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = KattsOrnithology.MOD_ID, bus = Bus.MOD)
public class MenuTypeInit {

	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS,
			KattsOrnithology.MOD_ID);

	public static final RegistryObject<MenuType<AeronautTableMenu>> AERONAUTS_TABLE = MENU_TYPES
			.register("aeronauts_table", () -> new MenuType<>(AeronautTableMenu::new));
}
