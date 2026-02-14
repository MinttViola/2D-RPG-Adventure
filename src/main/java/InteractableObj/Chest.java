package InteractableObj;

import Main.GamePanel;
import Util.Animator;
import Util.Enums.TypesOfSuperObjEnum;

public class Chest extends SuperObjectBaseModel{

	public Chest(GamePanel gp, int id, int xStartPos, int yStartPos){
		this.id = id;
		type = TypesOfSuperObjEnum.CHEST;
		name = type.getName();
		collision = true;
		start(gp, xStartPos, yStartPos);
		findPath();
		anim = new Animator(gp, path, frameRate);
	}

}
