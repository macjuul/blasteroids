package net.exodiusmc.blasteroids.layers.effects;

import javafx.scene.canvas.GraphicsContext;

public class FadeOut extends LayerEffect {
	private double fade = 1;
	private double fadeAmount = 0.01;

	@Override
	public void applyBefore(GraphicsContext gfx, long ticks) {
		// Apply our fade effect
		gfx.setGlobalAlpha(fade);
		
		this.fade -= this.fadeAmount;
	}
	
	@Override
	public void applyAfter(GraphicsContext gfx, long ticks) {
		gfx.setGlobalAlpha(1);	// Render everything after the Layer normally
		
		if(this.fade <= 0) {
			complete();
		}
	}
	
	public void setFadeAmount(double a) {
		this.fadeAmount = a;
	}

}
