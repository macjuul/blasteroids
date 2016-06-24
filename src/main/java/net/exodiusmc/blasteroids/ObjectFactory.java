package net.exodiusmc.blasteroids;

import net.exodiusmc.blasteroids.enums.LogLevel;
import net.exodiusmc.blasteroids.enums.LayerEffectType;
import net.exodiusmc.blasteroids.layers.effects.LayerEffect;

/**
 * The object factory is an abstraction factory for creating objects
 */
public class ObjectFactory {

	/**
	 * Creates a new LayerEffect object from the supplied enum value
	 * 
	 * @param t LayerEffectType
	 * @return LayerEffect
	 */
	public static LayerEffect newLayerEffect(LayerEffectType t) {
		try {
			return t.getTransitionClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			Logger.getLogger().log("Layer effect could not be initalized!", LogLevel.CRITICAL_ERROR);
			e.printStackTrace();
		}
		return null;
	}
	
}
