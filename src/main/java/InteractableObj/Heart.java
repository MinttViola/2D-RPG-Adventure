package InteractableObj;

import Main.GamePanel;
import Util.Animator;
import Util.Enums.TypesOfSuperObjEnum;

public class Heart extends SuperObjectBaseModel{

    public Heart(GamePanel gp, int xStartPos, int yStartPos) {
		type = TypesOfSuperObjEnum.HEART;
		name = type.getName();
		start(gp, xStartPos, yStartPos);
		findPath();
		anim = new Animator(gp, path, frameRate);
    }
}
