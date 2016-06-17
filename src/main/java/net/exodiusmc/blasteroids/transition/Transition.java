package net.exodiusmc.blasteroids.transition;

import javafx.scene.canvas.GraphicsContext;

public abstract class Transition {
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
