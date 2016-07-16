package net.exodiusmc.blasteroids;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import net.exodiusmc.blasteroids.enums.LayerEffectType;
import net.exodiusmc.blasteroids.layers.SpaceLayer;
import net.exodiusmc.blasteroids.layers.SplashLayer;
import net.exodiusmc.blasteroids.layers.effects.FadeIn;
import net.exodiusmc.blasteroids.manager.InputManager;
import net.exodiusmc.blasteroids.manager.LayerManager;
import net.exodiusmc.blasteroids.manager.MediaManager;
import net.exodiusmc.blasteroids.utils.FileUtils;

public class Main extends Application {
	public Stage window;
	public static double WIDTH;
	public static double HEIGHT;
	public static boolean DEV_MODE = true;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage window) throws Exception {
		Logger.getLogger().info("Starting Blasteroids version " + getVersion());
		// Store the window object;
		this.window = window;
		
		// Make it non-resizable
		window.setResizable(false);
		
		// Set the window size
		double height = Screen.getPrimary().getVisualBounds().getHeight() * 0.9;
		
		window.setWidth(height * 1.4);
		window.setHeight(height);
		Main.WIDTH = height * 1.4;
		Main.HEIGHT = height;
		
		// Populize the window
		StackPane pane = new StackPane();
		Canvas cvs = new Canvas(WIDTH, HEIGHT);
		
		pane.setPadding(new Insets(29, 0, 0, 6));
		
		pane.getChildren().add(cvs);
		
		Scene main = new Scene(pane);
		window.setScene(main);
		
		// Initialize different functions
		FileUtils.setResourceDirectory("net/exodiusmc/blasteroids");
		MediaManager.initialize(new String[] {
			"img/blasteroids_logo.png",
			"img/logo_128.png",
			"img/logo_32.png",
			"img/space.png",
			"img/splash.png",
			"img/sound_lvl_0.png",
			"img/sound_lvl_1.png",
			"img/sound_lvl_2.png",
			"img/sound_lvl_3.png",
			"img/sound_lvl_4.png",
			"img/sound_lvl_5.png",
			"img/sound_lvl_6.png",
			"img/icons/icon_128.png",
			"img/icons/icon_32.png",
			"img/icons/icon_16.png",
			"img/asteroids/1.png",
			"img/asteroids/2.png",
			"img/asteroids/3.png",
			"img/asteroids/4.png",
			"img/ship/air_wing.png",
			"img/ship/armageddon.png",
			"img/ship/blue_bird.png",
			"img/ship/default.png",
			"img/ship/exo_fighter.png",
			"img/ship/green_flame.png",
			"img/options/play_1.png",
			"img/options/play_2.png",
			"img/options/settings_1.png",
			"img/options/settings_2.png",
			"img/options/credits_1.png",
			"img/options/credits_2.png",
			"img/options/quit_1.png",
			"img/options/quit_2.png",
			"img/options/back_1.png",
			"img/options/back_2.png",
			"img/lined_settings.png",
			"sounds/boost.wav",
			"sounds/death.wav",
			"sounds/gun.wav",
			"sounds/select.wav",
			"sounds/shoot.wav",
			"sounds/music.mp3"
		});
		
		if(DEV_MODE) {
			Logger.getLogger().warn("DEV_MODE is enabled: additional debug messages might be logged");
		}
		
		Runtime.initalize(cvs.getGraphicsContext2D());
		InputManager.intialize(window);
		
		// Add the layers
		SpaceLayer space = new SpaceLayer();
		
		LayerManager.getManager().add(space);
		LayerManager.getManager().add(new SplashLayer());
		
		space.maxScrollSpeed();
		((FadeIn) space.applyEffect(LayerEffectType.FADE_IN)).setFadeAmount(0.006);
		
		// Complete window loading
		window.getIcons().addAll(FileUtils.LoadImage("img/icons/icon_128.png"), FileUtils.LoadImage("img/icons/icon_32.png"), FileUtils.LoadImage("img/icons/icon_16.png"));
		window.setTitle("Blasteroids " + getVersion());
		window.show();

		// Make sure the game exits correctly
		window.setOnCloseRequest(e -> {
			Logger.getLogger().info("Terminating Blasteroids " + getVersion());
			Platform.exit();
			System.exit(0);
		});
	}
	
	public static String getVersion() {
		return "0.0.2 PRE-ALPHA";
	}

}
