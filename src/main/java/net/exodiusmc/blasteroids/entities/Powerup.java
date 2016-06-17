package net.exodiusmc.blasteroids.entities;

import net.exodiusmc.blasteroids.enums.PowerupType;
import net.exodiusmc.blasteroids.utils.GeneralUtils;

public class Powerup extends Entity {
	private PowerupType type;
	
	private Powerup(PowerupType t) {
		this.type = t;
	}
	
	//Summon a powertype
	public static void spawnPowerup() {
		PowerupType t = (PowerupType)GeneralUtils.arrayRand(PowerupType.values());
		
		new Powerup(t); 
	}
	
	public void pickup() {
		switch(this.type) {
		case HEALTH_PACK:
			break;
		case SPEED_BOOST:
			break;
		case SUPER_BLASTER:
			break;
		default:
			break;
		}
	}

}
