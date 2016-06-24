package net.exodiusmc.blasteroids.layers.effects;

import javafx.scene.canvas.GraphicsContext;
import net.exodiusmc.blasteroids.Main;

public class SlideToRight extends LayerEffect {
	private double slide = 0;
	private double slideAmount = 0.1;

	@Override
	public void applyBefore(GraphicsContext gfx, long ticks) {
		this.slide += this.slideAmount;
	}
	
	@Override
	public void applyAfter(GraphicsContext gfx, long ticks) {
		if(this.slide >= Main.WIDTH) {
			complete();
		}
	}
	
	public void setSlideAmount(double a) {
		this.slideAmount = a;
	}
}
