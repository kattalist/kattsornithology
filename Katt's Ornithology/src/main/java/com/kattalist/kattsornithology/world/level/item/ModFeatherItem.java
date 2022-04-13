package com.kattalist.kattsornithology.world.level.item;

import net.minecraft.world.item.Item;

public class ModFeatherItem extends Item {

	private final float SPEED_MODIFIER;
	private final float GLIDE_MODIFIER;
	private final float ENDURANCE_MODIFIER;
	private final float SHIELD_MODIFIER;

	public ModFeatherItem(Properties properties, float speed, float glide, float endurance, float shield) {
		super(properties);
		this.SPEED_MODIFIER = speed;
		this.GLIDE_MODIFIER = glide;
		this.ENDURANCE_MODIFIER = endurance;
		this.SHIELD_MODIFIER = shield;
	}

	public double[] applyModifiers(double[] mods) {
		// mods[0] is speed, mods[1] is glide, mods[2] is endurance, mods[3] is shield
		return new double[] { mods[0] * this.SPEED_MODIFIER, mods[1] * GLIDE_MODIFIER, mods[2] * ENDURANCE_MODIFIER,
				mods[3] * SHIELD_MODIFIER };
	}
}
