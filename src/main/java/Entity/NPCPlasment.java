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
		gp.setNPCInArray(new NPCKnight(gp,1, 6, 9, DirectionEnum.right,2, 4, 8, 7, 10), 1);
		gp.setNPCInArray(new NPCKnight(gp,2, 6, 13, DirectionEnum.right,2, 4, 8, 7, 10), 3);
		gp.setNPCInArray(new NPCPhantom(gp,1, 12, 9, DirectionEnum.right,2, 4, 8, 7, 10), 2);
	}
}
