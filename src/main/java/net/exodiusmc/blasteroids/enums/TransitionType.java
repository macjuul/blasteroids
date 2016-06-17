package net.exodiusmc.blasteroids.enums;

import net.exodiusmc.blasteroids.Logger;
import net.exodiusmc.blasteroids.transition.FadeIn;
import net.exodiusmc.blasteroids.transition.FadeOut;
import net.exodiusmc.blasteroids.transition.Hide;
import net.exodiusmc.blasteroids.transition.Transition;

public enum TransitionType {
	FADE_IN(FadeIn.class),
	FADE_OUT(FadeOut.class),
	HIDE(Hide.class);
	
	private Transition transition;
	
	private TransitionType(Class<? extends Transition> clazz) {
		try {
			this.transition = clazz.newInstance();
		} catch (Exception e) {
			Logger.getLogger().error("Could not init Transition");
			e.printStackTrace();
		}
	}
	
	public Transition getInstance() {
		return transition;
	}
}
