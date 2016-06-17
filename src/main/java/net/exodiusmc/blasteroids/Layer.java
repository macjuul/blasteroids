package net.exodiusmc.blasteroids;

import javafx.scene.canvas.GraphicsContext;
import net.exodiusmc.blasteroids.enums.LayerEffectType;
import net.exodiusmc.blasteroids.layers.effects.LayerEffect;

public abstract class Layer {
	public LayerEffect transition;
	
	public abstract boolean updateOnCover();
	
	public abstract void update(double delta, long frame);
	
	public abstract void render(GraphicsContext gfx);
	
	public abstract void dispose();
	
	public LayerEffect applyEffect(LayerEffectType t) {
		this.transition = ObjectFactory.newLayerEffect(t);
		
		return this.transition;
	}
	
	public boolean hasTransition() {
		return transition != null;
	}
}
