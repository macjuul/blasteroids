package net.exodiusmc.blasteroids.manager;

import java.util.ArrayList;
import java.util.Iterator;

import net.exodiusmc.blasteroids.Layer;
import net.exodiusmc.blasteroids.Logger;
import net.exodiusmc.blasteroids.enums.LogLevel;

/**
 * The layer manager is used to alter the layers getting updated and rendered
 * by the game.
 */
public class LayerManager {
	private static LayerManager instance;
	private ArrayList<Layer> layerStack;
	
	private LayerManager() {
		layerStack = new ArrayList<Layer>();
		Logger.getLogger().info("LayerManager loaded!");
	}
	
	/**
	 * Returns the associated manager object
	 * 
	 * @return LayerManager
	 */
	public static LayerManager getManager() {
		if(instance == null) {
			instance = new LayerManager();
		}
		
		return instance;
	}
	
	/**
	 * Get the size of the layer stack
	 * 
	 * @return int Size
	 */
	public int size() {
		return layerStack.size();
	}
	
	public boolean isEmpty() {
		return this.layerStack.isEmpty();
	}
	
	/**
	 * Add a new layer to the layer stack. This layer will be added "on top" of the other layers
	 * 
	 * @param l Layer
	 * @return Layer
	 */
	public Layer add(Layer l) {
		layerStack.add(l);
		Logger.getLogger().log("Added new layer " + l.toString(), LogLevel.INFO, LogLevel.LAYER);
		return l;
	}
	
	/**
	 * Adds the given layers to the layer stack
	 * 
	 * @param ls Layers
	 */
	public void add(Layer... ls) {
		for(Layer l : ls) {
			add(l);
		}
	}
	
	/**
	 * Get the layer at the specified index
	 * 
	 * @param l Layer
	 * @return Layer
	 */
	public Layer get(int index) {
		return layerStack.get(index);
	}
	
	/**
	 * Remove the layer at the specified index from the stack
	 * 
	 * @param index int
	 */
	public void remove(int index) {
		Layer l = layerStack.get(index);
		Logger.getLogger().log("Removed layer " + l.toString() + " from the stack", LogLevel.INFO, LogLevel.LAYER);
		l.shouldBeRemoved = true;
	}
	
	/**
	 * Removes the layers on the given indexes from the stack
	 *  
	 * @param indexes List of ints
	 */
	public void remove(int... indexes) {
		for(int i : indexes) {
			remove(i);
		}
	}
	
	/**
	 * Remove a layer from the layer stack
	 * 
	 * @param layer Layer
	 */
	public void remove(Layer layer) {
		layer.shouldBeRemoved = true;
		
		Logger.getLogger().log("Removed layer " + layer.toString() + " from the stack", LogLevel.INFO, LogLevel.LAYER);
	}
	
	/**
	 * Removes the given layers from the stack
	 *  
	 * @param indexes List of layers
	 */
	public void remove(Layer... ls) {
		for(Layer l : ls) {
			remove(l);
		}
	}
	
	/**
	 * Pop the top most layer from the layer stack. Returns the popped layer
	 * 
	 * @return Layer
	 */
	public Layer pop() {
		Layer popped = this.layerStack.get(this.layerStack.size() - 1);
		popped.shouldBeRemoved = true;
		
		Logger.getLogger().log("Popped layer " + popped.toString() + " from the stack", LogLevel.INFO, LogLevel.LAYER);
		return popped;
	}
	
	/**
	 * Clear the entire layer stack
	 */
	public void clear() {
		for(Layer l : layerStack) {
			l.shouldBeRemoved = true;
		}
		Logger.getLogger().log("Cleared the LayerStack", LogLevel.INFO, LogLevel.LAYER);
	}
	
	/**
	 * Returns the top most Layer on the stack. If no layer can be found will return null instead
	 * 
	 * @return Layer
	 */
	public Layer getTopLayer() {
		try {
			return this.layerStack.get(this.layerStack.size() - 1);
		} catch(IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * Returns the bottom most Layer on the stack. If no layer can be found will return null instead
	 * 
	 * @return Layer
	 */
	public Layer getBottomLayer() {
		try {
			return this.layerStack.get(0);
		} catch(IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * Checks if the given layer is inside the layer stack and
	 * returns true if it is
	 * 
	 * @param l Layer
	 * @return Boolean
	 */
	public boolean contains(Layer l) {
		return this.layerStack.contains(l);
	}
	
	/**
	 * Returns the position of the given layer in the layer stack.
	 * Returns -1 if the layer cannot be found
	 * 
	 * @param l Layer
	 * @return int
	 */
	public int getPosition(Layer l) {
		return this.layerStack.indexOf(l);
	}
	
	/**
	 * <b>DO NOT USE OUTSIDE RUNTIME!</b>
	 * <p>
	 * This method is called to remove all "shouldBeRemoved" layers
	 * from the LayerStack
	 */
	public void __processShouldFields() {
		Iterator<Layer> i = layerStack.iterator();
		
		while(i.hasNext()) {
			Layer l = i.next();
			
			if(l.shouldBeRemoved) {
				l.dispose();
				i.remove();
			} else {
				l.rendering = l.shouldBeRendering;
				l.enabled = l.shouldBeEnabled;
			}
		}
	}
}