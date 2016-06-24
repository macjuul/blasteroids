package net.exodiusmc.blasteroids;

import net.exodiusmc.blasteroids.enums.LogLevel;
import net.exodiusmc.blasteroids.enums.LayerEffectType;
import net.exodiusmc.blasteroids.layers.effects.LayerEffect;

public class ObjectFactory {

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
