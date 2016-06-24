package net.exodiusmc.blasteroids.utils;

import java.net.URL;

import javafx.scene.image.Image;

/**
 * FileUtils contains utility methods for accessing files and resources
 */
public class FileUtils {
    private static String resourceDirectory = "";
    
    /**
     * Set the resource directory
     * 
     * @param dir String
     */
    public static void setResourceDirectory(String dir) {
        if(dir.substring(0, 1).equals("/")) dir = dir.substring(1);
        if(!dir.substring(dir.length() - 1, dir.length()).equals("/")) dir = dir + "/";
        FileUtils.resourceDirectory = dir;
    }
    
    /**
     * Returns the stored resource directory
     * 
     * @return String
     */
    public static String getResourceDirectory() {
        return FileUtils.resourceDirectory;
    }
    
    /**
     * Returns the URL object of a resource resolved from the given string
     * 
     * @param file String
     * @return URL
     */
    public static URL ResolveResource(String file) {
        return FileUtils.class.getClassLoader().getResource(resourceDirectory + file);
    }
    
    /**
     * Loads and returns the Image from the given location
     * 
     * @param file String
     * @return Image
     */
    public static Image LoadImage(String file) {
        return new Image(FileUtils.ResolveResource(file).toString());
    }
    
    public static String getFileExtension(String loc) {
    	String extension;
    	
    	int i = loc.lastIndexOf('.');
    	if (i >= 0) {
    	    extension = loc.substring(i + 1);
    	} else {
    		extension = "";
    	}
    	
    	return extension;
    }
    
    public static String getFileName(String loc) {
    	String name;
    	
    	int i = loc.lastIndexOf('.');
    	if (i >= 0) {
    		name = loc.substring(0, i);
    	} else {
    		name = "";
    	}
    	
    	int j = name.lastIndexOf('/');
    	if (j >= 0) {
    		name = name.substring(j + 1);
    	}
    	
    	return name;
    }
}

