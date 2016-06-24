package net.exodiusmc.blasteroids.enums;

import javafx.scene.image.Image;
import net.exodiusmc.blasteroids.manager.MediaManager;

/**
 * Represents a spaceship skin
 */
public enum SpaceshipType {
	DEFAULT("default"),
	BLUE_BIRD("blue_bird"),
	GREEN_FLAME("green_flame"),
	EXO_FIGHTER("exo_fighter"),
	AIR_WING("air_wing"),
	ARMAGEDDON("armageddon");
	
	private Image skin;
	
	private SpaceshipType(String file) {
		this.skin = MediaManager.getManager().getImage(file);
	}
	
	public Image getSkinImage() {
		return this.skin;
	}
}
