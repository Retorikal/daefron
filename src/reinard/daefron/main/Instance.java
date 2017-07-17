package reinard.daefron.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import reinard.daefron.sprites.*;

import reinard.daefron.blocks.Block;
import reinard.daefron.blocks.solid.IronPlatf;
import reinard.daefron.entities.Entity;
import reinard.daefron.entities.mobs.Blocski;
import reinard.daefron.entities.EntPlayer;

public class Instance implements Runnable{

	private final int DELAY = 20;
	private ArrayList<Entity> entList;
	private ArrayList<Block> blkList;
	private EntPlayer player;
	private EntSprite entSprites;
	private BlkSprite blkSprites;
    Thread t;

	public Instance() {
        setBlkList(new ArrayList<>());
        setEntList(new ArrayList<>());
        setPlayer(new EntPlayer(this,100,0));
		setEntSprites(new BlocskiSprite());
		setBlkSprites(new IronPlatfSprite());
		t=new Thread(this);
        t.start();
	}
	
	public void spawnE(Entity e){
		entList.add(e);
	}
	
	public void spawnB(Block b){
		blkList.add(b);
	}
	
    public void drawInstance(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
		try{
			for(Entity e:entList){
				e.drawSprite(g2d);
			}
			for(Block b:blkList){
				b.drawSprite(g2d);
			}
			player.drawSprite(g2d);
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
	}
    
	public EntPlayer getPlayer() {
		return player;
	}

	public void setPlayer(EntPlayer player) {
		this.player = player;
	}
    
	@Override
	public void run(){
		long beforeTime, timeElapsed, sleep;
        beforeTime = System.currentTimeMillis();
		//int i=500;
		//int j=0;
    	spawnE(new Blocski(this,250,0));
    	spawnB(new IronPlatf(this,250,250));
    	spawnB(new IronPlatf(this,200,250));
    	spawnB(new IronPlatf(this,150,250));
        while (true) {
        	
        	/*if(j>5){
            	if(i>=0){
            		spawn(new Blocski(this,i,0));
            	}
            	i-=25;
            	ii=0;
        	}
        	j++;*/
        	
        	for(Entity e:getEntList()){
    			e.doLoop();
    		}
        	for(Block b:getBlkList()){
    			b.doLoop();
    		}
        	getPlayer().doLoop();
        	
        	timeElapsed = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeElapsed;
            if(sleep < 0){sleep = 2;}
            try {Thread.sleep(sleep);}
            catch (InterruptedException e) {System.out.println("Interrupted: " + e.getMessage());}
            beforeTime = System.currentTimeMillis();
        }
	}
	
	public void setEntList(ArrayList<Entity> entity) {
		this.entList = entity;
	}

	public void setBlkList(ArrayList<Block> block) {
		this.blkList = block;
	}
	
	public ArrayList<Block> getBlkList() {
		return blkList;
	}

	public ArrayList<Entity> getEntList() {
		return entList;
	}

	public EntSprite getEntSprites() {
		return entSprites;
	}

	public void setEntSprites(EntSprite sprites) {
		this.entSprites = sprites;
	}

	public BlkSprite getBlkSprites() {
		return blkSprites;
	}

	public void setBlkSprites(BlkSprite sprites) {
		this.blkSprites = sprites;
	}
}
