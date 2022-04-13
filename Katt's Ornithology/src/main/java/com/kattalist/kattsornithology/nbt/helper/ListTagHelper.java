package com.kattalist.kattsornithology.nbt.helper;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

public class ListTagHelper {
	
	public ListTagHelper() {
	}

	public static CompoundTag[] splitListTag(ListTag list) {
		CompoundTag[] compounds = new CompoundTag[list.size()];
		for (int i = 0; i < list.size(); ++i) {
			compounds[i] = list.getCompound(i);
		}
		return compounds;
	}
}
