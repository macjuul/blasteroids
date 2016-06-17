package net.exodiusmc.blasteroids.layers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import net.exodiusmc.blasteroids.Layer;
import net.exodiusmc.blasteroids.Main;
import net.exodiusmc.blasteroids.enums.SpaceState;
import net.exodiusmc.blasteroids.manager.MediaManager;
import net.exodiusmc.blasteroids.utils.GeneralUtils;

public class SpaceLayer extends Layer {
	private SpaceState state;
	private double spaceWidth;
    private double spaceHeight;
    private int xStart;
    private int yStart;
    private double vel_x = 0;
    private double pos_x = 0;
    private double vel_y = 0;
    private double pos_y = 0;
    private Image space;
    
    public SpaceLayer() {
		this.space = MediaManager.getManager().getImage("space");
		this.spaceHeight = space.getHeight();
		this.spaceWidth = space.getWidth();
		this.state = SpaceState.SCROLL;
	}

	@Override
	public boolean updateOnCover() {
		return true;
	}

	@Override
	public void update(double delta, long frame) {
		if(this.state == SpaceState.INPUT) {
			this.vel_x += GeneralUtils.randomDoubleInRange(-1, 1.02);		
			this.vel_y += GeneralUtils.randomDoubleInRange(-1, 1.02);
			
			this.pos_x += this.vel_x;
			this.pos_y += this.vel_y;
			
			xStart = (int) (((pos_x * 1.5) % spaceWidth) - spaceWidth);
			yStart = (int) (((pos_y * 1.5) % spaceHeight) - spaceHeight);
		} else if(this.state == SpaceState.SCROLL) {
			this.vel_y += 0.013;
			
			if(this.vel_y > 2.5) this.vel_y = 2.5;
			
			this.pos_y += this.vel_y;
			
			yStart = (int) (((pos_y * 1.5) % spaceHeight) - spaceHeight);
		} else if(this.state == SpaceState.STILL) {
			this.vel_x *= 0.95;		
			this.vel_y *= 0.95;
			
			this.pos_x += this.vel_x;
			this.pos_y += this.vel_y;
			
			xStart = (int) (((pos_x * 1.5) % spaceWidth) - spaceWidth);
			yStart = (int) (((pos_y * 1.5) % spaceHeight) - spaceHeight);
		}
	}

	@Override
	public void render(GraphicsContext gfx) {
		
        for(int x = 0; x < Main.WIDTH + spaceWidth * 2; x += spaceWidth) {
            for(int y = 0; y < Main.HEIGHT * 2; y += spaceHeight) {
                gfx.drawImage(space, x + xStart, y + yStart);
            }
        }
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	public void setState(SpaceState state) {
		this.state = state;
	}
	
	public void maxScrollSpeed() {
		this.vel_y = 2.5;
	}

}
