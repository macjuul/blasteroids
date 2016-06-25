package net.exodiusmc.blasteroids.layers.effects;

import javafx.scene.canvas.GraphicsContext;
import net.exodiusmc.blasteroids.Layer;

public class FadeIn extends LayerEffect {
	private double fade = 0;
	private double fadeAmount = 0.01;

	@Override
	public void applyBefore(Layer l, GraphicsContext gfx, long ticks) {
		// Apply our fade effect
		gfx.setGlobalAlpha(fade);
		
		this.fade += this.fadeAmount;
	}
	
	@Override
	public void applyAfter(Layer l, GraphicsContext gfx, long ticks) {
		gfx.setGlobalAlpha(1);	// Render everything after the Layer normally
		
		if(this.fade >= 1) {
			complete();
		}
	}
	
	public void setFadeAmount(double a) {
		this.fadeAmount = a;
	}

}
