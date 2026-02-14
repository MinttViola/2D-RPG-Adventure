package Entity;

import Main.GamePanel;
import Util.DirectionEnum;

public class NPCKnight extends NPC{

	public NPCKnight(GamePanel gp,int id,int xStartPos, int yStartPos, DirectionEnum direction, int speed, int minX, int maxX, int minY, int maxY){
		super(gp,id,2,6, xStartPos, yStartPos,direction,speed, minX, maxX, minY, maxY);
		name = "KnightNPC";
		setUpAnimators(4);
	}

	@Override
	public void setAction(){
		randomDirectionForNPC();
	}
} 
