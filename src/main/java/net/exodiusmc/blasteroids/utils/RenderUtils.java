package net.exodiusmc.blasteroids.utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

/**
 * RenderUtils contains utility methods for rendering graphics on the screen
 */
public class RenderUtils {

	private static void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

	/**
	 * Draw a rotated image onto the GraphicsContext
	 * 
	 * @param gc GraphicsContext
	 * @param image Image
	 * @param angle Double
	 * @param tlpx Double
	 * @param tlpy Double
	 */
    public static void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        gc.save();
        rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        gc.drawImage(image, tlpx, tlpy);
        gc.restore();
    }
    
    /**
	 * Draw a rotated image onto the GraphicsContext
	 * 
	 * @param gc GraphicsContext
	 * @param image Image
	 * @param angle Double
	 * @param tlpx Double
	 * @param tlpy Double
	 * @param width Double
	 * @param height Double
	 */
    public static void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy, double width, double height) {
        gc.save();
        rotate(gc, angle, tlpx + width / 2, tlpy + height / 2);
        gc.drawImage(image, tlpx, tlpy, width, height);
        gc.restore();
    }
    
    /**
     * Returns a part of an image
     * 
     * @param img Image
     * @param x int
     * @param y int
     * @param w int
     * @param h int
     * @return Image
     */
    public static Image getSubImage(Image img, int x, int y, int w, int h) {
    	PixelReader reader = img.getPixelReader();
		WritableImage image = new WritableImage(reader, x, y, w, h);
		return image;
    }
    
    /**
     * Colorizes an image by the given amount
     * 
     * @param img Image
     * @param c Color
     * @param a Double amount
     * @return Image
     */
    public static Image colorizeImage(Image img, Color c, double a) {
    	PixelReader reader = img.getPixelReader();
		WritableImage write = new WritableImage((int) img.getWidth(), (int) img.getHeight());
		PixelWriter writer = write.getPixelWriter();
		
		for(int readY = 0; readY < img.getHeight(); readY++){
			for(int readX = 0; readX < img.getWidth(); readX++) {
				Color color = reader.getColor(readX, readY);
				if(color.getOpacity() == 0) continue;
				color = color.interpolate(c, a);
				writer.setColor(readX, readY, color);
			}
		}
		
		return (Image) write;
    }
    
}
