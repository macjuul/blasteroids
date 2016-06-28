package net.exodiusmc.blasteroids.utils;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.concurrent.Task;
import javafx.scene.media.MediaPlayer;

/**
 * GeneralUtils contains utility methods for a bunch of handy things you might need to use
 */
public class GeneralUtils {
	
	/**
	 * Delay a code from running
	 * 
	 * @param duration Long
	 * @param csr Runnable
	 */
	public static void setTimeout(Long duration, Runnable csr) {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        scheduler.schedule(csr, duration, TimeUnit.MILLISECONDS);
    }
    
	/**
	 * Simmilair to {@link GeneralUtils#setTimeout(Long, Runnable)} except
	 * the method will keep being called instead of running once
	 * 
	 * @param delay Long
	 * @param csr Runnable
	 */
    public static void setInterval(Long delay, Runnable csr) {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        scheduler.scheduleAtFixedRate(csr, 0, delay, TimeUnit.MILLISECONDS);
    }
    
    public static int randomIntInRange(int min, int max) {
    	return new Random().nextInt(max - min) + min;
    }
    
    public static double randomDoubleInRange(double min, double max) {
    	return min + (max - min) * new Random().nextDouble();
    }
    
    public static Object arrayRand(Object[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
    
    public static Object arrayRand(ArrayList<?> array) {
        int rnd = new Random().nextInt(array.size());
        return array.get(rnd);
    }
    
    public static void playSound(MediaPlayer sound) {
    	Task<Void> task = new Task<Void>() {
    	    @Override
    	    protected Void call() {
    	    	sound.play();
				return null;
    	    }
    	};
    	
    	new Thread(task).start();
    }
}
