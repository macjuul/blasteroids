package net.exodiusmc.blasteroids.manager;

import java.io.File;
import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import net.exodiusmc.blasteroids.Logger;
import net.exodiusmc.blasteroids.utils.FileUtils;

public class MediaManager {
	private static MediaManager instance;
	private HashMap<String, MediaPlayer> players;
	private HashMap<String, Image> images;
    
	private MediaManager(String[] media) {
		this.players = new HashMap<String, MediaPlayer>();
		this.images = new HashMap<String, Image>();
		File resDir = new File(FileUtils.ResolveResource("").getFile().replace("%20", " "));
		int changed = 0;
		
		for(String m : media) {
			String path = resDir + "\\" + m.replace("/", "\\");
			String ext = FileUtils.getFileExtension(path);
			String name = FileUtils.getFileName(path);
			String uri = new File(path).toURI().toString();
			
			if(ext.equals("wav") || ext.equals("mp3")) {
				this.players.put(name, new MediaPlayer(new Media(uri)));
				
				changed++;
			} else if(ext.equals("png")) {
				this.images.put(name, new Image(uri));
				
				changed++;
			}
		}
		
		Logger.getLogger().info("MediaManager loaded! " + changed + " media resources loaded");
	}
	
	public static void initialize(String[] media) {
		if(instance != null) {
			Logger.getLogger().warn("Cannot initalize MediaManager: Already initalized");
    	}
		
		instance = new MediaManager(media);
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
}
