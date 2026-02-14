package InteractableObj;

import Main.GamePanel;
import Util.Animator;
import Util.Enums.TypesOfSuperObjEnum;

public class Key extends SuperObjectBaseModel{

	public Key(GamePanel gp, int id, int xStartPos, int yStartPos){
		type = TypesOfSuperObjEnum.KEY;
		name = type.getName();
		this.id = id;
		start(gp, xStartPos, yStartPos);
		findPath();
		anim = new Animator(gp, path, frameRate);
	}
}
