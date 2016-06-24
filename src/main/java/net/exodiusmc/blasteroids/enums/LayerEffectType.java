package net.exodiusmc.blasteroids.enums;

import net.exodiusmc.blasteroids.layers.effects.FadeIn;
import net.exodiusmc.blasteroids.layers.effects.FadeOut;
import net.exodiusmc.blasteroids.layers.effects.Hide;
import net.exodiusmc.blasteroids.layers.effects.LayerEffect;
import net.exodiusmc.blasteroids.layers.effects.SlideFromLeft;
import net.exodiusmc.blasteroids.layers.effects.SlideToRight;

public enum LayerEffectType {
	FADE_IN(FadeIn.class),
	FADE_OUT(FadeOut.class),
	HIDE(Hide.class),
	SLIDE_TO_RIGHT(SlideToRight.class),
	SLIDE_FROM_LEFT(SlideFromLeft.class);
	
	private Class<? extends LayerEffect> clazz;
	
	private LayerEffectType(Class<? extends LayerEffect> clazz) {
		this.clazz = clazz;
	}
	
	public Class<? extends LayerEffect> getTransitionClass() {
		return clazz;
	}
}
