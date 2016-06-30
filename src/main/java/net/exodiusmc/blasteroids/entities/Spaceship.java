package net.exodiusmc.blasteroids.entities;

import net.exodiusmc.blasteroids.enums.SpaceshipType;

/**
 * Represents a Spaceship entity
 */
public class Spaceship extends Entity {
	private double angle;
	private double vel_x;
	private double vel_y;
	private SpaceshipType type;
	
	public Spaceship(SpaceshipType type) {
		this.type = type;
	}

	public double getVelX() {
		return vel_x;
	}

	public void setVelX(double vel_x) {
		this.vel_x = vel_x;
	}

	public double getVelY() {
		return vel_y;
	}

	public void setVelY(double vol_y) {
		this.vel_y = vol_y;
	}

	public double getAngle() {
		return angle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}

	public SpaceshipType getType() {
		return type;
	}
	
}
