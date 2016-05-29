package net.exodiusmc.blasteroids.manager;

import java.util.ArrayList;

import net.exodiusmc.blasteroids.interfaces.Layer;

public class LayerManager {
	private static LayerManager instance;
	private ArrayList<Layer> layerStack;
	
	private LayerManager() {
		layerStack = new ArrayList<Layer>();
	}
	
	public static LayerManager getManager() {
		if(instance == null) {
			instance = new LayerManager();
		}
		
		return instance;
	}
	
	/*
	 * Get the size of the layer stack
	 * 
	 * @return int Size
	 */
	public int size() {
		return layerStack.size();
	}
	
	/*
	 * Add a new layer to the layer stack. This layer will be added "on top" of the other layers
	 * 
	 * @param l Layer
	 * @return Layer
	 */
	public Layer add(Layer l) {
		layerStack.add(l);
		return l;
	}
	
	/*
	 * Get the layer at the specified index
	 * 
	 * @param l Layer
	 * @return Layer
	 */
	public Layer get(int index) {
		return layerStack.get(index);
	}
	
	/*
	 * Pop the top most layer from the layer stack. Returns the popped layer
	 * 
	 * @return Layer
	 */
	public Layer pop() {
		Layer popped = this.layerStack.get(this.layerStack.size() - 1);
		popped.dispose();
		this.layerStack.remove(this.layerStack.size() - 1);
		return popped;
	}
	
	/*
	 * Clear the layer stack
	 */
	public void clear() {
		for(Layer l : layerStack) {
			l.dispose();
		}
		this.layerStack.clear();
	}
}