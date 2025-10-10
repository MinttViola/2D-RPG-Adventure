package Entity;

import Main.GamePanel;
import Util.DirectionEnum;

public class NPCPhantom extends NPC {

	public NPCPhantom(GamePanel gp,int id,int xStartPos, int yStartPos, DirectionEnum direction, int speed){
		super(gp,id,2,6, xStartPos, yStartPos,direction,speed);
		name = "PhantomNPC";
		direction = DirectionEnum.left;
		setUpAnimators(4);
	}
	
	public void setAction(){
		randomDirectionForNPC();
	}

}
