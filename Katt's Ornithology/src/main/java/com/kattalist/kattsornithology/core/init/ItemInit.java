package com.kattalist.kattsornithology.core.init;

import com.kattalist.kattsornithology.KattsOrnithology;
import com.kattalist.kattsornithology.world.level.item.HummingbirdFeatherItem;
import com.kattalist.kattsornithology.world.level.item.SongbirdFeatherItem;
import com.kattalist.kattsornithology.world.level.item.ToucanFeatherItem;
import com.kattalist.kattsornithology.world.level.item.WingsuitItem;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = KattsOrnithology.MOD_ID, bus = Bus.MOD)
public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			KattsOrnithology.MOD_ID);

	public static final RegistryObject<BlockItem> AERONAUTS_TABLE = ITEMS.register("aeronauts_table",
			() -> new BlockItem(BlockInit.AERONAUTS_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

	public static final RegistryObject<Item> WINGSUIT = ITEMS.register("wingsuit",
			() -> new WingsuitItem(new Item.Properties().durability(432).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> SONGBIRD_FEATHER = ITEMS.register("songbird_feather",
			() -> new SongbirdFeatherItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> TOUCAN_FEATHER = ITEMS.register("toucan_feather",
			() -> new ToucanFeatherItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> HUMMINGBIRD_FEATHER = ITEMS.register("hummingbird_feather",
			() -> new HummingbirdFeatherItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}
