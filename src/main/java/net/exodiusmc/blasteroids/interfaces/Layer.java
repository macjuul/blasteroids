package net.exodiusmc.blasteroids.interfaces;

import javafx.scene.canvas.GraphicsContext;

public interface Layer {
	public boolean updateOnCover();
	
	public void update(double delta, long frame);
	
	public void render(GraphicsContext gfx);
	
	public void dispose();
}
