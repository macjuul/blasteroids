package net.exodiusmc.blasteroids.layers.effects;

import javafx.scene.canvas.GraphicsContext;
import net.exodiusmc.blasteroids.Layer;
import net.exodiusmc.blasteroids.Main;

public class SlideToRight extends LayerEffect {
	private double slide = 0;
	private double slideAmount = 8;

	@Override
	public void applyBefore(Layer l, GraphicsContext gfx, long ticks) {
		this.slide += this.slideAmount;
		
		if(this.slide >= Main.WIDTH) {
			this.slide = Main.WIDTH;
		}
		
		l.setOffsetX(this.slide);
	}
	
	@Override
	public void applyAfter(Layer l, GraphicsContext gfx, long ticks) {
		if(this.slide >= Main.WIDTH) {
			l.setOffsetX(0);
			complete();
		}
	}
	
	public void setSlideAmount(double a) {
		this.slideAmount = a;
	}
}
