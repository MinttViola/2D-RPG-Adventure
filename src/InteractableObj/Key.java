package InteractableObj;

import Main.GamePanel;
import Util.Animator;

public class Key extends SuperObject{
	public int id;
	public Key(GamePanel gp, int id, int xStartPos, int yStartPos){
		this.gp = gp;
		name = "key_item";
		findPath();
		anim = new Animator(gp, path, 0.16);
		worldX = xStartPos*gp.tileSize;
		worldY = yStartPos*gp.tileSize;

	}
}
