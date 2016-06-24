package net.exodiusmc.blasteroids.entities;

import net.exodiusmc.blasteroids.Location;

/**
 * Parent Entity class. All entities must extend this class
 */
public abstract class Entity {	
	private Location location;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
