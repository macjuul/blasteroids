package net.exodiusmc.blasteroids;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import net.exodiusmc.blasteroids.layers.GameLayer;
import net.exodiusmc.blasteroids.layers.MainMenuLayer;
import net.exodiusmc.blasteroids.layers.SpaceLayer;
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
		Logger.getLogger().info("Starting Blasteroids. Loading Main class...");
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
		
		// Initalize different functions
		FileUtils.setResourceDirectory("net/exodiusmc/blasteroids");
		MediaManager.initialize();
		Runtime.initalize(cvs.getGraphicsContext2D());
		
		// Add the layers
		LayerManager.getManager().add(new SpaceLayer());
		LayerManager.getManager().add(new MainMenuLayer());
		LayerManager.getManager().add(new GameLayer());
		
		// Complete window loading
		window.getIcons().addAll(FileUtils.LoadImage("img/icons/icon_128.png"), FileUtils.LoadImage("img/icons/icon_32.png"), FileUtils.LoadImage("img/icons/icon_16.png"));
		window.setTitle("Blasteroids " + getVersion());
		window.show();
		Logger.getLogger().info("Blasteroids version " + getVersion() + " successfully loaded!");
	}
	
	public static String getVersion() {
		return "0.2 PRE-ALPHA";
	}

}
