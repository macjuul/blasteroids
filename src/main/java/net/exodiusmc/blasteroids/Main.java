package net.exodiusmc.blasteroids;

import javafx.application.Application;
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
import net.exodiusmc.blasteroids.manager.LayerManager;
import net.exodiusmc.blasteroids.manager.MediaManager;
import net.exodiusmc.blasteroids.utils.FileUtils;

public class Main extends Application {
	public Stage window;
	public static double WIDTH;
	public static double HEIGHT;

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
			"img/icons/icon_128.png",
			"img/icons/icon_32.png",
			"img/icons/icon_16.png",
			"img/asteroids/asteroid_1.png",
			"img/asteroids/asteroid_2.png",
			"img/asteroids/asteroid_3.png",
			"img/asteroids/asteroid_4.png",
			"sounds/boost.wav",
			"sounds/death.wav",
			"sounds/gun.wav",
			"sounds/select.wav",
			"sounds/shoot.wav",
			"sounds/music.mp3"
		});
		Runtime.initalize(cvs.getGraphicsContext2D());
		
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
	}
	
	public static String getVersion() {
		return "0.2 PRE-ALPHA";
	}

}
