package Entity;

import Main.GamePanel;
import Util.Enums.DirectionEnum;

public class NPCPhantom extends NPC {

	public NPCPhantom(NPC npc){
		super(npc, 2, 6);
		name = "PhantomNPC";
		setUpAnimators(4);
	}
	public void setAction(){
		randomDirectionForNPC();
	}

}
