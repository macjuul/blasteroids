package net.exodiusmc.blasteroids.manager;

import java.util.HashSet;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.exodiusmc.blasteroids.Logger;

public class InputManager {
	private static InputManager instance;
	private Stage window;
    private HashSet<KeyCode> keys;
    private HashSet<MouseButton> buttons;
    
	private InputManager(Stage win) {
		this.keys = new HashSet<KeyCode>();
		this.buttons = new HashSet<MouseButton>();
		this.window = win;
		this.keys = new HashSet<KeyCode>();
        
        win.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				keys.add(event.getCode());
			}
        	
        });
        
        win.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				keys.remove(event.getCode());
			}
        });
        
        win.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				buttons.add(event.getButton());
			}
        });
        
        win.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				buttons.remove(event.getButton());
			}
        });
        
        Logger.getLogger().info("InputManager loaded!");
	}
    
    public static InputManager getManager() {
    	if(instance == null) throw new IllegalStateException("The input manager has not been initialized yet");
    	
    	return instance;
    }
    
    public static void intialize(Stage win) {
    	if(instance != null) {
    		Logger.getLogger().warn("Cannot initalize InputManager: Already initalized");
    	}
    	
    	instance = new InputManager(win);
    }
    
    public boolean isKeyPressed(KeyCode k) {
        return this.keys.contains(k);
    }

    public boolean isMouseButtonPressed(MouseButton b) {
        return this.buttons.contains(b);
    }
    
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void registerEventHandler(EventType t, EventHandler h) {
    	this.window.addEventHandler(t, h);
    }
    
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void unregisterEventHandler(EventType t, EventHandler h) {
    	this.window.removeEventHandler(t, h);
    }
}
