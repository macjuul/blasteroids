package net.exodiusmc.blasteroids;

import net.exodiusmc.blasteroids.enums.LogLevel;
import net.exodiusmc.blasteroids.enums.TransitionType;
import net.exodiusmc.blasteroids.transition.Transition;

public class ObjectFactory {

	public static Transition newTransition(TransitionType t) {
		try {
			return t.getTransitionClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			Logger.getLogger().log("Transition could not be initalized!", LogLevel.CRITICAL_ERROR);
			e.printStackTrace();
		}
		return null;
	}
	
}
