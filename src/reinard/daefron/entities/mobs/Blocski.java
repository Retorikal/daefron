package reinard.daefron.entities.mobs;

import reinard.daefron.entities.EntMobs;
import reinard.daefron.main.Instance;

public class Blocski extends EntMobs{

	public Blocski(Instance inst,int x,int y) {
		this.inst=inst;
		setSP(inst.getEntSprites());
		setIdle();
		setPos(x,y);
		enableGravity();
		setAlive(true);
		setHitboxSize(50,50);
	}
	
	@Override
	public void doLoop(){
		move();
		switchImage();
		if(getYPos()>=500){
			bounce();
		}
	}	
}
