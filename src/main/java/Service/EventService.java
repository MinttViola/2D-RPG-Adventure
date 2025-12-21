package Service;

import Main.GamePanel;
import Util.DirectionEnum;
import java.awt.Rectangle;

public class EventService {
	GamePanel gp;
	Rectangle eventRec;
	int eventRectDefaultX, eventRectDefaultY;

	public EventService(GamePanel gp){
		this.gp = gp;


		eventRec = new Rectangle();
		eventRec.x = 10;
		eventRec.y = 16;
		eventRec.width = 2;
		eventRec.height = 2;
		eventRectDefaultX = eventRec.x;
		eventRectDefaultY = eventRec.y;
	}

	public void checkEvent(){

	}

	public boolean hit(int eventCol,int eventRow,DirectionEnum dir){
		boolean hit = false;
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRec.x = eventCol*gp.getTileSize() + eventRec.x;


		return hit;
	}
}
