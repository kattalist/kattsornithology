package com.kattalist.kattsornithology.world.level.item;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.kattalist.kattsornithology.core.init.ItemInit;
import com.kattalist.kattsornithology.core.init.MaterialInit;
import com.kattalist.kattsornithology.nbt.helper.ListTagHelper;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Wearable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public class WingsuitItem extends ArmorItem implements Wearable {

	public static final String TAG_FEATHERS = "AppliedFeathers";
	
	public NonNullList<ItemStack> feathers = NonNullList.create();

	// ATTRIBUTES
	public double speedModifier = 1;
	public double glideModifier = 1;
	public double enduranceModifier = 1;
	public double shieldModifier = 1;

	public WingsuitItem(Item.Properties properties) {
		super(MaterialInit.WINGSUIT_MATERIAL, EquipmentSlot.CHEST, properties);
		DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
	}

	public static boolean isFlyEnabled(ItemStack p_41141_) {
		return p_41141_.getDamageValue() < p_41141_.getMaxDamage() - 1;
	}

	public boolean isValidRepairItem(ItemStack p_41134_, ItemStack p_41135_) {
		return p_41135_.is(Items.PHANTOM_MEMBRANE);
	}

	public InteractionResultHolder<ItemStack> use(Level p_41137_, Player p_41138_, InteractionHand p_41139_) {
		System.out.println("Using");
		ItemStack itemstack = p_41138_.getItemInHand(p_41139_);
		EquipmentSlot equipmentslot = Mob.getEquipmentSlotForItem(itemstack);
		ItemStack itemstack1 = p_41138_.getItemBySlot(equipmentslot);
		if (itemstack1.isEmpty()) {
			p_41138_.setItemSlot(equipmentslot, itemstack.copy());
			if (!p_41137_.isClientSide()) {
				p_41138_.awardStat(Stats.ITEM_USED.get(this));
			}

			itemstack.setCount(0);
			return InteractionResultHolder.sidedSuccess(itemstack, p_41137_.isClientSide());
		} else {
			return InteractionResultHolder.fail(itemstack);
		}
	}

	public static int getAppliedFeathers(WingsuitItem suit) {
		return suit.feathers.size();
	}

	public void updateModifiers(ItemStack stack) {
	}
	
	public static void addFeather(WingsuitItem suit, ItemStack feather) {
		suit.feathers.add(feather);
	}

	/*public static void addFeathers(ItemStack stack, int index, ItemStack... newStacks) {
		ListTag listtag = getAppliedFeathers(stack);
		for (ItemStack s : newStacks) {
			if (convertItemToName(s) == null) {
			} else {
				int featherCount = listtag.size();
				if (featherCount < 8) {
					CompoundTag newFeatherTag = new CompoundTag();
					newFeatherTag.putInt("index", index);
					newFeatherTag.putString("type", convertItemToName(s));
					listtag.add(newFeatherTag);
				}
			}
		}

		stack.getOrCreateTag().put(TAG_FEATHERS, listtag);
	}*/

	@Override
	public boolean canElytraFly(ItemStack stack, net.minecraft.world.entity.LivingEntity entity) {
		return WingsuitItem.isFlyEnabled(stack);
	}

	@Override
	public boolean elytraFlightTick(ItemStack stack, net.minecraft.world.entity.LivingEntity entity, int flightTicks) {
		if (!entity.level.isClientSide && (flightTicks + 1) % (int) (20 * enduranceModifier) == 0) {
			stack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(net.minecraft.world.entity.EquipmentSlot.CHEST));
		}
		return true;
	}

	@Nullable
	public SoundEvent getEquipSound() {
		return SoundEvents.ARMOR_EQUIP_ELYTRA;
	}

	// Reformat this once I know the code works
	public static String convertItemToName(ItemStack stack) {
		Item item = stack.getItem();
		if (item.equals(ItemInit.SONGBIRD_FEATHER.get())) {
			return "songbird_feather";
		} else if (item.equals(ItemInit.HUMMINGBIRD_FEATHER.get())) {
			return "hummingbird_feather";
		} else if (item.equals(ItemInit.TOUCAN_FEATHER.get())) {
			return "toucan_feather";
		} else {
			return null;
		}
	}

	public static ItemStack getItemFromName(String name) {
		switch (name) {
		case "songbird_feather":
			return ItemInit.SONGBIRD_FEATHER.get().getDefaultInstance();
		case "hummingbird_feather":
			return ItemInit.HUMMINGBIRD_FEATHER.get().getDefaultInstance();
		case "toucan_feather":
			return ItemInit.TOUCAN_FEATHER.get().getDefaultInstance();
		default:
			return null;
		}
	}
}
