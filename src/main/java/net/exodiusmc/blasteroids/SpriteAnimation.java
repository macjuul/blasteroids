package net.exodiusmc.blasteroids;

import javafx.scene.image.Image;
import net.exodiusmc.blasteroids.utils.RenderUtils;

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
	
	public void setImage(Image img) {
		this.img = img;
	}
	
	public Image getImage() {
		return this.img;
	}
	
	public void setSpriteOrder(int[] order) {
		this.customFrameOrder = true;
		this.frameOrder = order;
	}
	
	public int[] getSpriteOrder() {
		return this.frameOrder;
	}
	
	public void reset() {
		this.currentFrame = 0;
		if(this.customFrameOrder) {
			this.customFrameOrderIndex = 0;
		}
	}
	
	public void setCurrentFrame(int frame) {
		if(frame < 0 || frame > this.frames) {
			throw new IllegalArgumentException("Frame must be between 0 and max frame (" + this.frames + ")");
		}
		this.currentFrame = frame;
	}
	
	public Image nextFrame() {
		return nextFrame(true);
	}
	
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
}
