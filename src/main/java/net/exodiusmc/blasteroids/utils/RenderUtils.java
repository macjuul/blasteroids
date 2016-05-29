package net.exodiusmc.blasteroids.utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class RenderUtils {
	
	private static void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    public static void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        gc.save();
        rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        gc.drawImage(image, tlpx, tlpy);
        gc.restore();
    }
    
    public static void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy, double width, double height) {
        gc.save();
        rotate(gc, angle, tlpx + width / 2, tlpy + height / 2);
        gc.drawImage(image, tlpx, tlpy, width, height);
        gc.restore();
    }
    
    public static Image getSubImage(Image img, int x, int y, int w, int h) {
    	PixelReader reader = img.getPixelReader();
		WritableImage image = new WritableImage(reader, x, y, w, h);
		return image;
    }
    
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
