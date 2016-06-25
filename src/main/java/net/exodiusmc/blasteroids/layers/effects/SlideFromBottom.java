package net.exodiusmc.blasteroids.layers.effects;

import javafx.scene.canvas.GraphicsContext;
import net.exodiusmc.blasteroids.Layer;
import net.exodiusmc.blasteroids.Main;

public class SlideFromBottom extends LayerEffect {
	private double slide = Main.HEIGHT;
	private double slideAmount = 8;

	@Override
	public void applyBefore(Layer l, GraphicsContext gfx, long ticks) {
		this.slide -= this.slideAmount;
		
		if(this.slide <= 0) {
			this.slide = 0;
		}
		
		l.setOffsetY(this.slide);
	}
	
	@Override
	public void applyAfter(Layer l, GraphicsContext gfx, long ticks) {
		if(this.slide <= 0) {
			l.setOffsetY(0);
			complete();
		}
	}
	
	public void setSlideAmount(double a) {
		this.slideAmount = a;
	}
}
