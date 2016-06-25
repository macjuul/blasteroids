package net.exodiusmc.blasteroids.layers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import net.exodiusmc.blasteroids.Layer;
import net.exodiusmc.blasteroids.Main;
import net.exodiusmc.blasteroids.enums.LayerEffectType;
import net.exodiusmc.blasteroids.layers.effects.LayerEffect;
import net.exodiusmc.blasteroids.manager.InputManager;
import net.exodiusmc.blasteroids.manager.MediaManager;
import net.exodiusmc.blasteroids.utils.GeneralUtils;

public class MainMenuLayer extends Layer {
	byte item = 0, shiftDelay = 0;
	boolean clicked = false;
	InputManager input;
	
	public MainMenuLayer() {
		input = InputManager.getManager();
		
		GeneralUtils.setTimeout(1500L, () -> {
			this.applyEffect(LayerEffectType.SLIDE_TO_RIGHT);
		});
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
				shiftDelay = 10;
			} else if(input.isAnyKeyPressed(new KeyCode[] {KeyCode.DOWN, KeyCode.S})) {
				item++;
				shiftDelay = 10;
			}
			
			if(item < 0) item = 3;
			if(item > 3) item = 0;
		} else {
			this.shiftDelay--;
		}
		
		if(input.isAnyKeyPressed(new KeyCode[] {KeyCode.SPACE, KeyCode.ENTER})) {
			
		}
	}

	@Override
	public void render(GraphicsContext gfx) {
		MediaManager mngr = MediaManager.getManager();
		int xStart = 400;
		
		Image splash = mngr.getImage("blasteroids_logo");
		double ratio = splash.getHeight() / splash.getWidth();
		double width = Main.WIDTH * 0.6, height = width * ratio;
		
		gfx.drawImage(splash, Main.WIDTH * 0.2, 55, width, height);
		
		if(item == 0) {
			gfx.drawImage(mngr.getImage("play_2"), xStart, 280);
		} else {
			gfx.drawImage(mngr.getImage("play_1"), xStart, 280);
		}
		
		if(item == 1) {
			gfx.drawImage(mngr.getImage("settings_2"), xStart, 380);
		} else {
			gfx.drawImage(mngr.getImage("settings_1"), xStart, 380);
		}
		
		if(item == 2) {
			gfx.drawImage(mngr.getImage("credits_2"), xStart, 480);
		} else {
			gfx.drawImage(mngr.getImage("credits_1"), xStart, 480);
		}
		
		if(item == 3) {
			gfx.drawImage(mngr.getImage("quit_2"), xStart, 580);
		} else {
			gfx.drawImage(mngr.getImage("quit_1"), xStart, 580);
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
