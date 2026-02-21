package Service;

import java.awt.Rectangle;

import Main.GamePanel;
import Util.Enums.DirectionEnum;
import Util.Enums.GameStateEnum;

public class EventService {
	private GamePanel gp;
	private Rectangle eventRec;
	private int eventRectDefaultX, eventRectDefaultY;
	long timer = 0;
	long damageTimer = 0;

	public EventService(GamePanel gp){
		this.gp = gp;
		eventRec = new Rectangle();
		eventRec.x = 10;
		eventRec.y = 16;
		eventRec.width = gp.getTileSize()/gp.colDivisiorforTiles;
		eventRec.height = gp.getTileSize()/gp.colDivisiorforTiles;
		eventRectDefaultX = eventRec.x;
		eventRectDefaultY = eventRec.y;
	}

	public void checkEvent(){
		if(timer<System.nanoTime()){
			if(hit(2,7,DirectionEnum.right)){
				damagePit();
			}
			timer = System.nanoTime()+100000000;
			}
	}

	private boolean hit(int eventCol,int eventRow,DirectionEnum dir){
		boolean hit = false;
		gp.getPlayer().setPlayerSolidAreaWorldX();
		gp.getPlayer().setPlayerSolidAreaWorldY();
		eventRec.x = eventCol*gp.getTileSize() + eventRec.x;
		eventRec.y = eventRow*gp.getTileSize() + eventRec.y;
		if(gp.getPlayer().isPlayerIntersects(eventRec)){
			System.err.println(gp.getPlayer().getDirection());
			if(gp.getPlayer().getDirection() == dir){
				hit = true;
			}
		}
		gp.getPlayer().returnSolidAreaToDefault();
		eventRec.x = eventRectDefaultX;
		eventRec.y = eventRectDefaultY;
		return hit;
	}

	private void damagePit(){
		if(damageTimer<System.nanoTime()){
			System.err.println("damage pit");
			gp.getPlayer().changeHP(-10);
			gp.getlevelService().dialogueFromEvent("DamagePit");
		}
		damageTimer = System.nanoTime()+1000000000;
	}
}
