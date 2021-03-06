package net.exodiusmc.blasteroids.enums;

import net.exodiusmc.blasteroids.layers.effects.FadeIn;
import net.exodiusmc.blasteroids.layers.effects.FadeOut;
import net.exodiusmc.blasteroids.layers.effects.LayerEffect;
import net.exodiusmc.blasteroids.layers.effects.SlideFromBottom;
import net.exodiusmc.blasteroids.layers.effects.SlideFromLeft;
import net.exodiusmc.blasteroids.layers.effects.SlideFromRight;
import net.exodiusmc.blasteroids.layers.effects.SlideFromTop;
import net.exodiusmc.blasteroids.layers.effects.SlideToBottom;
import net.exodiusmc.blasteroids.layers.effects.SlideToLeft;
import net.exodiusmc.blasteroids.layers.effects.SlideToRight;
import net.exodiusmc.blasteroids.layers.effects.SlideToTop;

/**
 * Enum holding all LayerEffect types pointing to the Effect class
 */
public enum LayerEffectType {
	FADE_IN(FadeIn.class),
	FADE_OUT(FadeOut.class),
	SLIDE_TO_RIGHT(SlideToRight.class),
	SLIDE_FROM_LEFT(SlideFromLeft.class),
	SLIDE_TO_LEFT(SlideToLeft.class),
	SLIDE_FROM_RIGHT(SlideFromRight.class),
	SLIDE_TO_TOP(SlideToTop.class),
	SLIDE_FROM_BOTTOM(SlideFromBottom.class),
	SLIDE_TO_BOTTOM(SlideToBottom.class),
	SLIDE_FROM_TOP(SlideFromTop.class);
	
	private Class<? extends LayerEffect> clazz;
	
	private LayerEffectType(Class<? extends LayerEffect> clazz) {
		this.clazz = clazz;
	}
	
	public Class<? extends LayerEffect> getTransitionClass() {
		return clazz;
	}
}
