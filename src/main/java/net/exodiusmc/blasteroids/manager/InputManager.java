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

/**
 * The input manager is responsible for keyboard and mouse user input alongside
 * easy to use event binding. Before you can use this manager you will first
 * have to initalize it by calling {@link InputManager#intialize(Stage)}
 */
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
    
	/**
	 * Returns the associated manager object
	 * 
	 * @return InputManager
	 */
    public static InputManager getManager() {
    	if(instance == null) throw new IllegalStateException("The input manager has not been initialized yet");
    	
    	return instance;
    }
    
    
    /**
     * Initalize the manager Object
     *  
     * @param win Window
     */
    public static void intialize(Stage win) {
    	if(instance != null) {
    		Logger.getLogger().warn("Cannot initalize InputManager: Already initalized");
    	}
    	
    	instance = new InputManager(win);
    }
    
    /**
     * Check if a certain key is being pressed or not, returns true if it is
     * 
     * @param k KeyCode
     * @return Boolean
     */
    public boolean isKeyPressed(KeyCode k) {
        return this.keys.contains(k);
    }

    /**
     * Check if a certain mouse button is being pressed or not, returns true if it is
     * 
     * @param b MouseButton
     * @return Boolean
     */
    public boolean isMouseButtonPressed(MouseButton b) {
        return this.buttons.contains(b);
    }
    
    /**
     * Register a new event handler
     * 
     * @param t EventType
     * @param h EventHandler
     */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void registerEventHandler(EventType t, EventHandler h) {
    	this.window.addEventHandler(t, h);
    }
    
	/**
     * Unregister an event handler
     * 
     * @param t EventType
     * @param h EventHandler
     */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void unregisterEventHandler(EventType t, EventHandler h) {
    	this.window.removeEventHandler(t, h);
    }
}
