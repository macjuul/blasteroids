package net.exodiusmc.blasteroids.layers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import net.exodiusmc.blasteroids.Layer;
import net.exodiusmc.blasteroids.Main;
import net.exodiusmc.blasteroids.manager.MediaManager;

public class MainMenuLayer extends Layer {
	
	@Override
	public boolean updateOnCover() {
		return false;
	}

	@Override
	public void update(double delta, long frame) {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(GraphicsContext gfx) {
		Image splash = MediaManager.getManager().getImage("blasteroids_logo");
		double ratio = splash.getHeight() / splash.getWidth();
		double width = Main.WIDTH * 0.6, height = width * ratio;
		
		gfx.drawImage(splash, Main.WIDTH * 0.2, 55, width, height);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
