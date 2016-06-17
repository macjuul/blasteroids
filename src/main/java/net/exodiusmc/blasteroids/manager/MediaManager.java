package net.exodiusmc.blasteroids.manager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import net.exodiusmc.blasteroids.Logger;
import net.exodiusmc.blasteroids.enums.LogLevel;
import net.exodiusmc.blasteroids.utils.FileUtils;

public class MediaManager {
	private static MediaManager instance;
	private HashMap<String, MediaPlayer> players;
	private HashMap<String, Image> images;
    
	private MediaManager() {
		this.players = new HashMap<String, MediaPlayer>();
		this.images = new HashMap<String, Image>();
		File resDir = new File(FileUtils.ResolveResource("").getFile());
		
		Logger.getLogger().log("MediaManager path: " + FileUtils.ResolveResource("").getFile(), LogLevel.RUNTIME, LogLevel.WARNING);
		
		int changed = processRecursive(resDir);
		
		Logger.getLogger().info("MediaManager loaded! " + changed + " media resources loaded");
	}
	
	public static void initialize() {
		if(instance != null) {
			Logger.getLogger().warn("Cannot initalize MediaManager: Already initalized");
    	}
		
		instance = new MediaManager();
	}
    
    public static MediaManager getManager() {
    	if(instance == null) {
    		throw new NullPointerException("The manager has not been initialized yet");
    	}
    	
    	return instance;
    }
    
    public Image getImage(String name) {
    	Image i = this.images.get(name);
    	
    	if(i == null) {
    		throw new NullPointerException("Unknown media: " + name);
    	}
    	
    	return i;
    }
    
    public MediaPlayer getSound(String name) {
    	MediaPlayer i = this.players.get(name);
    	
    	if(i == null) {
    		throw new NullPointerException("Unknown media: " + name);
    	}
    	
    	return i;
    }
    
    private int processRecursive(File dir) {
    	int changed = 0;
		try {
			File[] files = dir.listFiles();
			
			if(files == null) {
				Logger.getLogger().log("Unable to load Media resources", LogLevel.CRITICAL_ERROR);
			}
				
			for (File file : files) {
				if (file.isDirectory()) {
					changed += processRecursive(file);
				} else {
					String path = file.getCanonicalPath();
					String ext = FileUtils.getFileExtension(path);
					String name = FileUtils.getFileName(path);
					
					if(ext.equals("wav") || ext.equals("mp3")) {
						this.players.put(name, new MediaPlayer(new Media(file.toURI().toString())));
						
						changed++;
					} else if(ext.equals("png")) {
						this.images.put(name, new Image(file.toURI().toString()));
						
						changed++;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return changed;
	}
}
