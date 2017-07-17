package reinard.daefron.blocks;

import reinard.daefron.entities.Entity;

public class BlkSolid extends Block{

	public BlkSolid() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void doCollide(Entity e){
		e.doCollide();
		e.stopFall(getYPos()-(e.getHitboxHeight()+height/2)-1);
		int xAjst=0,yAjst=0;
		if(abs(e.getXPrv()-getXPos())>=(e.getHitboxWidth()+width)/2){
			xAjst=(e.getXPrv()-getXPos())/abs(e.getXPrv()-getXPos());
		}
		if(abs(e.getYPrv()-getYPos())>=(e.getHitboxHeight()+height)/2){
			yAjst=(e.getYPrv()-getYPos())/abs(e.getYPrv()-getYPos());
		}
		e.setPos((e.getXPos()+(((e.getHitboxWidth()+width)/2)-e.getXPos()+getXPos())*xAjst)
				,(e.getYPos()-(((e.getHitboxHeight()+height)/2)+e.getYPos()-getYPos())*yAjst));
		System.out.println(e.getXPrv()-getXPos());
		
	}

	private int abs(int num) {
		int i=num;
		if(num<0){
			i=num*-1;
		}
		return i;
	}
}
