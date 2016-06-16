package net.exodiusmc.blasteroids.interfaces;

import javafx.scene.canvas.GraphicsContext;
import net.exodiusmc.blasteroids.transition.Transition;

public abstract class Layer {
	private Transition transition;
	
	public abstract boolean updateOnCover();
	
	public abstract void update(double delta, long frame);
	
	public abstract void render(GraphicsContext gfx);
	
	public abstract void dispose();
}
