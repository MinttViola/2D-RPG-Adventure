package InteractableObj;

import Main.GamePanel;

public class ObjPlasment {
	GamePanel gp;

	public ObjPlasment(GamePanel gp){
		this.gp = gp;
		setObj();
	}

	public void setObj(){
		gp.obj[0] = new Key(gp, 0, 6,14);
		gp.obj[1] = new Chest(gp, 0, 6,4);
		gp.obj[2] = new Crystal(gp, 8,6);

	}

}
