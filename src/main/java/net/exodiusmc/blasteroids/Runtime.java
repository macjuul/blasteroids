package net.exodiusmc.blasteroids;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import net.exodiusmc.blasteroids.interfaces.Layer;
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
		
		r.start();
	}

	@Override
	public void handle(long now) {
	    delta += (now - lastTime) / ns;
	    lastTime = now;
	    if(delta >= 1){
	        frame++;
	        
	        int stackSize = LayerManager.getManager().size();
	        
	        for(int c = 0; c < stackSize; c++) {
	        	Layer l = LayerManager.getManager().get(c);
	        	if((stackSize - 1 != c && l.updateOnCover()) || stackSize -1 == c) {
	        		l.update(this.delta, this.frame);
	        	}
	        	l.render(this.gfx);
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
	}
}
