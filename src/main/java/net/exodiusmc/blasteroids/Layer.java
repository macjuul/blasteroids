package net.exodiusmc.blasteroids;

import javafx.scene.canvas.GraphicsContext;
import net.exodiusmc.blasteroids.enums.LayerEffectType;
import net.exodiusmc.blasteroids.enums.LogLevel;
import net.exodiusmc.blasteroids.layers.effects.LayerEffect;

/**
 * A Layer is an object that can be added to the layer stack and will be
 * updated and rendered within the game loop. To use this extend this
 * Layer class and rename your class to be something like "ObjectLayer"
 */
public abstract class Layer {
	public LayerEffect transition;
	public boolean shouldBeRemoved = false; 
	private double offset_x = 0;
	private double offset_y = 0;
	
	/**
	 * Make this method return true if you want to update the layer when
	 * it is underneath another layer
	 * 
	 * @return Boolean
	 */
	public abstract boolean updateOnCover();
	
	/**
	 * This is the update() method of your layer. All calculations and
	 * updates to the layer should be made in here. This method will get
	 * called each time the game loop runs, by default at 60 FPS.
	 * <p>
	 * Make sure you don't make any rendering calls from here, since they
	 * should be made from the {@link Layer#render(GraphicsContext)} method
	 * 
	 * @param delta Double
	 * @param frame Long
	 */
	public abstract void update(double delta, long frame);
	
	/**
	 * This is the render() method of your layer. This is where you make
	 * all calls to render graphics to the GraphicsContext.
	 * <p>
	 * Try to keep calculations in this method to a minimum and instead
	 * process them in the {@link Layer#update(double, long)} method.
	 * However, it is fine to use simple if() checks to decide on what
	 * to render and what not.
	 * 
	 * @param gfx GraphicsContext
	 */
	public abstract void render(GraphicsContext gfx);
	
	/**
	 * This method will get called when the layer gets ejected from the
	 * LayerStack. Use this to clean up event listeners and other
	 * persistent code.
	 */
	public abstract void dispose();
	
	/**
	 * Apply a LayerEffect to the layer, altering it's appearance on screen
	 * 
	 * @param t LayerEffectType
	 * @return LayerEffect (Applied LayerEffect Object)
	 */
	public LayerEffect applyEffect(LayerEffectType t) {
		this.transition = ObjectFactory.newLayerEffect(t);
		
		Logger.getLogger().log("Applied " + t.name() + " LayerEffect to " + this.getClass().getSimpleName() + "@" + this.hashCode(), LogLevel.INFO, LogLevel.LAYER);
		
		return this.transition;
	}
	
	/**
	 * Check if this Layer has an effect active. Returns true if it does
	 * 
	 * @return Boolean
	 */
	public boolean hasLayerEffect() {
		return transition != null;
	}
	
	/**
	 * Returns the X-offset variable for the layer; This value is altered by LayerEffects
	 * 
	 * @return Double
	 */
	public double getOffsetX() {
		return this.offset_x;
	}
	
	/**
	 * Returns the Y-offset variable for the layer; This value is altered by LayerEffects
	 * 
	 * @return Double
	 */
	public double getOffsetY() {
		return this.offset_y;
	}
	
	/**
	 * Sets the X-offset variable for the layer
	 * 
	 * @param a Double
	 */
	public void setOffsetX(double a) {
		this.offset_x = a;
	}
	
	/**
	 * Sets the Y-offset variable for the layer
	 * 
	 * @param a Double
	 */
	public void setOffsetY(double a) {
		this.offset_y = a;
	}
}
