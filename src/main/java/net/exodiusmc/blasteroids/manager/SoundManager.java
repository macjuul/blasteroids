package net.exodiusmc.blasteroids.manager;

import java.util.HashMap;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import net.exodiusmc.blasteroids.utils.FileUtils;

public class SoundManager {
	private static SoundManager instance;
	private HashMap<String, MediaPlayer> media;
    
	private SoundManager() {}
    
    public static SoundManager getManager() {
    	if(instance == null) {
    		instance = new SoundManager();
    	}
    	
    	return instance;
    }
    
    public void load(String name, String file) {
    	Media m = new Media(FileUtils.ResolveResource("sounds/" + file).toString());
    	MediaPlayer player = new MediaPlayer(m);
    	
    	this.media.put(name, player);
    }
    
    public MediaPlayer play(String name) {
    	MediaPlayer player = this.media.get(name);
    	player.play();
    	return player;
    }
}
