package reinard.daefron.entities;

import reinard.daefron.main.Instance;
import reinard.daefron.sprites.BlocskiSprite;
import java.awt.event.KeyEvent;

public class EntPlayer extends Entity{

	public EntPlayer(Instance instance,int x, int y) {
		inst=instance;
		setSP(new BlocskiSprite());
		setIdle();
		setPos(x,y);
		enableGravity();
		setAlive(true);
		setHitboxSize(10,10);
	}
	
	@Override
	public void doLoop(){
		move();
		switchImage();
		//checkCollide();
		//System.out.println(getYVel()+" "+getYAcc());
		if(getYPos()>=500){	
			stopFall(499);
			//bounce();
		}
		if(!(getYPos()>=500)){	
			enableGravity();
			//bounce();
		}
	}
	
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        switch(key){
        	case KeyEvent.VK_W:
        		jump();
        	break;
        	case KeyEvent.VK_A:
        		moveLeft();
        	break;
        	case KeyEvent.VK_S:
        	break;
        	case KeyEvent.VK_D:
        		moveRight();
        	break;
        }
    }
	
	public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        switch(key){
    	case KeyEvent.VK_W:
    	break;
    	case KeyEvent.VK_A:
    		stopLeft();
    	break;
    	case KeyEvent.VK_S:
    	break;
    	case KeyEvent.VK_D:
    		stopRight();
    	break;
    }
    }
}
