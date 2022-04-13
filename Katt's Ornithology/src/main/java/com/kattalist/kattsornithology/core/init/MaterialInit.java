package com.kattalist.kattsornithology.core.init;

import com.kattalist.kattsornithology.world.level.item.material.BaseArmorMaterial;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class MaterialInit {

	public static final ArmorMaterial WINGSUIT_MATERIAL = new BaseArmorMaterial(35, new int[] { 0, 0, 432, 0 },
			new int[] { 0, 0, 0, 0 }, 0.0f, 0.0f, "wingsuit", SoundEvents.ARMOR_EQUIP_ELYTRA, () -> {
				return Ingredient.of(Items.PHANTOM_MEMBRANE);
			});
}
