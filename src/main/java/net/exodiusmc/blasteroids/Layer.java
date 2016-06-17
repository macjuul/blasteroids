package net.exodiusmc.blasteroids;

import javafx.scene.canvas.GraphicsContext;
import net.exodiusmc.blasteroids.enums.TransitionType;
import net.exodiusmc.blasteroids.transition.Transition;

public abstract class Layer {
	public Transition transition;
	
	public abstract boolean updateOnCover();
	
	public abstract void update(double delta, long frame);
	
	public abstract void render(GraphicsContext gfx);
	
	public abstract void dispose();
	
	public Transition applyTransition(TransitionType t) {
		this.transition = t.getInstance();
		
		return this.transition;
	}
	
	public boolean hasTransition() {
		return transition != null;
	}
}
