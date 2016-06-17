package net.exodiusmc.blasteroids.layers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import net.exodiusmc.blasteroids.Layer;
import net.exodiusmc.blasteroids.Main;
import net.exodiusmc.blasteroids.enums.SpaceState;
import net.exodiusmc.blasteroids.enums.TransitionType;
import net.exodiusmc.blasteroids.manager.LayerManager;
import net.exodiusmc.blasteroids.manager.MediaManager;
import net.exodiusmc.blasteroids.transition.FadeIn;
import net.exodiusmc.blasteroids.utils.GeneralUtils;

public class SplashLayer extends Layer {
	
	public SplashLayer() {
		this.applyTransition(TransitionType.HIDE);
	} 

	
	@Override
	public boolean updateOnCover() {
		return false;
	}

	@Override
	public void update(double delta, long frame) {
		if(frame == 80) {
			FadeIn fit = (FadeIn) this.applyTransition(TransitionType.FADE_IN);
			
			fit.setFadeAmount(0.011);
		} else if(frame == 200) {
			this.applyTransition(TransitionType.FADE_OUT).setOnCompleted(() -> {
				this.applyTransition(TransitionType.HIDE);
				
				GeneralUtils.setTimeout(1000L, () -> {
					LayerManager.getManager().pop();
					MainMenuLayer menu = new MainMenuLayer();
					
					menu.applyTransition(TransitionType.FADE_IN);
					LayerManager.getManager().add(menu);
				});
			});
		}
	}
	
	@Override
	public void render(GraphicsContext gfx) {
		Image splash = MediaManager.getManager().getImage("splash");
		double ratio = splash.getHeight() / splash.getWidth();
		double width = Main.WIDTH * 0.8, height = width * ratio;
		
		gfx.drawImage(splash, Main.WIDTH * 0.1, Main.HEIGHT - (Main.HEIGHT - (Main.HEIGHT - height) / 2), width, height);
	}

	@Override
	public void dispose() {
		
	}
	

}
