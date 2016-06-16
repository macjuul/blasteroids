package net.exodiusmc.blasteroids.layers;

import javafx.animation.FadeTransition;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import net.exodiusmc.blasteroids.Main;
import net.exodiusmc.blasteroids.interfaces.Layer;
import net.exodiusmc.blasteroids.manager.MediaManager;

public class SplashscreenLayer implements Layer {

	private Image exo;
	private Image bith;
	
	public SplashscreenLayer() {
		this.exo = MediaManager.getManager().getImage("exodius");
		this.bith = MediaManager.getManager().getImage("bitherus");
	} 

	
	@Override
	public boolean updateOnCover() {
		return false;
	}

	@Override
	public void update(double delta, long frame) {
		
	}
	
	@Override
	public void render(GraphicsContext gfx) {
		gfx.setFill(Color.BLACK);
		gfx.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		
		gfx.setFill(Color.WHITE);
	    gfx.setLineWidth(0.1);
	    Font roboto = Font.font("Roboto-Thin"/**, FontWeight.EXTRA_BOLD**/, 48 );
	    gfx.setFont(roboto);
		
		gfx.drawImage(exo, Main.WIDTH-950, Main.HEIGHT/2-100);
		gfx.drawImage(bith, Main.WIDTH-600, Main.HEIGHT/2-100);
		gfx.fillText( "A GAME CREATED BY EXODIUS AND BITHERUS STUDIOS", 50, 250);
	}

	@Override
	public void dispose() {
		
	}
	

}
