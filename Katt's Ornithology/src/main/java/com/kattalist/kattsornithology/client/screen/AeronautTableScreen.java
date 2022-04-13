package com.kattalist.kattsornithology.client.screen;

import com.kattalist.kattsornithology.KattsOrnithology;
import com.kattalist.kattsornithology.world.inventory.AeronautTableMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class AeronautTableScreen extends AbstractContainerScreen<AeronautTableMenu> {

	private static final ResourceLocation SCREEN_TEXTURE = new ResourceLocation(KattsOrnithology.MOD_ID,
			"textures/gui/aeronaut_table_gui.png");
	public static final Component SCREEN_TITLE = new TranslatableComponent(
			"container." + KattsOrnithology.MOD_ID + ".aeronauts_table");

	public AeronautTableScreen(AeronautTableMenu container, Inventory playerInventory, Component title) {
		super(container, playerInventory, SCREEN_TITLE);
		this.leftPos = 0;
		this.topPos = 0;
		this.imageWidth = 230;
		this.imageHeight = 219;
	}

	@Override
	public void render(PoseStack stack, int p_98480_, int p_98481_, float p_98482_) {
		super.render(stack, p_98480_, p_98481_, p_98482_);
		this.renderBackground(stack);
		this.renderBg(stack, p_98482_, p_98480_, p_98481_);
		this.renderTooltip(stack, p_98480_, p_98481_);
	}

	@Override
	protected void renderBg(PoseStack stack, float mouseX, int mouseY, int partialTicks) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, SCREEN_TEXTURE);
		blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
	}

	@Override
	protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
		drawString(stack, this.font, this.title, this.leftPos + 8, this.topPos + 3, 0x404040);
		drawString(stack, this.font, this.playerInventoryTitle, this.leftPos + 8, this.topPos + 100, 0x404040);
	}

	protected void renderTooltip(PoseStack stack, int mouseX, int mouseY) {
		if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
			this.renderTooltip(stack, this.hoveredSlot.getItem(), mouseX, mouseY);
		}

	}

}
