package net.exodiusmc.blasteroids.manager;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import net.exodiusmc.blasteroids.Logger;
import net.exodiusmc.blasteroids.utils.FileUtils;

/**
 * The media manager is responsible for texture and sound resources used
 * in the game. Before you can use this manager you will first
 * have to initalize it by calling {@link MediaManager#initialize(String[])}.
 * You will need to pass in a list of resource file names relative to the
 * set resource path.
 */
public class MediaManager {
	private static MediaManager instance;
	private HashMap<String, Media> players;
	private HashMap<String, Image> images;
    
	private MediaManager(String[] media) {
		this.players = new HashMap<String, Media>();
		this.images = new HashMap<String, Image>();
		int changed = 0;
		
		for(String m : media) {
			String path = FileUtils.ResolveResource(m).toString();
			String ext = FileUtils.getFileExtension(path);
			String name = FileUtils.getFileName(path);
			
			if(ext.equals("wav") || ext.equals("mp3")) {
				this.players.put(name, new Media(path));
				
				changed++;
			} else if(ext.equals("png")) {
				this.images.put(name, new Image(path));
				
				changed++;
			}
		}
		
		Logger.getLogger().info("MediaManager loaded! " + changed + " media resources loaded");
	}
	
	/**
     * Initalize the manager Object
     *  
     * @param media String[]
     */
	public static void initialize(String[] media) {
		if(instance != null) {
			Logger.getLogger().warn("Cannot initalize MediaManager: Already initalized");
    	}
		
		instance = new MediaManager(media);
	}
    
	/**
	 * Returns the associated manager object
	 * 
	 * @return MediaManager
	 */
    public static MediaManager getManager() {
    	if(instance == null) {
    		throw new NullPointerException("The manager has not been initialized yet");
    	}
    	
    	return instance;
    }
    
    /**
     * Get a resource image stored in the MediaManager
     * 
     * @param name String
     * @return Image
     */
    public Image getImage(String name) {
    	Image i = this.images.get(name);
    	
    	if(i == null) {
    		Logger.getLogger().error("Could not find Image media \"" + name + "\"");
    	}
    	
    	return i;
    }
    
    /**
     * Get a resource sound (MediaPlayer) stored in the MediaManager
     * 
     * @param name String
     * @return MediaPlayer
     */
    public MediaPlayer getSound(String name) {
    	Media i = this.players.get(name);
    	
    	if(i == null) {
    		Logger.getLogger().error("Could not find Sound media \"" + name + "\"");
    	}
    	
    	return new MediaPlayer(i);
    }
}
