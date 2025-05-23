package InteractableObj;

import Main.GamePanel;
import Util.Animator;
import Util.TypesOfSuperObj;

public class Chest extends SuperObject{

	public Chest(GamePanel gp, int id, int xStartPos, int yStartPos){
		this.id = id;
		type = TypesOfSuperObj.CHEST;
		name = type.getName();
		collision = true;
		start(gp, xStartPos, yStartPos);
		path = "/Assets/Props_Items_(animated)/"+name+"_anim.png";
		anim = new Animator(gp, path, frameRate);
	}

}
