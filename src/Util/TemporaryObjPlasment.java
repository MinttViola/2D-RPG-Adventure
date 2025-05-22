package Util;

import InteractableObj.Key;
import Main.GamePanel;

public class TemporaryObjPlasment {
	GamePanel gp;

	public TemporaryObjPlasment(GamePanel gp){
		this.gp = gp;
		setObj();
	}

	public void setObj(){
		gp.obj[0] = new Key(gp, 0, 4,4);

	}

}
