package net.exodiusmc.blasteroids.transition;

import javafx.scene.canvas.GraphicsContext;

public class Hide extends Transition {

	@Override
	public void applyBefore(GraphicsContext gfx, long ticks) {
		gfx.setGlobalAlpha(0);
	}

	@Override
	public void applyAfter(GraphicsContext gfx, long ticks) {
		gfx.setGlobalAlpha(1);
	}

}
