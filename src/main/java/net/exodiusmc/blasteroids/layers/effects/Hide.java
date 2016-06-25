package net.exodiusmc.blasteroids.layers.effects;

import javafx.scene.canvas.GraphicsContext;
import net.exodiusmc.blasteroids.Layer;

public class Hide extends LayerEffect {

	@Override
	public void applyBefore(Layer l, GraphicsContext gfx, long ticks) {
		gfx.setGlobalAlpha(0);
	}

	@Override
	public void applyAfter(Layer l, GraphicsContext gfx, long ticks) {
		gfx.setGlobalAlpha(1);
	}

}
