package net.exodiusmc.blasteroids.layers.effects;

import javafx.scene.canvas.GraphicsContext;

public class Hide extends LayerEffect {

	@Override
	public void applyBefore(GraphicsContext gfx, long ticks) {
		gfx.setGlobalAlpha(0);
	}

	@Override
	public void applyAfter(GraphicsContext gfx, long ticks) {
		gfx.setGlobalAlpha(1);
	}

}
