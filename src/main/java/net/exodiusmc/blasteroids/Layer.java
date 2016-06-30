package net.exodiusmc.blasteroids;

import javafx.scene.canvas.GraphicsContext;
import net.exodiusmc.blasteroids.enums.LayerEffectType;
import net.exodiusmc.blasteroids.enums.LogLevel;
import net.exodiusmc.blasteroids.layers.effects.LayerEffect;
import net.exodiusmc.blasteroids.manager.LayerManager;

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
	
	public boolean enabled = true;
	public boolean shouldBeEnabled = true;
	public boolean rendering = true;
	public boolean shouldBeRendering = true;
	
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
	 * should be made from the {@link Layer#render(GraphicsContext, Long)} method
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
	 * @param frame Long
	 */
	public abstract void render(GraphicsContext gfx, long frame);
	
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
		
		Logger.getLogger().log("Applied " + t.name() + " LayerEffect to " + this.toString(), LogLevel.INFO, LogLevel.LAYER);
		
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
	 * Get the current LayerEffect on this Layer.
	 * If there is no LayerEffect active null will
	 * be returned.
	 * 
	 * @return LayerEffect
	 */
	public LayerEffect getActiveLayerEffect() {
		if(hasLayerEffect()) {
			return this.transition;
		}
		
		Logger.getLogger().error("The requested layer currently has no active LayerEffect");
		return null;
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
	
	/**
	 * Checks if the layer is the top most layer in the stack
	 * 
	 * @return Boolean
	 */
	public boolean isOnTop() {
		return LayerManager.getManager().getTopLayer() == this;
	}
	
	/**
	 * Removes the layer from the layer stack.
	 * This is an alias to {@link LayerManager#remove(Layer)}
	 */
	public void eject() {
		LayerManager.getManager().remove(this);
	}
	
	/**
	 * Returns true if this Layer is enabled.
	 * If a layer is disabled it will not be updated and
	 * will not render. This allows you to temporarily
	 * disable a layer without removing it from the stack
	 * 
	 * @return Boolean
	 * 
	 * @see Layer#setEnabled(boolean)
	 */
	public boolean isEnabled() {
		return this.enabled;
	}
	
	/**
	 * Sets if the layer should be enabled or not.
	 * If a layer is disabled it will not be updated and
	 * will not render. This allows you to temporarily
	 * disable a layer without removing it from the stack
	 * 
	 * @param enable Boolean
	 * 
	 * @see Layer#isEnabled()
	 */
	public void setEnabled(boolean enable) {
		this.shouldBeEnabled = enable;
	}
	
	/**
	 * Returns true if this Layer gets rendered onto the screen
	 * 
	 * @return Boolean
	 * 
	 * @see Layer#setRendering(boolean)
	 */
	public boolean isRendering() {
		return this.rendering;
	}
	
	/**
	 * Sets if the layer gets rendered onto the screen
	 * 
	 * @param enable Boolean
	 * 
	 * @see Layer#isRendering()
	 */
	public void setRendering(boolean enable) {
		this.shouldBeRendering = enable;
	}
	
	/**
	 * Returns the position of this layer in the stack.
	 * Returns -1 if the layer is currently not in the stack
	 * 
	 * @return int
	 */
	public int getPosition() {
		return LayerManager.getManager().getPosition(this);
	}
	
	/**
	 * Returns true if the layer is currently on the stack
	 * 
	 * @return Boolean
	 */
	public boolean isInsideStack() {
		return LayerManager.getManager().contains(this);
	}
	
	/**
	 * Returns the class name + @ + hash code
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "@" + this.hashCode();
	}
}
