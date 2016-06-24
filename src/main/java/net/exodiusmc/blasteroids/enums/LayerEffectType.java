package net.exodiusmc.blasteroids.enums;

import net.exodiusmc.blasteroids.layers.effects.FadeIn;
import net.exodiusmc.blasteroids.layers.effects.FadeOut;
import net.exodiusmc.blasteroids.layers.effects.Hide;
import net.exodiusmc.blasteroids.layers.effects.LayerEffect;

/**
 * Enum holding all LayerEffect types pointing to the Effect class
 */
public enum LayerEffectType {
	FADE_IN(FadeIn.class),
	FADE_OUT(FadeOut.class),
	HIDE(Hide.class);
	
	private Class<? extends LayerEffect> clazz;
	
	private LayerEffectType(Class<? extends LayerEffect> clazz) {
		this.clazz = clazz;
	}
	
	public Class<? extends LayerEffect> getTransitionClass() {
		return clazz;
	}
}
