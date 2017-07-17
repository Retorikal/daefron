package reinard.daefron.sprites;

import java.awt.Image;

public abstract class EntSprite extends SpritePackage{
	
	private final int idleAmt=2;
	
	public Image[][] idle = new Image[2][idleAmt];
	
	public void setImgAsIdle(){
		setActiveImage(idle);
		setImgAmt(idleAmt);
	}

	public void setIdleImage(int faceOrient, int i,String path) {
		idle[faceOrient][i]=loadImage(path);
	}

}