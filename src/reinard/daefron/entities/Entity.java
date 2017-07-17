package reinard.daefron.entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import reinard.daefron.sprites.EntSprite;
import reinard.daefron.main.Instance;;

public abstract class Entity{
	
	public Image sprite;
	public Instance inst;
	EntSprite spritesPkg;
	
	private int xPos;
	private int yPos;
	private int xPrv;
	private int yPrv;
	private double xVel;
	private double yVel;
	private double xAcc;
	private double yAcc;
	
	private boolean alive;
	private boolean movingLeft;
	private boolean movingRight;
	
	private int hitboxHeight;	
	private int hitboxWidth;
	
	final private int gConst=1;
	
	private final int LEFT=1;
	private final int RIGHT=0;
	
	private int faceOrient=0;
	private int imgCounter = 0;
	private int activeImgId;
	private int switchDelay=4;

	private int maxJumpPermit=3;
	private int jumpPermit=3;
	
	public void drawSprite(Graphics2D g) {
        g.draw(getBounds());
        g.drawImage(sprite,xPos-(sprite.getWidth(null)/2),yPos-(sprite.getHeight(null)/2),null);
	}
	
	public void move(){
		xPrv=xPos;
		yPrv=yPos;
		xVelocity();
		yVelocity();
		xAcceleration();
		yAcceleration();
	}
	
	public void setHitboxSize(int hitboxHeight,int hitboxWidth){
		this.hitboxHeight=hitboxHeight;
		this.hitboxWidth=hitboxWidth;
	}
	
	public Rectangle getBounds() {
        return new Rectangle(xPos-(getHitboxWidth()/2),yPos-(hitboxHeight/2),getHitboxWidth(),hitboxHeight);
    }
//SetImages--------------------------------------------------------------------		
	public void setImage(Image i){
		sprite = i;
	}	
	
	public void switchImage(){
		activeImgId = imgCounter/switchDelay;
		try{
			setImage(spritesPkg.getImage(faceOrient,activeImgId));
		}
		catch(NullPointerException e){}
		imgCounter++;
		if(imgCounter>((spritesPkg.getImgAmt()*switchDelay)-1)){
			imgCounter=0;
		}
	}
//SpritePackage-------------------------------------------------------------------	
	public void setSP(EntSprite sp){
		spritesPkg = sp;
	}
	
	public EntSprite getSP(){
		return spritesPkg;
	}
//AliveStatus---------------------------------------------------------------------
	public boolean getAlive(){
		return alive;
	}
	
	public void setAlive(boolean aliveStat){
		alive=aliveStat;
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
//Velocity-----------------------------------------------------------------------	
	public void xVelocity(){
		xPos+=xVel;
	}
	
	public void yVelocity(){
		yPos+=yVel;
	}
//Acceleration-------------------------------------------------------------------	
	public void xAcceleration(){
		xVel=xVel + xAcc;
	}
	
	public void yAcceleration(){
		yVel=yVel + yAcc;
	}
	
	public double getYAcc(){
		return yAcc;
	}
	
	public void enableGravity(){
		yAcc=gConst;
	}
//MovementStuffs------------------------------------------------------------------
	public void bounce(){
		yPos=499;
		if(yVel<11){
			yVel=0;
			yAcc=0;
		}
		else{
			yVel=yVel*-0.7;
		}
		jumpPermit=maxJumpPermit;
	}
	
	public void stopFall(int pos){
		yPos=pos;
		yVel=0;
		yAcc=0;
		jumpPermit=maxJumpPermit;
	}
	
	public void jump(){
		if(jumpPermit!=0){
			yVel=-15;
			jumpPermit-=1;
		}
	}
	
	public void moveRight(){
		if(!movingRight){
			xVel=10;
			//xAcc+=5;
			faceOrient=RIGHT;
			movingRight=true;
			movingLeft=false;
		}
	}
	
	public void stopRight(){
		if(movingRight){
			xVel=xVel - 10;
			//xAcc=0;
			movingRight=false;
		}
	}
	
	public void moveLeft(){
		if(!movingLeft){
			xVel=- 10;
			//xAcc-=5;
			faceOrient=LEFT;
			movingLeft=true;
			movingRight=false;
		}
	}
	
	public void stopLeft(){
		if(movingLeft){
			xVel=xVel + 10;
			//xAcc=0;
			movingLeft=false;
		}
	}
//BehaviorRelated-----------------------------------------------------------------
	public void setIdle(){
		spritesPkg.setImgAsIdle();
		//this method later will do more than setImgAsIdle. It sets the mob's current status.
	}
//CollisionRelated----------------------------------------------------------------
	public void doCollide(){
		System.out.println("Has collided with "+this);
	}
	
	public void checkCollide(){
		for(Entity e:inst.getEntList()){
			if((getBounds().intersects(e.getBounds()))){
				e.doCollide();
			}
		}
	}
//LoopRelated---------------------------------------------------------------------	
	public void doLoop(){}

	public double getYVel() {
		return yVel;
	}

	public void setYVel(double yVel) {
		this.yVel = yVel;
	}

	public double getXVel() {
		return xVel;
	}

	public void setXVel(double xVel) {
		this.xVel = xVel;
	}

	public int getXPrv() {
		return xPrv;
	}

	public int getYPrv() {
		return yPrv;
	}

	public int getHitboxWidth() {
		return hitboxWidth;
	}

	public int getHitboxHeight() {
		return hitboxHeight;
	}

}
