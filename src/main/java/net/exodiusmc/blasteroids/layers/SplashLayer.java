package net.exodiusmc.blasteroids.layers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import net.exodiusmc.blasteroids.Layer;
import net.exodiusmc.blasteroids.Main;
import net.exodiusmc.blasteroids.enums.TransitionType;
import net.exodiusmc.blasteroids.manager.LayerManager;
import net.exodiusmc.blasteroids.manager.MediaManager;
import net.exodiusmc.blasteroids.transition.FadeIn;

public class SplashLayer extends Layer {
	private Image exo;
	private Image bith;
	
	public SplashLayer() {
		this.exo = MediaManager.getManager().getImage("exodius");
		this.bith = MediaManager.getManager().getImage("bitherus");
		
		this.applyTransition(TransitionType.HIDE);
	} 

	
	@Override
	public boolean updateOnCover() {
		return false;
	}

	@Override
	public void update(double delta, long frame) {
		if(frame == 20) {
			this.applyTransition(TransitionType.FADE_IN);
		} else if(frame == 180) {
			this.applyTransition(TransitionType.FADE_OUT).setOnCompleted(() -> {
				LayerManager.getManager().pop();
				
				MainMenuLayer menu = new MainMenuLayer();
				
				LayerManager.getManager().add(menu);
				menu.applyTransition(TransitionType.FADE_IN);
			});
		}
	}
	
	@Override
	public void render(GraphicsContext gfx) {
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
