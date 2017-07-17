package reinard.daefron.sprites;

import java.awt.Image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class SpritePackage {
	
	private int imgAmt;
	public final int RIGHT=0;
	public final int LEFT=1;
	public final int UP=3;
	public final int DOWN=2;
	
	private Image[][] activeImage;

	public Image getImage(int faceOrient,int i) {
		return activeImage[faceOrient][i];
	}

	public Image loadImage(String path){
		BufferedImage i = null;
		try {
			i= ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public int getImgAmt(){
		return imgAmt;
	}

	public void setImgAmt(int imgAmt) {
		this.imgAmt = imgAmt;
	}

	public Image[][] getActiveImage() {
		return activeImage;
	}

	public void setActiveImage(Image[][] activeImage) {
		this.activeImage = activeImage;
	}
}
