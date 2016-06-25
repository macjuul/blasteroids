package net.exodiusmc.blasteroids;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.exodiusmc.blasteroids.enums.LogLevel;
import net.exodiusmc.blasteroids.layers.effects.LayerEffect;
import net.exodiusmc.blasteroids.manager.LayerManager;

public class Runtime extends AnimationTimer {
	private long lastTime = System.nanoTime();
	private double ticks = 60D;  
	private double ns = 1000000000 / ticks;    
	private double delta = 0;
	private GraphicsContext gfx;
	private long frame;
	
	private Runtime(GraphicsContext gfx) {
		this.gfx = gfx;
	}
	
	public static void initalize(GraphicsContext gfx) {
		initalize(gfx, -1);
	}
	
	public static void initalize(GraphicsContext gfx, float fps) {
		Runtime r = new Runtime(gfx);
		
		if(fps >= 0) {
			r.setTargetFPS(fps);
		}
		
		Logger.getLogger().log("Initalized Runtime; Invoking main game loop", LogLevel.INFO, LogLevel.RUNTIME);
		
		r.start();
	}

	@Override
	public void handle(long now) {
	    delta += (now - lastTime) / ns;
	    lastTime = now;
	    if(delta >= 1){
	        frame++;
	        
	        gfx.setFill(Color.BLACK);
	        gfx.fillRect(-Main.WIDTH, -Main.HEIGHT, Main.WIDTH * 2, Main.HEIGHT * 2);
	        
	        LayerManager.getManager().__processShouldRemoveLayers();
	        
	        int stackSize = LayerManager.getManager().size();
	        
	        for(int c = 0; c < stackSize; c++) {
	        	try {
		        	Layer l = LayerManager.getManager().get(c);
		        	LayerEffect transition = null;
		        	long tick = 0;
		        	
		        	if(l.hasLayerEffect()) {
		        		transition = l.transition;
		        		tick = transition.tick();
		        		
		        		transition.applyBefore(l, this.gfx, tick);
		        		
		        		if(transition.isCompleted()) {
		        			callTransitionCallback(transition);
		        			
		        			if(l.transition == transition) {
		        				l.transition = null;
		        			}
		        		}
		        	}
		        	
		        	if((stackSize - 1 != c && l.updateOnCover()) || stackSize -1 == c) {
		        		l.update(this.delta, this.frame);
		        	}
		        	
		        	l.render(this.gfx);
		        	
		        	if(l.hasLayerEffect()) {
		        		transition = l.transition;
		        		transition.applyAfter(l, this.gfx, tick);
		        		
		        		if(transition.isCompleted()) {
		        			callTransitionCallback(transition);
		        			
		        			if(l.transition == transition) {
		        				l.transition = null;
		        			}
		        		}
		        	}
	        	} catch(Exception e) {
	        		Logger.getLogger().error("Failed to process layer");
	        	}
	        }
	        
	        delta--;
	    }
	}
	
	public long getCurrentFrame() {
		return this.frame;
	}
	
	public void setTargetFPS(double fps) {
		this.ns = 1000000000 / fps;  
		this.delta = 0;
		
		Logger.getLogger().log("Altered target FPS: Game might become unstable", LogLevel.INFO, LogLevel.RUNTIME);
	}
	
	private void callTransitionCallback(LayerEffect t) {
		Runnable r = t.getCallback();
		
		if(r != null) {
			r.run();
		}
	}
}
