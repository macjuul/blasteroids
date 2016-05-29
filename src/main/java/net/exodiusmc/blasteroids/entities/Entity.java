package net.exodiusmc.blasteroids.entities;

import net.exodiusmc.blasteroids.Location;

public abstract class Entity {	
	private Location location;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
