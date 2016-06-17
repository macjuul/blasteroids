package net.exodiusmc.blasteroids.layers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import net.exodiusmc.blasteroids.Layer;
import net.exodiusmc.blasteroids.manager.MediaManager;

public class MainMenuLayer extends Layer {

	private Image logo;
    
    public MainMenuLayer() {
		this.logo = MediaManager.getManager().getImage("blasteroids_logo");
	}
	
	@Override
	public boolean updateOnCover() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(double delta, long frame) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GraphicsContext gfx) {
		gfx.drawImage(logo, 345, 250);
		
		gfx.setFill(Color.WHITE);
	    gfx.setLineWidth(0.1);
	    Font pixelEmulator = Font.font("Pixel Emulator", FontWeight.BOLD, 48 );
	    gfx.setFont(pixelEmulator);
	    gfx.fillText( "Play Game", 485, 500 );
	    
	    gfx.setFont(pixelEmulator);
	    gfx.fillText( "Options", 510, 550 );
	    
	    gfx.setFont(pixelEmulator);
	    gfx.fillText( "Credits", 510, 600 );
	    
	    gfx.setFont(pixelEmulator);
	    gfx.fillText( "Exit", 575, 650 );
	
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
