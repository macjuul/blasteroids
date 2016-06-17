package net.exodiusmc.blasteroids.enums;

import net.exodiusmc.blasteroids.transition.FadeIn;
import net.exodiusmc.blasteroids.transition.FadeOut;
import net.exodiusmc.blasteroids.transition.Hide;
import net.exodiusmc.blasteroids.transition.Transition;

public enum TransitionType {
	FADE_IN(FadeIn.class),
	FADE_OUT(FadeOut.class),
	HIDE(Hide.class);
	
	private Class<? extends Transition> clazz;
	
	private TransitionType(Class<? extends Transition> clazz) {
		this.clazz = clazz;
	}
	
	public Class<? extends Transition> getTransitionClass() {
		return clazz;
	}
}
