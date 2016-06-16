package net.exodiusmc.blasteroids.transition;

import javafx.scene.canvas.GraphicsContext;

public abstract class Transition {
	private long ticks = 0;
	
	public abstract void apply(GraphicsContext gfx, long ticks);
	
	public abstract void completed();
}
