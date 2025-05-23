package InteractableObj;

import Main.GamePanel;

public class ObjPlasment {
	GamePanel gp;

	public ObjPlasment(GamePanel gp){
		this.gp = gp;
		setObj();
	}

	public void setObj(){
		gp.obj[0] = new Key(gp, 0, 15,4);
		gp.obj[1] = new Chest(gp, 0, 5,4);
		gp.obj[2] = new Crystal(gp, 6,6);

	}

}
