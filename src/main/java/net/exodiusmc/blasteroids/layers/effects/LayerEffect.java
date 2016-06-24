package net.exodiusmc.blasteroids.layers.effects;

import javafx.scene.canvas.GraphicsContext;

/**
 * LayerEffects can be applied to layers to alter their behavoir. You can use this
 * to create seamless transitions between layer changes
 * 
 * @author julian
 * @see net.exodiusmc.blasteroids.enums.LayerEffectType
 */
public abstract class LayerEffect {
	private long ticks = 0;
	private boolean completed = false;
	private Runnable callback;
	
	public abstract void applyBefore(GraphicsContext gfx, long ticks);
	public abstract void applyAfter(GraphicsContext gfx, long ticks);
	
	public void complete() {
		this.completed = true;
	}
	
	public boolean isCompleted() {
		return this.completed;
	}
	
	public long tick() {
		return ++this.ticks;
	}
	
	public void setOnCompleted(Runnable callback) {
		this.callback = callback;
	}
	
	public Runnable getCallback() {
		return this.callback;
	}
}
