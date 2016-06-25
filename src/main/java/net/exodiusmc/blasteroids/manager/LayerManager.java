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
	 * Get the layer at the specified index
	 * 
	 * @param l Layer
	 * @return Layer
	 */
	public Layer get(int index) {
		return layerStack.get(index);
	}
	
	/**
	 * Remove a layer from the layer stack
	 * 
	 * @param index int
	 */
	public void remove(int index) {
		layerStack.get(index).shouldBeRemoved = true;
	}
	
	/**
	 * Remove a layer from the layer stack
	 * 
	 * @param layer Layer
	 */
	public void remove(Layer layer) {
		layer.shouldBeRemoved = true;
	}
	
	/**
	 * Pop the top most layer from the layer stack. Returns the popped layer
	 * 
	 * @return Layer
	 */
	public Layer pop() {
		Layer popped = this.layerStack.get(this.layerStack.size() - 1);
		popped.shouldBeRemoved = true;
		
		Logger.getLogger().log("Popped existing layer " + popped.toString(), LogLevel.INFO, LogLevel.LAYER);
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
	 * <b>DO NOT USE OUTSIDE RUNTIME!</b>
	 * <p>
	 * This method is called to remove all "shouldBeRemoved" layers
	 * from the LayerStack
	 */
	public void __processShouldRemoveLayers() {
		Iterator<Layer> i = layerStack.iterator();
		
		while(i.hasNext()) {
			Layer l = i.next();
			
			if(l.shouldBeRemoved) {
				l.dispose();
				i.remove();
			}
		}
	}
}