package Entity;

import Main.GamePanel;
import Util.DirectionEnum;

public class NPCPhantom extends NPC {

	public NPCPhantom(GamePanel gp,int id,int xStartPos, int yStartPos, DirectionEnum direction, int speed, int minX, int maxX, int minY, int maxY){
		super(gp,id,2,6, xStartPos, yStartPos,direction,speed, minX, maxX, minY, maxY);
		name = "PhantomNPC";
		direction = DirectionEnum.left;
		setUpAnimators(4);
	}
	
	public void setAction(){
		randomDirectionForNPC();
	}

}
