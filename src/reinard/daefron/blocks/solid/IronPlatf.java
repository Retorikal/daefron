package reinard.daefron.blocks.solid;

import reinard.daefron.blocks.BlkSolid;
import reinard.daefron.main.Instance;

public class IronPlatf extends BlkSolid{

	public IronPlatf(Instance inst,int x,int y) {
		this.inst=inst;
		setSP(inst.getBlkSprites());
		setIdle();
		setPos(x,y);
		setHitboxSize(50,50);
		setFacing(super.UP);
	}
	
	@Override
	public void doLoop(){
		switchImage();
		checkCollide();
	}	
}
