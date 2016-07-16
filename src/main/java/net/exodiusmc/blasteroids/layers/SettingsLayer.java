package net.exodiusmc.blasteroids.layers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import net.exodiusmc.blasteroids.Layer;
import net.exodiusmc.blasteroids.Main;
import net.exodiusmc.blasteroids.enums.LayerEffectType;
import net.exodiusmc.blasteroids.layers.effects.LayerEffect;
import net.exodiusmc.blasteroids.layers.effects.SlideFromRight;
import net.exodiusmc.blasteroids.layers.effects.SlideToLeft;
import net.exodiusmc.blasteroids.manager.InputManager;
import net.exodiusmc.blasteroids.manager.LayerManager;
import net.exodiusmc.blasteroids.manager.MediaManager;
import net.exodiusmc.blasteroids.utils.GeneralUtils;

public class SettingsLayer extends Layer {
	private byte item = 0, shiftDelay = 0;
	private InputManager input;
	private Media select = MediaManager.getManager().getSound("select");
	
	public SettingsLayer() {
		input = InputManager.getManager();
	}
	
	@Override
	public boolean updateOnCover() {
		return false;
	}

	@Override
	public void update(double delta, long frame) {
		if(this.shiftDelay == 0) {
			if(input.isAnyKeyPressed(new KeyCode[] {KeyCode.UP, KeyCode.W})) {
				item--;
				shiftDelay = 9;
				
				GeneralUtils.playSound(select);
			} else if(input.isAnyKeyPressed(new KeyCode[] {KeyCode.DOWN, KeyCode.S})) {
				item++;
				shiftDelay = 9;
				
				GeneralUtils.playSound(select);
			}
			
			if(item < 0) item = 3;
			if(item > 3) item = 0;
		} else {
			this.shiftDelay--;
		}
		
		if(input.isAnyKeyPressed(new KeyCode[] {KeyCode.SPACE, KeyCode.ENTER}) && !this.hasLayerEffect()) {
			GeneralUtils.playSound(select);
			
			switch(item) {
			case 0:	// Start game
				LayerEffect effect = this.applyEffect(LayerEffectType.FADE_OUT);
				
				SettingsLayer l = this;
				
				effect.setOnCompleted(new Runnable() {
					@Override
					public void run() {
						l.eject();
					}
				});
				break;
			case 1:	// Settings
				SlideToLeft ss = (SlideToLeft) this.applyEffect(LayerEffectType.SLIDE_TO_LEFT);
				ss.setSlideAmount(45);
				
				SettingsLayer sl = this;
				
				ss.setOnCompleted(new Runnable() {
					@Override
					public void run() {
						sl.eject();
					}
				});
				
				SettingsLayer sset = new SettingsLayer();
				LayerManager.getManager().add(sset);
				
				SlideFromRight ssl = (SlideFromRight) sset.applyEffect(LayerEffectType.SLIDE_FROM_RIGHT);
				ssl.setSlideAmount(45);
				
				break;
			case 2:	// About
				SlideToLeft as = (SlideToLeft) this.applyEffect(LayerEffectType.SLIDE_TO_LEFT);
				as.setSlideAmount(45);
				
				SettingsLayer aa = this;
				
				as.setOnCompleted(new Runnable() {
					@Override
					public void run() {
						aa.eject();
					}
				});
				
				AboutLayer about = new AboutLayer();
				LayerManager.getManager().add(about);
				
				SlideFromRight asl = (SlideFromRight) about.applyEffect(LayerEffectType.SLIDE_FROM_RIGHT);
				asl.setSlideAmount(45);
				
				break;
			case 3:	// Go Back
				SlideToLeft ms = (SlideToLeft) this.applyEffect(LayerEffectType.SLIDE_TO_LEFT);
				ms.setSlideAmount(45);
				
				SettingsLayer mm = this;
				
				ms.setOnCompleted(new Runnable() {
					@Override
					public void run() {
						mm.eject();
					}
				});
				
				MenuLayer menu = new MenuLayer();
				LayerManager.getManager().add(menu);
				
				SlideFromRight msl = (SlideFromRight) menu.applyEffect(LayerEffectType.SLIDE_FROM_RIGHT);
				msl.setSlideAmount(45);
				break;
			}
		}
	}

	@Override
	public void render(GraphicsContext gfx) {
	MediaManager mngr = MediaManager.getManager();
		
		Image splash = mngr.getImage("lined_settings");
		double ratio = splash.getHeight() / splash.getWidth();
		double width = Main.WIDTH * 0.6, height = width * ratio;
		
		gfx.drawImage(splash, getOffsetX() + Main.WIDTH * 0.2, getOffsetY() + Main.HEIGHT * 0.1, width, height);
		
		Image play = mngr.getImage("play_1");
		
		double yStart = getOffsetY() + height + (Main.HEIGHT * 0.2);
		
		ratio = play.getHeight() / play.getWidth();
		width = Main.WIDTH * 0.5;
		height = width * ratio;
		
		double xStart = (Main.WIDTH - width) / 2;
		double offset = Main.HEIGHT * 0.1;
		
		if(item == 0) {
			gfx.drawImage(mngr.getImage("sound_lvl_6"), getOffsetX() + xStart, yStart, 150, 150);
		} else {
			gfx.drawImage(mngr.getImage("sound_lvl_6"), getOffsetX() + xStart, yStart, 150, 150);
		}
		
		if(item == 1) {
			gfx.drawImage(mngr.getImage("settings_2"), getOffsetX() + xStart, yStart + offset, width, height);
		} else {
			gfx.drawImage(mngr.getImage("settings_1"), getOffsetX() + xStart, yStart + offset, width, height);
		}
		
		if(item == 2) {
			gfx.drawImage(mngr.getImage("credits_2"), getOffsetX() + xStart, yStart + (offset * 2), width, height);
		} else {
			gfx.drawImage(mngr.getImage("credits_1"), getOffsetX() + xStart, yStart + (offset * 2), width, height);
		}
		
		if(item == 3) {
			gfx.drawImage(mngr.getImage("back_2"), getOffsetX() + xStart, yStart + (offset * 3), width, height);
		} else {
			gfx.drawImage(mngr.getImage("back_1"), getOffsetX() + xStart, yStart + (offset * 3), width, height);
		}
	}

	@Override
	public void dispose() {}


}
