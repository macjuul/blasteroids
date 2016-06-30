package net.exodiusmc.blasteroids;

import javafx.scene.image.Image;
import net.exodiusmc.blasteroids.utils.RenderUtils;

/**
 * A SpriteAnimation is an image containing multiple images that can be iterated over, creating an animation
 */
public class SpriteAnimation {
	private Image img;
	private boolean horizontal;
	private int frames;
	
	private double width;
	private double height;
	
	private int currentFrame;
	
	private boolean customFrameOrder;
	private int[] frameOrder;
	private int customFrameOrderIndex;
	
	public SpriteAnimation(Image img, int frames) {
		this(img, frames, false);
	}
	
	public SpriteAnimation(Image img, int frames, boolean horizontal) {
		this.img = img;
		this.frames = frames;
		this.horizontal = horizontal;
		this.currentFrame = 0;
		
		if(horizontal) {
			this.height = img.getHeight();
			this.width = img.getWidth() / frames;
		} else {
			this.width = img.getWidth();
			this.height = img.getHeight() / frames;
		}
	}
	
	/**
	 * Sets the image used for the sprite animation
	 * 
	 * @param img Image
	 */
	public void setImage(Image img) {
		this.img = img;
	}
	
	/**
	 * Returns the image used for the sprite animation
	 * 
	 * @return Image
	 */
	public Image getImage() {
		return this.img;
	}
	
	/**
	 * Sets a custom order of sprite iteration
	 * 
	 * @param order int[]
	 */
	public void setSpriteOrder(int[] order) {
		this.customFrameOrder = true;
		this.frameOrder = order;
	}
	
	/**
	 * Returns the current sprite order
	 * @return
	 */
	public int[] getSpriteOrder() {
		return this.frameOrder;
	}
	
	/**
	 * Resets the sprite animation to the first frame
	 */
	public void reset() {
		this.currentFrame = 0;
		if(this.customFrameOrder) {
			this.customFrameOrderIndex = 0;
		}
	}
	
	/**
	 * Sets the current frame used in the sprite animation
	 * 
	 * @param frame int
	 */
	public void setCurrentFrame(int frame) {
		if(frame < 0 || frame > this.frames) {
			throw new IllegalArgumentException("Frame must be between 0 and max frame (" + this.frames + ")");
		}
		this.currentFrame = frame;
	}
	
	/**
	 * Tells the SpriteAnimation to move to the next frame, and return the Image for the next frame
	 * 
	 * @return Image
	 */
	public Image nextFrame() {
		return nextFrame(true);
	}
	
	/**
	 * Tells the SpriteAnimation to move to the next frame, and return the Image for the next frame.
	 * If update is false it will instead return the current frame
	 * 
	 * @return Image
	 */
	public Image nextFrame(boolean update) {
		Image sub;
		if(this.horizontal) {
			if(this.customFrameOrder) {
				sub = RenderUtils.getSubImage(this.img, (int) (this.width * this.frameOrder[this.customFrameOrderIndex]), 0, (int) this.width, (int) this.height);
			} else {
				sub = RenderUtils.getSubImage(this.img, (int) (this.width * this.currentFrame), 0, (int) this.width, (int) this.height);
			}
		} else {
			if(this.customFrameOrder) {
				sub = RenderUtils.getSubImage(this.img, 0, (int) (this.height * this.frameOrder[this.customFrameOrderIndex]), (int) this.width, (int) this.height);
			} else {
				sub = RenderUtils.getSubImage(this.img, 0, (int) (this.height * this.currentFrame), (int) this.width, (int) this.height);
			}
		}
		
		if(update) {
			if(this.customFrameOrder) {
				this.customFrameOrderIndex++;
				
				if(this.customFrameOrderIndex >= this.frameOrder.length) {
					this.customFrameOrderIndex = 0;
				}
			} else {
				this.currentFrame++;
				
				if(this.currentFrame == this.frames) {
					this.currentFrame = 0;
				}
			}
		}
			
		return sub;
	}

	
	/**
	 * Returns the width of a single sprite frame
	 * 
	 * @return Double
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Returns the height of a single sprite frame
	 * 
	 * @return Double
	 */
	public double getHeight() {
		return height;
	}
}
