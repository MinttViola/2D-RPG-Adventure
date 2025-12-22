package InteractableObj;

import Main.GamePanel;

public class ObjPlasment {
	GamePanel gp;

	public ObjPlasment(GamePanel gp){
		this.gp = gp;
		setObj();
	}

	public void setObj(){
		gp.setSuperObjInArray(new Key(gp, 0, 6,14), 0);
		gp.setSuperObjInArray(new Chest(gp, 0, 6,4), 1);
		gp.setSuperObjInArray(new Crystal(gp, 8,6), 2);
		gp.setSuperObjInArray(new Heart(gp, 9,5), 3);

	}

}
