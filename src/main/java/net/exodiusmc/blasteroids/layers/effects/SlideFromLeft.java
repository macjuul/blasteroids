package net.exodiusmc.blasteroids.layers.effects;

import javafx.scene.canvas.GraphicsContext;
import net.exodiusmc.blasteroids.Layer;
import net.exodiusmc.blasteroids.Main;

public class SlideFromLeft extends LayerEffect {
	private double slide = -Main.WIDTH;
	private double slideAmount = 8;

	@Override
	public void applyBefore(Layer l, GraphicsContext gfx, long ticks) {
		this.slide += this.slideAmount;
		
		if(this.slide >= 0) {
			this.slide = 0;
		}
		
		l.setOffsetX(this.slide);
	}
	
	@Override
	public void applyAfter(Layer l, GraphicsContext gfx, long ticks) {
		if(this.slide >= 0) {
			l.setOffsetX(0);
			complete();
		}
	}
	
	public void setSlideAmount(double a) {
		this.slideAmount = a;
	}
}
