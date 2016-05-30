package net.exodiusmc.blasteroids.layers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import net.exodiusmc.blasteroids.Main;
import net.exodiusmc.blasteroids.interfaces.Layer;
import net.exodiusmc.blasteroids.manager.MediaManager;

public class SpaceLayer implements Layer {
	private double spaceWidth;
    private double spaceHeight;
    private int xStart;
    private int yStart;
    private Image space;
    
    public SpaceLayer() {
		this.space = MediaManager.getManager().getImage("space");
		this.spaceHeight = space.getHeight();
		this.spaceWidth = space.getWidth();
	}

	@Override
	public boolean updateOnCover() {
		return true;
	}

	@Override
	public void update(double delta, long frame) {
		xStart = (int) (((frame * 1.5) % spaceWidth) - spaceWidth);
		yStart = (int) (((frame * 1.5) % spaceHeight) - spaceHeight);
	}

	@Override
	public void render(GraphicsContext gfx) {
		
        for(int x = 0; x < Main.WIDTH + spaceWidth; x += spaceWidth) {
            for(int y = 0; y < Main.HEIGHT * 2; y += spaceHeight) {
                gfx.drawImage(space, x + xStart, y + yStart);
            }
        }
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
