package reinard.daefron.sprites;

import java.awt.Image;

public abstract class BlkSprite extends SpritePackage{
	
	private final int idleAmt=1;
	
	protected Image[][] idle = new Image[4][idleAmt];
	
	public void setImgAsIdle(){
		setActiveImage(idle);
		setImgAmt(idleAmt);
	}
	
	public void setIdleImage(int faceOrient, int i,String path) {
		idle[faceOrient][i]=loadImage(path);
	}

}