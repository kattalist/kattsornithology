package com.kattalist.kattsornithology.world.inventory;

import com.kattalist.kattsornithology.core.init.BlockInit;
import com.kattalist.kattsornithology.core.init.ItemInit;
import com.kattalist.kattsornithology.core.init.MenuTypeInit;
import com.kattalist.kattsornithology.nbt.helper.ListTagHelper;
import com.kattalist.kattsornithology.world.level.item.ModFeatherItem;
import com.kattalist.kattsornithology.world.level.item.WingsuitItem;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.LoomMenu;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AeronautTableMenu extends AbstractContainerMenu {
	private final ContainerLevelAccess access;

	private static final int WINGSUIT_SLOT = 0;
	private static final int FEATHER_SLOT_START = 1;
	private static final int FEATHER_SLOT_END = 9;
	private static final int INV_SLOT_START = 9;
	private static final int INV_SLOT_END = 36;
	private static final int USE_ROW_SLOT_START = 36;
	private static final int USE_ROW_SLOT_END = 45;

	public Slot wingSlot;

	Runnable slotUpdateListener = () -> {
	};
	private final Container featherSlots = new SimpleContainer(8) {
		public void setChanged() {
			super.setChanged();
			AeronautTableMenu.this.slotsChanged(this);
			AeronautTableMenu.this.slotUpdateListener.run();
		}
	};
	private final Container wingsuitSlot = new SimpleContainer(1) {
		public void setChanged() {
			super.setChanged();
			AeronautTableMenu.this.slotsChanged(this);
			AeronautTableMenu.this.slotUpdateListener.run();
		}
	};

	public AeronautTableMenu(int id, Inventory playerInventory) {
		this(id, playerInventory, ContainerLevelAccess.NULL);
	}

	public AeronautTableMenu(int id, Inventory playerInventory, ContainerLevelAccess access) {
		super(MenuTypeInit.AERONAUTS_TABLE.get(), id);
		this.access = access;

		// Add wingsuit slot
		wingSlot = new Slot(this.wingsuitSlot, WINGSUIT_SLOT, 45, 62) {
			public boolean mayPlace(ItemStack stack) {
				return stack.is(ItemInit.WINGSUIT.get());
			}

			public int getMaxStackSize() {
				return 1;
			}
		};
		this.addSlot(wingSlot);

		// Add feather slots
		this.addFeatherSlot(1, 36, 26);
		this.addFeatherSlot(2, 54, 26);
		this.addFeatherSlot(3, 80, 53);
		this.addFeatherSlot(4, 80, 71);
		this.addFeatherSlot(5, 54, 98);
		this.addFeatherSlot(6, 36, 98);
		this.addFeatherSlot(7, 9, 71);
		// this.addFeatherSlot(8, 9, 53);

		// Now add player inventory - since the GUI is the same size as the beacon's,
		// copy the vanilla code for BeaconMenu here

		for (int k = 0; k < 3; ++k) {
			for (int l = 0; l < 9; ++l) {
				this.addSlot(new Slot(playerInventory, l + k * 9 + 9, 36 + l * 18, 137 + k * 18));
			}
		}

		for (int i1 = 0; i1 < 9; ++i1) {
			this.addSlot(new Slot(playerInventory, i1, 36 + i1 * 18, 195));
		}

	}

	/*
	 * CHANGE THIS!!! For testing, only songbird feathers may be placed in the table
	 * and feathers can only be placed if a wingsuit is in the table
	 */
	public void addFeatherSlot(int id, int startX, int startY) {
		this.addSlot(new Slot(this.featherSlots, id, startX, startY) {
			public boolean mayPlace(ItemStack stack) {
				return (stack.is(ItemInit.SONGBIRD_FEATHER.get()) && wingSlot.hasItem());
			}

			public int getMaxStackSize() {
				return 1;
			}
		});
	}

	public void slotsChanged(Container cont) {
		if (cont == this.wingsuitSlot) {
			// If the wingsuit has just been added, load the feathers that are already on
			// the wingsuit into the slots
			if (!this.wingsuitSlot.isEmpty()) {
				ItemStack stack = this.wingSlot.getItem();
				if (stack.getItem() instanceof WingsuitItem) {
					WingsuitItem wingsuit = (WingsuitItem) this.wingSlot.getItem().getItem();
					for (int i = 0; i < wingsuit.feathers.size(); i++) {
						try {
							featherSlots.setItem(i, wingsuit.feathers.get(i));
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("Out of bounds at " + i);
						}
					}
				}
			} else {
				// If the wingsuit has just been removed, remove all the feathers from the slots
				featherSlots.clearContent();
			}
		} else if (cont == this.featherSlots) {
			ItemStack stack = this.wingSlot.getItem();
			for (int i = 0; i < featherSlots.getContainerSize(); i++) {
				if (featherSlots.getItem(i).getItem() == ItemInit.SONGBIRD_FEATHER.get() && !stack.isEmpty()
						&& ((WingsuitItem) stack.getItem()).feathers.size() < 8) {
					WingsuitItem.addFeather((WingsuitItem) stack.getItem(), featherSlots.getItem(i));
				}
			}
		}
	}

	public void loadFeathersFromWingsuit(CompoundTag[] tags) {
		for (CompoundTag tag : tags) {
			featherSlots.setItem(tag.getInt("index"), WingsuitItem.getItemFromName(tag.getString("type")));
		}
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(access, player, BlockInit.AERONAUTS_TABLE.get());
	}

}
