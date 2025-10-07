package Entity;

import Main.GamePanel;
import Util.DirectionEnum;

public class NPCKnight extends NPC{

	public NPCKnight(GamePanel gp,int id,int xStartPos, int yStartPos, DirectionEnum direction){
		super(gp,id,1,6, xStartPos, yStartPos,direction);
		name = "KnightNPC";
		dir = DirectionEnum.left;
		SetUpAnimators(4);
	}
} 
