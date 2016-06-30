package net.exodiusmc.blasteroids.layers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import net.exodiusmc.blasteroids.Layer;
import net.exodiusmc.blasteroids.Main;
import net.exodiusmc.blasteroids.enums.LayerEffectType;
import net.exodiusmc.blasteroids.layers.effects.FadeIn;
import net.exodiusmc.blasteroids.layers.effects.SlideFromBottom;
import net.exodiusmc.blasteroids.layers.effects.SlideToBottom;
import net.exodiusmc.blasteroids.layers.effects.SlideToTop;
import net.exodiusmc.blasteroids.manager.LayerManager;
import net.exodiusmc.blasteroids.manager.MediaManager;
import net.exodiusmc.blasteroids.utils.GeneralUtils;

public class SplashLayer extends Layer {
	
	public SplashLayer() {
		this.setRendering(false);
	} 

	
	@Override
	public boolean updateOnCover() {
		return false;
	}

	@Override
	public void update(double delta, long frame) {
		if(frame == 70) {
			FadeIn fit = (FadeIn) this.applyEffect(LayerEffectType.FADE_IN);
			fit.setFadeAmount(0.008);
			
			this.setRendering(true);
		} else if(frame == 230) {
			this.applyEffect(LayerEffectType.FADE_OUT).setOnCompleted(() -> {
				LayerManager.getManager().pop();	// Remove this layer from the stack
				
				GeneralUtils.setTimeout(450L, () -> {
					MenuLayer menu = new MenuLayer();
					
					((FadeIn) menu.applyEffect(LayerEffectType.FADE_IN)).setFadeAmount(0.02);
					LayerManager.getManager().add(menu);
				});
			});
		}
	}
	
	@Override
	public void render(GraphicsContext gfx, long ticks) {
		Image splash = MediaManager.getManager().getImage("splash");
		double ratio = splash.getHeight() / splash.getWidth();
		double width = Main.WIDTH * 0.8, height = width * ratio;
		
		gfx.drawImage(splash, Main.WIDTH * 0.1, Main.HEIGHT - (Main.HEIGHT - (Main.HEIGHT - height) / 2), width, height);
	}

	@Override
	public void dispose() {
		
	}
	

}
