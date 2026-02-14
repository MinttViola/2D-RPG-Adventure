package Entity;

import Main.GamePanel;
import Util.Enums.DirectionEnum;

public class NPCKnight extends NPC{


	public NPCKnight(NPC npc){
		super(npc, 2, 6);
		name = "KnightNPC";
		setUpAnimators(4);
	}

	@Override
	public void setAction(){
		randomDirectionForNPC();
	}
} 
