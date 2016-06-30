package net.exodiusmc.blasteroids.layers;

import javafx.scene.canvas.GraphicsContext;
import net.exodiusmc.blasteroids.Layer;
import net.exodiusmc.blasteroids.Location;
import net.exodiusmc.blasteroids.Main;
import net.exodiusmc.blasteroids.SpriteAnimation;
import net.exodiusmc.blasteroids.entities.Spaceship;
import net.exodiusmc.blasteroids.enums.SpaceshipType;
import net.exodiusmc.blasteroids.manager.MediaManager;

public class GameLayer extends Layer {
	private byte phase = 0;
	private Spaceship ship;
	private MediaManager media;
	private SpriteAnimation spaceship;
	
	@Override
	public boolean updateOnCover() {
		return false;
	}
	
	public GameLayer() {
		this.ship = new Spaceship(SpaceshipType.DEFAULT);
		this.media = MediaManager.getManager();
		this.spaceship = new SpriteAnimation(ship.getType().getSkinImage(), 4, true);
		
		ship.setLocation(new Location(Main.WIDTH / 2, Main.HEIGHT + spaceship.getHeight()));
	}

	@Override
	public void update(double delta, long frame) {
		if(phase == 0) {
			this.ship.getLocation().addY(-7);
			
			if() {
				
			}
		}
		
		
	}

	@Override
	public void render(GraphicsContext gfx, long frame) {
		// Draw the spaceship
		double scale = Main.WIDTH / 1000,
		       width = spaceship.getWidth() * scale,
		       height = spaceship.getHeight() * scale;
		
		gfx.drawImage(spaceship.nextFrame(frame % 5 == 0), ship.getLocation().getX() - (width / 2), ship.getLocation().getY() - (height / 2), width, height);
		
	}

	@Override
	public void dispose() {
		
	}

}
