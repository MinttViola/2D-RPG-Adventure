package Entity;

import Main.GamePanel;
import Util.DirectionEnum;

public class NPCKnight extends NPC{

	public NPCKnight(GamePanel gp,int id,int xStartPos, int yStartPos, DirectionEnum direction, int speed){
		super(gp,id,2,6, xStartPos, yStartPos,direction,speed);
		name = "KnightNPC";
		setDialogue();
		setUpAnimators(4);
	}

	@Override
	public void setAction(){
		randomDirectionForNPC();
	}
	@Override
	public void setDialogue(){
		dialoguesArray = new String[3];
		dialoguesArray[0] = "this is knight thing";
		dialoguesArray[1] = "this is also knight thing";
		dialoguesArray[2] = "this is more knight thing";
	}
} 
