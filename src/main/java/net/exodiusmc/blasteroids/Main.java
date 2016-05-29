package net.exodiusmc.blasteroids;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import net.exodiusmc.blasteroids.manager.MediaManager;
import net.exodiusmc.blasteroids.utils.FileUtils;

public class Main extends Application {
	public Stage window;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage window) throws Exception {
		// Store the window object;
		this.window = window;
		
		// Initalize different functions
		FileUtils.setResourceDirectory("net/exodiusmc/blasteroids");
		MediaManager.initialize();
		
		StackPane pane = new StackPane();
		Canvas cvs = new Canvas();
		
		pane.getChildren().add(cvs);
		
		Scene main = new Scene(pane);
		window.setScene(main);
		
		double height = Screen.getPrimary().getVisualBounds().getHeight() * 0.9;
		
		window.setWidth(height * 1.4);
		window.setHeight(height);
		
		window.getIcons().addAll(FileUtils.LoadImage("img/icon_128.png"), FileUtils.LoadImage("img/icon_32.png"), FileUtils.LoadImage("img/icon_16.png"));
		
		window.show();
		
		window.setTitle("Blasteroids " + getVersion());
		
		Logger.getLogger().info("Blasteroids version " + getVersion() + " successfully loaded!");
		
		new Runtime();
		
		Logger.getLogger().info("Runtime loaded");
	}
	
	public String getVersion() {
		return "v1.0.0 ALPHA";
	}

}
