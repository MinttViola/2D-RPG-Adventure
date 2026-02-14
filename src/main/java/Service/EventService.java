package Service;

import java.awt.Rectangle;

import Main.GamePanel;
import Util.GameState;
import Util.Enums.DirectionEnum;

public class EventService {
	private GamePanel gp;
	private Rectangle eventRec;
	private int eventRectDefaultX, eventRectDefaultY;
	long timer = 0;

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
				damagePit(GameState.DialogueState);
			}
			timer = System.nanoTime()+1000000000;
			}
	}

	private boolean hit(int eventCol,int eventRow,DirectionEnum dir){
		boolean hit = false;
		gp.getPlayer().setPlayerSolidAreaWorldX();
		gp.getPlayer().setPlayerSolidAreaWorldY();
		eventRec.x = eventCol*gp.getTileSize() + eventRec.x;
		eventRec.y = eventRow*gp.getTileSize() + eventRec.y;
		if(gp.getPlayer().isPlayerIntersects(eventRec)){
			if(gp.getPlayer().getDirection() == dir){
				hit = true;
			}
		}
		gp.getPlayer().returnSolidAreaToDefault();
		eventRec.x = eventRectDefaultX;
		eventRec.y = eventRectDefaultY;
		return hit;
	}

	private void damagePit(GameState gameState){
		gp.setGameState(gameState);	
		gp.getPlayer().changeHP(-10);
	}
}
