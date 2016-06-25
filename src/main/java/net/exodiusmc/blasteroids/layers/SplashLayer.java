package net.exodiusmc.blasteroids.layers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import net.exodiusmc.blasteroids.Layer;
import net.exodiusmc.blasteroids.Main;
import net.exodiusmc.blasteroids.enums.LayerEffectType;
import net.exodiusmc.blasteroids.layers.effects.FadeIn;
import net.exodiusmc.blasteroids.layers.effects.SlideFromLeft;
import net.exodiusmc.blasteroids.layers.effects.SlideToRight;
import net.exodiusmc.blasteroids.manager.LayerManager;
import net.exodiusmc.blasteroids.manager.MediaManager;
import net.exodiusmc.blasteroids.utils.GeneralUtils;

public class SplashLayer extends Layer {
	
	public SplashLayer() {
		this.applyEffect(LayerEffectType.HIDE);
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
		} else if(frame == 230) {
			this.applyEffect(LayerEffectType.FADE_OUT).setOnCompleted(() -> {
				this.applyEffect(LayerEffectType.HIDE);
				
				GeneralUtils.setTimeout(450L, () -> {
					LayerManager.getManager().pop();
					MainMenuLayer menu = new MainMenuLayer();
					
					((FadeIn) menu.applyEffect(LayerEffectType.FADE_IN)).setFadeAmount(0.02);
					LayerManager.getManager().add(menu);
					
					GeneralUtils.setTimeout(1500L, new Runnable() {

						@Override
						public void run() {
							SlideToRight str = (SlideToRight) menu.applyEffect(LayerEffectType.SLIDE_FROM_RIGHT);
							str.setSlideAmount(27);
							str.setOnCompleted(new Runnable() {
								@Override
								public void run() {
									LayerManager.getManager().remove(menu);
								}
							});
							
//							MainMenuLayer menu2 = new MainMenuLayer();
//							LayerManager.getManager().add(menu2);
//							
//							SlideFromLeft sfl = (SlideFromLeft) menu2.applyEffect(LayerEffectType.SLIDE_TO_RIGHT);
//							sfl.setSlideAmount(27);
						}
						
					});
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
