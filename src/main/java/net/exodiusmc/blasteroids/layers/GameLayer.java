package net.exodiusmc.blasteroids.layers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.exodiusmc.blasteroids.Layer;
import net.exodiusmc.blasteroids.Location;
import net.exodiusmc.blasteroids.Main;
import net.exodiusmc.blasteroids.SpriteAnimation;
import net.exodiusmc.blasteroids.entities.Spaceship;
import net.exodiusmc.blasteroids.enums.SpaceState;
import net.exodiusmc.blasteroids.enums.SpaceshipType;
import net.exodiusmc.blasteroids.manager.InputManager;
import net.exodiusmc.blasteroids.manager.LayerManager;
import net.exodiusmc.blasteroids.manager.MediaManager;
import net.exodiusmc.blasteroids.utils.RenderUtils;

public class GameLayer extends Layer {
	private final float FRICTION = 0.9f;
	
	private byte phase = 0;
	private Spaceship ship;
	private MediaManager media;
	private SpriteAnimation spaceship;
	private SpaceLayer space;
	private Location center;
	
	@Override
	public boolean updateOnCover() {
		return false;
	}
	
	public GameLayer() {
		this.ship = new Spaceship(SpaceshipType.DEFAULT);
		this.media = MediaManager.getManager();
		this.spaceship = new SpriteAnimation(ship.getType().getSkinImage(), 4, true);
		this.space = (SpaceLayer) LayerManager.getManager().getBottomLayer();
		
		ship.setLocation(new Location(Main.WIDTH / 2, Main.HEIGHT + spaceship.getHeight()));
	}

	@Override
	public void update(double delta, long frame) {
		// Phrase related calculations
		if(phase == 0) {
			ship.getLocation().addY(-5);
			
			if(ship.getLocation().getY() < Main.HEIGHT / 2) {
				ship.getLocation().setY(Main.HEIGHT / 2);
				ship.setVelY(-5);
				
				this.phase = 1;
				
				space.setState(SpaceState.INPUT);
			}
		} else if(phase == 1) {
			// Spaceship movement
			Location cur = InputManager.getManager().getCursorPosition();
			double angle = this.ship.getLocation().getAngle(cur);
			
			this.ship.setVelX(this.ship.getVelX() + 0.09 * Math.sin(Math.toRadians(angle)));
			this.ship.setVelX(this.ship.getVelX() + 0.09 * Math.sin(Math.toRadians(angle)));
			
			this.ship.setVelX(ship.getVelX() * FRICTION);
			this.ship.getLocation().addX(ship.getVelX());
			
			this.ship.setVelY(ship.getVelY() * FRICTION);
			this.ship.getLocation().addY(ship.getVelY());
			
			this.space.setAngle(angle);
			this.ship.setAngle(angle);
		} else {
			// TODO: Game ending (explosion 'n shit)
		}
	}

	@Override
	public void render(GraphicsContext gfx, long frame) {
		// Draw the spaceship
		double scale = Main.WIDTH / 1000,
		       width = spaceship.getWidth() * scale,
		       height = spaceship.getHeight() * scale;
		
		RenderUtils.drawRotatedImage(gfx, spaceship.nextFrame(frame % 5 == 0), ship.getAngle(), ship.getLocation().getX() - (width / 2), ship.getLocation().getY() - (height / 2), width, height);
		
		gfx.setFill(Color.RED);
		gfx.fillOval(ship.getLocation().getX(), ship.getLocation().getY(), 3, 3);
		
		Location mouse = InputManager.getManager().getCursorPosition();
		gfx.fillOval(mouse.getX(), mouse.getY(), 3, 3);
		
		System.out.println("ANGLE: " + ship.getLocation().getAngle(mouse));
		
		
	}

	@Override
	public void dispose() {
		
	}

}
