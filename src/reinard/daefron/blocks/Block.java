package reinard.daefron.blocks;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import reinard.daefron.entities.EntPlayer;
import reinard.daefron.entities.Entity;
import reinard.daefron.main.Instance;
import reinard.daefron.sprites.BlkSprite;
import reinard.daefron.sprites.SpritePackage;

public abstract class Block{

	public Instance inst;
	public Image sprite;
	BlkSprite spritePkg;
	
	protected int height;
	protected int width;

	public final int UP=3;
	public final int DOWN=2;
	private int faceOrient=3;
	
	private int imgCounter = 0;
	private int activeImgId;
	private int switchDelay=4;
	
	private int xPos;
	private int yPos;
	
	public void drawSprite(Graphics2D g) {
        if(sprite==null){System.out.println("Sprite is null!");}
		g.drawImage(sprite,xPos-(sprite.getWidth(null)/2),yPos-(sprite.getHeight(null)/2),null);
    }
	
	public void setHitboxSize(int hitboxHeight,int hitboxWidth){
		this.height=hitboxHeight;
		this.width=hitboxWidth;
	}
	
	public Rectangle getBounds() {
        return new Rectangle(xPos-(width/2),yPos-(height/2),width,height);
    }
//SetImages--------------------------------------------------------------------		
	public void setImage(Image i){
		sprite = i;
	}	
	
	public void switchImage(){
		activeImgId = imgCounter/switchDelay;
		try{
			setImage(spritePkg.getImage(faceOrient,activeImgId));
		}
		catch(NullPointerException e){}
		imgCounter++;
		if(imgCounter>((spritePkg.getImgAmt()*switchDelay)-1)){
			imgCounter=0;
		}
	}
//SpritePackage-------------------------------------------------------------------	
	public void setSP(BlkSprite sp){
		spritePkg = sp;
	}
	
	public SpritePackage getSP(){
		return spritePkg;
	}
//PosStatus-----------------------------------------------------------------------	
	public void setPos(int x,int y){
		xPos=x;
		yPos=y;
	}
	
	public int getXPos(){
		return xPos;
	}
	
	public int getYPos(){
		return yPos;
	}
	
	public void setFacing(int facing){
		faceOrient=facing;
	}
//BehaviorRelated-----------------------------------------------------------------
	public void setIdle(){
		spritePkg.setImgAsIdle();
		//this method later will do more than setImgAsIdle. It sets the mob's current status.
	}
//LoopRelated---------------------------------------------------------------------	
	public void doLoop(){}
	public void doCollide(Entity e){};
	public void checkCollide(){
		for(Entity e:inst.getEntList()){	
			if((getBounds().intersects(e.getBounds()))){
				//e.doCollide();
				doCollide(e);
			}
		}
		EntPlayer p=inst.getPlayer();
		if(getBounds().intersects(p.getBounds())){
			//p.doCollide();
			doCollide(p);
		}
	}
}
