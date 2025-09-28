package InteractableObj;

import Main.GamePanel;
import Util.Animator;
import Util.TypesOfSuperObjEnum;

public class Crystal extends SuperObjectBaseModel{

	public Crystal(GamePanel gp, int xStartPos, int yStartPos){
		type = TypesOfSuperObjEnum.CRYSTAL;
		name = type.getName();
		start(gp, xStartPos, yStartPos);
		findPath();
		anim = new Animator(gp, path, frameRate);
	}
}
