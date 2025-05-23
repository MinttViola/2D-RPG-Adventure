package InteractableObj;

import Main.GamePanel;
import Util.Animator;
import Util.TypesOfSuperObj;

public class Crystal extends SuperObject{

	public Crystal(GamePanel gp, int xStartPos, int yStartPos){
		type = TypesOfSuperObj.CRYSTAL;
		name = type.getName();
		start(gp, xStartPos, yStartPos);
		findPath();
		anim = new Animator(gp, path, frameRate);
	}
}
