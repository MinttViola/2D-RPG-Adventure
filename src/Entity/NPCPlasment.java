package Entity;

import Main.GamePanel;
import Util.DirectionEnum;

public class NPCPlasment {
GamePanel gp;

	public NPCPlasment(GamePanel gp){
		this.gp = gp;
		setNPC();
	}

	public void setNPC(){
		gp.npc[0] = new NPCKnight(gp,1, 6, 19, DirectionEnum.right);
	}
}
